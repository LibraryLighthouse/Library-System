package com.example.librarylighthouse.UI.Auth;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.librarylighthouse.ApiServices;
import com.example.librarylighthouse.MainActivity;
import com.example.librarylighthouse.R;
import com.example.librarylighthouse.TestActivity;
import com.example.librarylighthouse.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText textEmail, textPassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        sharedPreferences = getApplication()
                .getSharedPreferences("com.example.library_lighthouse", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        textEmail = findViewById(R.id.txtLoginEmail);
        textPassword = findViewById(R.id.txtLoginPassword);
        Button button = findViewById(R.id.btnLogin);
        button.setOnClickListener(v -> {
            loginUser();
        });
        if (sharedPreferences.getString("status", "").equals("true")) {
            startActivity(new Intent(getApplicationContext(), TestActivity.class));
            finish();
        }
        TextView textView = findViewById(R.id.btnToRegister);
        textView.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        });

    }
    void loginUser(){
        String email, password;
        email = textEmail.getText().toString();
        password = textPassword.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.31.206:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiServices apiServices = retrofit.create(ApiServices.class);
        User user = new User(email, password);
        Call<ResponseBody> call = apiServices.loginUser(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null){
                    try {
                        String res =  response.body().string();
                        JSONObject jsonObject = new JSONObject(res);
                        JSONObject user = jsonObject.getJSONObject("user");
                        String token = jsonObject.getString("token");
                        String name = user.getString("name");
                        String email = user.getString("email");
                        editor.putString("token", token);
                        editor.putString("name", name);
                        editor.putString("email", email);
                        editor.putString("status", "true");
                        editor.apply();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();

                    }catch (IOException | JSONException exception){
                        throw new RuntimeException(exception);
                    }

                }else {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
