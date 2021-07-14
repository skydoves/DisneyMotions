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

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.disneymotions.model.Poster
import com.skydoves.disneymotions.view.adapter.PosterAdapter
import com.skydoves.disneymotions.view.adapter.PosterCircleAdapter
import com.skydoves.disneymotions.view.adapter.PosterLineAdapter
import com.skydoves.whatif.whatIfNotNullAs
import com.skydoves.whatif.whatIfNotNullOrEmpty

object RecyclerViewBinding {
  @JvmStatic
  @BindingAdapter("adapter")
  fun bindAdapter(view: RecyclerView, baseAdapter: BaseAdapter) {
    view.adapter = baseAdapter
  }

  @JvmStatic
  @BindingAdapter("adapterPosterList")
  fun bindAdapterPosterList(view: RecyclerView, posters: List<Poster>?) {
    posters.whatIfNotNullOrEmpty { items ->
      view.adapter.whatIfNotNullAs<PosterAdapter> { adapter ->
        adapter.addPosterList(items)
      }
    }
  }

  @JvmStatic
  @BindingAdapter("adapterPosterLineList")
  fun bindAdapterPosterLineList(view: RecyclerView, posters: List<Poster>?) {
    posters.whatIfNotNullOrEmpty { items ->
      view.adapter.whatIfNotNullAs<PosterLineAdapter> { adapter ->
        adapter.addPosterList(items)
      }
    }
  }

  @JvmStatic
  @BindingAdapter("adapterPosterCircleList")
  fun bindAdapterPosterCircleList(view: RecyclerView, posters: List<Poster>?) {
    posters.whatIfNotNullOrEmpty { items ->
      view.adapter.whatIfNotNullAs<PosterCircleAdapter> { adapter ->
        adapter.addPosterList(items)
      }
    }
  }
}
