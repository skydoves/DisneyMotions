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

@file:Suppress("SpellCheckingInspection")

package com.skydoves.disneymotions.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.disneymotions.MainCoroutinesRule
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.network.ApiUtil.getCall
import com.skydoves.disneymotions.network.DisneyClient
import com.skydoves.disneymotions.network.DisneyService
import com.skydoves.disneymotions.persistence.PosterDao
import com.skydoves.themovies2.utils.MockTestUtil.mockPosterList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainRepositoryTest {

  private lateinit var repository: MainRepository
  private lateinit var client: DisneyClient
  private val service: DisneyService = mock()
  private val posterDao: PosterDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    client = DisneyClient(service)
    repository = MainRepository(client, posterDao)
  }

  @Test
  fun loadDisneyPostersTest() = runBlocking {
    val mockData = mockPosterList()
    whenever(posterDao.getPosterList()).thenReturn(emptyList())
    whenever(service.fetchDisneyPosterList()).thenReturn(getCall(mockData))

    val loadData = repository.loadDisneyPosters { }
    verify(posterDao, atLeastOnce()).getPosterList()
    verify(service, atLeastOnce()).fetchDisneyPosterList()

    val observer: Observer<List<Poster>> = mock()
    loadData.observeForever(observer)

    val updatedData = mockPosterList()
    whenever(posterDao.getPosterList()).thenReturn(updatedData)

    loadData.postValue(updatedData)
    verify(observer).onChanged(updatedData)
  }
}
