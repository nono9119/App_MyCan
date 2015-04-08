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
import java.util.HashMap;
import java.util.List;

import clases.ListaExpansibleAdapter;
import database.AdaptadorDBCitas;
import database.AdaptadorDBMascotas;


public class ListCitas extends ActionBarActivity {
    private ExpandableListView listaCitas;
    private ListaExpansibleAdapter mAdapter;
    private AdaptadorDBCitas adbCitas;
    private HashMap<String, List<String>> lista_items;
    private List<String> lista_encabezados;
    private List<String> lista_datos;
    private Intent itt;
    private String id_mascota, nombre;
    private Cursor csr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_citas);

        listaCitas = (ExpandableListView) findViewById(R.id.listCitas);
        itt = getIntent();
        id_mascota = itt.getStringExtra("id_mascota");
        nombre = itt.getStringExtra("nombre");
        this.setTitle(nombre);

        cargarLista(id_mascota);


        mAdapter = new ListaExpansibleAdapter(this, lista_encabezados, lista_items);
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

    public void cargarLista(String id_mascota) {
        lista_items = new HashMap<String, List<String>>();
        adbCitas = new AdaptadorDBCitas(this);
        try {
            adbCitas.abrirConexion();

            lista_encabezados = adbCitas.getFechasLE(id_mascota);

            for (int i = 0; i < lista_encabezados.size(); i++) {
                lista_datos = adbCitas.getDatosCita(lista_encabezados.get(i), id_mascota);
                lista_items.put(lista_encabezados.get(i), lista_datos);
                lista_datos = null;
            }
            adbCitas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
