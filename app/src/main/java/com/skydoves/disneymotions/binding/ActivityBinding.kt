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

package com.skydoves.disneymotions.binding

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.skydoves.disneymotions.R

@BindingAdapter("simpleToolbarWithHome", "simpleToolbarTitle", requireAll = true)
fun bindToolbarWithTitle(toolbar: MaterialToolbar, activity: AppCompatActivity, title: String?) {
  activity.simpleToolbarWithHome(toolbar, title)
}

private fun AppCompatActivity.simpleToolbarWithHome(
  toolbar: MaterialToolbar,
  title_: String? = ""
) {
  setSupportActionBar(toolbar)
  supportActionBar?.run {
    setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    setDisplayHomeAsUpEnabled(true)
    title = title_
  }
}
