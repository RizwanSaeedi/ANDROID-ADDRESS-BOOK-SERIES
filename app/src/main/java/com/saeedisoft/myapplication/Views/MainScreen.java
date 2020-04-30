package com.saeedisoft.myapplication.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.saeedisoft.myapplication.R;

public class MainScreen extends AppCompatActivity {

    Context mContext;
    Button btnAddContact, btnAllContacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mContext = this;

        initViews();
        attachEvents();
    }

    private void attachEvents() {
        btnAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AddContact.class);
            startActivity(intent);
        });

        btnAllContacts.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AllContacts.class);
            startActivity(intent);
        });
    }

    private void initViews() {
        btnAddContact = findViewById(R.id.btnAddContact);
        btnAllContacts = findViewById(R.id.btnAllContacts);
    }
}
