package com.mycan.app_mycan;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
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
    private AdaptadorDBMascotas adbMascotas;
    private HashMap<String, List<String>> lista_items;
    private List<String> lista_encabezados;
    private List<String> lista_datos;
    private Intent itt;
    private Cursor csr;
    private String id_mascota, nombre, raza, propietario, telefono;
    private EditText etNombre, etRaza, etPropietario, etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_citas);
        // CAPTURO LOS ELEMENTOS
        listaCitas = (ExpandableListView) findViewById(R.id.listCitas);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etRaza = (EditText) findViewById(R.id.etRaza);
        etPropietario = (EditText) findViewById(R.id.etPropietario);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        itt = getIntent();
        id_mascota = itt.getStringExtra("id_mascota");
        nombre = itt.getStringExtra("nombre");
        raza = itt.getStringExtra("raza");
        // ESTABLEZCO EL NOMBRE DE LA MASCOTA COMO EL TITULO DE LA ACTIVIDAD
        this.setTitle(nombre);
        // CARGO LA LISTA EXPANSIBLE EN BASE A LA MASCOTA
        cargarLista();
        cargarDatosMascota();
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

    private void cargarDatosMascota() {
        // ESCRIBO EL NOMBRE Y LA RAZA DE LA MASCOTA QUE VIENEN DE LA ACTIVIDAD ANTERIOR
        etNombre.setText(nombre);
        etRaza.setText(raza);
        adbMascotas = new AdaptadorDBMascotas(this);
        try {
            adbMascotas.abrirConexion();
            csr = adbMascotas.getMascota(Integer.parseInt(id_mascota));
            adbMascotas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // OBTENGO LOS DATOS QUE NECESITO
        propietario = csr.getString(csr.getColumnIndex(adbMascotas.getCampoPropietario()));
        telefono = csr.getString(csr.getColumnIndex(adbMascotas.getCampoTelefono()));
        // LOS MUESTRO EN EL ET CORRESPONDIENTE
        etPropietario.setText(propietario);
        etTelefono.setText(telefono);

    }

    private void cargarLista() {
        // LISTA DE ITEMS QUE CONTENDRA LOS GRUPOS (ENCABEZADOS) E HIJOS (DATOS)
        lista_items = new HashMap<String, List<String>>();
        // CONECTOR A LA BASE DE DATOS
        adbCitas = new AdaptadorDBCitas(this);
        try {
            adbCitas.abrirConexion();
            // CARGO LAS FECHAS EN EL ARRAY DE GRUPOS (ENCABEZADOS)
            lista_encabezados = adbCitas.getFechasLE(id_mascota);
            // RECORRO EL ARRAY DE FECHAS PARA EXTRAER LOS DATOS RELACIONADOS
            for (int i = 0; i < lista_encabezados.size(); i++) {
                /*
                * EN ESTE CASO EL ARRAY SOLO TENDRA UNA DIMENSION DE 1 PARA EVITAR QUE GENERE
                * MAS DE UN HIJO, ES DECIR, QUE CREE TANTOS HIJOS COMO POSICIONES HAY EN EL
                * ARRAY, DE ESTE MODO SOLO MOSTRARA UNO CON TODOS SUS DATOS
                */
                lista_datos = adbCitas.getDatosCita(lista_encabezados.get(i), id_mascota);
                // AGREGO AL ARRAY DE ITEMS LOS GRUPOS E HIJOS
                lista_items.put(lista_encabezados.get(i), lista_datos);
                /*
                 PONGO BORRO EL ARRAY CON LOS DATOS DE LAS CITAS PARA USARLO DE
                 NUEVO SI FUERA NECESARIO
                 */
                lista_datos = null;
            }
            adbCitas.cerrarConexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // CREO EL ADAPTADOR CON LA LISTA DE GRUPOS Y DATOS
        mAdapter = new ListaExpansibleAdapter(this, lista_encabezados, lista_items);
        // ESTABLEZCO EL ADAPTADOR
        listaCitas.setAdapter(mAdapter);
        //Drawable d = getResources().getDrawable(R.drawable.lista_icono);
        //listaCitas.setGroupIndicator(d);
        // ESTABLEZCO EL COLOR Y LA ANCHURA DEL SEPARADOR
        //listaCitas.setDivider(new ColorDrawable(Color.WHITE));
        //listaCitas.setDividerHeight(1);
    }
}
