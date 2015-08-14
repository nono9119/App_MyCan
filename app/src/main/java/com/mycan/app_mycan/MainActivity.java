package com.mycan.app_mycan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import bbdd.*;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private SQLiteDB sqliteDB; // Clase con la base de datos
    private SQLiteDatabase db;
    private Intent itt;
    protected TextView loadData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        loadData = (TextView) findViewById(R.id.tvCargandoDatos);

        sqliteDB = new SQLiteDB(getBaseContext());
        db = sqliteDB.getWritableDatabase();
        db.close();
        sqliteDB.close();

        loadData.setVisibility(View.INVISIBLE);
    }

    public void onClick(View v) {
        itt = new Intent(this, ListActivity.class);
        startActivity(itt);
    }
}
