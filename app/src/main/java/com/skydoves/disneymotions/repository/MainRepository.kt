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

import androidx.lifecycle.MutableLiveData
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.network.DisneyService
import com.skydoves.disneymotions.persistence.PosterDao
import com.skydoves.sandwich.ResponseDataSource
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.onSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainRepository constructor(
  private val disneyService: DisneyService,
  private val dataSource: ResponseDataSource<List<Poster>>,
  private val posterDao: PosterDao
) : Repository {

  override var isLoading = false

  init {
    Timber.d("Injection MainRepository")
  }

  suspend fun loadDisneyPosters(error: (String) -> Unit) = withContext(Dispatchers.IO) {
    val liveData = MutableLiveData<List<Poster>>()
    var posters = posterDao.getPosterList()
    if (posters.isEmpty()) {
      isLoading = true
      dataSource
        // retry fetching data 3 times with 5000 milli-seconds time interval when the request gets failure.
        .retry(3, 5000L)
        // combine network service to the data source.
        .combine(disneyService.fetchDisneyPosterList()) { apiResponse ->
          // handle the case when the API request gets a success response.
          apiResponse.onSuccess {
            data.whatIfNotNull {
              posters = it
              liveData.postValue(it)
              posterDao.insertPosterList(it)
            }
          }
            // handle the case when the API request gets a error response.
            // e.g. internal server error.
            .onError {
              error(message())
            }
            // handle the case when the API request gets a exception response.
            // e.g. network connection error.
            .onException {
              error(message())
            }
          isLoading = false
        }
        // request API network call asynchronously.
        // if the request is successful, the DataSource will hold the success data.
        // in the next request after success, returns the cached API response data.
        .request()
    }
    liveData.apply { postValue(posters) }
  }
}
