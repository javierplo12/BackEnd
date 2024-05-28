package Model;

import java.util.Date;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Pedido {

    private int idPedido;
    private int idCliente;
    private int idEmpleado;
    private Date fecha;
    private String estadoPedido;
    private int metodoDePago;

    // Constructor con parámetros
    public Pedido(int idPedido, int idCliente, int idEmpleado, Date fecha, String estadoPedido, int metodoDePago) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.fecha = fecha;
        this.estadoPedido = estadoPedido;
        this.metodoDePago = metodoDePago;
    }

    // Constructor sin parámetros
    public Pedido() {
    }

    // Getters y Setters
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public int getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(int metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", idEmpleado=" + idEmpleado +
                ", fecha=" + fecha +
                ", estadoPedido='" + estadoPedido + '\'' +
                ", metodoDePago=" + metodoDePago +
                '}';
    }

    // Método para convertir un objeto Pedido a cadena
    public static String toCadena(Pedido pedido) {
        return "Pedido{" +
                "idPedido=" + pedido.getIdPedido() +
                ", idCliente=" + pedido.getIdCliente() +
                ", idEmpleado=" + pedido.getIdEmpleado() +
                ", fecha=" + pedido.getFecha() +
                ", estadoPedido='" + pedido.getEstadoPedido() + '\'' +
                ", metodoDePago=" + pedido.getMetodoDePago() +
                '}';
    }

    public static String fromArrayToJson(ArrayList<Pedido> pedidos) {
        StringBuilder resp = new StringBuilder("[");
        for (Pedido pedido : pedidos) {
            resp.append("{")
                    .append("\"idPedido\"=").append(pedido.getIdPedido()).append(", ")
                    .append("idCliente=").append(pedido.getIdCliente()).append(", ")
                    .append("idEmpleado=").append(pedido.getIdEmpleado()).append(", ")
                    .append("fecha=").append(pedido.getFecha()).append(", ")
                    .append("estadoPedido='").append(pedido.getEstadoPedido()).append("', ")
                    .append("metodoDePago=").append(pedido.getMetodoDePago())
                    .append('}')
                    .append(",");
        }
        resp.setLength(resp.length() - 1); // Eliminar la última coma
        resp.append("]");
        return resp.toString();
    }

    public static String toArrayJson(ArrayList<Pedido> pedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(pedidos);
    }
}
