WeatherApp

A modern weather application built using Jetpack Compose that provides detailed weather updates with a focus on a clean architecture design. The app uses the Open Meteo API for fetching real-time weather data and displays it using a user-friendly interface.

Features

Jetpack Compose: Modern Android UI toolkit for building native UI.

MVVM Architecture: Ensures separation of concerns, making the code more modular and testable.

Clean Architecture: Divided into three main layers:

Data Layer: Responsible for API calls and data management using Retrofit.

Domain Layer: Isolated for handling business logic and data transformation.

Presentation Layer: For UI and user interaction.

Dependency Injection: Implemented using Dagger and Hilt for better dependency management.

Hourly Weather Data: Displays detailed weather updates, including hourly forecasts.

Reusable Components: Modular components designed for UI reusability and scalability.

Tech Stack

Programming Language: Kotlin

UI Framework: Jetpack Compose

Networking: Retrofit

Dependency Injection: Dagger, Hilt

Architecture: MVVM with Clean Architecture principles


How to Run the Project

Clone this repository:
git clone https://github.com/Ajverma2004/JetWeatherApp.git

Open the project in Android Studio.

Sync the project with Gradle files.

Run the app on an emulator or physical device.

![Screenshot_20241113_182408](https://github.com/user-attachments/assets/1c7b9ba2-fcee-41a2-b1c2-2431ccc24500)



Future Enhancements

Implement more weather data details like humidity, wind speed, and precipitation.

Add themes for light and dark mode.

Optimize performance for data fetching and UI rendering.
