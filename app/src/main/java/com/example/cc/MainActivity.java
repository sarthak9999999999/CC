package com.example.cc;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText card_num, expiry_date, cvv, first, last;
    Button pay;
    AlertDialog.Builder builder;
    ImageView back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        card_num = findViewById(R.id.card_number);
        expiry_date = findViewById(R.id.expiry);
        cvv = findViewById(R.id.cvv);
        first = findViewById(R.id.f_name);
        last = findViewById(R.id.last_name);
        pay = findViewById(R.id.pay);
        back_btn=findViewById(R.id.back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        builder = new AlertDialog.Builder(MainActivity.this);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCredentials();
            }
        });
    }

    public void checkCredentials() {
        card_num.setError(null);
        expiry_date.setError(null);
        cvv.setError(null);
        first.setError(null);
        last.setError(null);
        final String card_number = card_num.getText().toString().trim();
        String expiry = expiry_date.getText().toString().trim();
        String cvv_pass = cvv.getText().toString().trim();
        String first_name = first.getText().toString().trim();
        String last_name = last.getText().toString().trim();

        

        if (!CardModel.isValidCardNumber(card_number)) {
            card_num.setError("Invalid Card!");
            card_num.requestFocus();
        } else if (cvv_pass.length() < 3 || cvv_pass.isEmpty()) {
            cvv.setError("Invalid CVV!");
            cvv.requestFocus();
        } else if (first_name.isEmpty() || first_name.length() < 2) {
            first.setError("First Name is required!");
            first.requestFocus();
        } else if (last_name.isEmpty() || first_name.length() < 2) {
            last.setError("Last Name required!");
            last.requestFocus();
        } else {
            builder.setMessage("Confirm Payment?").setCancelable(true).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    ConfirmationSheet confirmationSheet=new ConfirmationSheet();
                    confirmationSheet.show(getSupportFragmentManager(),"Bottom Sheet");
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        }
    }
}

