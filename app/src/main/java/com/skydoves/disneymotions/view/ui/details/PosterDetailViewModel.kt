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

package com.skydoves.disneymotions.view.ui.details

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.skydoves.bindables.BindingViewModel
import com.skydoves.bindables.asBindingProperty
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class PosterDetailViewModel(
  posterId: Long,
  private val repository: DetailRepository
) : BindingViewModel() {

  private val posterIdStateFlow: MutableStateFlow<Long> = MutableStateFlow(posterId)
  private val posterFlow = posterIdStateFlow.flatMapLatest { id ->
    repository.getPosterById(id)
  }

  @get:Bindable
  val poster: Poster? by posterFlow.asBindingProperty(viewModelScope, null)
}
