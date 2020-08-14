package com.coreypre.dalistjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itemAdapter;
    private ListView listView;
    private Button button;
    private DatabaseReference reff;
    private Member member = new Member(items);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        reff = FirebaseDatabase.getInstance().getReference().child("Member");
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.btn_AddToList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        items = new ArrayList<>();
        itemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items){
            
        };
        listView.setAdapter(itemAdapter);
        setUpListViewListener();

    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();
                items.remove(position);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem() {
         EditText input = findViewById(R.id.Textbox);
         String itemText = input.getText().toString();

         if(!(itemText.equals(""))){
             itemAdapter.add(itemText);
             input.setText("");}
         else {
             Toast.makeText(getApplicationContext(),"Please enter text...",Toast.LENGTH_LONG);

         }
         member.setListArray(items);
         reff.push().setValue(member);
         Toast.makeText(this,"Item Added", Toast.LENGTH_LONG).show();

    }

}
