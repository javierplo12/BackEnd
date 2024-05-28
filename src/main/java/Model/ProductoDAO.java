package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoDAO implements IDao<Producto, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PRODUCTO WHERE 1=1 ORDER BY ID_PRODUCTO";

    private final String SQL_DELETE_PRODUCTO = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO = ";
    private final String SQL_UPGRADE_PRODUCTO = "UPDATE PRODUCTO SET = ";
    private final String SQL_ADD_PRODUCTO = "INSERT INTO PRODUCTO (ID_PRODUCTO, NOMBRE, DESCRIPCION, PRECIO, ID_CATEGORIA) VALUES";

    @Override
    public int delete(Integer id) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_DELETE_PRODUCTO + id;
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
    public int update(Producto bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = "UPDATE PRODUCTO SET ";

            if (bean.getNombre() != null && !bean.getNombre().isEmpty()) {
                sql += "NOMBRE='" + bean.getNombre() + "', ";
            }
            if (bean.getDescripcion() != null && !bean.getDescripcion().isEmpty()) {
                sql += "DESCRIPCION='" + bean.getDescripcion() + "', ";
            }
            if (bean.getPrecio() != 0) {
                sql += "PRECIO=" + bean.getPrecio() + ", ";
            }
            if (bean.getId_categoria() != 0) {
                sql += "ID_CATEGORIA=" + bean.getId_categoria() + ", ";
            }

            // Eliminar la coma y el espacio al final de la cadena
            sql = sql.substring(0, sql.length() - 2);

            sql += " WHERE ID_PRODUCTO=" + bean.getId_Producto();

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
    public ArrayList<Producto> findAll(Producto bean) {
        ArrayList<Producto> productos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Producto() != 0) {
                    sql += "AND ID_PRODUCTO=" + bean.getId_Producto() + " ";
                }
                if (bean.getNombre() != null) {
                    sql += "AND NOMBRE='" + bean.getNombre() + "' ";
                }
                if (bean.getDescripcion() != null) {
                    sql += "AND DESCRIPCION='" + bean.getDescripcion() + "' ";
                }
                if (bean.getPrecio() != 0) {
                    sql += "AND PRECIO='" + bean.getPrecio() + "' ";
                }
                if (bean.getId_categoria() != 0) {
                    sql += "AND ID_CATEGORIA='" + bean.getId_categoria() + "' ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_Producto(rs.getInt("ID_PRODUCTO"));
                producto.setNombre(rs.getString("NOMBRE"));
                producto.setDescripcion(rs.getString("DESCRIPCION"));
                producto.setPrecio(rs.getDouble("PRECIO"));
                producto.setId_categoria(rs.getInt("ID_CATEGORIA"));
                productos.add(producto);
            }

        } catch (Exception ex) {
            productos.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return productos;
    }

    public int add(Producto bean) {
        int resp = 0;
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_ADD_PRODUCTO + "("
                    + bean.getId_Producto() + ", '"
                    + bean.getNombre() + "', '"
                    + bean.getDescripcion() + "', "
                    + bean.getPrecio() + ", "
                    + bean.getId_categoria() + ")";
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
