
<h1 align="center">DisneyMotions</h1></br>
<p align="center">  
A demo Disney app using <a href="https://material.io/design/motion/the-motion-system.html" target="_blank"> transformation motions </a> based on MVVM architecture.<br>
The motion system is included in the 1.2.0-alpha05 released material version.
</p>
</br>

<p align="center">
  <a href="https://devlibrary.withgoogle.com/products/android/repos/skydoves-DisneyMotions"><img alt="Google" src="https://skydoves.github.io/badges/google-devlib.svg"/></a><br>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves/DisneyMotions/actions"><img alt="Build Status" src="https://github.com/skydoves/DisneyMotions/workflows/Android%20CI/badge.svg"/></a> 
  <a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-189"><img alt="KotlinWeekly" src="https://skydoves.github.io/badges/kotlin-weekly.svg"/></a>
  <a href="https://proandroiddev.com/building-a-beautiful-disney-android-application-1-material-motion-systems-34607eae2501"><img alt="Medium" src="https://skydoves.github.io/badges/Story-Medium.svg"/></a>
  <a href="https://github.com/skydoves"><img alt="Profile" src="https://skydoves.github.io/badges/skydoves.svg"/></a> 
</p>

## Download
Go to the [Releases](https://github.com/skydoves/DisneyMotions/releases) to download the latest APK.

## Screenshots
<p align="center">
<img src="/preview/preview0.gif" width="32%"/>
<img src="/preview/preview1.gif" width="32%"/>
<img src="/preview/preview2.gif" width="32%"/>
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - [Bindables](https://github.com/skydoves/bindables) - Android DataBinding kit for notifying data changes to UI layers.
  - Repository pattern
  - [Koin](https://github.com/InsertKoinIO/koin) - dependency injection
- Material Design & Animations
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight network response interfaces and handling error responses.
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server
- [Glide](https://github.com/bumptech/glide) - loading images
- [BaseRecyclerViewAdapter](https://github.com/skydoves/BaseRecyclerViewAdapter) - implementing adapters and viewHolders
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently
- [Bundler](https://github.com/skydoves/bundler) - Android Intent & Bundle extensions that insert and retrieve values elegantly.
- [Timber](https://github.com/JakeWharton/timber) - logging
- Ripple animation, Shared element container transform/transition

## MAD Score
![summary](https://user-images.githubusercontent.com/24237865/103010250-58eaaa00-457b-11eb-90d6-e62beda756b0.png)
![kotlin](https://user-images.githubusercontent.com/24237865/103010255-5a1bd700-457b-11eb-8959-0a7c4a2b4bda.png)

## Unit Testing Frameworks
Unit Tests verify the interactions of viewmodels between repositories and dao & REST api requests.
- [Robolectric](https://github.com/robolectric/robolectric) - Robolectric is the industry-standard unit testing framework for Android.
- [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) - a small library that provides helper functions to work with Mockito in Kotlin.

![screenshot483387955](https://user-images.githubusercontent.com/24237865/79007747-c47c1b00-7b96-11ea-95c0-f6579f2d865b.png)

<img src="https://user-images.githubusercontent.com/24237865/141674368-6013d77c-d52b-4bb1-afe4-9a57a06be32f.jpg" width="18%" align="right" />

## Contents Credits
All copyrights of the contents, concepts, and phrases used for this open-source project belong to [The Walt Disney Company](https://www.disneyplus.com/).

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
