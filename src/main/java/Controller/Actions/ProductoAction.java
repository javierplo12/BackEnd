package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.*;
import java.util.ArrayList;

public class ProductoAction implements IAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "FIND_FIRST":
                // Implementar lógica para FIND_FIRST si es necesario
                strReturn = "FIND_FIRST action not implemented.";
                break;
            case "FIND_ALL":
                strReturn = findAll();
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "DELETE":
                strReturn = delete(request);

            case "UPDATE":
                strReturn = add(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        ProductoDAO productoDAO = new ProductoDAO();
        ArrayList<Producto> productos = productoDAO.findAll(null);
        return Producto.toArrayJSon(productos);
    }

    private String add(HttpServletRequest request) {
        try {
            int id_producto = Integer.parseInt(request.getParameter("ID_PRODUCTO"));
            String nombre = request.getParameter("NOMBRE");
            String descripcion = request.getParameter("DESCRIPCION");
            String precio = request.getParameter("PRECIO");
            String id_categoria = request.getParameter("ID_CATEGORIA");

            // Validar que todos los parámetros estén presentes
            if (nombre == null || nombre.isEmpty() ||
                    descripcion == null || descripcion.isEmpty() ||
                    precio == null || precio.isEmpty() ||
                    id_categoria == null || id_categoria.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            // Crear un nuevo producto y establecer sus propiedades
            Producto producto = new Producto();
            producto.setId_Producto(id_producto);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(Double.parseDouble(precio));
            producto.setId_categoria(Integer.parseInt(id_categoria));

            // Llamar al DAO para agregar el producto
            ProductoDAO productoDAO = new ProductoDAO();
            int result = productoDAO.add(producto);

            // Verificar el resultado y devolver el mensaje apropiado
            if (result > 0) {
                return "Producto añadido con éxito.";
            } else {
                return "Error al añadir producto.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del producto y el ID de la categoría deben ser números enteros.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    private String delete(HttpServletRequest request) {
        try {

            int id_producto = Integer.parseInt(request.getParameter("ID_PRODUCTO"));

            ProductoDAO productoDAO = new ProductoDAO();


            productoDAO = new ProductoDAO();
            int result = productoDAO.delete(id_producto);

            if (result > 0) {
                return "Producto eliminado con éxito.";
            } else {
                return "Error al eliminar producto.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del producto debe ser un número entero.";
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }
    private String update(HttpServletRequest request) {
        try {
            int id_producto = Integer.parseInt(request.getParameter("ID_PRODUCTO"));
            String nombre = request.getParameter("NOMBRE");
            String descripcion = request.getParameter("DESCRIPCION");
            String precio = request.getParameter("PRECIO");
            String id_categoria = request.getParameter("ID_CATEGORIA");

            // Validar que el ID esté presente
            if (id_producto <= 0) {
                return "Error: El ID del producto es obligatorio y debe ser un número entero positivo.";
            }

            // Validar que todos los campos estén presentes
            if (nombre == null || nombre.isEmpty() ||
                    descripcion == null || descripcion.isEmpty() ||
                    precio == null || precio.isEmpty() ||
                    id_categoria == null || id_categoria.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            // Crear un objeto Producto y establecer sus propiedades
            Producto producto = new Producto();
            producto.setId_Producto(id_producto);
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setPrecio(Double.parseDouble(precio));
            producto.setId_categoria(Integer.parseInt(id_categoria));

            // Llamar al DAO para actualizar el producto
            ProductoDAO productoDAO = new ProductoDAO();
            int result = productoDAO.update(producto);

            // Verificar el resultado y devolver el mensaje apropiado
            if (result > 0) {
                return "Producto actualizado con éxito.";
            } else {
                return "Error al actualizar producto.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del producto y el ID de la categoría deben ser números enteros.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
