package com.example.pi.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pi.R;

public class AtividadeLoja extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);


        texto = (TextView) findViewById(R.id.token);

        String value = getIntent().getExtras().getString("token");

        texto.setText(value);
    }
}
