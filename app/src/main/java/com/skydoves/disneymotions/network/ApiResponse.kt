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

import okhttp3.ResponseBody
import retrofit2.Response

@Suppress("unused")
sealed class ApiResponse<out T> {

  /**
   * API Success response class from retrofit.
   *
   * [data] is optional. (There are responses without data)
   */
  class Success<T>(response: Response<T>) : ApiResponse<T>() {
    val data: T? = response.body()
    override fun toString() = "[ApiResponse.Success]: $data"
  }

  /**
   * API Failure response class.
   *
   * ## Throw Exception case.
   * Gets called when an unexpected exception occurs while creating the request or processing the response.
   *
   * ## API Network format error case.
   * API communication conventions do not match or applications need to handle errors.
   *
   * ## API Network Excepttion error case.
   * Gets called when an unexpected exception occurs while creating the request or processing the response.
   */
  sealed class Failure<out T> {
    class Error<out T>(response: Response<out T>) : ApiResponse<T>() {
      val responseBody: ResponseBody? = response.errorBody()?.apply { close() }
      val code: Int = response.code()
      override fun toString(): String = "[ApiResponse.Failure $code]: ${responseBody?.string()}"
    }

    class Exception<out T>(exception: Throwable) : ApiResponse<T>() {
      val message: String? = exception.localizedMessage
      override fun toString(): String = "[ApiResponse.Failure]: $message"
    }
  }

  companion object {
    /**
     * ApiResponse Factory
     *
     * [Failure] factory function. Only receives [Throwable] arguments.
     */
    fun <T> error(ex: Throwable) = Failure.Exception<T>(ex)

    /**
     * ApiResponse Factory
     *
     * [f] Create ApiResponse from [retrofit2.Response] returning from the block.
     * If [retrofit2.Response] has no errors, it will create [ApiResponse.Success]
     * If [retrofit2.Response] has errors, it will create [ApiResponse.Failure.Error]
     */
    fun <T> of(f: () -> Response<T>): ApiResponse<T> = try {
      val response = f()
      if (response.isSuccessful) {
        Success(response)
      } else {
        Failure.Error(response)
      }
    } catch (ex: Exception) {
      Failure.Exception(ex)
    }
  }
}
