package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClienteDAO implements IDao<Cliente, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM CLIENTE WHERE 1=1 ";
    private final String SQL_DELETE_CLIENTE = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ";
    private final String SQL_UPDATE_CLIENTE = "UPDATE CLIENTE SET = ";
    private final String SQL_ADD_CLIENTE = "INSERT INTO CLIENTE (ID_CLIENTE, NOMBRE,CONTRASENA, EMAIL, TELEFONO) VALUES";

    @Override
    public int update(Cliente bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = "UPDATE CLIENTE SET ";

            if (bean.getNombre() != null && !bean.getNombre().isEmpty()) {
                sql += "NOMBRE='" + bean.getNombre() + "', ";
            }

            if (bean.getEmail() != null && !bean.getEmail().isEmpty()) {
                sql += "EMAIL='" + bean.getEmail() + "', ";
            }

            if (bean.getContrasena() != null && !bean.getContrasena().isEmpty()) {
                sql += "CONTRASENA='" + bean.getContrasena() + "', ";
            }

            if (bean.getTelefono() != null && !bean.getTelefono().isEmpty()) {
                sql += "TELEFONO='" + bean.getTelefono() + "', ";
            }

            sql = sql.substring(0, sql.length() - 2); // Elimina la coma y el espacio sobrantes
            sql += " WHERE ID_CLIENTE=" + bean.getId_Cliente();

            System.out.println(sql); // Muestra la consulta SQL para depuración
            resp = motor.execute(sql);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        return resp;
    }

    @Override
    public ArrayList<Cliente> findAll(Cliente bean) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Cliente() != 0) {
                    sql += "AND ID_CLIENTE=" + bean.getId_Cliente() + " ";
                }
                if (bean.getNombre() != null) {
                    sql += "AND NOMBRE='" + bean.getNombre() + "' ";
                }
                if (bean.getContrasena() != null) {
                    sql += "AND CONTRASENA='" + bean.getContrasena() + "'";
                }
                if (bean.getContrasena() != null) {
                    sql += "AND EMAIL,='" + bean.getEmail() + "'";
                }
                if (bean.getTelefono() != null) {
                    sql += "AND TELEFONO='" + bean.getTelefono() + "'";
                }

            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setId_Cliente(rs.getInt("ID_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setContrasena(rs.getString("CONTRASENA"));
                cliente.setEmail(rs.getString("EMAIL"));
                cliente.setTelefono(rs.getString("TELEFONO"));


                clientes.add(cliente);
            }


        } catch (Exception ex) {
            clientes.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return clientes;
    }
    public int add(Cliente bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();

            // Generar un nuevo ID_CLIENTE automáticamente
            int nuevoIDCliente = generarNuevoIDCliente();
            bean.setId_Cliente(nuevoIDCliente);

            // Construir la consulta SQL para insertar el cliente
            String sql = SQL_ADD_CLIENTE + "("
                    + bean.getId_Cliente() + ", '"
                    + bean.getNombre() + "', '"
                    + bean.getContrasena() + "', '"
                    + bean.getEmail() + "', '"
                    + bean.getTelefono() + "')";

            // Ejecutar la consulta SQL
            resp = motor.execute(sql);
            System.out.println(sql);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Usuario insertada con exito.");
        }
        return resp;
    }

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        motor.connect();
        try {
            String sql = SQL_DELETE_CLIENTE + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        if (resp > 0) {
            System.out.println("Borrado con exito.");
        } else {
            System.out.println("No se pudo borrar.");
        }
        return resp;
    }

    public int generarNuevoIDCliente() {
        MotorOracle motor = new MotorOracle();
        int nuevoIDCliente = 0;
        try {
            motor.connect();

            // Consultar el máximo ID_CLIENTE en la tabla CLIENTE
            String sql = "SELECT MAX(ID_CLIENTE) AS MAX_ID FROM CLIENTE";
            ResultSet rs = motor.executeQuery(sql);

            // Si hay resultados, obtener el máximo ID_CLIENTE y aumentarlo en uno
            if (rs.next()) {
                int maxID = rs.getInt("MAX_ID");
                nuevoIDCliente = maxID + 1;
            } else {
                // Si no hay resultados (tabla vacía), asignar ID 1
                nuevoIDCliente = 1;
            }
        } catch (Exception e) {
            System.out.println("Error al generar nuevo ID de cliente: " + e.getMessage());
        } finally {
            motor.disconnect();
        }
        return nuevoIDCliente;
    }

    public boolean validateUser(String email, String password) {
        MotorOracle motor = new MotorOracle();
        boolean isValid = false;
        try {
            motor.connect();
            String sql = "SELECT * FROM CLIENTE WHERE EMAIL = '" + email + "' AND CONTRASENA = '" + password + "'";
            ResultSet rs = motor.executeQuery(sql);

            if (rs != null && rs.next()) {
                isValid = true;
            }
        } catch (Exception e) {
            System.out.println("Error while validating user: " + e.getMessage());
        } finally {
            motor.disconnect();
        }
        return isValid;
    }

}
