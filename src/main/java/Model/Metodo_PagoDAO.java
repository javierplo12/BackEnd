package Model;

import Controller.Actions.Metodo_PagoAction;

import java.sql.ResultSet;
import java.util.ArrayList;

public class Metodo_PagoDAO implements IDao<Metodo_Pago, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM METODO_PAGO WHERE 1=1 ";

    @Override
    public int add(Metodo_Pago bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Metodo_Pago bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Metodo_Pago> findAll(Metodo_Pago bean) {
        ArrayList<Metodo_Pago> metodoPagos = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Metodo_Pago() != 0) {
                    sql += "AND ID_EMPLEADO=" + bean.getId_Metodo_Pago() + " ";
                }
                if (bean.getNombre() != null && !bean.getNombre().isEmpty()) {
                    sql += "AND NOMBRE='" + bean.getNombre() + "' ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Metodo_Pago metodoPago = new Metodo_Pago();

                metodoPago.setId_Categoria(rs.getInt("ID_Metodo_Pago"));
                metodoPago.setNombre(rs.getString("NOMBRE"));


                metodoPagos.add(metodoPago);
            }

        } catch (Exception ex) {
            metodoPagos.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return metodoPagos;
    }
}
