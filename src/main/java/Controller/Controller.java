package Controller;

import Controller.Actions.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        response.setHeader("Access-Control-Max-Age", "3600");

        PrintWriter out = response.getWriter();
        String strAction = request.getParameter("ACTION");

        String[] arrayAction = new String[2];
        if (strAction != null && !strAction.isEmpty()) {
            arrayAction = strAction.split("\\."); //[0] CLIENTE <-> [1] ADD
        } else {
            throw new ServletException("No se especificó ninguna acción.");
        }

        switch (arrayAction[0].toUpperCase()) {
            case "EMPLEADO": {
                out.print(new EmpleadoAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "CATEGORIA": {
                out.print(new CategoriaAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "METODO_PAGO": {
                out.print(new Metodo_PagoAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "CLIENTE": {

                if (arrayAction[1].equalsIgnoreCase("LOGIN")) {
                    out.print(new ClienteAction().execute(request, response, arrayAction[1]));
                } else {
                    out.print(new ClienteAction().execute(request, response, arrayAction[1]));
                }
                break;
            }
            case "PRODUCTO": {
                out.print(new ProductoAction().execute(request, response, arrayAction[1]));
                break;
            }
            case "DETALLE_PEDIDOS": {
                out.print(new ProductoAction().execute(request, response, arrayAction[1]));
                break;
            }
            default: {
                System.out.println(arrayAction[0]);
                throw new ServletException("Acción " + arrayAction[0] + " no válida");
            }
        }
        System.out.println(strAction);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
