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

package com.skydoves.disneymotions.network

import android.app.Application
import android.widget.Toast
import com.skydoves.disneymotions.mapper.ErrorResponseMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.StatusCode
import com.skydoves.sandwich.map
import com.skydoves.sandwich.message
import com.skydoves.sandwich.operators.ApiResponseSuspendOperator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class GlobalResponseOperator<T> constructor(
  private val application: Application
) : ApiResponseSuspendOperator<T>() {

  override suspend fun onError(apiResponse: ApiResponse.Failure.Error<T>) =
    withContext(Dispatchers.Main) {
      apiResponse.run {
        Timber.d(message())

        when (statusCode) {
          StatusCode.InternalServerError -> toast("InternalServerError")
          StatusCode.BadGateway -> toast("BadGateway")
          else -> toast("$statusCode(${statusCode.code}): ${message()}")
        }

        map(ErrorResponseMapper) {
          Timber.d(message())
        }
      }
    }

  override suspend fun onException(apiResponse: ApiResponse.Failure.Exception<T>) =
    withContext(Dispatchers.Main) {
      apiResponse.run {
        Timber.d(message())
        toast(message())
      }
    }

  override suspend fun onSuccess(apiResponse: ApiResponse.Success<T>) = Unit

  private fun toast(message: String) {
    Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
  }
}
