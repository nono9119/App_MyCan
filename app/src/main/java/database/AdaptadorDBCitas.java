package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nono on 02/04/2015.
 */
public class AdaptadorDBCitas {
    private final String TABLA = "citas";
    private final String CAMPO_ID = "_id";
    private final static String CAMPO_FECHA = "fecha";
    private final static String CAMPO_HORA = "hora";
    private final static String CAMPO_PRECIO = "precio";
    private final static String CAMPO_DESCRIPCION = "descripcion";
    private final static String CAMPO_ID_MASCOTA = "id_mascota";
    private Context ctx;
    private SQLiteDB sqliteDB;
    private SQLiteDatabase db;
    // CONSTRUCTOR
    public AdaptadorDBCitas(Context ctx) { this.ctx = ctx; }

    // GETTERS PARA ACCEDER A LOS ATRIBUTOS DE LA CLASE
    public String getCampoID() { return this.CAMPO_ID; }
    public String getCampoFecha() { return this.CAMPO_FECHA; }
    public String getCampoHora() { return this.CAMPO_HORA; }
    public String getCampoPrecio() { return this.CAMPO_PRECIO; }
    public String getCampoDescripcion() { return this.CAMPO_DESCRIPCION; }
    public String getCampoIdMascota() { return this.CAMPO_ID_MASCOTA; }

    // CONEXION A LA BASE DE DATOS
    public AdaptadorDBCitas abrirConexion() throws SQLException {
        sqliteDB = new SQLiteDB(ctx);
        db = sqliteDB.getWritableDatabase();
        return this;
    }

    // CERRAR CONEXION CON LA BASE DE DATOS
    public void cerrarConexion() throws SQLException { sqliteDB.close(); }

    // INSERTAR CITA
    public boolean insertarCita(ContentValues cv) {
        if (db == null) {
            try {
                abrirConexion();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (db.insert("citas", null, cv) != -1 )?true:false;
    }

    // OBTENER FECHAS POR ID_MASCOTA
    public ArrayList<String> getFechasLE(String id_mascota) {
        Cursor csr = db.rawQuery("SELECT * FROM citas WHERE id_mascota = " + id_mascota, null);
        ArrayList<String> fechas = null;

        if (csr != null) {
            csr.moveToFirst();
            // PREPARO Y CREO LOS OBJETOS SOLO SI EL CURSOR SE HA CREADO CORRECTAMENTE
            fechas = new ArrayList<String>();

            // RECORRO EL CURSOR Y OBTENGO LOS DATOS
            do {
                fechas.add(csr.getString(csr.getColumnIndex(this.CAMPO_FECHA)));
            } while (csr.moveToNext());
        }

        return fechas;
    }

    // OBTENER LOS DATOS PARA LA LISTA EXPANSIBLE
    public Cursor getDatosCita(String fecha, String id_mascota) {
        String hora, precio, descripcion;
        Cursor csr = db.rawQuery("SELECT * FROM citas WHERE fecha LIKE '" + fecha +
                "' AND id_mascota = " + Integer.parseInt(id_mascota), null);

        if (csr != null) { csr.moveToFirst(); }

        return csr;
    }
}
