# 🚀 TechNewsExploreApp
<video src="https://github.com/user-attachments/assets/4334594d-caff-4251-a93b-007bf2c8f8ce" controls></video>
## 🧐 Overview
This is a full-stack news discovery platform that leverages external News APIs to provide real-time global coverage. The application focuses on user-centric customization, featuring a robust filtering system for category-based browsing and a persistent bookmarking engine. Built with a focus on clean UI and efficient data fetching, it provides a streamlined reading experience without the clutter of traditional news sites.
[NewsApi](https://newsapi.org/) is used in this app.

## 🏗️ Architecture
* **[MVVM Pattern](https://developer.android.com/topic/libraries/architecture/viewmodel)** - Separation of UI logic and business logic.
* **[Multi-Module](https://developer.android.com/topic/modularization)** - Clean architecture approach for better build times and code organization.
* **[Hilt-Dagger](https://developer.android.com/training/dependency-injection/hilt-android)** - Standard library for Dependency Injection.

## 💻 Core Technologies
* **[Kotlin](https://kotlinlang.org/)** - First-class language for Android development.
* **[Jetpack Compose](https://developer.android.com/jetpack/compose)** - Modern toolkit for building native UI.
* **[Coroutines & Flow](https://developer.android.com/kotlin/coroutines)** - For asynchronous programming and reactive data streams.
* **[Retrofit 2](https://square.github.io/retrofit/)** - Type-safe REST client for consuming the News API.
* **[Room Database](https://developer.android.com/training/data-storage/room)** - Local persistence for caching and bookmarking articles.
* **[DataStore](https://developer.android.com/topic/libraries/architecture/datastore)** - Modern replacement for SharedPreferences to store user filters/preferences.
* **[Firebase](https://firebase.google.com/)** - Backend integration for analytics/crash reporting.
* **[Navigation Component](https://developer.android.com/guide/navigation)** - Centralized navigation for Compose.
* **[Deep Linking](https://developer.android.com/training/app-links/deep-linking)** - Direct navigation support to specific articles from external sources.
* **[MockK](https://mockk.io/)** - A powerful mocking library built for Kotlin, used to isolate dependencies and verify interactions.
* **[Turbine](https://github.com/cashapp/turbine)** - A specialized testing library for Kotlin Flows to handle asynchronous stream assertions cleanly.
* **[JUnit 4](https://junit.org/junit4/)** - Standard framework for writing repeatable unit tests and managing the test lifecycle.
