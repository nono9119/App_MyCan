package com.mycan.app_mycan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

import clases.AdaptadorCursorMascotas;
import database.AdaptadorDBMascotas;


public class ListActivity extends ActionBarActivity {
    private ListView listaMascotas;
    private Cursor csr;
    private AdaptadorDBMascotas adbMascotas;
    private AdaptadorCursorMascotas acMascotas;
    private Context ctx;
    private Intent itt;
    private String texto_lista, nombre, raza, id_mascota;
    private int numCitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        cargarLista();
    }
    // onResume PARA ACTUALIZAR LA LISTA
    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent itt;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.menu_insertarMascota) {
            itt = new Intent(ctx, InsertarMascota.class);
            startActivity(itt);
        } else if (id == R.id.menu_insertarCita) {
            itt = new Intent(ctx, InsertarCita.class);
            startActivity(itt);
        }

        return super.onOptionsItemSelected(item);
    }

    // METODOS PROPIOS
    private void cargarLista() {
        // Capturo la lista y establezco el contexto
        listaMascotas = (ListView) findViewById(R.id.listPets);
        ctx = this;

        // Abro la conexion con la base de datos
        try {
            adbMascotas = new AdaptadorDBMascotas(ctx);
            adbMascotas.abrirConexion();
            // Creo el cursor con todas las mascotas y cierro la conexion
            csr = adbMascotas.getMascotas();
            // Creo el adaptador para el cursor
            acMascotas = new AdaptadorCursorMascotas(this, csr);
            // Asigno el adaptador a la lista
            listaMascotas.setAdapter(acMascotas);
            listaMascotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    texto_lista = csr.getString(csr.getColumnIndex(adbMascotas.CAMPO_NOMBRE))
                            + " " + csr.getString(csr.getColumnIndex(adbMascotas.CAMPO_RAZA));
                    /*
                    if (texto_lista.length() != 0) {
                    }
                    */

                        nombre = csr.getString(csr.getColumnIndex(adbMascotas.CAMPO_NOMBRE));
                        raza = csr.getString(csr.getColumnIndex(adbMascotas.CAMPO_RAZA));
                        try {
                            adbMascotas.abrirConexion();
                            id_mascota = String.valueOf(adbMascotas.getIdMascota(nombre, raza));
                            numCitas = adbMascotas.hayCitas(id_mascota);
                            adbMascotas.cerrarConexion();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        // COMPRUEBO QUE LA MASCOTA TIENE CITAS PARA QUE LA APP NO FALLE
                        if (numCitas != 0) {
                            itt = new Intent(ctx, ListCitas.class);
                            itt.putExtra("id_mascota", id_mascota);
                            itt.putExtra("nombre", nombre);
                            startActivity(itt);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    R.string.noCitas, Toast.LENGTH_SHORT).show();
                        }

                }

            });
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (adbMascotas != null) {
                    adbMascotas.cerrarConexion();
                }
                acMascotas = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
