package co.edu.uptc.models;

/**
 * Entidad Vendedor.
 * Relación: AGREGACIÓN con Factura (el vendedor existe independientemente
 * de la factura; si se elimina la factura, el vendedor sigue existiendo).
 */
public class Vendedor {

    private int     idVendedor;
    private String  nombreV;
    private String  zona;
    private double  comision;
    private boolean activo;

    public Vendedor() {}

    public Vendedor(int idVendedor, String nombreV, String zona,
                    double comision, boolean activo) {
        this.idVendedor = idVendedor;
        this.nombreV    = nombreV;
        this.zona       = zona;
        this.comision   = comision;
        this.activo     = activo;
    }

    // Getters y setters
    public int     getIdVendedor()             { return idVendedor; }
    public void    setIdVendedor(int id)       { this.idVendedor = id; }

    public String  getNombreV()                { return nombreV; }
    public void    setNombreV(String nombre)   { this.nombreV = nombre; }

    public String  getZona()                   { return zona; }
    public void    setZona(String zona)        { this.zona = zona; }

    public double  getComision()               { return comision; }
    public void    setComision(double com)     { this.comision = com; }

    public boolean isActivo()                  { return activo; }
    public void    setActivo(boolean activo)   { this.activo = activo; }

    @Override
    public String toString() {
        return "ID: " + idVendedor + " | Nombre: " + nombreV +
               " | Zona: " + zona + " | Comisión: " + comision +
               "% | Activo: " + (activo ? "Sí" : "No");
    }
}
