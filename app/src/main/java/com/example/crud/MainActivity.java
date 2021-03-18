package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id,nombre,cedula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id=findViewById(R.id.id);
        nombre=findViewById(R.id.nombre);
        cedula=findViewById(R.id.cedula);

    }
    public void entrar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String aid= id.getText().toString();
        String acedula = cedula.getText().toString();
        String anombre= nombre.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("id", aid);
        registro.put("cedula", acedula);
        registro.put("nombre", anombre);
        bd.insert("Usuario", null, registro);
        bd.close();
        id.setText("");
        nombre.setText("");
        cedula.setText("");
        Toast.makeText(this, "Se guardaron los datos",Toast.LENGTH_SHORT).show();
    }
    public void cancelar(View v){
        id.setText("");
        nombre.setText("");
        cedula.setText("");
            Toast.makeText(this, "campos limpios", Toast.LENGTH_SHORT).show();

    }
    public void consultarporid(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String aid = id.getText().toString();
        Cursor fila = bd.rawQuery("select nombre,cedula from Usuario where id=" + aid, null);
        if (fila.moveToFirst()) {
            nombre.setText(fila.getString(0));
            cedula.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe un usuario con dicho id", Toast.LENGTH_SHORT).show();
        bd.close();
    }
    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String aid= id.getText().toString();
        String acedula = cedula.getText().toString();
        String anombre= nombre.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("id", aid);
        registro.put("cedula", acedula);
        registro.put("nombre", anombre);
        int cant = bd.update("Usuario", registro, "id=" + aid, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un usuario con el id ingresado",
                    Toast.LENGTH_SHORT).show();
    }
    public void borrar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String aid= id.getText().toString();
        int cant = bd.delete("Usuario", "id=" + aid, null);
        bd.close();
        id.setText("");
        cedula.setText("");
        nombre.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró el usuario con dicho id", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe un usuario con dicho id",Toast.LENGTH_SHORT).show();
    }
}