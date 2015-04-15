package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.mycan.app_mycan.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nono on 02/04/2015.
 */
public class AdaptadorDBCitas {
    private final String TABLA = "citas";
    private final String CAMPO_ID = "_id";
    private final String CAMPO_FECHA = "fecha";
    private final String CAMPO_HORA = "hora";
    private final String CAMPO_PRECIO = "precio";
    private final String CAMPO_DESCRIPCION = "descripcion";
    private final String CAMPO_ID_MASCOTA = "id_mascota";
    private Context ctx;
    private SQLiteDB sqliteDB;
    private SQLiteDatabase db;

    // CONSTRUCTOR
    public AdaptadorDBCitas(Context ctx) { this.ctx = ctx; }

    //////////////////////////////////////////////////////
    //// GETTERS PARA ACCEDER A LOS ATRIBUTOS DE LA CLASE
    //////////////////////////////////////////////////////
    public String getCampoID() { return this.CAMPO_ID; }
    public String getCampoFecha() { return this.CAMPO_FECHA; }
    public String getCampoHora() { return this.CAMPO_HORA; }
    public String getCampoPrecio() { return this.CAMPO_PRECIO; }
    public String getCampoDescripcion() { return this.CAMPO_DESCRIPCION; }
    public String getCampoIdMascota() { return this.CAMPO_ID_MASCOTA; }

    //////////////////////////////////////////////////////
    //// ABRIR CONEXION A LA BASE DE DATOS
    //////////////////////////////////////////////////////
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
    public List<String> getFechasLE(String id_mascota) {
        Cursor csr = db.rawQuery("SELECT * FROM citas WHERE id_mascota = " + id_mascota, null);
        List<String> fechas = null;

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
    public List<String> getDatosCita(String fecha, String id_mascota) {
        String hora, precio, descripcion;
        Cursor csr = db.rawQuery("SELECT * FROM citas WHERE fecha LIKE '" + fecha +
                "' AND id_mascota = " + Integer.parseInt(id_mascota), null);
        List<String> datos_cita = null;

        if (csr != null) {
            csr.moveToFirst();
            datos_cita = new ArrayList<String>();

            // RECORRO EL CURSOR Y OBTENGO LOS DATOS
            do {
                datos_cita.add(csr.getString(csr.getColumnIndex(this.CAMPO_HORA)) + "-" +
                        csr.getString(csr.getColumnIndex(this.CAMPO_PRECIO)) + "*" +
                        csr.getString(csr.getColumnIndex(this.CAMPO_DESCRIPCION)));
            } while (csr.moveToNext());
        }

        return datos_cita;
    }

    // BORRAR CITA
    public void borrarCita(String id_mascota, String fecha) throws SQLException {
        boolean flag = false;
        String where = "id_mascota = " + Integer.parseInt(id_mascota) + " AND fecha LIKE '"
                + fecha + "'";
        if (db == null) { abrirConexion(); }

        flag = (db.delete("citas", where, null) != -1 )?true:false;

        if (flag) { Toast.makeText(ctx, R.string.citaBorrada, Toast.LENGTH_SHORT).show(); }
        else { Toast.makeText(ctx, R.string.errorBorrarCita, Toast.LENGTH_SHORT).show(); }
    }
}
