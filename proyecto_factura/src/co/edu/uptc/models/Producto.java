package co.edu.uptc.models;
/**
 * Entidad Producto.
 * Relación: ASOCIACIÓN con DetalleFactura (cada línea de la factura
 * referencia un producto existente).
 */
public class Producto {

    private int    idProducto;
    private String nombre;
    private double precio;
    private int    stock;
    private String categoria;

    public Producto() {}

    public Producto(int idProducto, String nombre, double precio,
                    int stock, String categoria) {
        this.idProducto = idProducto;
        this.nombre     = nombre;
        this.precio     = precio;
        this.stock      = stock;
        this.categoria  = categoria;
    }

    // Getters y setters
    public int    getIdProducto()              { return idProducto; }
    public void   setIdProducto(int id)        { this.idProducto = id; }

    public String getNombre()                  { return nombre; }
    public void   setNombre(String nombre)     { this.nombre = nombre; }

    public double getPrecio()                  { return precio; }
    public void   setPrecio(double precio)     { this.precio = precio; }

    public int    getStock()                   { return stock; }
    public void   setStock(int stock)          { this.stock = stock; }

    public String getCategoria()               { return categoria; }
    public void   setCategoria(String cat)     { this.categoria = cat; }

    @Override
    public String toString() {
        return "ID: " + idProducto + " | Nombre: " + nombre +
               " | Precio: $" + precio + " | Stock: " + stock +
               " | Cat: " + categoria;
    }
}
