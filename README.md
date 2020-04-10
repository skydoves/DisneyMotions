
<h1 align="center">DisneyMotions</h1></br>
<p align="center">  
A demo Disney app using <a href="https://material.io/design/motion/the-motion-system.html" target="_blank"> transformation motions </a> based on MVVM architecture.<br>
The motion system is included in the 1.2.0-alpha05 released material version.
</p>
</br>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves/DisneyMotions/actions"><img alt="Build Status" src="https://github.com/skydoves/DisneyMotions/workflows/Android%20CI/badge.svg"/></a> 
  <a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-189"><img alt="KotlinWeekly" src="https://skydoves.github.io/badges/kotlin-weekly.svg"/></a>
  <a href="https://proandroiddev.com/building-a-beautiful-disney-android-application-1-material-motion-systems-34607eae2501"><img alt="Medium" src="https://skydoves.github.io/badges/Story-Medium.svg"/></a>
  <a href="https://github.com/skydoves"><img alt="Profile" src="https://skydoves.github.io/badges/skydoves.svg"/></a> 
</p>

## Download
Go to the [Releases](https://github.com/skydoves/DisneyMotions/releases) to download the lastest APK.

## Screeshots
<p align="center">
<img src="/preview/preview0.gif" width="32%"/>
<img src="/preview/preview1.gif" width="32%"/>
<img src="/preview/preview2.gif" width="32%"/>
</p>



## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- JetPack
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern
  - [Koin](https://github.com/InsertKoinIO/koin) - dependency injection
- Material Design & Animations
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server
- [Glide](https://github.com/bumptech/glide) - loading images
- [BaseRecyclerViewAdapter](https://github.com/skydoves/BaseRecyclerViewAdapter) - implementing adapters and viewHolders
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently
- [Timber](https://github.com/JakeWharton/timber) - logging
- Ripple animation, Shared element container transform/transition

## Unit Testing Frameworks
Unit Tests verify the interactions of viewmodels between repositories and dao & REST api requests.
- [Robolectric](https://github.com/robolectric/robolectric) - Robolectric is the industry-standard unit testing framework for Android.
- [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) - a small library that provides helper functions to work with Mockito in Kotlin.

![screenshot483387955](https://user-images.githubusercontent.com/24237865/79007747-c47c1b00-7b96-11ea-95c0-f6579f2d865b.png)

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/DisneyMotions/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/skydoves)__ me for my next creations! 🤩

# License
```xml
Designed and developed by 2020 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
