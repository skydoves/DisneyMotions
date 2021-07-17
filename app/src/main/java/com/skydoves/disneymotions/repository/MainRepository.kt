/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.disneymotions.repository

import androidx.annotation.WorkerThread
import com.skydoves.disneymotions.mapper.ErrorResponseMapper
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.model.PosterErrorResponse
import com.skydoves.disneymotions.network.DisneyService
import com.skydoves.disneymotions.persistence.PosterDao
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import timber.log.Timber

class MainRepository constructor(
  private val disneyService: DisneyService,
  private val posterDao: PosterDao
) : Repository {

  init {
    Timber.d("Injection MainRepository")
  }

  @WorkerThread
  fun loadDisneyPosters(
    onSuccess: () -> Unit,
  ) = flow {
    val posters: List<Poster> = posterDao.getPosterList()
    if (posters.isEmpty()) {
      // request API network request asynchronously.
      disneyService.fetchDisneyPosterList()
        // handles the success case when the API request gets a successful response.
        .suspendOnSuccess {
          posterDao.insertPosterList(data)
          emit(data)
        }
        /**
         * handles error cases when the API request gets an error response.
         * e.g., internal server error.
         * maps the [ApiResponse.Failure.Error] to the [PosterErrorResponse] using the mapper.
         */
        .onError(ErrorResponseMapper) {
          Timber.d("[Code: $code]: $message")
        }
        // handles exceptional cases when the API request gets an exception response.
        // e.g., network connection error.
        .onException {
          Timber.d(message())
        }
    } else {
      emit(posters)
    }
  }.onCompletion { onSuccess() }.flowOn(Dispatchers.IO)
}
