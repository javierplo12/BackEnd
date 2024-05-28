package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Metodo_Pago {

    private int Id_Metodo_Pago;
    private String nombre;

    // Constructor con parámetros
    public Metodo_Pago(int Id_Categoria, String nombre) {
        this.Id_Metodo_Pago = Id_Categoria;
        this.nombre = nombre;

    }

    // Constructor sin parámetros
    public Metodo_Pago() {
    }

    public int getId_Metodo_Pago() {
        return Id_Metodo_Pago;
    }

    public void setId_Categoria(int id_Categoria) {
        id_Categoria = id_Categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Metodo_Pago{" + "Id_Metodo_Pago=" + Id_Metodo_Pago + ", nombre=" + nombre +'}';
    }

    // Método para convertir un objeto Empleado a cadena
    public static String toCadena(Metodo_Pago metodoPago) {
        return "Empleado{" +
                "\"Id_Metodo_Pago\"=" + metodoPago.getId_Metodo_Pago() +
                ", nombre='" + metodoPago.getNombre() + '\'' + '}';
    }
    public static String fromArrayToJson(ArrayList<Metodo_Pago> metodoPagos) {
        String resp = "[";
        for (Metodo_Pago metodoPago : metodoPagos) {
            resp += "{"
                    + "\"Id_Metodo_Pago\"=" + metodoPago.Id_Metodo_Pago+ ", "
                    + "nombre=" + metodoPago.getNombre() + ", "+'}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Metodo_Pago> metodoPagos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(metodoPagos);
    }
}
