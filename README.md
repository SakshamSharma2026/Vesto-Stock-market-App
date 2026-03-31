<div align="center">

# 📈 Vesto - Modern Stock Market App

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/Android%20Studio-3DDC84.svg?style=for-the-badge&logo=android-studio&logoColor=white" alt="Android Studio" />
  <img src="https://img.shields.io/badge/Jetpack%20Compose-4285F4.svg?style=for-the-badge&logo=Jetpack-Compose&logoColor=white" alt="Jetpack Compose" />
  <img src="https://img.shields.io/badge/API-36%2B-brightgreen.svg?style=for-the-badge&logo=android" alt="API" />
  <img src="https://img.shields.io/badge/Architecture-Clean%20%7C%20MVVM-orange.svg?style=for-the-badge" alt="MVVM" />
</p>

*A beautifully crafted, feature-rich Android stock market application built entirely with Kotlin and Jetpack Compose. Track real-time prices, analyze deeply with interactive charts, and stay ahead of the curve!*

[Features](#-key-features) •
[Tech Stack](#-tech-stack-and-architecture) •
[Getting Started](#-getting-started) •
[Screenshots](#-screenshots)

</div>

---

## ✨ Key Features

| Feature | Description |
| :--- | :--- |
| **📉 Dynamic Interactive Charts** | Explore stock trends with deep scrubbing, dynamic price indicators, and smooth line interpolation for a fluid experience. |
| **📊 Comprehensive Stock Info** | Dedicated screens for detailed fetching of live data, company descriptions, and vital financial metrics. |
| **🏆 Market Movers** | Instantly discover the day’s top gainers and losers using intuitively sorted market tabs. |
| **🚀 Optimized Performance** | Built with rendering efficiency in mind, managing recomposition natively to ensure butter-smooth scrolling. |
| **📱 Edge-to-Edge UI** | Dynamic system bar adaptations and modern overlays that look gorgeous on any modern Android device. |
| **📡 Robust Error Handling** | Seamless transitions between loading, success, and offline error states throughout the app lifecycle. |

---

## 🛠️ Tech Stack and Architecture

Vesto is built with a highly scalable, robust **Multi-Module Architecture** based on **Clean Architecture** principles and the **MVVM** pattern. This structure ensures a strict separation of concerns, improves build times, and enables feature isolation, making the codebase highly maintainable and testable as it grows.

### 🏗️ Module Structure

The project is divided into clearly defined layers and independent modules:

- **`:app`**: The application's entry point, handling dependency injection wiring, global navigation, and core application-level configurations.
- **`:core`**: Contains foundational modules meant to be shared across the entire application.
  - `:core:common`: Shared utilities, extensions, and constants.
  - `:core:feature_api`: Navigation interfaces representing contracts between features.
  - `:core:network`: Centralized networking infrastructure (Retrofit setup, OkHttp interceptors).
  - `:core:database`: Local persistence setup.
- **`:feature`**: Houses isolated business features. Features (like `stock`) are further decomposed into layer modules to strictly enforce Clean Architecture:
  - `...:data`: Repository implementations, remote endpoints, and network/local DTOs.
  - `...:domain`: Pure Kotlin business logic, Use Cases, domain models, and repository contracts. Fully abstracted from Android UI/Data dependencies.
  - `...:ui`: Jetpack Compose screens, ViewModels, and UI-specific State/Event handling.
- **`:utilities`**: High-level shared components used app-wide.

### 🛡 Data Flow & Modeling Strategy
To prevent leaky abstractions, the architecture enforces strict boundary crossing:
- **DTOs (Data Transfer Objects)** map raw network/database structures in the Data layer.
- **Domain Models** represent pristine business data required for domain logic.
- **UI States** represent exactly what should be rendered on screen.
- **Mappers** are used to safely bridge and transform these representations between the Data, Domain, and UI layers.

### Core Technologies
- **[Kotlin](https://kotlinlang.org/)**: 100% Kotlin implementation for expressive, concise, and safe code.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: Modern declarative, reactive Native UI toolkit.
- **[Coroutines & Flow](https://kotlinlang.org/docs/coroutines-overview.html)**: For managing background threads and asynchronous sequences.

### Libraries & Frameworks
- **[Dagger Hilt](https://dagger.dev/hilt/)**: Robust and scalable dependency injection.
- **[Retrofit2](https://square.github.io/retrofit/) & [OkHttp3](https://square.github.io/okhttp/)**: Lightning-fast and reliable network calls, interceptors, and logging.
- **[Gson](https://github.com/google/gson)**: Seamless JSON parsing and serialization.
- **[Splash Screen API](https://developer.android.com/develop/ui/views/launch/splash-screen)**: Seamless Native Android starting experience.

---

## 🚀 Getting Started

To build, test, and run Vesto locally, follow the steps below.

### 📋 Prerequisites
- **Android Studio** (Koala or newer Recommended)
- **JDK 11+**
- **Android SDK** (Target API 36)

### ⚙️ Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/SakshamSharma2026/Vesto-Stock-market-App.git
   ```

2. **Supply your API Credentials**
   This application utilizes a financial API and requires an access key to fetch genuine stock data.
   
   Create a `secret.properties` file in the **root** of the project (`app/../secret.properties`) to safely supply your configuration:
   
   ```properties
   BASE_URL="https://your.api.baseurl.com/"
   API_KEY="your_api_key_here"
   ```

3. **Build & Run**
   Sync your Gradle files and run the `app` target onto an emulator or physical device running **API 33** or higher.

---

## 📷 Screenshots

<div align="center">

| Home Screen | Stock Details | News & Analysis |
|:---:|:---:|:---:|
| <img src="https://via.placeholder.com/250x500.png?text=Home+Screen" width="250"/> | <img src="https://via.placeholder.com/250x500.png?text=Stock+Details" width="250"/> | <img src="https://via.placeholder.com/250x500.png?text=News+Analysis" width="250"/> |

</div>

---

## 🤝 Contributing

Contributions, bug reports, and feature requests are very welcome! If you have an idea to improve the application, feel free to open a pull request or submit an issue.

<div align="center">
  
Made with ❤️ by Saksham Sharma

</div>
