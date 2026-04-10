package co.edu.uptc.models;

import java.util.List;

/**
 * Interfaz genérica que define las operaciones del CRUD para cualquier entidad.
 */
public abstract class BaseClass<T> {
    public abstract void    crear(T t);
    public abstract T       consultar(int id);
    public abstract void    actualizar(T t);
    public abstract void    eliminar(int id);
    public abstract List<T> listarTodos();
}
