package clases;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mycan.app_mycan.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nono on 31/03/2015.
 */
public class ListaExpansibleAdapter extends BaseExpandableListAdapter {
    private Context contexto;
    private List<String> listaGrupoItems;
    private HashMap<String, List<String>> listaItems;

    public ListaExpansibleAdapter(Context context, List<String> listaGrupoItems,
                            HashMap<String, List<String>> listaItems) {
        this.contexto = context;
        this.listaGrupoItems = listaGrupoItems;
        this.listaItems = listaItems;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listaItems.get(this.listaGrupoItems.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String textoItem = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.contexto
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.datos_lista_extensible, null);
        }


        TextView txtListChild = (TextView) convertView.findViewById(R.id.tvHoraDato);
        txtListChild.setText(textoItem);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listaItems.get(this.listaGrupoItems.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listaGrupoItems.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listaGrupoItems.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.contexto
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.grupo_lista_expansible, null);
        }

        TextView encabezado = (TextView) convertView.findViewById(R.id.tvCita);
        encabezado.setTypeface(null, Typeface.BOLD);
        encabezado.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}