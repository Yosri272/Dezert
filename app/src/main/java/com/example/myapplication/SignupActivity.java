package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPassword;
    private Button btnSignup;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignup = findViewById(R.id.btnSignup);
        tvMessage = findViewById(R.id.tvMessage);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                // Check if any field is empty
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    showMessage("Please fill in all fields");
                } else {
                    signupUser(username, email, password);
                }
            }
        });
    }

    private void signupUser(String username, String email, String password) {
        String url = "https://wepower.wepower.host/api/signup";

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("name", username); // The correct key according to the API
            jsonBody.put("email", email);
            jsonBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            showMessage("Error creating JSON data.");
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("SignupActivity", "Response: " + response);
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            String message = jsonResponse.optString("message", "Registered successfully!");

                            if (jsonResponse.has("success") && jsonResponse.getBoolean("success")) {
                                showMessage(" " + message);
                            } else if (message.equals("The email has already been taken.")) {
                                showMessage(" This email is already registered.");
                            } else {
                                showMessage(" " + message);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            showMessage(" Error parsing server response.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("SignupActivity", "Error: " + error.getMessage());
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    try {
                        String errorMsg = new String(error.networkResponse.data);
                        JSONObject errorJson = new JSONObject(errorMsg);

                        if (errorJson.has("errors")) {
                            JSONObject errors = errorJson.getJSONObject("errors");

                            if (errors.has("email")) {
                                String emailError = errors.getJSONArray("email").getString(0);
                                showMessage(" Email error: " + emailError);
                            } else if (errors.has("name")) {
                                String nameError = errors.getJSONArray("name").getString(0);
                                showMessage(" Username error: " + nameError);
                            } else {
                                showMessage(" Registration errors occurred. Please check your data.");
                            }

                        } else if (errorJson.has("message")) {
                            showMessage("  " + errorJson.getString("message"));
                        } else {
                            showMessage(" Unexpected server error.");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        showMessage(" Error handling error response.");
                    }
                } else {
                    showMessage(" Could not connect to the server. Please check your internet connection and try again.");
                }
            }
        }) {
            @Override
            public byte[] getBody() {
                return jsonBody.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Signup Notice");
        builder.setMessage(message);
        builder.setPositiveButton("OK", null);
        builder.show();

        // Do not display the message in the TextView in the background
        tvMessage.setVisibility(View.GONE);
    }
}
