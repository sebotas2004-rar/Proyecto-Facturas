package co.edu.uptc.run;

import co.edu.uptc.models.*;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Clase principal del sistema de facturación.
 * Implementa CRUD completo con JOptionPane para todas las entidades:
 *   Factura, Cliente, Vendedor, Producto, DetalleFactura
 *   
 */
public class Main {

    static FacturaRepositorio       repoFactura   = new FacturaRepositorio();
    static ClienteRepositorio       repoCliente   = new ClienteRepositorio();
    static VendedorRepositorio      repoVendedor  = new VendedorRepositorio();
    static ProductoRepositorio      repoProducto  = new ProductoRepositorio();
    static DetalleFacturaRepositorio repoDetalle  = new DetalleFacturaRepositorio();

    // ────────────────────────────────────────────────
    //  PUNTO DE ENTRADA
    // ────────────────────────────────────────────────
    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            String[] opciones = {
                "Gestionar Clientes",
                "Gestionar Vendedores",
                "Gestionar Productos",
                "Gestionar Facturas",
                "Gestionar Detalles de Factura",
                "Salir"
            };
            int opcion = JOptionPane.showOptionDialog(
                null,
                "=== SISTEMA DE FACTURACIÓN ===\nSeleccione un módulo:",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]
            );

            switch (opcion) {
                case 0: menuCliente();   break;
                case 1: menuVendedor();  break;
                case 2: menuProducto();  break;
                case 3: menuFactura();   break;
                case 4: menuDetalle();   break;
                case 5:
                default:
                    salir = true;
                    JOptionPane.showMessageDialog(null,
                        "¡Hasta luego!", "Sistema cerrado",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // ════════════════════════════════════════════════
    //  MÓDULO CLIENTE
    // ════════════════════════════════════════════════
    static void menuCliente() {
        String[] ops = {"Crear", "Consultar por ID", "Actualizar", "Eliminar", "Listar todos", "Volver"};
        int op = JOptionPane.showOptionDialog(null,
            "── Módulo CLIENTE ──", "Clientes",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, ops, ops[0]);

        switch (op) {
            case 0: crearCliente();         break;
            case 1: consultarCliente();     break;
            case 2: actualizarCliente();    break;
            case 3: eliminarCliente();      break;
            case 4: listarClientes();       break;
        }
    }

    static void crearCliente() {
        try {
            int    id  = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente:"));
            String nom = JOptionPane.showInputDialog("Nombre:");
            String em  = JOptionPane.showInputDialog("Email:");
            String tel = JOptionPane.showInputDialog("Teléfono:");
            String dir = JOptionPane.showInputDialog("Dirección:");

            Cliente c = new Cliente(id, nom, em, tel, dir);
            repoCliente.crear(c);
            JOptionPane.showMessageDialog(null,
                "Cliente creado exitosamente:\n" + c,
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                "Error al crear cliente: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void consultarCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente a consultar:"));
            Cliente c = repoCliente.consultar(id);
            if (c != null)
                JOptionPane.showMessageDialog(null, "Cliente encontrado:\n" + c, "Consulta", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No se encontró cliente con ID " + id, "No encontrado", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void actualizarCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente a actualizar:"));
            Cliente c = repoCliente.consultar(id);
            if (c == null) { JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            String nom = JOptionPane.showInputDialog("Nuevo nombre:", c.getNombre());
            String em  = JOptionPane.showInputDialog("Nuevo email:", c.getEmail());
            String tel = JOptionPane.showInputDialog("Nuevo teléfono:", c.getTelefono());
            String dir = JOptionPane.showInputDialog("Nueva dirección:", c.getDireccion());

            c.setNombre(nom); c.setEmail(em); c.setTelefono(tel); c.setDireccion(dir);
            repoCliente.actualizar(c);
            JOptionPane.showMessageDialog(null, "Cliente actualizado:\n" + c, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void eliminarCliente() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente a eliminar:"));
            int conf = JOptionPane.showConfirmDialog(null, "¿Eliminar cliente ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                repoCliente.eliminar(id);
                JOptionPane.showMessageDialog(null, "Cliente eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void listarClientes() {
        List<Cliente> lista = repoCliente.listarTodos();
        if (lista.isEmpty()) { JOptionPane.showMessageDialog(null, "No hay clientes registrados.", "Lista vacía", JOptionPane.INFORMATION_MESSAGE); return; }
        StringBuilder sb = new StringBuilder("── Clientes registrados ──\n");
        for (Cliente c : lista) sb.append(c).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString(), "Clientes", JOptionPane.PLAIN_MESSAGE);
    }

    // ════════════════════════════════════════════════
    //  MÓDULO VENDEDOR
    // ════════════════════════════════════════════════
    static void menuVendedor() {
        String[] ops = {"Crear", "Consultar por ID", "Actualizar", "Eliminar", "Listar todos", "Volver"};
        int op = JOptionPane.showOptionDialog(null,
            "── Módulo VENDEDOR ──", "Vendedores",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, ops, ops[0]);

        switch (op) {
            case 0: crearVendedor();      break;
            case 1: consultarVendedor();  break;
            case 2: actualizarVendedor(); break;
            case 3: eliminarVendedor();   break;
            case 4: listarVendedores();   break;
        }
    }

    static void crearVendedor() {
        try {
            int    id  = Integer.parseInt(JOptionPane.showInputDialog("ID del vendedor:"));
            String nom = JOptionPane.showInputDialog("Nombre:");
            String zon = JOptionPane.showInputDialog("Zona:");
            double com = Double.parseDouble(JOptionPane.showInputDialog("Comisión (%):"));
            int    act = JOptionPane.showConfirmDialog(null, "¿Está activo?", "Estado", JOptionPane.YES_NO_OPTION);

            Vendedor v = new Vendedor(id, nom, zon, com, act == JOptionPane.YES_OPTION);
            repoVendedor.crear(v);
            JOptionPane.showMessageDialog(null, "Vendedor creado:\n" + v, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void consultarVendedor() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del vendedor:"));
            Vendedor v = repoVendedor.consultar(id);
            if (v != null)
                JOptionPane.showMessageDialog(null, "Vendedor encontrado:\n" + v, "Consulta", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No se encontró vendedor con ID " + id, "No encontrado", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void actualizarVendedor() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del vendedor a actualizar:"));
            Vendedor v = repoVendedor.consultar(id);
            if (v == null) { JOptionPane.showMessageDialog(null, "Vendedor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            String nom = JOptionPane.showInputDialog("Nuevo nombre:", v.getNombreV());
            String zon = JOptionPane.showInputDialog("Nueva zona:", v.getZona());
            double com = Double.parseDouble(JOptionPane.showInputDialog("Nueva comisión (%):", v.getComision()));
            int    act = JOptionPane.showConfirmDialog(null, "¿Activo?", "Estado", JOptionPane.YES_NO_OPTION);

            v.setNombreV(nom); v.setZona(zon); v.setComision(com); v.setActivo(act == JOptionPane.YES_OPTION);
            repoVendedor.actualizar(v);
            JOptionPane.showMessageDialog(null, "Vendedor actualizado:\n" + v, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void eliminarVendedor() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del vendedor a eliminar:"));
            int conf = JOptionPane.showConfirmDialog(null, "¿Eliminar vendedor ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                repoVendedor.eliminar(id);
                JOptionPane.showMessageDialog(null, "Vendedor eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void listarVendedores() {
        List<Vendedor> lista = repoVendedor.listarTodos();
        if (lista.isEmpty()) { JOptionPane.showMessageDialog(null, "No hay vendedores registrados.", "Lista vacía", JOptionPane.INFORMATION_MESSAGE); return; }
        StringBuilder sb = new StringBuilder("── Vendedores registrados ──\n");
        for (Vendedor v : lista) sb.append(v).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString(), "Vendedores", JOptionPane.PLAIN_MESSAGE);
    }

    // ════════════════════════════════════════════════
    //  MÓDULO PRODUCTO
    // ════════════════════════════════════════════════
    static void menuProducto() {
        String[] ops = {"Crear", "Consultar por ID", "Actualizar", "Eliminar", "Listar todos", "Volver"};
        int op = JOptionPane.showOptionDialog(null,
            "── Módulo PRODUCTO ──", "Productos",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, ops, ops[0]);

        switch (op) {
            case 0: crearProducto();      break;
            case 1: consultarProducto();  break;
            case 2: actualizarProducto(); break;
            case 3: eliminarProducto();   break;
            case 4: listarProductos();    break;
        }
    }

    static void crearProducto() {
        try {
            int    id  = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
            String nom = JOptionPane.showInputDialog("Nombre:");
            double pre = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
            int    sto = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));
            String cat = JOptionPane.showInputDialog("Categoría:");

            Producto p = new Producto(id, nom, pre, sto, cat);
            repoProducto.crear(p);
            JOptionPane.showMessageDialog(null, "Producto creado:\n" + p, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void consultarProducto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
            Producto p = repoProducto.consultar(id);
            if (p != null)
                JOptionPane.showMessageDialog(null, "Producto encontrado:\n" + p, "Consulta", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No se encontró producto con ID " + id, "No encontrado", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void actualizarProducto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar:"));
            Producto p = repoProducto.consultar(id);
            if (p == null) { JOptionPane.showMessageDialog(null, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            String nom = JOptionPane.showInputDialog("Nuevo nombre:", p.getNombre());
            double pre = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio:", p.getPrecio()));
            int    sto = Integer.parseInt(JOptionPane.showInputDialog("Nuevo stock:", p.getStock()));
            String cat = JOptionPane.showInputDialog("Nueva categoría:", p.getCategoria());

            p.setNombre(nom); p.setPrecio(pre); p.setStock(sto); p.setCategoria(cat);
            repoProducto.actualizar(p);
            JOptionPane.showMessageDialog(null, "Producto actualizado:\n" + p, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void eliminarProducto() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));
            int conf = JOptionPane.showConfirmDialog(null, "¿Eliminar producto ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                repoProducto.eliminar(id);
                JOptionPane.showMessageDialog(null, "Producto eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void listarProductos() {
        List<Producto> lista = repoProducto.listarTodos();
        if (lista.isEmpty()) { JOptionPane.showMessageDialog(null, "No hay productos registrados.", "Lista vacía", JOptionPane.INFORMATION_MESSAGE); return; }
        StringBuilder sb = new StringBuilder("── Productos registrados ──\n");
        for (Producto p : lista) sb.append(p).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString(), "Productos", JOptionPane.PLAIN_MESSAGE);
    }

    // ════════════════════════════════════════════════
    //  MÓDULO FACTURA
    // ════════════════════════════════════════════════
    static void menuFactura() {
        String[] ops = {"Crear", "Consultar por ID", "Actualizar", "Eliminar", "Listar todas", "Volver"};
        int op = JOptionPane.showOptionDialog(null,
            "── Módulo FACTURA ──", "Facturas",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, ops, ops[0]);

        switch (op) {
            case 0: crearFactura();      break;
            case 1: consultarFactura();  break;
            case 2: actualizarFactura(); break;
            case 3: eliminarFactura();   break;
            case 4: listarFacturas();    break;
        }
    }

    static void crearFactura() {
        try {
            int    id  = Integer.parseInt(JOptionPane.showInputDialog("ID de la factura:"));
            String num = JOptionPane.showInputDialog("Número de factura (ej. FAC-001):");
            String fec = JOptionPane.showInputDialog("Fecha (dd/mm/aaaa):");
            String mon = JOptionPane.showInputDialog("Moneda (ej. COP):");
            String est = JOptionPane.showInputDialog("Estado (Pendiente/Pagada/Anulada):");
            double imp = Double.parseDouble(JOptionPane.showInputDialog("IVA (%):"));
            double des = Double.parseDouble(JOptionPane.showInputDialog("Descuento (%):"));

            // Asociar Cliente
            listarClientes();
            int idCli = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente para esta factura:"));
            Cliente cli = repoCliente.consultar(idCli);
            if (cli == null) { JOptionPane.showMessageDialog(null, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            // Asociar Vendedor (agregación)
            listarVendedores();
            int idVen = Integer.parseInt(JOptionPane.showInputDialog("ID del vendedor para esta factura:"));
            Vendedor ven = repoVendedor.consultar(idVen);
            if (ven == null) { JOptionPane.showMessageDialog(null, "Vendedor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            Factura f = new Factura(id, num, fec, mon, est, imp, des, cli, ven);
            repoFactura.crear(f);
            JOptionPane.showMessageDialog(null, "Factura creada:\n" + f, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void consultarFactura() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID de la factura a consultar:"));
            Factura f = repoFactura.consultar(id);
            if (f != null) {
                StringBuilder sb = new StringBuilder("Factura encontrada:\n" + f + "\n\nDetalles:\n");
                for (DetalleFactura d : f.getDetalles()) sb.append("  ").append(d).append("\n");
                JOptionPane.showMessageDialog(null, sb.toString(), "Consulta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró factura con ID " + id, "No encontrado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void actualizarFactura() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID de la factura a actualizar:"));
            Factura f = repoFactura.consultar(id);
            if (f == null) { JOptionPane.showMessageDialog(null, "Factura no encontrada.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            String fec = JOptionPane.showInputDialog("Nueva fecha:", f.getFecha());
            String est = JOptionPane.showInputDialog("Nuevo estado:", f.getEstado());
            double imp = Double.parseDouble(JOptionPane.showInputDialog("Nuevo IVA (%):", f.getImpuesto()));
            double des = Double.parseDouble(JOptionPane.showInputDialog("Nuevo descuento (%):", f.getDescuento()));

            f.setFecha(fec); f.setEstado(est); f.setImpuesto(imp); f.setDescuento(des);
            f.calcularTotal();
            repoFactura.actualizar(f);
            JOptionPane.showMessageDialog(null, "Factura actualizada:\n" + f, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void eliminarFactura() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID de la factura a eliminar:"));
            int conf = JOptionPane.showConfirmDialog(null,
                "¿Eliminar factura ID " + id + "?\nTambién se eliminarán sus detalles.",
                "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                repoFactura.eliminar(id);
                JOptionPane.showMessageDialog(null, "Factura eliminada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void listarFacturas() {
        List<Factura> lista = repoFactura.listarTodos();
        if (lista.isEmpty()) { JOptionPane.showMessageDialog(null, "No hay facturas registradas.", "Lista vacía", JOptionPane.INFORMATION_MESSAGE); return; }
        StringBuilder sb = new StringBuilder("── Facturas registradas ──\n");
        for (Factura f : lista) sb.append(f).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString(), "Facturas", JOptionPane.PLAIN_MESSAGE);
    }

    // ════════════════════════════════════════════════
    //  MÓDULO DETALLE FACTURA
    // ════════════════════════════════════════════════
    static void menuDetalle() {
        String[] ops = {"Agregar detalle a factura", "Consultar por ID", "Actualizar", "Eliminar", "Listar todos", "Volver"};
        int op = JOptionPane.showOptionDialog(null,
            "── Módulo DETALLE DE FACTURA ──", "Detalles",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, ops, ops[0]);

        switch (op) {
            case 0: crearDetalle();      break;
            case 1: consultarDetalle();  break;
            case 2: actualizarDetalle(); break;
            case 3: eliminarDetalle();   break;
            case 4: listarDetalles();    break;
        }
    }

    static void crearDetalle() {
        try {
            // Mostrar facturas disponibles
            listarFacturas();
            int idFac = Integer.parseInt(JOptionPane.showInputDialog("ID de la factura a la que pertenece el detalle:"));
            Factura f = repoFactura.consultar(idFac);
            if (f == null) { JOptionPane.showMessageDialog(null, "Factura no encontrada.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            int    id  = Integer.parseInt(JOptionPane.showInputDialog("ID del detalle:"));
            String des = JOptionPane.showInputDialog("Descripción del ítem:");
            int    can = Integer.parseInt(JOptionPane.showInputDialog("Cantidad:"));
            double pre = Double.parseDouble(JOptionPane.showInputDialog("Precio unitario:"));

            // Asociar Producto
            listarProductos();
            int idPro = Integer.parseInt(JOptionPane.showInputDialog("ID del producto (0 si no aplica):"));
            Producto prod = (idPro > 0) ? repoProducto.consultar(idPro) : null;

            DetalleFactura det = new DetalleFactura(id, can, pre, des, prod);
            repoDetalle.crear(det);

            // Composición: agregar el detalle a la factura
            f.agregarDetalle(det);
            repoFactura.actualizar(f);

            JOptionPane.showMessageDialog(null,
                "Detalle agregado:\n" + det + "\nTotal factura actualizado: $" + f.getTotalPagar(),
                "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void consultarDetalle() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del detalle a consultar:"));
            DetalleFactura d = repoDetalle.consultar(id);
            if (d != null)
                JOptionPane.showMessageDialog(null, "Detalle encontrado:\n" + d, "Consulta", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "No se encontró detalle con ID " + id, "No encontrado", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void actualizarDetalle() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del detalle a actualizar:"));
            DetalleFactura d = repoDetalle.consultar(id);
            if (d == null) { JOptionPane.showMessageDialog(null, "Detalle no encontrado.", "Error", JOptionPane.ERROR_MESSAGE); return; }

            String des = JOptionPane.showInputDialog("Nueva descripción:", d.getDescripcion());
            int    can = Integer.parseInt(JOptionPane.showInputDialog("Nueva cantidad:", d.getCantidad()));
            double pre = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio unitario:", d.getPrecioUnitario()));

            d.setDescripcion(des); d.setCantidad(can); d.setPrecioUnitario(pre);
            repoDetalle.actualizar(d);
            JOptionPane.showMessageDialog(null, "Detalle actualizado:\n" + d, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void eliminarDetalle() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del detalle a eliminar:"));
            int conf = JOptionPane.showConfirmDialog(null, "¿Eliminar detalle ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (conf == JOptionPane.YES_OPTION) {
                repoDetalle.eliminar(id);
                JOptionPane.showMessageDialog(null, "Detalle eliminado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    static void listarDetalles() {
        List<DetalleFactura> lista = repoDetalle.listarTodos();
        if (lista.isEmpty()) { JOptionPane.showMessageDialog(null, "No hay detalles registrados.", "Lista vacía", JOptionPane.INFORMATION_MESSAGE); return; }
        StringBuilder sb = new StringBuilder("── Detalles registrados ──\n");
        for (DetalleFactura d : lista) sb.append(d).append("\n");
        JOptionPane.showMessageDialog(null, sb.toString(), "Detalles", JOptionPane.PLAIN_MESSAGE);
    }
}
