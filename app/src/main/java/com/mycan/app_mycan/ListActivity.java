package com.mycan.app_mycan;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.sql.SQLException;
import clases.AdaptadorCursorMascotas;
import bbdd.AdaptadorDBMascotas;

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

        // DEJO EL ICONO TAL CUAL, PORQUE QUIERO QUE ESTA SEA LA ACTIVIDAD HOME
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        // Capturo la lista y establezco el contexto
        listaMascotas = (ListView) findViewById(R.id.listPets);
        ctx = this;

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
        if (id == R.id.menu_buscarMascota) {
            crearDialogo();
        } else if (id == R.id.menu_insertarMascota) {
            modo = "insertar";
            itt = new Intent(ctx, InsertarMascota.class);
            itt.putExtra("modo", modo);
            startActivity(itt);
        } else if (id == R.id.menu_insertarCita) {
            modo = "insertar";
            itt = new Intent(ctx, InsertarCita.class);
            itt.putExtra("modo", modo);
            itt.putExtra("id_mascota", String.valueOf(0));
            startActivity(itt);
        } else if (id == R.id.menu_recargar) {
            cargarLista();
        }

        return super.onOptionsItemSelected(item);
    }

    // METODOS PROPIOS
    private void cargarLista() {
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
                    // INICIAR LA OTRA ACTIVIDAD MANDANDO EL NUMERO DE CITAS
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
    // DIALOGO PARA BUSCAR MASCOTAS
    private void crearDialogo() {
        // CREO EL EDITEXT DEL CUAL OBTENDRE EL NOMBRE DE LA MASCOTA
        final EditText edt = new EditText(this);
        edt.setHint(R.string.buscarMascotaEDT);
        edt.setSingleLine(true);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
        builder1.setCancelable(true);
        //builder1.setTitle(R.string.buscarMascota);
        builder1.setView(edt);
        builder1.setPositiveButton(R.string.dialogoBuscar,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        adbMascotas = new AdaptadorDBMascotas(ctx);
                        try {
                            adbMascotas.abrirConexion();
                            csr = adbMascotas.getBuscar(edt.getText().toString());
                            adbMascotas.cerrarConexion();
                        } catch (SQLException e) {
                            Toast.makeText(ctx, R.string.errorBuscar,
                                Toast.LENGTH_SHORT).show();
                        } finally {
                            listaBuscar();
                        }
                    }
                });
        builder1.setNegativeButton(R.string.dialogoCancelar,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void listaBuscar() {
        try {
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
                    // INICIAR LA OTRA ACTIVIDAD MANDANDO EL NUMERO DE CITAS
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
