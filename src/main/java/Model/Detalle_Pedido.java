package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Detalle_Pedido {

    private int Id_Detalle;
    private int Id_Producto ;
    private int Id_Pedido;
    private int Cantidad;
    private double Precio;

    // Constructor con parámetros
    public Detalle_Pedido(int Id_Categoria, int Id_Producto, int Id_Pedido, int Cantidad, double Precio) {
        this.Id_Detalle = Id_Detalle;
        this.Id_Producto = Id_Producto;
        this.Id_Pedido = Id_Pedido;
        this.Cantidad =Cantidad;
        this.Precio = Precio;

    }

    // Constructor sin parámetros
    public Detalle_Pedido() {
    }

    public int getId_Detalle() {
        return Id_Detalle;
    }

    public void setId_Detalle(int id_Detalle) {
        Id_Detalle = id_Detalle;
    }

    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        Id_Producto = id_Producto;
    }

    public int getId_Pedido() {
        return Id_Pedido;
    }

    public void setId_Pedido(int id_Pedido) {
        Id_Pedido = id_Pedido;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    @Override
    public String toString() {
        return "Detalle_Pedido{" + "Id_Detalle_Pedido=" + Id_Detalle + ", Id_Producto=" + Id_Producto + ", Id_Pedido=" + Id_Pedido + ", Cantidad=" + Cantidad + ", Precio=" + Precio + '}';
    }

    // Método para convertir un objeto Empleado a cadena
    public static String toCadena(Model.Detalle_Pedido detallePedido) {
        return "Detalle_Pedido{" +
                "\"Id_Detalle\"=" + detallePedido.getId_Detalle() +
                ", Id_Producto='" + detallePedido.getId_Producto() +
                ",Id_Pedido=" + detallePedido.getId_Pedido() +
                "Cantidad=" + detallePedido.getCantidad() +
                "Precio=" + detallePedido.getPrecio() ;

    }
    public static String fromArrayToJson(ArrayList<Model.Detalle_Pedido> detallePedidos) {
        String resp = "[";
        for (Model.Detalle_Pedido detallePedido : detallePedidos) {
            resp += "{"
                    + "\"Id_Detalle\"=" + detallePedido.getId_Detalle()+ ", "
                    + "Id_Producto=" + detallePedido.getId_Producto() + ", "
                    + "Id_Pedido=" + detallePedido.getId_Pedido() + ", "
                    + "Cantidad=" + detallePedido.getCantidad() + ", "
                    + "Precio=" + detallePedido.getPrecio() + ", "+'}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Model.Detalle_Pedido> detallePedidos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(detallePedidos);
    }
}
