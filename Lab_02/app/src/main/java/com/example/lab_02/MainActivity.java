package com.example.lab_02;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button button_1, button_2, button_3;
    EditText add_city;
    int del_idx = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        String [] cities = {"Edmonton", "Calgary", "Toronto", "Vancouver"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener((parent, view, position, id) -> {
            del_idx = position;
        });
        button_1 = findViewById(R.id.button_add);
        button_1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                add_city.setVisibility(0);
                button_3.setVisibility(0);
            }
        });


        button_2 = findViewById(R.id.button_delete);
        button_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                add_city.setVisibility(4);
                button_3.setVisibility(4);
                if (del_idx != -1) {
                    dataList.remove(del_idx);
                    cityAdapter.notifyDataSetChanged();
                    del_idx = -1;
                }
                else {
                    Toast.makeText(MainActivity.this,"No city is selected.", Toast.LENGTH_SHORT).show();
                }
            }


        });

        add_city = findViewById(R.id.add_city);
        add_city.setVisibility(4);

        button_3 = findViewById(R.id.button_confirm);
        button_3.setVisibility(4);
        button_3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String new_city = add_city.getText().toString().trim();
                if (!new_city.isEmpty()) {
                    add_city.setVisibility(4);
                    button_3.setVisibility(4);
                    dataList.add(new_city);
                    cityAdapter.notifyDataSetChanged();
                    add_city.setText("");
                }
                else {
                    Toast.makeText(MainActivity.this,"No city is entered.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}