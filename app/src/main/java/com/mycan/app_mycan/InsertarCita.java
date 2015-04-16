package com.mycan.app_mycan;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import clases.Mascota;
import database.AdaptadorDBCitas;
import database.AdaptadorDBMascotas;


public class InsertarCita extends ActionBarActivity {
    // NECESARIOS PARA EL SPINNER
    private Spinner spMascotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList mascotaListCitas;
    private ArrayAdapter<Mascota> spAdapterMascotas;
    private AdaptadorDBMascotas adbMascotas;
    private AdaptadorDBCitas adbCitas;
    private Intent itt;
    private Context ctx;
    private Cursor csr;
    private ContentValues cv;
    private int id_mascota;
    private EditText etFecha, etPrecio, etHora, etDescripcion;
    private String texto_sp, nombre, raza, fecha, hora, precio, descripcion, modo;
    private boolean flag, flagFecha, flagHora, flagPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_cita);

        etFecha = (EditText) findViewById(R.id.etFecha);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etHora = (EditText) findViewById(R.id.etHora);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        spMascotas = (Spinner) findViewById(R.id.spMascotas);
        ctx = this;
        itt = getIntent();
        modo = itt.getStringExtra("modo");

        if (modo.equalsIgnoreCase("modificar")) {
            id_mascota = Integer.parseInt(itt.getStringExtra("id_mascota"));
            nombre = itt.getStringExtra("nombre");
            raza = itt.getStringExtra("raza");
            fecha = itt.getStringExtra("fecha");
            mascotaListCitas = new ArrayList<>();
            mascotaListCitas.add(nombre + " ("+ raza + ")");
            spAdapterMascotas = new ArrayAdapter<Mascota>(ctx,
                    android.R.layout.simple_spinner_item, mascotaListCitas);
            spAdapterMascotas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMascotas.setAdapter(spAdapterMascotas);
            spMascotas.setClickable(false);
            establecerDatos();
        } else {
            // OBTENGO LAS MASCOTAS PARA EL SPINNER
            adbMascotas = new AdaptadorDBMascotas(ctx);
            try {
                adbMascotas.abrirConexion();
                mascotas = adbMascotas.getMascotasSP();
                adbMascotas.cerrarConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // INSERTAR MASCOTAS EN EL SPINNER
            if (mascotas != null) {
                spAdapterMascotas = new ArrayAdapter<Mascota>(ctx,
                        android.R.layout.simple_spinner_item, mascotas);
                spAdapterMascotas.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spMascotas.setAdapter(spAdapterMascotas);
                spMascotas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // FILTRO EL STRING PARA OBTENER NOMBRE Y RAZA
                        texto_sp = parent.getSelectedItem().toString();
                        nombre = texto_sp.substring(0, texto_sp.lastIndexOf("("));
                        raza = texto_sp.substring(texto_sp.lastIndexOf("(") + 1,
                                texto_sp.length() - 1);
                        try {
                            adbMascotas.abrirConexion();
                            id_mascota = adbMascotas.getIdMascota(nombre, raza);
                            adbMascotas.cerrarConexion();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
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

    public void establecerDatos() {
        adbCitas = new AdaptadorDBCitas(ctx);
        try {
            adbCitas.abrirConexion();
            csr = adbCitas.datosCita(id_mascota, fecha);
            adbCitas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        etFecha.setText(fecha);
        etHora.setText(csr.getString(csr.getColumnIndex(adbCitas.getCampoHora())));
        etPrecio.setText(csr.getString(csr.getColumnIndex(adbCitas.getCampoPrecio())));
        etDescripcion.setText(csr.getString(csr.getColumnIndex(adbCitas.getCampoDescripcion())));
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btGuardarCita) {
            insertarCita();
        } else if (v.getId() == R.id.btVolverCita) {
            finish();
        }
    }

    public void insertarCita() {
        flag = false;
        flagFecha = false;
        flagHora = false;
        flagPrecio = false;

        // CONTROLO QUE NO QUEDE VACIA LA FECHA
        if (etFecha.getText().toString().length() == 10
                && etFecha.getText().toString().contains("/")) {
            fecha = etFecha.getText().toString();
            flagFecha = true;
            // CONTROLO QUE NO QUEDE VACIA LA HORA
            if (etHora.getText().toString().length() == 5
                    && etHora.getText().toString().contains(":")) {
                hora = etHora.getText().toString();
                flagHora = true;
                // CONTROLO QUE NO QUEDE VACIO EL PRECIO
                if (etPrecio.getText().toString().length() != 0) {
                    precio = etPrecio.getText().toString();
                    flagPrecio = true;
                    // COMPRUEBO QUE NO HAYA ERRORES
                    if (flagFecha && flagHora && flagPrecio) {
                        if (etDescripcion.getText().toString().length() != 0) {
                            descripcion = etDescripcion.getText().toString();
                        } else {
                            descripcion = "No introducida";
                        }

                        cv = new ContentValues();
                        cv.put("fecha", fecha);
                        cv.put("hora", hora);
                        cv.put("precio", precio);
                        cv.put("descripcion", descripcion);
                        cv.put("id_mascota", id_mascota);

                        adbCitas = new AdaptadorDBCitas(this);
                        // INSERTO LA CITA (TRUE -> OK | FALSE -> ERROR AL INSERTAR)
                        try {
                            adbCitas.abrirConexion();
                            flag = adbCitas.insertarCita(cv);
                            adbCitas.cerrarConexion();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        // COMPRUEBO QUE LA INSERCION HA SIDO CORRECTA
                        if (flag) {
                            Toast.makeText(this, R.string.citaOK,
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(this, R.string.falloCita,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(this, R.string.precioVacio,
                            Toast.LENGTH_SHORT).show();
                }
            } else if ((etHora.getText().toString().length() < 5
                    && etHora.getText().toString().length() > 0)
                    || !etHora.getText().toString().contains(":")) {
                Toast.makeText(this, R.string.horaIncorrecta,
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.horaVacia,
                        Toast.LENGTH_SHORT).show();
            }
        } else if (((etFecha.getText().toString().length() < 10 ||
                etFecha.getText().toString().length() > 10)
                && etFecha.getText().toString().length() > 0)
                || !etFecha.getText().toString().contains("/")) {
                /*
                * SI LA FECHA ES MENOR DE 10 CARACTERES O MAYOR DE 10,
                * Y ES MAYOR DE 0 O NO CONTIENE BARRA MUESTRO EL TOAST
                */
            Toast.makeText(this, R.string.fechaIncorrecta,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.fechaVacia,
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarCita() {

    }
    // FUNCION PARA COMPROBAR SI SE HA INTRODUCIDO CORRECTAMENTE LA FECHA
    private boolean comprobarFecha(String fch) {
        SimpleDateFormat formatoFecha = null;
        boolean flagComprobacion = false;

        try {
            // CONVIERTO LA FECHA AL FORMATO SIMPLE
            formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fch);
        } catch (ParseException e) {
            // SI FALLA LA CONVERSION ES PORQUE NO SE HA INTRODUCIDO CORRECTAMENTE LA FECHA
           flagComprobacion = false;
        }
        flagComprobacion = true;

        return flagComprobacion;
    }
    // FUNCION PARA COMPROBAR SI SE HA INTRODUCIDO CORRECTAMENTE LA HORA
    private boolean comproarHora(String hr) {
        SimpleDateFormat formatoFecha = null;
        boolean flagComprobacion = false;

        try {
            // CONVIERTO LA FECHA AL FORMATO SIMPLE
            formatoFecha = new SimpleDateFormat("HH:mm:ss");
            formatoFecha.setLenient(false);
            formatoFecha.parse(hr);
        } catch (ParseException e) {
            // SI FALLA LA CONVERSION ES PORQUE NO SE HA INTRODUCIDO CORRECTAMENTE LA FECHA
            flagComprobacion = false;
        }
        flagComprobacion = true;

        return flagComprobacion;
    }

}
