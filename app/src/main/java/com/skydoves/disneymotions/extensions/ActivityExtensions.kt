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

package com.skydoves.disneymotions.extensions

import android.content.Context
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.transition.platform.MaterialArcMotion
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.skydoves.disneymotions.R

/** get a material container arc transform. */
internal fun getContentTransform(context: Context): MaterialContainerTransform {
  return MaterialContainerTransform().apply {
    addTarget(android.R.id.content)
    duration = 450
    pathMotion = MaterialArcMotion()
    isElevationShadowEnabled = true
    startElevation = 9f
    endElevation = 9f
    startContainerColor = ContextCompat.getColor(context, R.color.colorPrimary)
  }
}

/** apply material exit container transformation. */
fun AppCompatActivity.applyExitMaterialTransform() {
  window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
  setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
  window.sharedElementsUseOverlay = false
}

/** apply material entered container transformation. */
fun AppCompatActivity.applyMaterialTransform(transitionName: String?) {
  window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
  ViewCompat.setTransitionName(findViewById(android.R.id.content), transitionName)

  // set up shared element transition
  setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
  window.sharedElementEnterTransition = getContentTransform(this)
  window.sharedElementReturnTransition = getContentTransform(this)
}
