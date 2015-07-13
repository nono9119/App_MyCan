package bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nono on 26/03/2015.
 */
public class SQLiteDB extends SQLiteOpenHelper {
    private static int version = 1;
    private static String dbname = "app";
    private static String tbMascotas =
            "CREATE TABLE mascotas (" +
            "_id INTEGER PRIMARY KEY, " +
            "nombre TEXT, " +
            "raza TEXT, " +
            "propietario TEXT, " +
            "telefono INTEGER)";
    private static String tbCitas =
            "CREATE TABLE citas (" +
            "_id INTEGER PRIMARY KEY, " +
            "fecha TEXT, " +
            "hora TEXT, " +
            "precio TEXT," +
            "descripcion TEXT, " +
            "id_mascota INTEGER, " +
            "FOREIGN KEY(id_mascota) REFERENCES mascotas(_id))";

    public SQLiteDB(Context ctx) {
        super(ctx, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(this.getClass().toString(), "Creando la base de datos...");

        db.execSQL(tbMascotas);
        Log.i(this.getClass().toString(), "Tabla mascotas creada");

        db.execSQL(tbCitas);
        Log.i(this.getClass().toString(), "Tabla citas creada");

		// Datos de prueba

        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (1, 'Punk', 'Erizo', 'Antonio Martinez', 617267261)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (2, 'Iker', 'Mestizo', 'Mari Angeles', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (3, 'Susu', 'Galgo', 'Mari Angeles', 123456789)");
        Log.i(this.getClass().toString(), "Mascotas insertadas correctamente");

        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (1, '27/03/2015', '10:00', '15', 'lavado', 1)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (2, '10/02/2015', '11:00', '20', 'baño', 2)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (3, '27/03/2015', '12:00', '20', 'corte a tijera', 2)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (4, '27/03/2015', '17:00', '20', 'baño', 3)");
        Log.i(this.getClass().toString(), "Citas insertadas correctamente");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + tbMascotas);
            db.execSQL("DROP TABLE IF EXISTS " + tbCitas);
            db.execSQL(tbMascotas);
            db.execSQL(tbCitas);
        }
    }
}
