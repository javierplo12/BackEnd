package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Cliente {

    private int Id_Cliente;
    private String nombre;
    private String contrasena;
    private String email;

    private String telefono;

    // Constructor con parámetros
    public Cliente(int Id_Cliente, String nombre,  String contrasena, String email, String telefono) {
        this.Id_Cliente = Id_Cliente;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.email = email;
        this.telefono = telefono;
    }

    // Constructor sin parámetros
    public Cliente() {
    }

    // Getters y Setters
    public int getId_Cliente() {
        return Id_Cliente;
    }
    public void setId_Cliente(int id_Cliente) {
        Id_Cliente = id_Cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" + "Id_Cliente=" + Id_Cliente + ", nombre=" + nombre + ", contrasena=" + contrasena + ", email=" + email  + ", telefono=" + telefono +'}';
    }

    // Método para convertir un objeto Empleado a cadena
    public static String toCadena(Cliente cliente) {
        return "Empleado{" +
                "\"Id_Cliente\"=" + cliente.getId_Cliente() +
                ", nombre='" + cliente.getNombre() + '\'' +
                ", contrasena='" + cliente.getContrasena() + '\'' +
                ", email='" + cliente.getEmail() + '\'' +
                ", telefono='" + cliente.getTelefono() + '\'' +
                '}';
    }
    public static String fromArrayToJson(ArrayList<Cliente> clientes) {
        String resp = "[";
        for (Cliente cliente : clientes) {
            resp += "{"
                    + "\"Id_Cliente\"=" + cliente.getId_Cliente() + ", "
                    + "nombre=" + cliente.getNombre() + ", "
                    + "contrasena=" + cliente.getContrasena() + ","
                    + " email=" + cliente.getEmail() + ", "
                    + "telefono=" + cliente.getTelefono()+ '}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Cliente> clientes) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(clientes);
    }
}
