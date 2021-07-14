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
import com.skydoves.disneymotions.network.DisneyService
import com.skydoves.disneymotions.persistence.PosterDao
import com.skydoves.disneymotions.repository.MainRepository
import com.skydoves.disneymotions.utils.MockTestUtil
import com.skydoves.disneymotions.view.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

  private lateinit var viewModel: MainViewModel
  private lateinit var mainRepository: MainRepository
  private val disneyService: DisneyService = mock()
  private val posterDao: PosterDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    mainRepository = MainRepository(disneyService, posterDao)
    viewModel = MainViewModel(mainRepository)
  }

  @Test
  fun fetchDisneyPosterListTest() = runBlocking {
    val mockData = MockTestUtil.mockPosterList()
    whenever(posterDao.getPosterList()).thenReturn(mockData)

    val fetchedData = mainRepository.loadDisneyPosters(
      onSuccess = {}
    ).asLiveData()

    val observer: Observer<List<Poster>> = mock()
    fetchedData.observeForever(observer)

    delay(500L)

    verify(posterDao, atLeastOnce()).getPosterList()
    verify(observer).onChanged(mockData)
    fetchedData.removeObserver(observer)
  }
}
