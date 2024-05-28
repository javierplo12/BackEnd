package Controller.Actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.*;

import java.util.ArrayList;

public class Metodo_PagoAction implements IAction {
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
            default:
                strReturn = "ERROR. Invalid Action";
        }
        return strReturn;
    }

    private String findAll() {
        Metodo_PagoDAO metodoPagoDAO = new Metodo_PagoDAO();
        ArrayList<Metodo_Pago> metodoPagos = metodoPagoDAO.findAll(null);
        return Metodo_Pago.toArrayJSon(metodoPagos);
    }
}
