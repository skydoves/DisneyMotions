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

import android.os.Bundle
import com.skydoves.bindables.BindingActivity
import com.skydoves.disneymotions.R
import com.skydoves.disneymotions.databinding.ActivityMainBinding
import com.skydoves.disneymotions.extensions.applyExitMaterialTransform
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  override fun onCreate(savedInstanceState: Bundle?) {
    applyExitMaterialTransform()
    super.onCreate(savedInstanceState)
    binding {
      pagerAdapter = MainPagerAdapter(this@MainActivity)
      vm = getViewModel()
    }
  }
}
