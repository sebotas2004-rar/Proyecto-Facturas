package co.edu.uptc.models;

/**
 * Clase abstracta que representa un documento comercial genérico.
 * Es la clase PADRE de Factura (relación de herencia).
 */
public abstract class DocumentoComercial {

    protected int idDocumento;
    protected String fecha;
    protected double totalBruto;
    protected String moneda;
    protected String estado;

    public DocumentoComercial() {}

    public DocumentoComercial(int idDocumento, String fecha, double totalBruto,
                               String moneda, String estado) {
        this.idDocumento = idDocumento;
        this.fecha       = fecha;
        this.totalBruto  = totalBruto;
        this.moneda      = moneda;
        this.estado      = estado;
    }

    // Método abstracto que cada documento implementa a su manera
    public abstract double calcularTotal();

    // Getters y setters
    public int getIdDocumento()              { return idDocumento; }
    public void setIdDocumento(int id)       { this.idDocumento = id; }

    public String getFecha()                 { return fecha; }
    public void setFecha(String fecha)       { this.fecha = fecha; }

    public double getTotalBruto()            { return totalBruto; }
    public void setTotalBruto(double total)  { this.totalBruto = total; }

    public String getMoneda()                { return moneda; }
    public void setMoneda(String moneda)     { this.moneda = moneda; }

    public String getEstado()                { return estado; }
    public void setEstado(String estado)     { this.estado = estado; }

    @Override
    public String toString() {
        return "ID: " + idDocumento + " | Fecha: " + fecha +
               " | Total: " + totalBruto + " " + moneda +
               " | Estado: " + estado;
    }
}
