package com.example.shared;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText name, phone;

    Button save, next;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



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



        try{

            name = findViewById(R.id.name);
            phone = findViewById(R.id.phone);

            save = findViewById(R.id.save);
            next = findViewById(R.id.next);


            sharedPreferences = getSharedPreferences("Shaon", MODE_PRIVATE);
            editor = sharedPreferences.edit();


            save.setEnabled(false);
            name.addTextChangedListener(textWatcher);
            phone.addTextChangedListener(textWatcher);


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(MainActivity.this, SecondActivity.class));

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String myName = name.getText().toString().trim();
            String myPhone = phone.getText().toString().trim();

            save.setEnabled(!myName.isEmpty() || !myPhone.isEmpty());
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String myName = name.getText().toString().trim();
                    String myPhone = phone.getText().toString().trim();

                    editor.putString("name", myName);
                    editor.putString("phone", myPhone);
                    editor.apply();


                }
            });

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}