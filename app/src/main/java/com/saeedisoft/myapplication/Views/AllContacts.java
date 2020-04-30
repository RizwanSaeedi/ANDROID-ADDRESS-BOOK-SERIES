package com.saeedisoft.myapplication.Views;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.saeedisoft.myapplication.Datasource.Data;
import com.saeedisoft.myapplication.Models.ContactModel;
import com.saeedisoft.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class AllContacts extends AppCompatActivity {

    EditText etSearch;
    ListView listView;
    Button btnSearch;
    Context mContext;
    List<ContactModel> allContacts;
    List<ContactModel> filteredContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);
        mContext = this;

        allContacts = new ArrayList<>();
        filteredContacts = new ArrayList<>();

        initViews();
        attachEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        etSearch.setText("");
        allContacts = Data.getContactList();
        loadListView();
    }

    private void attachEvents() {

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position > -1) {
                String name = allContacts.get(position).getName();
               showAlert("Warning", "Do You Really Want To Delete This Contact", name);
            }
        });


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputName = etSearch.getText().toString().trim();
                filteredContacts = Data.searchContactModelByName(inputName);
                loadFilteredListView();
                /*if (Data.getListSize() < 1) {
                    Toast.makeText(AllContacts.this, "Contact List Is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }*/
            }
        });

        btnSearch.setOnClickListener(v ->
        {



//            if (model == null) {
//                Toast.makeText(this, "No Matches Found", Toast.LENGTH_SHORT).show();
//                return;
//            } else {
//                loadListView();
//            }
        });
    }

    private void loadListView() {
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, allContacts);
        listView.setAdapter(adapter);
    }

    private void loadFilteredListView() {
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, filteredContacts);
        listView.setAdapter(adapter);
    }

    private void initViews() {
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        listView = findViewById(R.id.listView);
    }

    private void showAlert(String title, String message, String name) {
        AlertDialog.Builder alert = new AlertDialog.Builder(AllContacts.this);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("YES", (dialog, which) -> {
            Data.deleteByName(name);
            showNotification(AllContacts.this, "Address Book App", "Record Deleted Successfully");
            loadListView();
        });
        alert.setNegativeButton("NO", null);
        alert.setNeutralButton("CANCEL", null);
        alert.create();
        alert.setCancelable(false);
        alert.show();
    }

    private void showNotification(Context context, String title, String body) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel("1", "MyChannel",NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body);
        notificationManager.notify(1, mBuilder.build());
    }
}
