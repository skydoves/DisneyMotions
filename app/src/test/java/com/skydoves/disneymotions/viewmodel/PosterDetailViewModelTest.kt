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

package com.skydoves.disneymotions.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.disneymotions.MainCoroutinesRule
import com.skydoves.disneymotions.repository.DetailRepository
import com.skydoves.disneymotions.utils.MockTestUtil.mockPoster
import com.skydoves.disneymotions.view.ui.details.PosterDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PosterDetailViewModelTest {

  private lateinit var viewModel: PosterDetailViewModel
  private val repository: DetailRepository = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @Before
  fun setup() {
    viewModel = PosterDetailViewModel(repository)
  }

  @Test
  fun getPosterTest() = runBlocking {
    val mockData = mockPoster()
    whenever(repository.getPosterById(0)).thenReturn(mockPoster())

    val loadFromDB = viewModel.getPoster(0)
    verify(repository).getPosterById(0)
    assertThat(loadFromDB, `is`(mockData))
  }
}
