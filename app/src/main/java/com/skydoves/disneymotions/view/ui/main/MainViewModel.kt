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

package com.skydoves.disneymotions.view.ui.main

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.skydoves.bindables.bindingProperty
import com.skydoves.disneymotions.base.LiveCoroutinesViewModel
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.repository.MainRepository
import timber.log.Timber

class MainViewModel constructor(
  private val mainRepository: MainRepository
) : LiveCoroutinesViewModel() {

  val posterListLiveData: LiveData<List<Poster>>

  @get:Bindable
  var isLoading: Boolean by bindingProperty(true)
    private set

  @get:Bindable
  var errorToast: String? by bindingProperty(null)
    private set

  init {
    Timber.d("injection MainViewModel")

    posterListLiveData = launchOnViewModelScope {
      this.mainRepository.loadDisneyPosters(
        onSuccess = { isLoading = false },
        onError = { errorToast = it }
      ).asLiveData()
    }
  }
}
