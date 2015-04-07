package clases;

import java.util.ArrayList;

/**
 * Created by Nono on 31/03/2015.
 */
public class ElementosListaExpansible {
    private String padre;
    private ArrayList<String> hijos = new ArrayList<String>();

    public ElementosListaExpansible(String padre) {
        this.padre = padre;
    }

    ////////////////////////////////////////////
    //// SETTERS
    ///////////////////////////////////////////

    public void setPadre(String padre) {
        this.padre = padre;
    }

    public void setHijos(ArrayList<String> hijos) {
        this.hijos = hijos;
    }

    ////////////////////////////////////////////
    //// GETTERS
    ///////////////////////////////////////////

    public String getPadre() {
        return this.padre;
    }

    public ArrayList<String> getHijos() {
        return this.hijos;
    }
}
