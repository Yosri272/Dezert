Android Signup App – Frontend
A simple Android application that displays a user signup form and sends the data to a Laravel API using the Volley library.

📱 Project Description
This is a frontend mobile app for user registration. The user fills in their details, and the app sends the data to a Laravel API. The app shows a success or error message based on the server's response.

🛠️ Features
Simple and user-friendly signup form.

Basic input validation before submission.

Uses Volley for HTTP POST requests.

Connects to a Laravel backend API.

Displays success or error message based on API response.

🧾 Signup Form Fields
The form includes the following fields:

name – Full Name

email – Email Address

password – Password

password_confirmation – Confirm Password

🚀 How It Works
User opens the app and fills out the signup form.

Inputs are validated locally.

Data is sent using Volley via a POST request.

The Laravel API processes the request.

The app displays a success or error message based on the response.

📡 API Endpoint
nginx
Copy
Edit
POST https://wepower.wepower.host/api/signup
✅ Example Request (JSON):
json
Copy
Edit
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "123456",
  "password_confirmation": "123456"
}
📬 Example Success Response:
json
Copy
Edit
{
  "success": true,
  "message": "User registered successfully."
}
❌ Example Error Response:
json
Copy
Edit
{
  "success": false,
  "errors": {
    "email": ["The email has already been taken."]
  }
}
🧰 Technologies Used
Android Studio

Java or Kotlin

Volley – For HTTP networking

Laravel – Backend API

🛠️ How to Run the App
Open the project in Android Studio.

Make sure the device or emulator is connected to the internet.

(Optional) Update the API URL if needed.

Run the app on a physical device or emulator.

Try registering a new user through the form.
