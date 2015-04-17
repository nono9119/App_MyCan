package com.mycan.app_mycan;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import bbdd.AdaptadorDBCitas;
import bbdd.AdaptadorDBMascotas;


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
    private String texto_sp, id_cita, nombre, raza, fecha, hora, precio, descripcion, modo;

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

        if (modo.equalsIgnoreCase("modificar") || modo.equalsIgnoreCase("menulistcitas")) {
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
            if (modo.equalsIgnoreCase("modificar")) { establecerDatos(); }
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

    public void establecerDatos() {
        adbCitas = new AdaptadorDBCitas(ctx);
        try {
            adbCitas.abrirConexion();
            csr = adbCitas.datosCita(id_mascota, fecha);
            adbCitas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id_cita = csr.getString(csr.getColumnIndex(adbCitas.getCampoID()));
        etFecha.setText(fecha);
        etHora.setText(csr.getString(csr.getColumnIndex(adbCitas.getCampoHora())));
        etPrecio.setText(csr.getString(csr.getColumnIndex(adbCitas.getCampoPrecio())));
        etDescripcion.setText(csr.getString(csr.getColumnIndex(adbCitas.getCampoDescripcion())));
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btGuardarCita) {
            if (modo.equalsIgnoreCase("insertar") || modo.equalsIgnoreCase("menulistcitas")) {
                insertarCita();
            } else if (modo.equalsIgnoreCase("modificar")) {
                modificarCita();
            }
        } else if (v.getId() == R.id.btVolverCita) {
            finish();
        }
    }

    public void insertarCita() {
        boolean flag = false;

        // COMPRUEBO QUE LA FECHA ES CORRECTA
        if (comprobarFecha(etFecha.getText().toString())) {
            fecha = etFecha.getText().toString();
            // COMPRUEBO LA HORA
            if (comprobarHora(etHora.getText().toString())) {
                hora = etHora.getText().toString();
                // COMPRUEBO QUE EL PRECIO NO ESTE VACIO
                if (etPrecio.getText().toString().length() != 0) {
                    precio = etPrecio.getText().toString();
                    // COMPRUEBO SI HAY ALGO EN DESCRIPCION,
                    // EN CASO DE QUE NO HAYA NADA LE PONGO UN VALOR
                    if (etDescripcion.getText().toString().length() != 0) {
                        descripcion = etDescripcion.getText().toString();
                    } else {
                        descripcion = "No introducida";
                    }
                    // PREPARO LA INSERCION
                    adbCitas = new AdaptadorDBCitas(ctx);
                    cv = new ContentValues();
                    cv.put("fecha", fecha);
                    cv.put("hora", hora);
                    cv.put("precio", precio);
                    cv.put("descripcion", descripcion);
                    cv.put("id_mascota", id_mascota);
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
                } else {
                    Toast.makeText(this, R.string.precioVacio,
                        Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.horaIncorrecta,
                    Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.fechaIncorrecta,
                Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarCita() {
        boolean flag = false;

        // COMPRUEBO QUE LA FECHA ES CORRECTA
        if (comprobarFecha(etFecha.getText().toString())) {
            fecha = etFecha.getText().toString();
            // COMPRUEBO LA HORA
            if (comprobarHora(etHora.getText().toString())) {
                hora = etHora.getText().toString();
                // COMPRUEBO QUE EL PRECIO NO ESTE VACIO
                if (etPrecio.getText().toString().length() != 0) {
                    precio = etPrecio.getText().toString();
                    // COMPRUEBO SI HAY ALGO EN DESCRIPCION,
                    // EN CASO DE QUE NO HAYA NADA LE PONGO UN VALOR
                    if (etDescripcion.getText().toString().length() != 0) {
                        descripcion = etDescripcion.getText().toString();
                    } else {
                        descripcion = "No introducida";
                    }

                    // PREPARO LA INSERCION
                    adbCitas = new AdaptadorDBCitas(ctx);
                    cv = new ContentValues();
                    cv.put("fecha", fecha);
                    cv.put("hora", hora);
                    cv.put("precio", precio);
                    cv.put("descripcion", descripcion);
                    cv.put("id_mascota", id_mascota);

                    // INSERTO LA CITA (TRUE -> OK | FALSE -> ERROR AL INSERTAR)
                    try {
                        adbCitas.abrirConexion();
                        adbCitas.modificarCita(id_cita, cv);
                        adbCitas.cerrarConexion();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        finish();
                    }
                } else {
                    Toast.makeText(this, R.string.precioVacio,
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, R.string.horaIncorrecta,
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.fechaIncorrecta,
                    Toast.LENGTH_SHORT).show();
        }
    }

    // FUNCION PARA COMPROBAR SI SE HA INTRODUCIDO CORRECTAMENTE LA FECHA
    private boolean comprobarFecha(String fch) {
        SimpleDateFormat formatoFecha = null;
        boolean flagFecha = true;

        try {
            // CONVIERTO LA FECHA AL FORMATO SIMPLE
            formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(fch);
        } catch (ParseException e) {
            // SI FALLA LA CONVERSION ES PORQUE NO SE HA INTRODUCIDO CORRECTAMENTE LA FECHA
           flagFecha = false;
        }

        return flagFecha;
    }

    // FUNCION PARA COMPROBAR SI SE HA INTRODUCIDO CORRECTAMENTE LA HORA
    private boolean comprobarHora(String hr) {
        SimpleDateFormat formatoHora = null;
        boolean flagHora = true;

        try {
            // CONVIERTO LA FECHA AL FORMATO SIMPLE
            formatoHora = new SimpleDateFormat("HH:mm", Locale.getDefault());
            formatoHora.parse(hr);
        } catch (ParseException e) {
            // SI FALLA LA CONVERSION ES PORQUE NO SE HA INTRODUCIDO CORRECTAMENTE LA FECHA
            flagHora = false;
        }

        return flagHora;
    }
}
