package com.example.cse489_2019262035;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Signup extends AppCompatActivity {

    EditText username, email, phone, uid, pass, rpass;
    CheckBox cbremid;
    Button exit, login, go;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        uid = findViewById(R.id.uid);
        pass = findViewById(R.id.pass);
        rpass = findViewById(R.id.rpass);
        cbremid = findViewById(R.id.cbremid);
        exit = findViewById(R.id.exit);
        login = findViewById(R.id.login);
        go = findViewById(R.id.go);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, login.class);
                startActivity(intent);
                finish();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processsignup();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void processsignup() {
        String usernamevalue = username.getText().toString();
        String emailvalue = email.getText().toString();
        String phonevalue = phone.getText().toString();
        String uidvalue = uid.getText().toString();
        String passvalue = pass.getText().toString();
        String rpassvalue = rpass.getText().toString();
        String errMsg = "";
        preferences = getSharedPreferences("userinfo", 0);

        if (usernamevalue.trim().isEmpty() ||
                emailvalue.trim().isEmpty() || phonevalue.trim().isEmpty()
                || uidvalue.trim().isEmpty() || passvalue.trim().isEmpty() || rpassvalue.trim().isEmpty()) {
            errMsg = "Please fill in all fields";
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailvalue).matches()) {
            errMsg = "Please enter a valid email address";
        } else if (!android.util.Patterns.PHONE.matcher(phonevalue).matches()) {
            errMsg = "Please enter a valid phone number";
        } else if (!passvalue.equals(rpassvalue)) {
            errMsg = "Passwords do not match";
        }

        if (errMsg.length() > 0) {
            showErrorDialog(errMsg);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username", usernamevalue);
            editor.putString("email", emailvalue);
            editor.putString("phone", phonevalue);
            editor.putString("uid", uidvalue);
            editor.putString("password", passvalue); // consistent key
            editor.putString("rpassword", rpassvalue); // consistent key
            boolean isChecked = cbremid.isChecked();
            editor.apply();
            Toast.makeText(Signup.this, "User registered", Toast.LENGTH_SHORT).show();

        }
    }

    private void showErrorDialog(String errorMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(errorMessage);

        builder.setTitle("Error");

        builder.setCancelable(true);

        builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();

        alert.show();

    }
}