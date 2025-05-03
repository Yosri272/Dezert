
# Android Signup App – Frontend

A simple Android application that provides a user registration form and connects to a Laravel API using the Volley library.

## 📱 Project Overview

This app allows users to sign up by filling out a registration form. The form data is sent to a Laravel-based backend API using an HTTP POST request through the Volley library. The app then displays a success or error message based on the server’s response.

## 🧾 Form Fields

- `name` – Full Name  
- `email` – Email Address  
- `password` – Password  
- `password_confirmation` – Confirm Password  

## 📡 API Endpoint

```
POST https://wepower.wepower.host/api/signup
```

### Example Request Body:

```json
{
  "name": "Yosri",
  "email": "Yosri@example.com",
  "password": "123456",
  
}
```

### Example Success Response:

```json
{
  "success": true,
  "message": "User registered successfully."
}
```

### Example Error Response:

```json
{
  "success": false,
  "errors": {
    "email": ["The email has already been taken."]
  }
}
```

## 🛠️ Features

- Simple and clean UI for signup
- Local form validation
- Integration with Laravel API using Volley
- Success and error response handling

## 🧰 Technologies Used

- Android Studio
- Java or Kotlin
- Volley HTTP Library
- Laravel (Backend API)

## 🚀 Getting Started

1. Clone or download this project.
2. Open the project using Android Studio.
3. Make sure your device or emulator has internet access.
4. Build and run the application.
5. Test the signup form by entering user data and submitting it.

## 📂 Project Structure

```
├── MainActivity.java
├── Network/
│   └── VolleySingleton.java
├── res/
│   └── layout/activity_main.xml
```

## 🔐 Notes

- Ensure the API endpoint is reachable from your Android device or emulator.
- If testing on an emulator, the endpoint must be accessible over the internet (not `localhost`).
