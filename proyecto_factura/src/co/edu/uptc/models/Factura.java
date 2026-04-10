package co.edu.uptc.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad principal: Factura.
 * Hereda de DocumentoComercial (herencia).
 * Composición con DetalleFactura (los detalles no existen sin la factura).
 * Agregación con Vendedor (el vendedor existe sin la factura).
 * Asociación con Cliente.
 */
public class Factura extends DocumentoComercial {

    private int                 idFactura;
    private String              numeroFactura;
    private double              subtotal;
    private double              impuesto;
    private double              descuento;
    private double              totalPagar;

    // Asociación con Cliente
    private Cliente             cliente;

    // Agregación con Vendedor
    private Vendedor            vendedor;

    // Composición: los detalles solo existen dentro de esta factura
    private List<DetalleFactura> detalles;

    public Factura() {
        this.detalles = new ArrayList<>();
    }

    public Factura(int idFactura, String numeroFactura, String fecha,
                   String moneda, String estado,
                   double impuesto, double descuento,
                   Cliente cliente, Vendedor vendedor) {
        super(idFactura, fecha, 0, moneda, estado);
        this.idFactura     = idFactura;
        this.numeroFactura = numeroFactura;
        this.impuesto      = impuesto;
        this.descuento     = descuento;
        this.cliente       = cliente;
        this.vendedor      = vendedor;
        this.detalles      = new ArrayList<>();
    }

    /**
     * Implementación del método abstracto de DocumentoComercial.
     * Calcula subtotal, aplica descuento e impuesto.
     */
    @Override
    public double calcularTotal() {
        subtotal = 0;
        for (DetalleFactura d : detalles) {
            subtotal += d.calcularSubtotal();
        }
        double conDescuento = subtotal - (subtotal * descuento / 100);
        totalPagar  = conDescuento + (conDescuento * impuesto / 100);
        totalBruto  = totalPagar;
        return totalPagar;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        calcularTotal();
    }

    // Getters y setters
    public int    getIdFactura()                    { return idFactura; }
    public void   setIdFactura(int id)              { this.idFactura = id; }

    public String getNumeroFactura()                { return numeroFactura; }
    public void   setNumeroFactura(String num)      { this.numeroFactura = num; }

    public double getSubtotal()                     { return subtotal; }
    public double getImpuesto()                     { return impuesto; }
    public void   setImpuesto(double imp)           { this.impuesto = imp; }

    public double getDescuento()                    { return descuento; }
    public void   setDescuento(double desc)         { this.descuento = desc; }

    public double getTotalPagar()                   { return totalPagar; }

    public Cliente  getCliente()                    { return cliente; }
    public void     setCliente(Cliente c)           { this.cliente = c; }

    public Vendedor getVendedor()                   { return vendedor; }
    public void     setVendedor(Vendedor v)         { this.vendedor = v; }

    public List<DetalleFactura> getDetalles()       { return detalles; }

    @Override
    public String toString() {
        String cli = (cliente  != null) ? cliente.getNombre()  : "Sin cliente";
        String ven = (vendedor != null) ? vendedor.getNombreV(): "Sin vendedor";
        calcularTotal();
        return "Factura #" + numeroFactura + " | ID: " + idFactura +
               " | Fecha: " + fecha + " | Cliente: " + cli +
               " | Vendedor: " + ven + " | Subtotal: $" + subtotal +
               " | Desc: " + descuento + "% | IVA: " + impuesto +
               "% | TOTAL: $" + totalPagar + " | Estado: " + estado;
    }
}
