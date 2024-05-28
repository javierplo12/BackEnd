package Model;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaDAO implements IDao<Categoria, Integer> {
    private final String SQL_FIND_ALL = "SELECT * FROM CATEGORIA WHERE 1=1 ";

    @Override
    public int add(Categoria bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int update(Categoria bean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Categoria> findAll(Categoria bean) {
        ArrayList<Categoria> categorias = new ArrayList<>();
        MotorOracle motor = new MotorOracle();
        try {
            motor.connect();
            String sql = SQL_FIND_ALL;
            if (bean != null) {
                if (bean.getId_Categoria() != 0) {
                    sql += "AND ID_CATEGORIA=" + bean.getId_Categoria() + " ";
                }
                if (bean.getNombre() != null) {
                    sql += "AND NOMBRE='" + bean.getNombre() + "' ";
                }
            }
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Categoria categoria = new Categoria();

                categoria.setId_Categoria(rs.getInt("ID_CATEGORIA"));
                categoria.setNombre(rs.getString("NOMBRE"));


                categorias.add(categoria);
            }

        } catch (Exception ex) {
            categorias.clear();
            ex.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return categorias;
    }
}
