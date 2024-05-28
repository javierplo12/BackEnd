package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Categoria;
import Model.CategoriaDAO;
import Model.Empleado;
import Model.EmpleadoDAO;
import java.util.ArrayList;

public class CategoriaAction implements IAction {
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
           /* case "ADD":
                strReturn = Add(request);
                break;*/
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
         CategoriaDAO CategoriaDao = new CategoriaDAO();
        ArrayList<Categoria> categorias = CategoriaDao.findAll(null);
        return Categoria.toArrayJSon(categorias);
    }

}
