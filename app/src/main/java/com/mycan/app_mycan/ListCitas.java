package com.mycan.app_mycan;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.ListaExpansibleAdapter;
import database.AdaptadorDBCitas;
import database.AdaptadorDBMascotas;


public class ListCitas extends ActionBarActivity {
    private ExpandableListView listaCitas;
    private ListaExpansibleAdapter mAdapter;
    private AdaptadorDBCitas adbCitas;
    private AdaptadorDBMascotas adbMascotas;
    private ArrayList<String> fechas;
    private ArrayList<ArrayList<ArrayList<String>>> citas;
    private Intent itt;
    private String id_mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_citas);

        listaCitas = (ExpandableListView) findViewById(R.id.listCitas);
        itt = getIntent();
        id_mascota = itt.getStringExtra("id_mascota");

        cargarLista(id_mascota);


        mAdapter = new ListaExpansibleAdapter(this, fechas, citas);
        listaCitas.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_citas, menu);
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

    private void cargarLista(String id_mascota) {
        Cursor csr;
        adbCitas = new AdaptadorDBCitas(this);
        try {
            adbCitas.abrirConexion();
            fechas = adbCitas.getFechasLE(id_mascota);

            citas = new ArrayList<ArrayList<ArrayList<String>>>();
            for (int i = 0; i < fechas.size(); i++) {
                csr = adbCitas.getDatosCita(fechas.get(i), id_mascota);
                citas.add(new ArrayList<ArrayList<String>>());
                citas.get(i).add(new ArrayList<String>());
                citas.get(i).get(0).add(csr.getString(
                        csr.getColumnIndex(adbCitas.getCampoHora())));
                citas.get(i).get(0).add(csr.getString(
                        csr.getColumnIndex(adbCitas.getCampoPrecio())));
                citas.get(i).get(0).add(csr.getString(
                        csr.getColumnIndex(adbCitas.getCampoDescripcion())));
            }

            adbCitas.cerrarConexion();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
