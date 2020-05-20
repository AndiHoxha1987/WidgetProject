package com.example.widgetproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.widgetproject.WidgetService.WidgetServiceFactory.AMOUNT;
import static com.example.widgetproject.WidgetService.WidgetServiceFactory.COLOR;
import static com.example.widgetproject.WidgetService.WidgetServiceFactory.NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String name = intent.getStringExtra(NAME);
        String color = intent.getStringExtra(COLOR);
        String amount = intent.getStringExtra(AMOUNT);

        TextView textName = findViewById(R.id.name);
        TextView textColor = findViewById(R.id.color);
        TextView textAmount = findViewById(R.id.amount);

        textName.setText(name);
        textColor.setText(color);
        textAmount.setText(amount);
    }
}
