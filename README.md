📰 News App

A modern Android News Application built with Jetpack Compose, Dagger Hilt, Room Database, and Clean Architecture (MVVM).
It fetches the latest news using a Free News API, provides offline support, and delivers a sleek UI with Shimmer Loading Effect.

🚀 Features

📰 Fetch latest news from News API

📱 Built with Jetpack Compose + some Android Views

⚡ Dagger Hilt for Dependency Injection

💾 Room Database for offline caching

🎨 Shimmer effect for loading states

🔍 Search news by keywords

🌓 Light & Dark theme support

📡 MVVM + Clean Architecture

🛠️ Tech Stack

Kotlin – Programming Language

Jetpack Compose – Modern UI Toolkit

Room Database – Local storage

Dagger Hilt – Dependency Injection

Retrofit + OkHttp – Networking

Coil – Image Loading in Compose

Coroutines + Flow – Asynchronous operations

Shimmer Effect – Skeleton loading screens

🏗️ Clean Architecture

The app is organized into layers for better separation of concerns and testability:

1. Presentation Layer (UI)

Jetpack Compose screens, Android Views

ViewModels (MVVM)

2. Domain Layer

Entities → Core models

UseCases → Business logic (e.g., GetNewsUseCase, SearchNewsUseCase)

3. Data Layer

Repositories → Implementation of data sources

Remote → Retrofit API service + DTOs

Local → Room Database + DAO

4. Dependency Injection Layer

Dagger Hilt Modules (Network, Database, Repository)

📂 Project Structure
📦 NewsApp
 ┣ 📂 presentation          # UI Layer
 ┃ ┣ 📂 components          # Reusable Composables
 ┃ ┣ 📂 screens             # Screens (Home, Details, Search)
 ┃ ┣ 📂 viewmodel           # ViewModels
 ┃ ┗ 📜 MainActivity.kt
 ┣ 📂 di                    # Hilt Modules
 ┃ ┣ 📜 NetworkModule.kt
 ┃ ┣ 📜 DatabaseModule.kt
 ┃ ┗ 📜 RepositoryModule.kt
 ┣ 📂 data                  # Data Layer
 ┃ ┣ 📂 local               # Room Database
 ┃ ┃ ┣ 📜 NewsDao.kt
 ┃ ┃ ┗ 📜 NewsDatabase.kt
 ┃ ┣ 📂 remote              # Retrofit API
 ┃ ┃ ┣ 📜 NewsApiService.kt
 ┃ ┃ ┗ 📜 NewsDto.kt
 ┃ ┣ 📂 repository
 ┃ ┃ ┗ 📜 NewsRepositoryImpl.kt
 ┣ 📂 domain                # Domain Layer
 ┃ ┣ 📂 model               # Entities
 ┃ ┣ 📂 repository          # Repository Interfaces
 ┃ ┗ 📂 usecase             # UseCases
 ┃   ┣ 📜 GetNewsUseCase.kt
 ┃   ┗ 📜 SearchNewsUseCase.kt
 ┣ 📜 build.gradle
 ┗ 📜 settings.gradle
