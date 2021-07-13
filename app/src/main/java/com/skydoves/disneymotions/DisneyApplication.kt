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

@file:Suppress("unused")

package com.skydoves.disneymotions

import android.app.Application
import com.skydoves.disneymotions.di.networkModule
import com.skydoves.disneymotions.di.persistenceModule
import com.skydoves.disneymotions.di.repositoryModule
import com.skydoves.disneymotions.di.viewModelModule
import com.skydoves.disneymotions.network.GlobalResponseOperator
import com.skydoves.sandwich.SandwichInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DisneyApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@DisneyApplication)
      modules(networkModule)
      modules(persistenceModule)
      modules(repositoryModule)
      modules(viewModelModule)
    }

    // initialize global sandwich operator
    SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
