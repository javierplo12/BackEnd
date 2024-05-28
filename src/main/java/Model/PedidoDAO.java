package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PedidoDAO implements IDao<Pedido, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM PEDIDO WHERE 1=1 ";

    @Override
    public int add(Pedido bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Pedido bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Pedido> findAll(Pedido bean) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getIdPedido() != 0) {
                    sql += "AND ID_PEDIDO=" + bean.getIdPedido() + " ";
                }
                if (bean.getIdCliente() != 0) {
                    sql += "AND ID_CLIENTE=" + bean.getIdCliente() + " ";
                }
                if (bean.getIdEmpleado() != 0) {
                    sql += "AND ID_EMPLEADO=" + bean.getIdEmpleado() + " ";
                }
                if (bean.getFecha() != null) {
                    sql += "AND FECHA=" + new java.sql.Date(bean.getFecha().getTime()) + " ";
                }
                if (bean.getEstadoPedido() != null && !bean.getEstadoPedido().isEmpty()) {
                    sql += "AND ESTADO_PEDIDO='" + bean.getEstadoPedido() + "' ";
                }
                if (bean.getMetodoDePago() != 0) {
                    sql += "AND METODO_DE_PAGO=" + bean.getMetodoDePago() + " ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pedido pedido = new Pedido();

                pedido.setIdPedido(rs.getInt("ID_PEDIDO"));
                pedido.setIdCliente(rs.getInt("ID_CLIENTE"));
                pedido.setIdEmpleado(rs.getInt("ID_EMPLEADO"));
                pedido.setFecha(rs.getDate("FECHA"));
                pedido.setEstadoPedido(rs.getString("ESTADO_PEDIDO"));
                pedido.setMetodoDePago(rs.getInt("METODO_DE_PAGO"));

                pedidos.add(pedido);
            }

        } catch (Exception ex) {
            pedidos.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return pedidos;
    }
}
