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

import retrofit2.Call
import retrofit2.Callback

/** transforms [Call] to [ApiResponse] via enqueueing response callback. */
fun <T> Call<T>.transform(onResult: (response: ApiResponse<T>) -> Unit) {
  enqueue(object : Callback<T> {
    override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
      onResult(ApiResponse.of { response })
    }

    override fun onFailure(call: Call<T>, throwable: Throwable) {
      onResult(ApiResponse.error(throwable))
    }
  })
}

/** gets the [ApiResponse.Failure.Error] message with a error code. */
fun <T> ApiResponse.Failure.Error<T>.message() = "$code: ${responseBody?.string()}"

/** gets the [ApiResponse.Failure.Exception] message. */
fun <T> ApiResponse.Failure.Exception<T>.message() = "$message"
