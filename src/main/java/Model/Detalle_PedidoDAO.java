package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Detalle_PedidoDAO implements IDao<Detalle_Pedido, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM DETALLE_PEDIDO WHERE 1=1 ";

    @Override
    public int add(Detalle_Pedido bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Detalle_Pedido bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Detalle_Pedido> findAll(Detalle_Pedido bean) {
        ArrayList<Detalle_Pedido> detallePedidos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Detalle() != 0) {
                    sql += "AND ID_DETALLE=" + bean.getId_Detalle() + " ";
                }
                if (bean.getId_Producto() != 0) {
                    sql += "AND ID_PRODUCTO='" + bean.getId_Producto() + "' ";
                }
                if (bean.getId_Pedido() != 0) {
                    sql += "AND ID_PEDIDO='" + bean.getId_Pedido() + "' ";
                }
                if (bean.getCantidad() != 0) {
                    sql += "AND CANTIDAD='" + bean.getCantidad() + "' ";
                }
                if (bean.getPrecio() != 0) {
                    sql += "AND PRECIO='" + bean.getPrecio() + "' ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Detalle_Pedido detallePedido = new Detalle_Pedido();

                detallePedido.setId_Detalle(rs.getInt("ID_CATEGORIA"));
                detallePedido.setId_Producto(rs.getInt("ID_PRODUCTO"));
                detallePedido.setId_Pedido(rs.getInt("ID_PEDIDO"));
                detallePedido.setCantidad(rs.getInt("CANTIDAD"));
                detallePedido.setPrecio(rs.getDouble("PRECIO"));


                detallePedidos.add(detallePedido);
            }

        } catch (Exception ex) {
            detallePedidos.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return detallePedidos;
    }



}
