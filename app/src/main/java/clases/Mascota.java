package clases;

/**
 * Created by Nono on 28/03/2015.
 */
public class Mascota {
    private int _id;
    private String nombre;
    private String raza;
    private int telefono;
    private String propietario;

    // CONSTRUCTOR PARA EL SPINNER
    public Mascota(int _id, String nombre) {
        this._id = _id;
        this.nombre = nombre;
    }

    public Mascota(int _id, String nombre, String raza) {
        this._id = _id;
        this.nombre = nombre;
        this.raza = raza;
    }

    @Override
    public String toString() { return this.nombre + "(" + this.raza + ")"; }
    ////////////////////////////////////////////
    //// SETTERS
    ////////////////////////////////////////////
    public void setId(int _id) { this._id = _id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setRaza(String raza) { this.raza = raza; }
    public void setTelefono(int telefono) { this.telefono = telefono; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
    ////////////////////////////////////////////
    //// GETTERS
    ////////////////////////////////////////////
    public int getId() { return this._id; }
    public String getNombre() { return this.nombre; }
    public String getRaza() { return this.raza; }
    public int getTelefono() { return this.telefono; }
    public String getPropietario() { return this.propietario; }
}
