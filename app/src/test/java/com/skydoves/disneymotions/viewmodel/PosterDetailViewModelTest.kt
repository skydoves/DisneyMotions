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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.disneymotions.MainCoroutinesRule
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.persistence.PosterDao
import com.skydoves.disneymotions.repository.DetailRepository
import com.skydoves.disneymotions.utils.MockTestUtil.mockPoster
import com.skydoves.disneymotions.view.ui.details.PosterDetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PosterDetailViewModelTest {

  private lateinit var viewModel: PosterDetailViewModel
  private lateinit var repository: DetailRepository
  private val posterDao: PosterDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setup() {
    repository = DetailRepository(posterDao)
    viewModel = PosterDetailViewModel(0L, repository)
  }

  @Test
  fun getPosterTest() = runBlocking {
    val mockData = mockPoster()
    whenever(posterDao.getPoster(0)).thenReturn(mockData)

    val dataFromDB = repository.getPosterById(0).asLiveData()
    val observer: Observer<Poster> = mock()
    dataFromDB.observeForever(observer)

    delay(500L)

    verify(posterDao, atLeastOnce()).getPoster(0)
    verify(observer).onChanged(mockData)
    dataFromDB.removeObserver(observer)
  }
}
