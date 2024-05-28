package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class EmpleadoDAO implements IDao<Empleado, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM EMPLEADO WHERE 1=1 ";
    private final String SQL_DELETE_EMPLEADO = "DELETE FROM EMPLEADO WHERE ID_EMPLEADO = ";
    private final String SQL_UPDATE_EMPLEADO = "UPDATE EMPLEADO SET = ";
    private final String SQL_ADD_EMPLEADO = "INSERT INTO EMPLEADO (ID_EMPLEADO, NOMBRE, APELLIDO, CONTRASENA, DNI,PUESTO, TELEFONO ) VALUES";

    

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE_EMPLEADO + id;
            motor.execute("SET FOREIGN_KEY_CHECKS=0;");
            resp = motor.execute(sql);
            motor.execute("SET FOREIGN_KEY_CHECKS=1;");
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        return resp;
    }
    @Override
    public int update(Empleado bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = "UPDATE EMPLEADO SET ";

            if (bean.getNombre() != null && !bean.getNombre().isEmpty()) {
                sql += "NOMBRE='" + bean.getNombre() + "', ";
            }

            if (bean.getApellido() != null && !bean.getApellido().isEmpty()) {
                sql += "APELLIDO='" + bean.getApellido() + "', ";
            }

            if (bean.getContrasena() != null && !bean.getContrasena().isEmpty()) {
                sql += "CONTRASENA='" + bean.getContrasena() + "', ";
            }

            if (bean.getDNI() != null && !bean.getDNI().isEmpty()) {
                sql += "DNI='" + bean.getDNI() + "', ";
            }

            if (bean.getPuesto() != null && !bean.getPuesto().isEmpty()) {
                sql += "PUESTO='" + bean.getPuesto() + "', ";
            }

            if (bean.getTelefono() != null && !bean.getTelefono().isEmpty()) {
                sql += "TELEFONO='" + bean.getTelefono() + "', ";
            }

            sql = sql.substring(0, sql.length() - 2); // Elimina la coma y el espacio sobrantes
            sql += " WHERE ID_EMPLEADO=" + bean.getId_Empleado();

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
    public ArrayList<Empleado> findAll(Empleado bean) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Empleado() != 0) {
                    sql += "AND ID_EMPLEADO=" + bean.getId_Empleado() + " ";
                }
                if (bean.getNombre() != null && !bean.getNombre().isEmpty()) {
                    sql += "AND NOMBRE='" + bean.getNombre() + "' ";
                }
                if (bean.getApellido() != null && !bean.getApellido().isEmpty()) {
                    sql += "AND APELLIDO='" + bean.getApellido() + "' ";
                }
                if (bean.getContrasena() != null && !bean.getContrasena().isEmpty()) {
                    sql += "AND CONTRASENA='" + bean.getContrasena() + "' ";
                }
                if (bean.getDNI() != null && !bean.getDNI().isEmpty()) {
                    sql += "AND DNI='" + bean.getDNI() + "' ";
                }
                if (bean.getPuesto() != null && !bean.getPuesto().isEmpty()) {
                    sql += "AND PUESTO='" + bean.getPuesto() + "' ";
                }
                if (bean.getTelefono() != null && !bean.getTelefono().isEmpty()) {
                    sql += "AND TELEFONO='" + bean.getTelefono() + "' ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Empleado empleado = new Empleado();

                empleado.setId_Empleado(rs.getInt("ID_EMPLEADO"));
                empleado.setNombre(rs.getString("NOMBRE"));
                empleado.setApellido(rs.getString("APELLIDO"));
                empleado.setContrasena(rs.getString("CONTRASENA"));
                empleado.setDNI(rs.getString("DNI"));
                empleado.setPuesto(rs.getString("PUESTO"));
                empleado.setTelefono(rs.getString("TELEFONO"));

                empleados.add(empleado);
            }

        } catch (Exception ex) {
            empleados.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return empleados;
    }
    @Override
    public int add(Empleado bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_ADD_EMPLEADO + "("
                    + bean.getId_Empleado() + ", '"
                    + bean.getNombre() + "', '"
                    + bean.getApellido() + "', '"
                    + bean.getContrasena() + "', '"
                    + bean.getDNI() + "', '"
                    + bean.getPuesto() + "', '"
                    + bean.getTelefono() + "')";
            resp = motor.execute(sql);
            System.out.println(sql);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            motor.disconnect();
        }
        return resp;
    }


}
