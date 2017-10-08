package com.example.alexandre.netflixlibraryproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InscriptionActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etNom;
    private EditText etPrenom;
    private EditText etEmail;
    private EditText etPhone;
    private Button btnCancel;
    private Button btnCheckin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        etUsername = (EditText) findViewById(R.id.et_inscription_username);
        etPassword = (EditText) findViewById(R.id.et_inscription_password);
        etNom = (EditText) findViewById(R.id.et_inscription_nom);
        etPrenom = (EditText) findViewById(R.id.et_inscription_prenom);
        etEmail = (EditText) findViewById(R.id.et_inscription_email);
        etPhone = (EditText) findViewById(R.id.et_inscription_phone);
        btnCancel = (Button) findViewById(R.id.btn_inscription_cancel);
        btnCheckin = (Button) findViewById(R.id.btn_inscription_checkin);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenuIntent = new Intent(InscriptionActivity.this, LoginActivity.class);
                startActivity(toMenuIntent);
                finish();
            }
        });

        btnCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenuIntent = new Intent(InscriptionActivity.this, LoginActivity.class);
                startActivity(toMenuIntent);
                finish();
            }
        });

    }
}
