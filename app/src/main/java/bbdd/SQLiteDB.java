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

        /** Mascotas */
        /******************************
         * JUNIO 2014
         ******************************/
        // 5 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (1, 'Cokito', 'N/E', 'N/E', 123456789)");
        // 6 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (2, 'Sara', 'Pastor Aleman', 'Pepe', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (3, 'May', 'Yorkshire', 'N/E', 123456789)");
        // 7 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (4, 'Terry', 'Yorkshire', 'Encarnita', 123456789)");
        // 9 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (5, 'Liber', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (6, 'Max', 'Golden', 'N/E', 123456789)");
        // 10 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (7, 'Blanca', 'Cocker', 'Bea Protectora', 123456789)");
        // 11 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (8, 'Maya', 'Snauzer', 'Ana (tia de Marta)', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (9, 'Africa', 'Husky', 'N/E', 123456789)");
        // 12 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (10, 'Toby', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (11, 'Turco', 'N/E', 'N/E', 633016203)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (12, 'Lucas', 'Yorkshire', 'N/E', 626342975)");
        // 13 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (13, 'Laika', 'Mestiza', 'Tere', 953503204)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (14, 'Linda', 'Pequinesa', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (15, 'Chopi', 'X Teckel', 'N/E', 123456789)");
        // 14 JUNIO - NADA
        // 15 JUNIO - DOMINGO
        // 16 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (16, 'Chispa', 'Caniche', 'Raquel', 690952761)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (17, 'Bola', 'Yorkshire', 'N/E', 123456789)");
        // 17 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (18, 'Draco', 'Perro Aguas', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (19, 'Lana', 'Border Collie', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (20, 'Zara', 'Cocker', 'N/E', 123456789)");
        // 18 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (21, 'Lucas', 'Mestizo', 'N/E', 653123359)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (22, 'Kira', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (23, 'Duda', 'Westie', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (24, 'Richi', 'Yorkshire', 'N/E', 686901109)");
        // 19 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (25, 'Benji', 'Snauzer', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (26, 'Lolo', 'N/E', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (27, 'Kuate', 'Yorkshire', 'N/E', 953510630)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (28, 'Jambo', 'N/E', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (29, 'Bruno', 'Perro Aguas', 'N/E', 123456789)");
        // 20 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (30, 'Zeus', 'Yorkshire', 'Sara', 674437763)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (31, 'Ares', 'Cocker Americano', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (32, 'Tana', 'Basset', 'N/E', 123456789)");
        // 21 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (33, 'Kala', 'Golden', 'Salva', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (34, 'Cuqui', 'Mestizo', 'N/E', 953506567)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (35, 'Tigre', 'Yorkshire', 'N/E', 123456789)");
        // 22 JUNIO - DOMINGO
        // 23 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (36, 'Bruno', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (37, 'Maggie', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (38, 'Chica', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (39, 'Lala', 'Bichon Maltes', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (40, 'Coco', 'Bichon Maltes', 'N/E', 630106116)");
        // 24 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (41, 'Conguito', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (42, 'Mickey', 'Mestizo', 'Maria Jose', 655226321)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (43, 'Coco', 'Yorkshire', 'N/E', 123456789)");
        // 25 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (44, 'Lassie', 'Mestiza', 'N/E', 607423637)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (45, 'Naco', 'Breton', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (46, 'Lola', 'Yorkshire', 'N/E', 664608117)");
        // 26 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (47, 'Leo', 'N/E', 'Ana', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (48, 'Chica', 'Bretona', 'N/E', 123456789)");
        // 27 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (49, 'Tete', 'N/E', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (50, 'Choco', 'Cocker', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (51, 'Donna', 'Bobtail', 'Mª Carmen', 609305317)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (52, 'Bobby', 'Breton', 'N/E', 123456789)");
        // 28 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (53, 'Pepo', 'Chow Chow', 'Francis', 659223623)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (54, 'Otto', 'Pastor Aleman', 'Eva', 661355320)");
        // 29 JUNIO - DOMINGO
        // 30 JUNIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (55, 'Cora', 'Cocker', 'Angela y Ana(609134934)', 649589929)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (56, 'Dino', 'X Cocker', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (57, 'Suki', 'Akita', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (58, 'Coco', 'N/E', 'Juan', 123456789)");
        /******************************
         * JULIO 2014
         ******************************/
        // 1 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (59, 'Draco', 'Yorkshire', 'Tomas', 669913196)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (60, 'Boby', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (61, 'Choco', 'Labrador', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (62, 'Obi', 'Yorkshire', 'N/E', 123456789)");
        // 2 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (63, 'Noa', 'Bichon', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (64, 'Boby', 'X Cocker', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (65, 'Bobby', 'X Caniche', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (66, 'Malu', 'Bichon', 'N/E', 953501028)");
        // 3 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (67, 'Athor', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (68, 'Luna', 'Bichon', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (69, 'Blacky', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (70, 'Perla', 'Cocker', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (71, 'Maite', 'Mestiza', 'N/E', 678046409)");
        // 4 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (72, 'Cocki', 'N/E', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (73, 'Kiko', 'Teckel', 'N/E', 610492734)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (74, 'Cloe', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (75, 'Duque', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (76, 'Peluso', 'Yorkshire', 'Angelica', 659591795)");
        // 5 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (77, 'Chispita', 'Yorkshire', 'N/E', 608024242)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (78, 'Lassie', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (79, 'Robin', 'Mestizo', 'N/E', 123456789)");
        // 6 JULIO - DOMINGO
        // 7 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (80, 'Lucky', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (81, 'Noa', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (82, 'Lala', 'X Caniche', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (83, 'Stywy', 'Mestizo', 'N/E', 123456789)");
        // 8 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (84, 'Pantoja', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (85, 'Lupo', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (86, 'Nico', 'Snauzer', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (87, 'Roco', 'Snauzer', 'N/E', 123456789)");
        // 9 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (88, 'Marie Claire', 'Fox Terrier', 'Protectora', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (89, 'Wendy', 'Caniche', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (90, 'Vilma', 'Cocker', 'N/E', 123456789)");
        // 10 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (91, 'Bilbo', 'Mestizo', 'Alicia', 686791018)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (92, 'Lana', 'Mestiza', 'Alicia', 686791018)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (93, 'Daysi', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (94, 'Golfi', 'Cocker', 'N/E', 123456789)");
        // 11 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (95, 'Jara', 'Cocker', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (96, 'Coco', 'Labrador', 'N/E', 650382545)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (97, 'Jago', 'Pomerania', 'N/E', 123456789)");
        // 12 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (98, 'Donna', 'Caniche', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (99, 'Toby', 'Yorkshire', 'N/E', 123456789)");
        // 13 JULIO - DOMINGO
        // 14 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (100, 'Luna', 'Mestiza', 'Ext-Ballesteros', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (101, 'Nico', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (102, 'Niebla', 'X Cocker', 'Juanjo', 617529822)");
        // 15 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (103, 'Lana', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (104, 'Boro', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (105, 'Kiko', 'Mestizo', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (106, 'Borys', 'Mestizo', 'N/E', 123456789)");
        // 16 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (107, 'Luna', 'Caniche', 'N/E', 667027100)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (108, 'DArtacan', 'Gato', 'N/E', 123456789)");
        // 17 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (109, 'Tomy', 'Dalmata', 'N/E', 691204302)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (110, 'Sarita', 'Bodeguera', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (111, 'Tribuno', 'N/E', 'Jose Ayuso Sanchez', 636600765)");
        // 18 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (112, 'Daisy', 'X Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (113, 'Coffee', 'Perro Aguas', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (114, 'Gyn', 'Bichon', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (115, 'Coco', 'Mestizo', 'N/E', 123456789)");
        // 19 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (116, 'Willy', 'Mestizo', 'N/E', 123456789)");
        // 20 JULIO - DOMINGO
        // 21 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (117, 'Linda', 'Shiztzu', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (118, 'Iker', 'Yorkshire', 'N/E', 608726680)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (119, 'Luna', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (120, 'Trompi', 'Yorkshire', 'N/E', 123456789)");
        // 22 JULIO
        // TURCO Y BASSET QUE YA NO VA
        // 23 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (121, 'Nala', 'Mestiza', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (122, 'Bianca', 'Bichon Frise', 'N/E', 123456789)");
        // 24 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (123, 'Ali', 'Cocker', 'Rafa', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (124, 'Maya', 'Yorkshire', 'N/E', 603407527)");
        // 25 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (125, 'Nani', 'X Cocker', 'N/E', 649912034)");
        // NOA Y DONNA TAMBIEN
        // 26 JULIO
        // JAMBO
        // 27 JULIO - DOMINGO
        // 28 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (126, 'Mora', 'Caniche', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (127, 'Yaki', 'N/E', 'N/E', 123456789)");
        // 29 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (128, 'Shira', 'Yorkshire', 'N/E', 123456789)");
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (129, 'Perlita', 'Bichon', 'N/E', 123456789)");
        // 30 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (130, 'Nube', 'Mestizo', 'N/E', 123456789)");
        // 31 JULIO
        db.execSQL("INSERT INTO mascotas (_id, nombre, raza, propietario, telefono) " +
                "VALUES (131, 'Rachel', 'Mestiza.Caniche', 'N/E', 123456789)");
        /******************************
         * AGOSTO 2014
         ******************************/
        // 1 AGOSTO


        /** Citas */
        /******************************
         * JUNIO 2014
         ******************************/
        // 5 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (1, '05/06/2014', '18:00', '8', 'Recalce nº21 o 19', 1)");
        // 6 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (2, '06/06/2014', '10:00', '14', 'N/E', 2)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (3, '06/06/2014', '20:00', 'N/E', 'N/E', 3)");
        // 7 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (4, '07/06/2014', '10:00', '6', 'Baño', 4)");
        // 8 JUNIO - DOMINGO
        // 9 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (5, '09/06/2014', '10:00', '12', 'Recalce nº16', 5)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (6, '09/06/2014', '18:00', '12', 'N/E', 6)");
        // 10 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (7, '10/06/2014', '18:00', '14', 'N/E', 7)");
        // 11 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (8, '11/06/2014', '10:30', '14', 'N/E', 8)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (9, '11/06/2014', '18:00', '10', 'N/E', 9)");
        // 12 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (10, '12/06/2014', '10:30', '12', 'N/E', 10)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (11, '12/06/2014', '12:00', '8', 'Baño', 11)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (12, '12/06/2014', '17:30', '12', 'N/E', 12)");
        // 13 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (13, '13/06/2014', '12:00', '12', 'N/E', 13)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (14, '13/06/2014', '17:30', '12', 'N/E', 14)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (15, '13/06/2014', '19:45', '12', 'N/E', 15)");
        // 14 JUNIO - NADA
        // 15 JUNIO - DOMINGO
        // 16 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (16, '16/06/2014', '10:30', '12', 'N/E', 16)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (17, '16/06/2014', '17:30', '12', 'N/E', 17)");
        // 17 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (18, '17/06/2014', '10:00', '12', 'N/E', 18)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (19, '17/06/2014', '19:00', '16', 'N/E', 19)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (20, '17/06/2014', '17:30', '14', 'N/E', 20)");
        // 18 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (21, '18/06/2014', '10:30', '12', 'N/E', 21)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (22, '18/06/2014', '12:00', '12', 'N/E', 22)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (23, '18/06/2014', '17:30', '14', 'N/E', 23)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (24, '18/06/2014', '19:00', '12', 'N/E', 24)");
        // 19 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (25, '19/06/2014', '10:30', '14', 'Faldon bajo y cortito', 25)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (26, '19/06/2014', '12:00', '12', 'N/E', 26)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (27, '19/06/2014', '17:30', '12', 'N/E', 27)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (28, '19/06/2014', '19:00', '16', 'N/E', 28)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (29, '19/06/2014', '19:00', '10', 'Baño', 29)");
        // 20 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (30, '20/06/2014', '10:30', '12', 'N/E', 30)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (31, '20/06/2014', '17:30', '18', 'N/E', 31)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (32, '20/06/2014', '20:00', '10', 'Baño', 32)");
        // 21 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (33, '21/06/2014', '10:00', '14', 'N/E', 33)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (34, '21/06/2014', '11:30', '6', 'Baño', 34)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (35, '21/06/2014', '12:00', '12', 'N/E', 35)");
        // 22 - DOMINGO
        // 23 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (36, '23/06/2014', '10:30', '15', 'N/E', 36)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (37, '23/06/2014', '12:00', '6', 'N/E', 37)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (38, '23/06/2014', '10:00', '6', 'Baño', 38)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (39, '23/06/2014', '17:30', '13', 'N/E', 39)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (40, '23/06/2014', '19:00', '12', 'N/E', 40)");
        // 24 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (41, '24/06/2014', '10:30', '12', 'N/E', 41)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (42, '24/06/2014', '10:30', '6', 'N/E', 42)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (43, '24/06/2014', '17:30', '6', 'Baño', 43)");
        // 25 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (44, '25/06/2014', '10:30', '12', 'N/E', 44)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (45, '25/06/2014', '12:00', '14', 'N/E', 45)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (46, '25/06/2014', '19:00', '12', 'N/E', 46)");
        // 26 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (47, '26/06/2014', '10:00', '18', 'N/E', 2)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (48, '26/06/2014', '12:00', '12', 'N/E', 47)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (49, '26/06/2014', '17:30', '12', 'N/E', 48)");
        // 27 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (50, '27/06/2014', '10:15', '16', 'N/E', 49)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (51, '27/06/2014', '12:00', '14', 'N/E', 50)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (52, '27/06/2014', '17:30', '18', 'N/E', 51)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (53, '27/06/2014', '19:30', '8', 'N/E', 52)");
        // 28 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (54, '28/06/2014', '10:15', '18', 'N/E', 53)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (55, '28/06/2014', '12:15', '18', 'N/E', 54)");
        // 29 JUNIO - DOMINGO
        // 30 JUNIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (56, '30/06/2014', '10:15', '14', 'N/E', 55)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (57, '30/06/2014', '12:00', '10', 'N/E', 56)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (58, '30/06/2014', '19:00', '16', 'N/E', 57)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (59, '30/06/2014', '17:30', '14', 'N/E', 58)");
        /******************************
         * JULIO 2014
         ******************************/
        // 1 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (60, '01/07/2014', '10:30', '12', 'Rec + Tijera', 59)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (61, '01/07/2014', '12:00', '7', 'Baño, pelado proximo 14€', 60)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (62, '01/07/2014', '17:30', '14', 'N/E', 61)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (63, '01/07/2014', '19:00', '12', 'N/E', 62)");
        // 2 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (64, '02/07/2014', '10:30', '14', 'N/E', 63)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (65, '02/07/2014', '12:00', '14', 'Mantener precio', 64)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (66, '02/07/2014', '19:00', '14', 'N/E', 65)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (67, '02/07/2014', '17:30', '12', 'Recalce nº16', 66)");
        // 3 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (68, '03/07/2014', '10:30', '14', 'N/E', 67)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (69, '03/07/2014', '13:00', '12', 'Recalce nº16', 68)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (70, '03/07/2014', '12:00', '14', 'N/E', 69)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (71, '03/07/2014', '17:30', '14', 'N/E', 70)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (72, '03/07/2014', '19:00', '15', 'N/E', 71)");
        // 4 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (73, '04/07/2014', '10:30', '10', '43 cms de cuello', 72)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (74, '04/07/2014', '12:30', '15', 'N/E', 73)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (75, '04/07/2014', '17:30', '12', 'N/E', 74)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (76, '04/07/2014', '17:30', '12', 'N/E', 75)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (77, '04/07/2014', '19:00', '12', 'N/E', 76)");
        // 5 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (78, '05/07/2014', '10:00', '6', 'N/E', 4)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (79, '05/07/2014', '10:30', '12', 'N/E', 77)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (80, '05/07/2014', '12:00', '12', 'N/E', 78)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (81, '05/07/2014', '12:30', '7', 'Baño', 79)");
        // 6 JULIO - DOMINGO
        // 7 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (82, '07/07/2014', '10:15', '12', 'A tijera', 80)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (83, '07/07/2014', '12:00', '14', 'N/E', 81)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (84, '07/07/2014', '17:30', '15', 'N/E', 82)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (85, '07/07/2014', '19:00', '15', 'N/E', 83)");
        // 8 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (86, '08/07/2014', '10:15', '14', 'N/E', 84)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (87, '08/07/2014', '12:00', '13', 'N/E', 85)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (88, '08/07/2014', '17:30', '17', 'N/E', 86)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (89, '08/07/2014', '19:00', '17', 'N/E', 87)");
        // 9 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (90, '09/07/2014', '12:00', 'N/E', 'N/E', 88)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (91, '09/07/2014', '19:00', '16', 'A tijera', 89)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (92, '09/07/2014', '17:30', '10', 'Baño', 90)");
        // 10 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (93, '10/07/2014', '10:15', '16', 'N/E', 91)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (94, '10/07/2014', '10:15', '16', 'N/E', 92)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (95, '10/07/2014', '17:30', '15', 'A tijera', 93)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (96, '10/07/2014', '19:30', '13', 'N/E', 94)");
        // 11 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (97, '11/07/2014', '12:0', '15', '13 mm recalce', 34)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (98, '11/07/2014', '10:30', '17', 'N/E', 95)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (99, '11/07/2014', '17:30', '18', 'N/E', 96)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (100, '11/07/2014', '19:00', '15', 'N/E', 97)");
        // 12 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (101, '12/07/2014', '10:15', '14', 'A tijera, Rec 13 mm', 98)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (102, '12/07/2014', '12:15', '15', 'N/E', 99)");
        // 13 JULIO - DOMINGO
        // 14 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (103, '14/07/2014', '12:00', '15', 'N/E', 100)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (104, '14/07/2014', '10:30', '15', 'N/E', 101)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (105, '14/07/2014', '17:30', '16', 'N/E', 102)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (106, '14/07/2014', '19:00', '8', 'Baño', 11)");
        // 15 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (107, '15/07/2014', '10:30', '15', 'N/E', 103)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (108, '15/07/2014', '12:00', '16', 'N/E', 104)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (109, '15/07/2014', '17:30', '16', 'N/E', 105)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (110, '15/07/2014', '19:00', '16', 'Cuidado patas atras', 106)");
        // 16 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (111, '16/07/2014', '10:15', '16', 'N/E', 107)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (112, '16/07/2014', '17:30', '12', 'Baño', 31)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (113, '16/07/2014', '20:30', '8', 'Muda', 108)");
        // 17 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (114, '17/07/2014', '10:15', '8', 'N/E', 109)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (115, '17/07/2014', '12:00', '18', 'N/E', 2)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (116, '17/07/2014', '17:30', '10', 'N/E', 108)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (117, '17/07/2014', '19:00', '8', 'Baño', 108)");
        // 18 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (118, '18/07/2014', '10:30', '15', 'N/E', 112)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (119, '18/07/2014', '12:00', '17', 'N/E', 113)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (120, '18/07/2014', '17:30', '16', 'N/E', 114)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (121, '18/07/2014', '19:00', '16', 'N/E', 115)");
        // 19 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (122, '19/07/2014', '10:15', '24', '4 mm', 29)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (123, '19/07/2014', '12:00', '16', 'N/E', 116)");
        // 20 JULIO - DOMINGO
        // 21 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (124, '21/07/2014', '10:15', '16', 'N/E', 117)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (125, '21/07/2014', '12:00', '16', 'N/E', 118)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (126, '21/07/2014', '17:30', '15', 'N/E', 119)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (127, '21/07/2014', '19:00', '16', 'N/E', 120)");
        // 22 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (128, '22/07/2014', '10:30', '18', 'N/E', 11)");
        // 23 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (129, '23/07/2014', '10:30', '17', 'N/E', 121)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (130, '23/07/2014', '17:30', '6', 'Baño', 4)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (131, '23/07/2014', '19:00', 'N/E', 'Concurso', 122)");
        // 24 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (132, '24/07/2014', '10:00', '17', 'N/E', 123)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (133, '24/07/2014', '11:45', '16', 'N/E', 49)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (134, '24/07/2014', '19:30', '16', 'N/E', 124)");
        // 25 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (135, '25/07/2014', '10:30', '17', 'N/E', 125)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (136, '25/07/2014', '12:00', '16', 'N/E', 81)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (137, '25/07/2014', '17:30', '18', '16 mm', 51)");
        // 26 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (138, '26/07/2014', '12:00', '12', 'Baño', 28)");
        // 27 JULIO - DOMINGO
        // 28 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (139, '28/07/2014', '10:30', '12', 'N/E', 126)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (140, '28/07/2014', '12:00', '7', 'Baño', 127)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (141, '28/07/2014', '19:30', '8', 'Baño', 66)");
        // 29 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (142, '29/07/2014', '10:15', '15', 'N/E', 81)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (143, '29/07/2014', '12:00', '16', 'A tijera', 128)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (144, '29/07/2014', '18:00', '16', 'N/E', 129)");
        // 30 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (145, '30/07/2014', '10:00', '14', 'Mantenimiento', 12)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (146, '30/07/2014', '12:00', '4', 'Pasar maquina', 81)");
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (147, '30/07/2014', '18:30', '15', 'N/E', 130)");
        // 31 JULIO
        db.execSQL("INSERT INTO citas (_id, fecha, hora, precio, descripcion, id_mascota) " +
                "VALUES (148, '31/07/2014', '11:00', '16', 'N/E', 131)");
        /******************************
         * AGOSTO 2014
         ******************************/
         // 1 AGOSTO

        /*
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
        */
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
