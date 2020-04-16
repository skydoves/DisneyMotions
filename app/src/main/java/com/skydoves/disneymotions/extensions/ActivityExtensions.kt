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

import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.google.android.material.transition.MaterialArcMotion
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback

/** get a material container arc transform. */
internal fun getContentTransform(): MaterialContainerTransform {
  return MaterialContainerTransform().apply {
    addTarget(android.R.id.content)
    duration = 450
    pathMotion = MaterialArcMotion()
  }
}

/** apply material exit container transformation. */
fun AppCompatActivity.applyExitMaterialTransform() {
  window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
  setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
  window.sharedElementsUseOverlay = false
}

/** apply material entered container transformation. */
fun AppCompatActivity.applyMaterialTransform(transitionName: String) {
  window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
  ViewCompat.setTransitionName(findViewById<View>(android.R.id.content), transitionName)

  // set up shared element transition
  setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
  window.sharedElementEnterTransition = getContentTransform()
  window.sharedElementReturnTransition = getContentTransform()
}
