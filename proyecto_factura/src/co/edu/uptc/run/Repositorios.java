package co.edu.uptc.run;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.models.BaseClass;
import co.edu.uptc.models.Cliente;
import co.edu.uptc.models.DetalleFactura;
import co.edu.uptc.models.Factura;
import co.edu.uptc.models.Producto;
import co.edu.uptc.models.Vendedor;

// ─────────────────────────────────────────────
//  Factura
// ─────────────────────────────────────────────
class FacturaRepositorio extends BaseClass<Factura> {

    private List<Factura> lista = new ArrayList<>();

    @Override
    public void crear(Factura f) {
        lista.add(f);
    }

    @Override
    public Factura consultar(int id) {
        for (Factura f : lista) {
            if (f.getIdFactura() == id) return f;
        }
        return null;
    }

    @Override
    public void actualizar(Factura nueva) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdFactura() == nueva.getIdFactura()) {
                lista.set(i, nueva);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(f -> f.getIdFactura() == id);
    }

    @Override
    public List<Factura> listarTodos() { return lista; }
}

// ─────────────────────────────────────────────
//  Cliente
// ─────────────────────────────────────────────
class ClienteRepositorio extends BaseClass<Cliente> {

    private List<Cliente> lista = new ArrayList<>();

    @Override
    public void crear(Cliente c) { lista.add(c); }

    @Override
    public Cliente consultar(int id) {
        for (Cliente c : lista) {
            if (c.getIdCliente() == id) return c;
        }
        return null;
    }

    @Override
    public void actualizar(Cliente nuevo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdCliente() == nuevo.getIdCliente()) {
                lista.set(i, nuevo);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(c -> c.getIdCliente() == id);
    }

    @Override
    public List<Cliente> listarTodos() { return lista; }
}

// ─────────────────────────────────────────────
//  VendedorRepositorio
// ─────────────────────────────────────────────
 class VendedorRepositorio extends BaseClass<Vendedor> {

    private List<Vendedor> lista = new ArrayList<>();

    @Override
    public void crear(Vendedor v)    { lista.add(v); }

    @Override
    public Vendedor consultar(int id) {
        for (Vendedor v : lista) {
            if (v.getIdVendedor() == id) return v;
        }
        return null;
    }

    @Override
    public void actualizar(Vendedor nuevo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdVendedor() == nuevo.getIdVendedor()) {
                lista.set(i, nuevo);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(v -> v.getIdVendedor() == id);
    }

    @Override
    public List<Vendedor> listarTodos() { return lista; }
}

// ─────────────────────────────────────────────
//  ProductoRepositorio
// ─────────────────────────────────────────────
class ProductoRepositorio extends BaseClass<Producto> {

    private List<Producto> lista = new ArrayList<>();

    @Override
    public void crear(Producto p)    { lista.add(p); }

    @Override
    public Producto consultar(int id) {
        for (Producto p : lista) {
            if (p.getIdProducto() == id) return p;
        }
        return null;
    }

    @Override
    public void actualizar(Producto nuevo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdProducto() == nuevo.getIdProducto()) {
                lista.set(i, nuevo);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(p -> p.getIdProducto() == id);
    }

    @Override
    public List<Producto> listarTodos() { return lista; }
}

// ─────────────────────────────────────────────
//  DetalleFacturaRepositorio
// ─────────────────────────────────────────────
class DetalleFacturaRepositorio extends BaseClass<DetalleFactura> {

    private List<DetalleFactura> lista = new ArrayList<>();

    @Override
    public void crear(DetalleFactura d)    { lista.add(d); }

    @Override
    public DetalleFactura consultar(int id) {
        for (DetalleFactura d : lista) {
            if (d.getIdDetalle() == id) return d;
        }
        return null;
    }

    @Override
    public void actualizar(DetalleFactura nuevo) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getIdDetalle() == nuevo.getIdDetalle()) {
                lista.set(i, nuevo);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        lista.removeIf(d -> d.getIdDetalle() == id);
    }

    @Override
    public List<DetalleFactura> listarTodos() { return lista; }
}
