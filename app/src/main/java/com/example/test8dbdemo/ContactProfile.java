package com.example.test8dbdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test8dbdemo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class ContactProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String phone=intent.getStringExtra("phone");

        CollapsingToolbarLayout collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(name);

    }
}
