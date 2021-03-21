package com.maxsoftlk.loginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.maxsoftlk.loginapplication.Constants.PASSWORD;
import static com.maxsoftlk.loginapplication.Constants.USERNAME;

public class MainActivity extends AppCompatActivity {

    private EditText eUsername;
    private EditText ePassword;
    private Button eLogin;
    private TextView eAttemptsInfo;

    private boolean isValid = false;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eUsername = findViewById(R.id.etUserName);
        ePassword = findViewById(R.id.etPassword);
        eLogin = findViewById(R.id.btnLogin);
        eAttemptsInfo = findViewById(R.id.tvAttemptsInfo);

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = eUsername.getText().toString();
                String inputPassword = ePassword.getText().toString();

                if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this,
                            "Please enter both username and password!", Toast.LENGTH_SHORT).show();
                } else {
                    isValid = validateCredentials(inputUsername, inputPassword);

                    if (!isValid) {
                        counter--;
                        Toast.makeText(MainActivity.this,
                                "Incorrect credentials!", Toast.LENGTH_SHORT).show();
                        eAttemptsInfo.setText("Number of attempts remaining: " + counter);

                        if (counter == 0) {
                            eLogin.setEnabled(false);
                        }
                    } else {
                        Toast.makeText(MainActivity.this,
                                "Login was successful!", Toast.LENGTH_SHORT).show();
                        Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(homeIntent);
                    }
                }
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        return username.equalsIgnoreCase(USERNAME) && password.equals(PASSWORD);
    }
}