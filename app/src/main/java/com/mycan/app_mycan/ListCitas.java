package com.mycan.app_mycan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import clases.ListaExpansibleAdapter;
import bbdd.AdaptadorDBCitas;
import bbdd.AdaptadorDBMascotas;


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
    private Context ctx;
    private String id_mascota, nombre, raza, propietario, telefono, numCitas, modo, eliminar,
            fechaGrupoLista;
    private EditText etNombre, etRaza, etPropietario, etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_citas);

        // Icono como boton home
        getSupportActionBar().setHomeAsUpIndicator(getResources().getDrawable(R.drawable.ic_launcher));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // CAPTURO LOS ELEMENTOS
        ctx = this;
        listaCitas = (ExpandableListView) findViewById(R.id.listCitas);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etRaza = (EditText) findViewById(R.id.etRaza);
        etPropietario = (EditText) findViewById(R.id.etPropietario);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        // OBTENGO LOS DATOS DE LA OTRA ACTIVIDAD
        itt = getIntent();
        id_mascota = itt.getStringExtra("id_mascota");
        nombre = itt.getStringExtra("nombre");
        raza = itt.getStringExtra("raza");
        numCitas = itt.getStringExtra("numCitas");
        // ESTABLEZCO EL NOMBRE DE LA MASCOTA COMO EL TITULO DE LA ACTIVIDAD
        this.setTitle(nombre);
        // CARGO LA LISTA EXPANSIBLE EN BASE A LA MASCOTA
        if (Integer.parseInt(numCitas) != 0) { cargarLista(); }
        cargarDatosMascota();
        registerForContextMenu(listaCitas);
    }

    // onResume PARA ACTUALIZAR LA LISTA
    @Override
    protected void onResume() {
        super.onResume();
        if (Integer.parseInt(numCitas) != 0) { cargarLista(); }
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
        switch (item.getItemId()) {
            case R.id.menuCitas_modificarMascota:
                modo = "modificar";
                itt = new Intent(ctx, InsertarMascota.class);
                itt.putExtra("modo", modo);
                itt.putExtra("id_mascota", id_mascota);
                itt.putExtra("nombre", nombre);
                itt.putExtra("raza", raza);
                itt.putExtra("propietario", propietario);
                itt.putExtra("telefono", telefono);
                startActivity(itt);
                break;
            case R.id.menuCitas_insertarCita:
                modo = "menulistcitas";
                itt = new Intent(ctx, InsertarCita.class);
                itt.putExtra("modo", modo);
                itt.putExtra("id_mascota", id_mascota);
                itt.putExtra("nombre", nombre);
                itt.putExtra("raza", raza);
                startActivity(itt);
                break;
            case R.id.menuCitas_eliminarMascota:
                eliminar = "mascota";
                crearDialogo();
                break;
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        int group = ExpandableListView.getPackedPositionGroup(info.packedPosition);
        int child = ExpandableListView.getPackedPositionChild(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            menu.setHeaderTitle(R.string.tituloMenu);
            String[] menuItems = getResources().getStringArray(R.array.menuLista);

            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info =
                (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        int groupPos = 0;
        int childPos = 0;
        int type = ExpandableListView.getPackedPositionType(info.packedPosition);
        if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
            groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);
            childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);
        }
        fechaGrupoLista = lista_encabezados.get(groupPos);

        switch (item.getItemId()) {
            case 0:
                modo = "modificar";
                itt = new Intent(ctx, InsertarCita.class);
                itt.putExtra("modo", modo);
                itt.putExtra("id_mascota", id_mascota);
                itt.putExtra("nombre", nombre);
                itt.putExtra("raza", raza);
                itt.putExtra("fecha", fechaGrupoLista);
                startActivity(itt);
                break;
            case 1:
                eliminar = "cita";
                crearDialogo();
                break;
        }
        return true;
    }

    // CARGAR DATOS DE LA MASCOTA
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
    // CARGAR LA LISTA EXPANSIBLE
    private void cargarLista() {
        // LISTA DE ITEMS QUE CONTENDRA LOS GRUPOS (ENCABEZADOS) E HIJOS (DATOS)
        lista_items = new HashMap<String, List<String>>();
        // CONECTOR A LA BASE DE DATOS
        adbCitas = new AdaptadorDBCitas(this);
        try {
            adbCitas.abrirConexion();
            // CARGO LAS FECHAS EN EL ARRAY DE GRUPOS (ENCABEZADOS)
            lista_encabezados = adbCitas.getFechasLE(id_mascota);
            lista_encabezados = ordenarFecha(lista_encabezados);
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
    }

    private void crearDialogo() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ctx);
        builder1.setCancelable(true);
        // ESTABLEZCO LA PREGUNTA DEPENDIENDO DESDE DONDE SE EJECUTE EL DIALOGO
        if (eliminar.equalsIgnoreCase("mascota")) {
            builder1.setMessage(R.string.dialogoPreguntaMascota);
        } else if (eliminar.equalsIgnoreCase("cita")) {
            builder1.setMessage(R.string.dialogoPreguntaCita);
        }
        builder1.setPositiveButton(R.string.dialogoBorrar,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // DEPENDIENDO DESDE DONDE SE EJECUTE EL DIALOGO EJECUTO UNA ACCION U OTRA
                        if (eliminar.equalsIgnoreCase("mascota")) {
                            adbMascotas = new AdaptadorDBMascotas(ctx);
                            try {
                                adbMascotas.abrirConexion();
                                adbMascotas.borrarMascota(id_mascota);
                                adbMascotas.cerrarConexion();
                            } catch (SQLException e) {
                                Toast.makeText(ctx, R.string.errorMenuBorrar,
                                        Toast.LENGTH_SHORT).show();
                            } finally {
                                finish();
                            }
                        } else if (eliminar.equalsIgnoreCase("cita")) {
                            adbCitas = new AdaptadorDBCitas(ctx);
                            try {
                                adbCitas.abrirConexion();
                                adbCitas.borrarCita(id_mascota, fechaGrupoLista);
                                adbCitas.cerrarConexion();
                            } catch (SQLException e) {
                                Toast.makeText(ctx, R.string.errorMenuBorrar,
                                        Toast.LENGTH_SHORT).show();
                            } finally {
                                cargarLista();
                            }
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
    // FUNCION PARA ORDENAR LAS FECHAS
    public List<String> ordenarFecha(List<String> fechas) {
        Collections.sort(fechas, new Comparator<String>() {
            @Override
            public int compare(String arg0, String arg1) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                int compareResult = 0;
                try {
                    Date arg0Date = format.parse(arg0);
                    Date arg1Date = format.parse(arg1);
                    compareResult = arg0Date.compareTo(arg1Date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    compareResult = arg0.compareTo(arg1);
                }
                return compareResult;
            }
        });
        return fechas;

    }
}
