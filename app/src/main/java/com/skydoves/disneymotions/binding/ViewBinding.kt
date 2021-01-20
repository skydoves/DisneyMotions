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
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.skydoves.androidbottombar.AndroidBottomBarView
import com.skydoves.androidbottombar.BottomMenuItem
import com.skydoves.disneymotions.R
import com.skydoves.disneymotions.extensions.gone
import com.skydoves.disneymotions.extensions.visible
import kotlin.math.abs

object ViewBinding {
  @JvmStatic
  @BindingAdapter("loadImage")
  fun bindLoadImage(view: AppCompatImageView, url: String?) {
    Glide.with(view.context)
      .load(url)
      .into(view)
  }

  @JvmStatic
  @BindingAdapter("pagerAdapter")
  fun bindPagerAdapter(view: ViewPager2, adapter: FragmentStateAdapter) {
    view.adapter = adapter
    view.offscreenPageLimit = 3
  }

  @JvmStatic
  @BindingAdapter("gone")
  fun bindGone(view: View, shouldBeGone: Boolean?) {
    if (shouldBeGone == true) {
      view.gone(true)
    } else {
      view.gone(false)
    }
  }

  @JvmStatic
  @BindingAdapter("bindNavigation")
  fun bindNavigation(view: ViewPager2, navigationView: AndroidBottomBarView) {
    navigationView.addBottomMenuItems(
      mutableListOf(
        BottomMenuItem(view.context)
          .setTitle("Home")
          .setIcon(R.drawable.ic_home)
          .setIconSize(24)
          .build(),

        BottomMenuItem(view.context)
          .setTitle("Tv")
          .setIcon(R.drawable.ic_library)
          .setIconSize(24)
          .build(),

        BottomMenuItem(view.context)
          .setTitle("Radio")
          .setIcon(R.drawable.ic_radio)
          .setIconSize(24)
          .build()
      )
    )

    navigationView.setOnMenuItemSelectedListener { index, _, _ ->
      navigationView.dismissBadge(index)
      view.currentItem = index
    }

    navigationView.setOnBottomMenuInitializedListener {
      navigationView.bindViewPager2(view)
    }
  }

  @JvmStatic
  @BindingAdapter("bindFab")
  fun bindAppBarLayoutWithFab(appBarLayout: AppBarLayout, fab: FloatingActionButton) {
    appBarLayout.addOnOffsetChangedListener(
      OnOffsetChangedListener { appBarLayout1: AppBarLayout, verticalOffset: Int ->
        val verticalOffsetPercentage = abs(
          verticalOffset
        ).toFloat() / appBarLayout1.totalScrollRange.toFloat()
        if (verticalOffsetPercentage > 0.4f && fab.isOrWillBeShown) {
          fab.hide()
        } else if (verticalOffsetPercentage <= 0.4f && fab.isOrWillBeHidden && fab.tag != View.GONE) {
          fab.show()
        }
      }
    )
  }

  @JvmStatic
  @BindingAdapter("transformFab", "transformContainer")
  fun bindTransformFab(view: View, fab: FloatingActionButton, container: CoordinatorLayout) {
    fab.setOnClickListener {
      // Begin the transition by changing properties on the start and end views or
      // removing/adding them from the hierarchy.
      fab.tag = View.GONE
      TransitionManager.beginDelayedTransition(container, getTransform(fab, view))
      fab.gone(true)
      view.visible()
    }

    view.setOnClickListener {
      fab.tag = View.VISIBLE
      TransitionManager.beginDelayedTransition(container, getTransform(view, fab))
      fab.visible()
      view.gone(true)
    }
  }

  private fun getTransform(mStartView: View, mEndView: View): MaterialContainerTransform {
    return MaterialContainerTransform().apply {
      startView = mStartView
      endView = mEndView
      addTarget(mEndView)
      pathMotion = MaterialArcMotion()
      duration = 550
      scrimColor = Color.TRANSPARENT
    }
  }
}
