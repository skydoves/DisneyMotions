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

import android.graphics.Color
import android.transition.TransitionManager
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.skydoves.disneymotions.R
import com.skydoves.disneymotions.extensions.gone
import com.skydoves.disneymotions.extensions.visible
import kotlin.math.abs

@BindingAdapter("loadImage")
fun bindLoadImage(view: AppCompatImageView, url: String) {
  Glide.with(view.context)
    .load(url)
    .into(view)
}

@BindingAdapter("pagerAdapter")
fun bindPagerAdapter(view: ViewPager, adapter: PagerAdapter) {
  view.adapter = adapter
  view.offscreenPageLimit = 3
}

@BindingAdapter("bindNavigation")
fun bindNavigation(view: ViewPager, navigationView: BottomNavigationView) {
  view.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) = Unit
    override fun onPageScrolled(
      position: Int,
      positionOffset: Float,
      positionOffsetPixels: Int
    ) = Unit

    override fun onPageSelected(position: Int) {
      navigationView.menu.getItem(position).isChecked = true
    }
  })

  navigationView.setOnNavigationItemSelectedListener {
    when (it.itemId) {
      R.id.action_home -> view.currentItem = 0
      R.id.action_library -> view.currentItem = 1
      R.id.action_radio -> view.currentItem = 2
    }
    true
  }
}

@BindingAdapter("bindFab")
fun bindAppBarLayoutWithFab(appBarLayout: AppBarLayout, fab: FloatingActionButton) {
  appBarLayout.addOnOffsetChangedListener(
    OnOffsetChangedListener { appBarLayout1: AppBarLayout, verticalOffset: Int ->
      val verticalOffsetPercentage = abs(
        verticalOffset).toFloat() / appBarLayout1.totalScrollRange.toFloat()
      if (verticalOffsetPercentage > 0.4f && fab.isOrWillBeShown) {
        fab.hide()
      } else if (verticalOffsetPercentage <= 0.4f && fab.isOrWillBeHidden && fab.tag != View.GONE) {
        fab.show()
      }
    })
}

@BindingAdapter("transformFab", "transformContainer")
fun bindTransformFab(view: View, fab: FloatingActionButton, container: CoordinatorLayout) {
  fab.setOnClickListener {
    // Begin the transition by changing properties on the start and end views or
    // removing/adding them from the hierarchy.
    fab.tag = View.GONE
    TransitionManager.beginDelayedTransition(container, getTransform(fab, view))
    fab.gone()
    view.visible()
  }

  view.setOnClickListener {
    fab.tag = View.VISIBLE
    TransitionManager.beginDelayedTransition(container, getTransform(view, fab))
    fab.visible()
    view.gone()
  }
}

internal fun getTransform(mStartView: View, mEndView: View): MaterialContainerTransform {
  return MaterialContainerTransform().apply {
    startView = mStartView
    endView = mEndView
    pathMotion = MaterialArcMotion()
    duration = 650
    scrimColor = Color.TRANSPARENT
  }
}
