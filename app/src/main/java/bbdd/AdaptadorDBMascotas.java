package bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.mycan.app_mycan.R;

import java.sql.SQLException;
import java.util.ArrayList;

import clases.Mascota;

/**
 * Created by Nono on 28/03/2015.
 */
public class AdaptadorDBMascotas {
    private final String TABLA = "mascotas";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_NOMBRE = "nombre";
    private final String CAMPO_RAZA = "raza";
    private final String CAMPO_TELEFONO = "telefono";
    private final String CAMPO_PROPIETARIO = "propietario";
    private String columnas[] = { CAMPO_ID, CAMPO_NOMBRE, CAMPO_RAZA, CAMPO_TELEFONO,
            CAMPO_PROPIETARIO };
    private Context ctx;
    private SQLiteDB sqliteDB;
    private SQLiteDatabase db;

    public AdaptadorDBMascotas (Context ctx) { this.ctx = ctx; }

    //////////////////////////////////////////////////////
    //// GETTERS PARA ACCEDER A LOS ATRIBUTOS DE LA CLASE
    //////////////////////////////////////////////////////
    public String getTabla() { return this.TABLA; }
    public String getCampoId() { return this.CAMPO_ID; }
    public String getCampoNombre() { return this.CAMPO_NOMBRE; }
    public String getCampoRaza() { return this.CAMPO_RAZA; }
    public String getCampoTelefono() { return this.CAMPO_TELEFONO; }
    public String getCampoPropietario() { return this.CAMPO_PROPIETARIO; }

    // Establecer la conexion con la base de datos
    public AdaptadorDBMascotas abrirConexion() throws SQLException {
        sqliteDB = new SQLiteDB(ctx);
        db = sqliteDB.getWritableDatabase();
        return this;
    }
    // CERRAR CONEXION CON LA BASE DE DATOS
    public void cerrarConexion() throws SQLException { sqliteDB.close(); }
    // OBTENER TODOS LOS DATOS DE LAS MASCOTAS
    public Cursor getMascotas() throws SQLException {
        Cursor csr = db.rawQuery("SELECT * FROM mascotas ORDER BY nombre", null);
        if (csr != null) { csr.moveToFirst(); }
        return csr;
    }
    // OBTENER LAS MASCOTAS BUSCADAS
    public Cursor getBuscar(String nombre) throws SQLException {
        nombre = "%" + nombre + "%";
        Cursor csr = db.rawQuery("SELECT * FROM mascotas WHERE nombre LIKE '" + nombre
                + "' ORDER BY nombre", null);
        if (csr != null) { csr.moveToFirst(); }
        return csr;
    }
    // OBTENER UNA MASCOTA POR ID
    public Cursor getMascota(int id) throws SQLException {
        Cursor csr = db.rawQuery("SELECT * FROM mascotas WHERE _id = " + id, null);
        if (csr != null) { csr.moveToFirst(); }
        return csr;
    }
    // OBTENER ID DE MASCOTA
    public int getIdMascota(String nombre, String raza) throws SQLException {
        int id_mascota = 0;
        Cursor csr = db.rawQuery("SELECT _id FROM mascotas WHERE nombre LIKE '" + nombre + "' AND" +
                " raza LIKE '" + raza + "'", null);
        if (csr != null) {
            csr.moveToFirst();
            id_mascota = csr.getInt(csr.getColumnIndex(this.CAMPO_ID));
        }
        return id_mascota;
    }
    // OBTENER MASCOTAS PARA EL SPINNER
    public ArrayList<Mascota> getMascotasSP() {
        Cursor csr = db.rawQuery("SELECT * FROM mascotas ORDER BY nombre", null);
        ArrayList<Mascota> mascotas = null;
        Mascota mascota;

        if (csr != null) {
            csr.moveToFirst();
            // PREPARO Y CREO LOS OBJETOS SOLO SI EL CURSOR SE HA CREADO CORRECTAMENTE
            mascotas = new ArrayList<Mascota>();

            // RECORRO EL CURSOR Y OBTENGO LOS DATOS
            do {
                mascota = new Mascota(csr.getInt(csr.getColumnIndex(this.CAMPO_ID)),
                        csr.getString(csr.getColumnIndex(this.CAMPO_NOMBRE)),
                        csr.getString(csr.getColumnIndex(this.CAMPO_RAZA)));
                mascotas.add(mascota);
            } while (csr.moveToNext());
        }
        return mascotas;
    }

    // COMPROBAR SI LA MASCOTA TIENE CITAS
    public int hayCitas(String id_mascota) {
        int numCitas;
        String select =
                "SELECT COUNT(_id) FROM citas WHERE id_mascota = " + Integer.parseInt(id_mascota);
        numCitas = (int)(DatabaseUtils.longForQuery(db, select, null));

        return numCitas;
    }
    // INSERTAR MASCOTA
    public boolean insertarMascota(ContentValues cv) {
        if (db == null) {
            try {
                abrirConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (db.insert("mascotas", null, cv) != -1 )?true:false;
    }

    // MODIFICAR MASCOTA
    public void modificarMascota(ContentValues cv, int id_mascota) throws SQLException {
        int numFilasAfectadas;
        String where = "_id=?";
        String []whereArgs = new String[] { String.valueOf(id_mascota) };

        if (db == null) { abrirConexion(); }

        numFilasAfectadas = db.update(getTabla(), cv, where, whereArgs);

        if (numFilasAfectadas > 0) {
            Toast.makeText(ctx, R.string.updateOK,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ctx, R.string.errorUpdate,
                    Toast.LENGTH_SHORT).show();
        }
    }
    // BORRAR UNA MASCOTA DADA LA ID
    public void borrarMascota(String id_mascota) throws SQLException {
        int tieneCitas = 0;
        boolean flagMascota = false;
        boolean flagCitas = false;
        boolean flagBorrar = false;

        if (db == null) { abrirConexion(); }

        tieneCitas = hayCitas(id_mascota);
        // SI LA MASCOTA TIENE CITAS ASIGNADAS, BORRO LAS CITAS Y LUEGO LA MASCOTA
        if (tieneCitas > 0) {
            flagCitas = (db.delete("citas", "id_mascota = "
                + Integer.parseInt(id_mascota), null) != -1 )?true:false;
            flagMascota = (db.delete("mascotas", "_id = "
                + Integer.parseInt(id_mascota), null) != -1 )?true:false;
        } else {
            flagMascota = (db.delete("mascotas", "_id = "
                + Integer.parseInt(id_mascota), null) != -1 )?true:false;
        }
        // COMPRUEBO SI ALGO HA FALLADO
        if ((tieneCitas > 0) && (flagCitas && flagMascota)) {
            Toast.makeText(ctx, R.string.borradoMascotaCitas,
                    Toast.LENGTH_SHORT).show();
        } else if ((tieneCitas > 0) && (!flagCitas && !flagMascota)) {
            Toast.makeText(ctx, R.string.falloBorradoMascotaCitas,
                    Toast.LENGTH_SHORT).show();
        } else if ((tieneCitas > 0) && (!flagCitas || !flagMascota)) {
            if (!flagCitas) {
                Toast.makeText(ctx, R.string.falloBorradoCita,
                        Toast.LENGTH_SHORT).show();
            } else if (!flagMascota) {
                Toast.makeText(ctx, R.string.falloBorradoMascota,
                        Toast.LENGTH_SHORT).show();
            }
        } else if ((tieneCitas == 0) && flagMascota) {
            Toast.makeText(ctx, R.string.borradoMascota,
                    Toast.LENGTH_SHORT).show();
        } else if ((tieneCitas == 0) && !flagMascota) {
            Toast.makeText(ctx, R.string.falloBorradoMascota,
                    Toast.LENGTH_SHORT).show();
        }

    }
}
