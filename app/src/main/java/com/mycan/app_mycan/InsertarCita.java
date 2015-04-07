package com.mycan.app_mycan;

import android.content.ContentValues;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import clases.Mascota;
import database.AdaptadorDBCitas;
import database.AdaptadorDBMascotas;


public class InsertarCita extends ActionBarActivity {
    // NECESARIOS PARA EL SPINNER
    private Spinner spMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayAdapter<Mascota> spAdapterMascotas;
    private AdaptadorDBMascotas adbMascotas;
    private AdaptadorDBCitas adbCitas;
    private int id_mascota;
    private EditText etFecha, etPrecio, etHora, etDescripcion;
    private String texto_sp, nombre, raza, fecha, hora, precio, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_cita);

        etFecha = (EditText) findViewById(R.id.etFecha);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etHora = (EditText) findViewById(R.id.etHora);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        spMascotas = (Spinner) findViewById(R.id.spMascotas);
        // OBTENGO LAS MASCOTAS PARA EL SPINNER
        adbMascotas = new AdaptadorDBMascotas(this);
        try {
            adbMascotas.abrirConexion();
            mascotas = adbMascotas.getMascotasSP();
            adbMascotas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // INSERTAR MASCOTAS EN EL SPINNER
        if (mascotas != null) {
            spAdapterMascotas = new ArrayAdapter<Mascota>(this,
                    android.R.layout.simple_spinner_item, mascotas);
            spAdapterMascotas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMascotas.setAdapter(spAdapterMascotas);
            spMascotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    texto_sp = parent.getSelectedItem().toString();
                    nombre = texto_sp.substring(0, texto_sp.lastIndexOf("("));
                    raza = texto_sp.substring(texto_sp.lastIndexOf("(") + 1, texto_sp.length() - 1);

                    try {
                        adbMascotas.abrirConexion();
                        id_mascota = adbMascotas.getIdMascota(nombre, raza);
                        adbMascotas.cerrarConexion();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    /*
                    etfecha.setText(nombre);
                    precio.setText(raza);
                    hora.setText(String.valueOf(id_mascota));
                    */
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) { }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insertar_cita, menu);
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

    public void onClick(View v) {
        if (v.getId() == R.id.btGuardarCita) {
            ContentValues cv;
            boolean flag = false;
            fecha = etFecha.getText().toString();
            hora = etHora.getText().toString();
            precio = etPrecio.getText().toString();
            descripcion = etDescripcion.getText().toString();

            cv = new ContentValues();
            cv.put("fecha", fecha);
            cv.put("hora", hora);
            cv.put("precio", precio);
            cv.put("descripcion", descripcion);
            cv.put("id_mascota", id_mascota);

            adbCitas = new AdaptadorDBCitas(this);
            try {
                adbCitas.abrirConexion();
                flag = adbCitas.insertarCita(cv);
                adbCitas.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (flag) {
                Toast.makeText(this, "Cita insertada correctamente",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al insertar la cita, llama a Nono",
                        Toast.LENGTH_SHORT).show();
            }

            finish();
        } else if (v.getId() == R.id.btVolverCita) {
            finish();
        }
    }
}
