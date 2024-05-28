package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Categoria {

    private int Id_Categoria;
    private String nombre;

    // Constructor con parámetros
    public Categoria(int Id_Categoria, String nombre) {
        this.Id_Categoria = Id_Categoria;
        this.nombre = nombre;

    }

    // Constructor sin parámetros
    public Categoria() {
    }

    public int getId_Categoria() {
        return Id_Categoria;
    }

    public void setId_Categoria(int id_Categoria) {
        Id_Categoria = id_Categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{" + "Id_Categoria=" + Id_Categoria + ", nombre=" + nombre +'}';
    }

    // Método para convertir un objeto Empleado a cadena
    public static String toCadena(Categoria categoria) {
        return "Empleado{" +
                "\"Id_Categoria\"=" + categoria.getId_Categoria() +
                ", nombre='" + categoria.getNombre() + '\'' + '}';
    }
    public static String fromArrayToJson(ArrayList<Categoria> categorias) {
        String resp = "[";
        for (Categoria categoria : categorias) {
            resp += "{"
                    + "\"Id_Categoria\"=" + categoria.getId_Categoria()+ ", "
                    + "nombre=" + categoria.getNombre() + ", "+'}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Categoria> categorias) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(categorias);
    }
}
