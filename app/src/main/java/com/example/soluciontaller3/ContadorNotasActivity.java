package com.example.soluciontaller3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ContadorNotasActivity extends AppCompatActivity {
    private EditText cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador_notas);
        cantidad = findViewById(R.id.txtCantidad);
    }

    public void MostrarNotas(View view){
        String cantidadNotas = cantidad.getText().toString();

        int promedio = Integer.parseInt(cantidadNotas);
        if (promedio == 3){
            Intent ventana = new Intent(this, Notas3Activity.class);
            startActivity(ventana);
        }else if(promedio == 4){
            Intent ventana = new Intent(this, NotasActivity.class);
            startActivity(ventana);
        }
    }
}
