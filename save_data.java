package com.example.latticeapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class save_data extends AppCompatActivity {
    private DbData mDatabase;
    ImageView ibLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        RecyclerView contactView = findViewById(R.id.myDataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        contactView.setLayoutManager(linearLayoutManager);
        contactView.setHasFixedSize(true);


        try {
           mDatabase = new DbData(this);
           mDatabase.open();
            final ArrayList<PersonData> myData = mDatabase.getData();
            DataClass mAdapter = new DataClass(this, myData);
            contactView.setAdapter(mAdapter);
            mDatabase.close();
        }
        catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}