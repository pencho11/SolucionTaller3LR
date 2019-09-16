package com.example.soluciontaller3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soluciontaller3.basesdedatos.AdminSQLiteOpenHelper;

public class Notas3Activity extends AppCompatActivity {

    private EditText etCodigo;
    private EditText etNombre;
    private EditText etNota1;
    private EditText etNota2;
    private EditText etNota3;
    private EditText cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas3);

        etCodigo = findViewById(R.id.txtCodigo);
        etNombre = findViewById(R.id.txtNombre);
        etNota1 = findViewById(R.id.txtNota1);
        etNota2 = findViewById(R.id.txtNota2);
        etNota3 = findViewById(R.id.txtNota3);
        cantidad = findViewById(R.id.txtCantidad);
    }

    public void Registro(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basesDeDatos = admin.getWritableDatabase();

        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String nota1 = etNota1.getText().toString();
        String nota2 = etNota2.getText().toString();
        String nota3 = etNota3.getText().toString();
        String cantidadNotas = cantidad.getText().toString();


        int calificacion1 = Integer.parseInt(nota1);
        int calificacion2 = Integer.parseInt(nota2);
        int calificacion3 = Integer.parseInt(nota3);
        int promedio = Integer.parseInt(cantidadNotas);
        int totalNota = 0;


            totalNota = ((calificacion1 + calificacion2 + calificacion3) / promedio);


            if (!codigo.isEmpty() && !nombre.isEmpty() && !nota1.isEmpty() && !nota2.isEmpty()
                    && !nota3.isEmpty()) {
                ContentValues registro = new ContentValues();


                registro.put("codigo", codigo);
                registro.put("nombre", nombre);
                registro.put("nota1", nota1);
                registro.put("nota2", nota2);
                registro.put("nota3", nota3);
                registro.put("total", totalNota);

                basesDeDatos.insert("notas1", null, registro);
                basesDeDatos.close();

                etCodigo.setText("");
                etNombre.setText("");
                etNota1.setText("");
                etNota2.setText("");
                etNota3.setText("");
                cantidad.setText("");
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}

