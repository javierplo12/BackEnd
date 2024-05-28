package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Model.Empleado;
import Model.EmpleadoDAO;
import java.util.ArrayList;

public class EmpleadoAction implements IAction {
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
            case "DELETE":
                strReturn = delete(request);
                break;
            case "ADD":
                strReturn = add(request);
                break;
            case "UPDATE":
                strReturn = update(request);
                break;
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        ArrayList<Empleado> empleados = empleadoDAO.findAll(null);
        return Empleado.toArrayJSon(empleados);
    }

    private String delete(HttpServletRequest request) {
        try {
            int id_empleado = Integer.parseInt(request.getParameter("ID_EMPLEADO"));
            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            int result = empleadoDAO.delete(id_empleado);

            if (result > 0) {
                return "Empleado eliminado con éxito.";
            } else {
                return "Error al eliminar Empleado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del empleado debe ser un número entero.";
        } catch (Exception e) {
            return "Error:" + e.getMessage();
        }
    }

    private String add(HttpServletRequest request) {
        try {
            int id_empleado = Integer.parseInt(request.getParameter("ID_EMPLEADO"));
            String nombre = request.getParameter("NOMBRE");
            String apellido = request.getParameter("APELLIDO");
            String contrasena = request.getParameter("CONTRASENA");
            String dni = request.getParameter("DNI");
            String puesto = request.getParameter("PUESTO");
            String telefono = request.getParameter("TELEFONO");

            Empleado empleado = new Empleado();
            empleado.setId_Empleado(id_empleado);
            empleado.setNombre(nombre);
            empleado.setApellido(apellido);
            empleado.setContrasena(contrasena);
            empleado.setDNI(dni);
            empleado.setPuesto(puesto);
            empleado.setTelefono(telefono);

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            int result = empleadoDAO.add(empleado);

            if (result > 0) {
                return "Empleado agregado con éxito.";
            } else {
                return "Error al agregar Empleado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del empleado debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    private String update(HttpServletRequest request) {
        try {
            int id_empleado = Integer.parseInt(request.getParameter("ID_EMPLEADO"));
            String nombre = request.getParameter("NOMBRE");
            String apellido = request.getParameter("APELLIDO");
            String dni = request.getParameter("DNI");
            String telefono = request.getParameter("TELEFONO");
            String puesto = request.getParameter("PUESTO");

            // Validar que el ID esté presente
            if (id_empleado <= 0) {
                return "Error: El ID del empleado es obligatorio y debe ser un número entero positivo.";
            }

            EmpleadoDAO empleadoDAO = new EmpleadoDAO();
            Empleado empleado = new Empleado();
            empleado.setId_Empleado(id_empleado);

            // Only set non-null fields
            if (nombre != null && !nombre.isEmpty()) {
                empleado.setNombre(nombre);
            }
            if (apellido != null && !apellido.isEmpty()) {
                empleado.setApellido(apellido);
            }
            if (dni != null && !dni.isEmpty()) {
                empleado.setDNI(dni);
            }
            if (telefono != null && !telefono.isEmpty()) {
                empleado.setTelefono(telefono);
            }
            if (empleado.getPuesto() != null && !puesto.isEmpty()) {
                empleado.setPuesto(puesto);
            }

            int result = empleadoDAO.update(empleado);

            if (result > 0) {
                return "Empleado actualizado con éxito.";
            } else {
                return "Error al actualizar empleado.";
            }
        } catch (NumberFormatException e) {
            return "Error: El ID del empleado debe ser un número entero.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

}
