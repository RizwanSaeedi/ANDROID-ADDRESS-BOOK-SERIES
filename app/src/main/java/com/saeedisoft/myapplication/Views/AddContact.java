package com.saeedisoft.myapplication.Views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.saeedisoft.myapplication.Datasource.Data;
import com.saeedisoft.myapplication.Models.ContactModel;
import com.saeedisoft.myapplication.R;

public class AddContact extends AppCompatActivity {

    EditText etName, etMob, etAddress;
    Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        attachEvents();
    }

    private void attachEvents() {
        btnAddContact.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String mob = etMob.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (name.length() < 1 || mob.length() < 1) {
                Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_SHORT).show();
                return;
            }

            ContactModel model = new ContactModel(name, mob, address);
            Data.addToList(model);

            etName.setText("");
            etMob.setText("");
            etAddress.setText("");

            Toast.makeText(this, "size of list is now " + Data.getListSize(), Toast.LENGTH_LONG).show();

        });
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        etMob = findViewById(R.id.etMob);
        etAddress = findViewById(R.id.etAddress);
        btnAddContact = findViewById(R.id.btnAddContact);
    }
}
