package co.edu.uptc.models;

/**
 * Entidad Cliente.
 * Relación: ASOCIACIÓN con Factura (un cliente puede tener muchas facturas,
 * pero existe de forma independiente).
 */
public class Cliente {

    private int    idCliente;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;

    public Cliente() {}

    public Cliente(int idCliente, String nombre, String email,
                   String telefono, String direccion) {
        this.idCliente  = idCliente;
        this.nombre     = nombre;
        this.email      = email;
        this.telefono   = telefono;
        this.direccion  = direccion;
    }

    // Getters y setters
    public int    getIdCliente()              { return idCliente; }
    public void   setIdCliente(int id)        { this.idCliente = id; }

    public String getNombre()                 { return nombre; }
    public void   setNombre(String nombre)    { this.nombre = nombre; }

    public String getEmail()                  { return email; }
    public void   setEmail(String email)      { this.email = email; }

    public String getTelefono()               { return telefono; }
    public void   setTelefono(String tel)     { this.telefono = tel; }

    public String getDireccion()              { return direccion; }
    public void   setDireccion(String dir)    { this.direccion = dir; }

    @Override
    public String toString() {
        return "ID: " + idCliente + " | Nombre: " + nombre +
               " | Email: " + email + " | Tel: " + telefono +
               " | Dir: " + direccion;
    }
}
