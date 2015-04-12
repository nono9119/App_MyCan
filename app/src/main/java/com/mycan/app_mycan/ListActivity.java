package com.mycan.app_mycan;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
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
    private String modo, nombre, raza, id_mascota;
    private int numCitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        cargarLista();
        registerForContextMenu(listaMascotas);
    }

    // onResume PARA ACTUALIZAR LA LISTA
    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cargarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.listPets) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
            menu.setHeaderTitle(R.string.tituloMenu);
            String[] menuItems = getResources().getStringArray(R.array.menuListaMascotas);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch(item.getItemId()) {
            case 0:
                modo = "modificar";
                itt = new Intent(ctx, InsertarMascota.class);
                itt.putExtra("modo", modo);
                startActivity(itt);
                break;
            case 1:
                try {
                    cargarLista();
                    adbMascotas = new AdaptadorDBMascotas(this);
                    adbMascotas.abrirConexion();
                    adbMascotas.borrarMascota(id_mascota);
                    adbMascotas.cerrarConexion();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
        return true;
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
            modo = "insertar";
            itt = new Intent(ctx, InsertarMascota.class);
            itt.putExtra("modo", modo);
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
                    nombre = csr.getString(csr.getColumnIndex(adbMascotas.getCampoNombre()));
                    raza = csr.getString(csr.getColumnIndex(adbMascotas.getCampoRaza()));
                    try {
                        adbMascotas.abrirConexion();
                        id_mascota = String.valueOf(adbMascotas.getIdMascota(nombre, raza));
                        numCitas = adbMascotas.hayCitas(id_mascota);
                        adbMascotas.cerrarConexion();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    // INICIAR LA OTRA ACTIVIDAD MANDANDO EL NUMERO DE LISTAS
                    // COMPROBAR ALLI EL NUMERO DE CITAS PARA CARGAR LA LISTA
                    itt = new Intent(ctx, ListCitas.class);
                    itt.putExtra("id_mascota", id_mascota);
                    itt.putExtra("nombre", nombre);
                    itt.putExtra("raza", raza);
                    itt.putExtra("numCitas", String.valueOf(numCitas));
                    startActivity(itt);
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
