package com.example.alexandre.netflixlibraryproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private CheckBox cbRemember;
    private Button btnSubmit;
    private Button btnReset;
    private Button btnCheckin;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_login_username);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        cbRemember = (CheckBox) findViewById(R.id.cb_login_remember);
        btnSubmit = (Button) findViewById(R.id.btn_login_submit);
        btnReset = (Button) findViewById(R.id.btn_login_reset);
        btnCheckin = (Button) findViewById(R.id.btn_login_checkin);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRemembered = preferences.getBoolean("remember_me", false);
        if (isRemembered) {
            etUsername.setText(preferences.getString("username", ""));
            etPassword.setText(preferences.getString("password", ""));
        }
        cbRemember.setChecked(isRemembered);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                boolean rememberMe = cbRemember.isChecked();

                if (username.equals("helha") && password.equals("pass")) {

                    SharedPreferences.Editor editor = preferences.edit();

                    if (rememberMe) {
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.putBoolean("remember_me", true);
                    } else {
                        editor.remove("username");
                        editor.remove("password");
                        editor.putBoolean("remember_me", false);
                    }

                    editor.apply();

                    Intent toMenuIntent = new Intent(LoginActivity.this, MainActivity.class);
                    toMenuIntent.putExtra("username", username);
                    startActivity(toMenuIntent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Erreur !",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUsername.setText("");
                etPassword.setText("");
            }
        });

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenuIntent = new Intent(LoginActivity.this, InscriptionActivity.class);
                startActivity(toMenuIntent);
                finish();
            }
        });

    }
}
