package com.example.cse489_2019262035;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {

    EditText username, pass;
    Button signup, exit, go;
    CheckBox cbremid;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        signup = findViewById(R.id.signup);
        exit = findViewById(R.id.exit);
        go = findViewById(R.id.go);
        cbremid = findViewById(R.id.cbremid);

        preferences = getSharedPreferences("userinfo", 0);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    private void processLogin() {
        String usernamevalue = username.getText().toString();
        String passvalue = pass.getText().toString();

        String signedupusername = preferences.getString("username", ""); // consistent key
        String signeduppass = preferences.getString("password", ""); // consistent key

        if (usernamevalue.trim().isEmpty() || passvalue.trim().isEmpty()) {
            showErrorDialog("Please fill in all fields");
        } else if (usernamevalue.equals(signedupusername) && passvalue.equals(signeduppass)) {
            Intent intent = new Intent(login.this, homelist.class);
            startActivity(intent);
        } else {
            showErrorDialog("Invalid username or password");
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