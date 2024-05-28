package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Cliente;
import Model.ClienteDAO;
import Model.Producto;
import Model.ProductoDAO;

import java.util.ArrayList;

public class ClienteAction implements IAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, String action) {
        String strReturn = "";
        switch (action) {
            case "FIND_ALL":
                strReturn = findAll();
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "DELETE":
                strReturn = delete(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;
            case "LOGIN":
                strReturn = login(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String login(HttpServletRequest request) {
        String email = request.getParameter("EMAIL");
        String password = request.getParameter("CONTRASENA");
        ClienteDAO clienteDAO = new ClienteDAO();

        boolean isValidUser = clienteDAO.validateUser(email, password);

        if (isValidUser) {
            return "{\"status\":\"success\"}";
        } else {
            return "{\"status\":\"error\", \"message\":\"Wrong email or password.\"}";
        }
    }
    private String update(HttpServletRequest request) {
        try {
            int id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));
            String nombre = request.getParameter("NOMBRE");
            String email = request.getParameter("EMAIL");
            String contrasena = request.getParameter("CONTRASENA");
            String telefono = request.getParameter("TELEFONO");

            // Validar que el ID esté presente
            if (id_cliente <= 0) {
                return "Error: El ID del cliente es obligatorio y debe ser un número entero positivo.";
            }

            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setId_Cliente(id_cliente);

            // Only set non-null fields
            if (nombre != null && !nombre.isEmpty()) {
                cliente.setNombre(nombre);
            }
            if (email != null && !email.isEmpty()) {
                cliente.setEmail(email);
            }
            if (contrasena != null && !contrasena.isEmpty()) {
                cliente.setContrasena(contrasena);
            }
            if (telefono != null && !telefono.isEmpty()) {
                cliente.setTelefono(telefono);
            }

            int result = clienteDAO.update(cliente);

            if (result > 0) {
                return "Cliente actualizado con éxito.";
            } else {
                return "Error al actualizar cliente.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del cliente debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }


    private String findAll() {
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<Cliente> clientes = clienteDAO.findAll(null);
        return Cliente.toArrayJSon(clientes);
    }

    private String add(HttpServletRequest request) {
        try {
            String nombre = request.getParameter("NOMBRE");
            String email = request.getParameter("EMAIL");
            String contrasena = request.getParameter("CONTRASENA");
            String telefono = request.getParameter("TELEFONO");

            // Validar que todos los parámetros estén presentes
            if (nombre == null || nombre.isEmpty() ||
                    email == null || email.isEmpty() ||
                    contrasena == null || contrasena.isEmpty() ||
                    telefono == null || telefono.isEmpty()) {
                return "Error: Todos los campos son obligatorios.";
            }

            // Crear un nuevo cliente y establecer sus propiedades
            Cliente cliente = new Cliente();
            cliente.setNombre(nombre);
            cliente.setEmail(email);
            cliente.setContrasena(contrasena);
            cliente.setTelefono(telefono);

            // Generar un nuevo ID_CLIENTE automáticamente (por ejemplo, mediante un método en ClienteDAO)
            ClienteDAO clienteDAO = new ClienteDAO();
            int nuevoIDCliente = clienteDAO.generarNuevoIDCliente(); // Esto es un ejemplo, debes implementar el método correspondiente
            cliente.setId_Cliente(nuevoIDCliente);

            // Llamar al DAO para agregar el cliente
            int result = clienteDAO.add(cliente);

            // Verificar el resultado y devolver el mensaje apropiado
            if (result > 0) {
                return "Successfully registered.";
            } else {
                return "Error al añadir cliente.";
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String delete(HttpServletRequest request) {
        try {

            int id_cliente = Integer.parseInt(request.getParameter("ID_CLIENTE"));

            ClienteDAO clienteDAO = new ClienteDAO();


            ClienteDAO clientesDAO = new ClienteDAO();
            int result = clientesDAO.delete(id_cliente);

            if (result > 0) {
                return "Cliente eliminado con éxito.";
            } else {
                return "Error al eliminar cliente.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del cliente debe ser un número entero.";
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }
}