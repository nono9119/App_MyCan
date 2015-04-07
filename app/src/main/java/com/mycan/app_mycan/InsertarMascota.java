package com.mycan.app_mycan;

import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.SQLException;
import database.AdaptadorDBMascotas;


public class InsertarMascota extends ActionBarActivity {
    private EditText etNombre, etRaza, etPropietario, etTelefono;
    private String nombre, raza, propietario;
    private int id, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_mascota);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etRaza = (EditText) findViewById(R.id.etRaza);
        etPropietario = (EditText) findViewById(R.id.etPropietario);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insertar_mascota, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // METODOS PROPIOS
    public void onClick(View v) {
        if (v.getId() == R.id.btVolverMascota) {
            finish();
        } else if (v.getId() == R.id.btGuardarMascota) {
            ContentValues cv;
            AdaptadorDBMascotas adbMascotas;
            boolean flag = false;

            nombre = etNombre.getText().toString();
            raza = etRaza.getText().toString();
            propietario = etPropietario.getText().toString();
            telefono = Integer.parseInt(etTelefono.getText().toString());

            cv = new ContentValues();
            cv.put("nombre", nombre);
            cv.put("raza", raza);
            cv.put("propietario", propietario);
            cv.put("telefono", telefono);

            adbMascotas = new AdaptadorDBMascotas(this);
            try {
                adbMascotas.abrirConexion();
                flag = adbMascotas.insertarMascota(cv);
                adbMascotas.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (flag) {
                Toast.makeText(this, "Mascota insertada correctamente",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al agregar la mascota, llama a Nono",
                        Toast.LENGTH_SHORT).show();
            }

            finish();
        }
    }

}
