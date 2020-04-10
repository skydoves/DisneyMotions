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

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.disneymotions.persistence.PosterDao
import com.skydoves.disneymotions.utils.MockTestUtil.mockPoster
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test

class DetailRepositoryTest {

  private lateinit var repository: DetailRepository
  private val posterDao: PosterDao = mock()

  @Before
  fun setup() {
    repository = DetailRepository(posterDao)
  }

  @Test
  fun getPosterByIdTest() {
    val mockData = mockPoster()
    whenever(posterDao.getPoster(0)).thenReturn(mockPoster())

    val loadFromDB = repository.getPosterById(0)
    verify(posterDao).getPoster(0)
    assertThat(loadFromDB, `is`(mockData))
  }
}
