package com.example.bestvideogame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TextView;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public RadioButton b1, b2, b3, b4;

    public RadioGroup radioGroup;

    public CheckBox checkBox;

    public TextView changeText;

    public Button button;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.lang);

        b1 = (RadioButton) findViewById(R.id.b1);
        b2 = (RadioButton) findViewById(R.id.b2);
        b3 = (RadioButton) findViewById(R.id.b3);
        b4 = (RadioButton) findViewById(R.id.b4);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        checkBox = (CheckBox) findViewById(R.id.checkBox);

        changeText = (TextView) findViewById(R.id.changeText);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.b1:
                        changeText.setText("Veo que te gusta el "+b1.getText());
                        break;

                    case R.id.b2:
                        changeText.setText("Veo que te gusta el "+b2.getText());
                        break;

                    case R.id.b3:
                        changeText.setText("Veo que te gusta el "+b3.getText());
                        break;

                    case R.id.b4:
                        changeText.setText("Veo que te gusta el "+b4.getText());
                        break;
                }
            }
        });

        checkBox.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    checkBox.setText(R.string.checkbox_one);
                }else {
                    checkBox.setText(R.string.checkbox_two);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    showLanguageDialog();

            }
        });

    }
    public void showLanguageDialog() {
        final String[] languages = {"Español", "Euskera"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecciona idioma");
        Intent i = new Intent(MainActivity.this, MainActivity.class);
        builder.setItems(languages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        setLocale("es");
                         //change for your case
                        startActivity(i);

                        break;
                    case 1:
                        setLocale("eu");
                        startActivity(i);

                        break;
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        saveLocale(lang);

    }

    private void saveLocale(String lang) {
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String lang = prefs.getString("My_Lang", "");
        setLocale(lang);
    }

}