package com.example.latticeapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  {
    Button submitBtn;
    EditText etName,etAddress,etPhone,etEmail,etLocation,etPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String passwordPattern ="^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            ".{8,}" +               //at least 8 characters
            "$";
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etAddress= findViewById(R.id.etAddress);
        etPhone = findViewById(R.id.etPhone);
        etEmail= findViewById(R.id.etEmail);
        etLocation = findViewById(R.id.etLocation);
        etPassword = findViewById(R.id.etPassword);
    }

    public void submitBtn( View view){
        String name= etName.getText().toString().trim();
        String number = etPhone.getText().toString().trim();
        String email= etEmail.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String password= etPassword.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        if(name.length()<4 || number.length()<10 || !email.matches(emailPattern) || address.length()<10
        || !password.matches(passwordPattern)) {
            Toast.makeText(this, "Enter a Valid Content to Submit", Toast.LENGTH_SHORT).show();
        }
   else {
            try {
                DbData db = new DbData(this);
                db.open();
                db.createEntry(name, number, email, address, password, location);
                db.close();
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etPhone.setText("");
                etEmail.setText("");
                etAddress.setText("");
                etPassword.setText("");
                etLocation.setText("");
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

    }
    public void saveData(View view){
        startActivity(new Intent(this,save_data.class));
    }
}