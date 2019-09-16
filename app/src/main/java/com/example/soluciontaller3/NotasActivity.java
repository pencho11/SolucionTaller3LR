package com.example.soluciontaller3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.soluciontaller3.basesdedatos.AdminSQLiteOpenHelper;

public class NotasActivity extends AppCompatActivity {

    private EditText etCodigo;
    private EditText etNombre;
    private EditText etNota1;
    private EditText etNota2;
    private EditText etNota3;
    private EditText etNota4;

    private EditText cantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);

        etCodigo = findViewById(R.id.txtCodigo);
        etNombre = findViewById(R.id.txtNombre);
        etNota1 = findViewById(R.id.txtNota1);
        etNota2 = findViewById(R.id.txtNota2);
        etNota3 = findViewById(R.id.txtNota3);
        etNota4 = findViewById(R.id.txtNota4);

        cantidad = findViewById(R.id.txtCantidad);
    }


    public void Registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basesDeDatos = admin.getWritableDatabase();

        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String nota1 = etNota1.getText().toString();
        String nota2 = etNota2.getText().toString();
        String nota3 = etNota3.getText().toString();
        String nota4 = etNota4.getText().toString();
        String cantidadNotas = cantidad.getText().toString();


        int calificacion1 = Integer.parseInt(nota1);
        int calificacion2 = Integer.parseInt(nota2);
        int calificacion3 = Integer.parseInt(nota3);
        int calificacion4 = Integer.parseInt(nota4);
        int promedio = Integer.parseInt(cantidadNotas);
        int totalNota = 0;


        if(promedio == 4) {

            totalNota = ((calificacion1 + calificacion2 + calificacion3 + calificacion4) / promedio);


            if (!codigo.isEmpty() && !nombre.isEmpty() && !nota1.isEmpty() && !nota2.isEmpty()
                    && !nota3.isEmpty() && !nota4.isEmpty()) {
                ContentValues registro = new ContentValues();


                registro.put("codigo", codigo);
                registro.put("nombre", nombre);
                registro.put("nota1", nota1);
                registro.put("nota2", nota2);
                registro.put("nota3", nota3);
                registro.put("nota4", nota4);
                registro.put("total", totalNota);

                basesDeDatos.insert("notas", null, registro);
                basesDeDatos.close();

                etCodigo.setText("");
                etNombre.setText("");
                etNota1.setText("");
                etNota2.setText("");
                etNota3.setText("");
                etNota4.setText("");
                cantidad.setText("");
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basesDeDatos = admin.getWritableDatabase();

        String codigo = etCodigo.getText().toString();
        if(!codigo.isEmpty()){
            Cursor fila = basesDeDatos.rawQuery("select nombre, nota1, nota2 ,nota3 ,nota4 , total from notas where codigo ="+codigo,null);

            if (fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                etNota1.setText(fila.getString(1));
                etNota2.setText(fila.getString(2));
                etNota3.setText(fila.getString(3));
                etNota4.setText(fila.getString(4));
                //resultado.setText(fila.getString(5));
                basesDeDatos.close();
            }else{
                Toast.makeText(this,"No existe ninguna identificacion de estudiante.", Toast.LENGTH_SHORT).show();
                basesDeDatos.close();
            }
        }else{
            Toast.makeText(this,"Debes de introducir el codigo del estudiante", Toast.LENGTH_SHORT).show();
        }
    }

    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basesDeDatos = admin.getWritableDatabase();

        String codigo = etCodigo.getText().toString();

        if (!codigo.isEmpty()){
            int cantidad1 = basesDeDatos.delete("notas","codigo=" + codigo, null);
            basesDeDatos.close();

            etCodigo.setText("");
            etNombre.setText("");
            etNota1.setText("");
            if (cantidad1 == 1){
                Toast.makeText(this,"Estudiante eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Estudiante no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Debes de introducir el codigo del estudiante", Toast.LENGTH_SHORT).show();
        }
    }

    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase basesDeDatos = admin.getWritableDatabase();

        String codigo = etCodigo.getText().toString();
        String nombre = etNombre.getText().toString();
        String nota1 = etNota1.getText().toString();
        if (!codigo.isEmpty() && !nombre.isEmpty() && !nota1.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("nombre", nombre);
            registro.put("nota1", nota1);

            int cantidad1 = basesDeDatos.update("notas",registro, "codigo="+codigo, null);
            basesDeDatos.close();

            if (cantidad1 == 1){
                Toast.makeText(this,"El estudiante se ha modificado.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"El estudiante no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
