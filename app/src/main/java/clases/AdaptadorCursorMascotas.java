package clases;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import java.sql.SQLException;
import bbdd.AdaptadorDBMascotas;

/**
 * Created by Nono on 28/03/2015.
 */
public class AdaptadorCursorMascotas extends CursorAdapter {
    private AdaptadorDBMascotas adbMascotas;

    @SuppressWarnings("deprecation")
    public AdaptadorCursorMascotas(Context context, Cursor c) throws SQLException {
        super(context, c);
        adbMascotas = new AdaptadorDBMascotas(context);
    }

    @Override
    public void bindView(View v, Context context, Cursor csr) {
        TextView tv = (TextView) v;
        tv.setTextColor(Color.WHITE);
        tv.setText(csr.getString(csr.getColumnIndex(adbMascotas.getCampoNombre())) + " ("
                + csr.getString(csr.getColumnIndex(adbMascotas.getCampoRaza())) + ")");

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View v = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        return v;
    }
}
