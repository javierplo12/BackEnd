package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Producto {

    private int Id_Producto;
    private String nombre;
    private String descripcion;
    private double precio;

    private int id_categoria;


    // Constructor con parámetros
    public Producto(int id_Producto, String nombre, String descripcion, double precio, int id_categoria) {
        this.Id_Producto = id_Producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.id_categoria = id_categoria;

    }

    // Constructor sin parámetros
    public Producto() {
    }

    // Getters y Setters


    public int getId_Producto() {
        return Id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        Id_Producto = id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }




    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }



    @Override
    public String toString() {
        return "Producto{" + "Id_Producto=" + Id_Producto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", id_categoria=" + id_categoria + '}';
    }

    // Método para convertir un objeto Empleado a cadena
    public static String toCadena(Producto producto) {
        return "Producto{"
                + "\"Id_Producto\" =" + producto.getId_Producto() + ", "
                + "nombre=" + producto.getNombre() + ", "
                + "descripcion=" + producto.getDescripcion() + ", "
                + "precio=" + producto.getPrecio() + ", "
                + "Id_Categoria=" + producto.getId_categoria() + '}';
    }
    public static String fromArrayToJson(ArrayList<Producto> productos) {
        String resp = "[";
        for (Producto producto : productos) {
            resp += "{"
                    + "\"Id_Producto\" =" + producto.getId_Producto() + ", "
                    + "nombre=" + producto.getNombre() + ", "
                    + "descripcion=" + producto.getDescripcion() + ", "
                    + "precio=" + producto.getPrecio() + ", "
                    + "Id_categoria=" + producto.getId_categoria() + '}';

            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Producto> productos) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(productos);
    }
}
