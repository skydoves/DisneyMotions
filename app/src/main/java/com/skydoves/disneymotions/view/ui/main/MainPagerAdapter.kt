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

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

  private val fragmentsCreators: Map<Int, () -> Fragment> = mapOf(
    HOME to { HomeFragment() },
    LIBRARY to { LibraryFragment() },
    RADIO to { RadioFragment() }
  )

  override fun createFragment(position: Int): Fragment {
    return fragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
  }

  override fun getItemCount() = fragmentsCreators.size

  companion object {
    private const val HOME = 0
    private const val LIBRARY = 1
    private const val RADIO = 2
  }
}
