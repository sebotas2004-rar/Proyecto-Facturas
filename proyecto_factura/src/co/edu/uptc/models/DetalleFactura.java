package co.edu.uptc.models;
/**
 * Entidad DetalleFactura.
 * Relación: COMPOSICIÓN con Factura (no existe sin su factura padre).
 * Relación: ASOCIACIÓN con Producto.
 */
public class DetalleFactura {

    private int      idDetalle;
    private int      cantidad;
    private double   precioUnitario;
    private double   subtotalLinea;
    private String   descripcion;
    private Producto producto;   // asociación con Producto

    public DetalleFactura() {}

    public DetalleFactura(int idDetalle, int cantidad, double precioUnitario,
                          String descripcion, Producto producto) {
        this.idDetalle      = idDetalle;
        this.cantidad       = cantidad;
        this.precioUnitario = precioUnitario;
        this.descripcion    = descripcion;
        this.producto       = producto;
        this.subtotalLinea  = cantidad * precioUnitario;
    }

    // Calcula el subtotal de la línea
    public double calcularSubtotal() {
        this.subtotalLinea = cantidad * precioUnitario;
        return subtotalLinea;
    }

    // Getters y setters
    public int     getIdDetalle()                  { return idDetalle; }
    public void    setIdDetalle(int id)            { this.idDetalle = id; }

    public int     getCantidad()                   { return cantidad; }
    public void    setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double  getPrecioUnitario()             { return precioUnitario; }
    public void    setPrecioUnitario(double precio) {
        this.precioUnitario = precio;
        calcularSubtotal();
    }

    public double  getSubtotalLinea()              { return subtotalLinea; }

    public String  getDescripcion()                { return descripcion; }
    public void    setDescripcion(String desc)     { this.descripcion = desc; }

    public Producto getProducto()                  { return producto; }
    public void     setProducto(Producto p)        { this.producto = p; }

    @Override
    public String toString() {
        String prod = (producto != null) ? producto.getNombre() : "Sin producto";
        return "ID: " + idDetalle + " | Producto: " + prod +
               " | Cant: " + cantidad + " | P.Unit: $" + precioUnitario +
               " | Subtotal: $" + subtotalLinea + " | Desc: " + descripcion;
    }
}
