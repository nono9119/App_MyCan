package clases;

/**
 * Created by Nono on 28/03/2015.
 */
public class Cita {
    private int _id;
    private String fecha;
    private String hora;
    private String precio;
    private String descripcion;
    private int id_mascota;

    public Cita(int _id, String fecha, String hora, String precio, String descripcion,
                int id_mascota) {
        this._id = _id;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.descripcion = descripcion;
        this.id_mascota = id_mascota;
    }

    @Override
    public String toString() {
        return "Dia: " + this.fecha + " Hora: " + this.hora + " Precio: " + this.precio;
    }
    ////////////////////////////////////////////
    //// SETTERS
    ////////////////////////////////////////////
    public void setId(int _id) { this._id = _id; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public void setHora(String hora) { this.hora = hora; }
    public void setPrecio(String precio) { this.precio = precio; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setIdMascota(int id_mascota) { this.id_mascota = id_mascota; }
    ////////////////////////////////////////////
    //// GETTERS
    ////////////////////////////////////////////
    public int getId() { return this._id; }
    public String getFecha() { return this.fecha; }
    public String getHora() { return this.hora; }
    public String getPrecio() { return this.precio; }
    public String getDescripcion() { return this.descripcion; }
    public int getIdMascota() { return this.id_mascota; }
}
