package clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.mycan.app_mycan.R;

import java.util.ArrayList;

/**
 * Created by Nono on 31/03/2015.
 */
public class ListaExpansibleAdapter extends BaseExpandableListAdapter {
    private ArrayList<String> groups;
    private ArrayList<ArrayList<ArrayList<String>>> children;
    private Context context;

    public ListaExpansibleAdapter(Context context, ArrayList<String> grupos,
                                  ArrayList<ArrayList<ArrayList<String>>> hijos) {
        this.context = context;
        this.groups = grupos;
        this.children = hijos;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public ArrayList<String> getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        String hora = (String) ((ArrayList<String>)getChild(groupPosition, childPosition)).get(0);
        //String precio = (String) ((ArrayList<String>)getChild(groupPosition, childPosition)).get(0);
        //String descripcion = (String) ((ArrayList<String>)getChild(groupPosition, childPosition)).get(0);

        if (convertView == null)  {
            LayoutInflater infallInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infallInflater.inflate(R.layout.datos_lista_extensible, null);
        }

        TextView tvHora = (TextView) convertView.findViewById(R.id.tvHoraDato);
        tvHora.setText(hora);
        //TextView tvPrecio = (TextView) convertView.findViewById(R.id.tvPrecioDato);
        //tvPrecio.setText(precio);
        //TextView tvDescripcion = (TextView) convertView.findViewById(R.id.tvDescripcionDato);
        //tvDescripcion.setText(descripcion);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String) getGroup(groupPosition);

        // no se esta reciclando, esta vacio y hay que crearlo de nuevo
        if (convertView == null) {

            LayoutInflater infallInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infallInflater.inflate(R.layout.grupo_lista_expansible, null);
        }

        TextView grouptxt = (TextView) convertView.findViewById(R.id.tvCita);
        grouptxt.setText(group);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }
}
