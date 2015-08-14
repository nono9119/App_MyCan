package com.mycan.app_mycan;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.SQLException;
import bbdd.AdaptadorDBMascotas;


public class InsertarMascota extends Activity {
    private EditText etNombre, etRaza, etPropietario, etTelefono;
    private String nombre, raza, propietario, modo, telefonoExtra;
    private int id_mascota, telefono;
    private Intent itt;
    private AdaptadorDBMascotas adbMascotas;
    private ContentValues cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_mascota);
        // OBTENGO LOS ELEMENTOS
        etNombre = (EditText) findViewById(R.id.etNombre);
        etRaza = (EditText) findViewById(R.id.etRaza);
        etPropietario = (EditText) findViewById(R.id.etPropietario);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        itt = getIntent();
        modo = itt.getStringExtra("modo");

        if (modo.equalsIgnoreCase("modificar")) {
            this.setTitle(R.string.activityModificar);
            // OBTENGO LOS DATOS DE LA OTRA ACTIVIDAD
            id_mascota = Integer.parseInt(itt.getStringExtra("id_mascota"));
            nombre = itt.getStringExtra("nombre");
            raza = itt.getStringExtra("raza");
            propietario = itt.getStringExtra("propietario");
            telefonoExtra = itt.getStringExtra("telefono");
            // LOS INSERTO EN LOS EDIT-TEXT
            etNombre.setText(nombre);
            etRaza.setText(raza);
            etPropietario.setText(propietario);
            etTelefono.setText(telefonoExtra);
        }
    }

    // METODOS PROPIOS
    public void onClick(View v) {
        if (v.getId() == R.id.btVolverMascota) {
            finish();
        } else if (v.getId() == R.id.btGuardarMascota) {
            if (modo.equalsIgnoreCase("insertar")) {
                btnInsertarMascota();
            } else if (modo.equalsIgnoreCase("modificar")) {
                btnModificarMascota();
            }
            finish();
        }
    }
    // INSERTAR MASCOTA
    public void btnInsertarMascota() {
        boolean flag = false;
        // OBTENGO LO QUE SE HAYA EN LOS EDIT-TEXT
        nombre = etNombre.getText().toString();
        raza = etRaza.getText().toString();
        propietario = etPropietario.getText().toString();
        telefono = Integer.parseInt(etTelefono.getText().toString());
        // PREPARO LA INSERCION
        cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("raza", raza);
        cv.put("propietario", propietario);
        cv.put("telefono", telefono);
        // CREO EL ADAPTADOR E INSERTO LA MASCOTA
        adbMascotas = new AdaptadorDBMascotas(this);
        try {
            adbMascotas.abrirConexion();
            flag = adbMascotas.insertarMascota(cv);
            adbMascotas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // EN FUNCION DEL RESULTADO MUESTRO UN MENSAJE
        if (flag) {
            Toast.makeText(this, R.string.mascotaOK,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.falloMascota,
                    Toast.LENGTH_SHORT).show();
        }
    }

    // MODIFICAR MASCOTA
    public void btnModificarMascota() {
        // OBTENGO LOS DATOS DE LOS EDIT-TEXT
        if (etNombre.getText().toString().length() != 0) {
            nombre = etNombre.getText().toString();
        }
        if (etRaza.getText().toString().length() != 0) {
            raza = etRaza.getText().toString();
        }
        if (etPropietario.getText().toString().equalsIgnoreCase("")) {
            propietario = "No especificado";
        } else {
            propietario = etPropietario.getText().toString();
        }
        if (etTelefono.getText().toString().length() == 9) {
            telefono = Integer.parseInt(etTelefono.getText().toString());
        } else {
            telefono = 123456789;
        }
        // PREPARO LA MODIFICACION
        cv = new ContentValues();
        cv.put("nombre", nombre);
        cv.put("raza", raza);
        cv.put("propietario", propietario);
        cv.put("telefono", telefono);
        // CREO EL ADAPTADOR Y MODIFICO LA MASCOTA
        adbMascotas = new AdaptadorDBMascotas(this);
        try {
            adbMascotas.abrirConexion();
            adbMascotas.modificarMascota(cv, id_mascota);
            adbMascotas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
