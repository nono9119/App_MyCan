package com.mycan.app_mycan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import database.*;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private SQLiteDB sqliteDB; // Clase con la base de datos
    private SQLiteDatabase db;
    private Intent itt;
    protected TextView loadData;
    private Button btEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData = (TextView) findViewById(R.id.tvCargandoDatos);
        btEntrar = (Button) findViewById(R.id.btEntrar);

        sqliteDB = new SQLiteDB(getBaseContext());
        db = sqliteDB.getWritableDatabase();
        db.close();
        sqliteDB.close();

        loadData.setVisibility(View.INVISIBLE);
        btEntrar.setEnabled(true);
    }

    /*
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
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
    */
    public void onClick(View v) {
        itt = new Intent(this, ListActivity.class);
        startActivity(itt);
    }
}
