package Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class Empleado {

    private int Id_Empleado;
    private String nombre;
    private String apellido;
    private String contrasena;
    private String DNI;
    private String puesto;
    private String telefono;

    // Constructor con parámetros
    public Empleado(int Id_Empleado, String nombre, String apellido, String contrasena, String DNI, String puesto, String telefono) {
        this.Id_Empleado = Id_Empleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.DNI = DNI;
        this.puesto = puesto;
        this.telefono = telefono;
    }

    // Constructor sin parámetros
    public Empleado() {
    }

    // Getters y Setters
    public int getId_Empleado() {
        return Id_Empleado;
    }

    public void setId_Empleado(int Id_Empleado) {
        this.Id_Empleado = Id_Empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Empleado{" + "Id_Empleado=" + Id_Empleado + ", nombre=" + nombre + ", apellidos=" + apellido + ", contrasena=" + contrasena + ", DNI=" + DNI + ", puesto=" + puesto + ", telefono=" + telefono +'}';
    }

    // Método para convertir un objeto Empleado a cadena
    public static String toCadena(Empleado empleado) {
        return "Empleado{" +
                "\"Id_Empleado\"=" + empleado.getId_Empleado() +
                ", nombre='" + empleado.getNombre() + '\'' +
                ", apellido='" + empleado.getApellido() + '\'' +
                ", contrasena='" + empleado.getContrasena() + '\'' +
                ", DNI='" + empleado.getDNI() + '\'' +
                ", puesto='" + empleado.getPuesto() + '\'' +
                ", telefono='" + empleado.getTelefono() + '\'' +
                '}';
    }
    public static String fromArrayToJson(ArrayList<Empleado> empleados) {
        String resp = "[";
        for (Empleado empleado : empleados) {
            resp += "{"
                    + "\"Id_Empleado\"=" + empleado.getId_Empleado() + ", "
                    + "nombre=" + empleado.getNombre() + ", "
                    + "apellido=" + empleado.getApellido() + ","
                    + "contrasena=" + empleado.getContrasena() + ","
                    + " puesto=" + empleado.getPuesto() + ", "
                    + "DNI=" + empleado.getDNI() + ", "
                    + "telefono=" + empleado.getTelefono()+ '}';
            resp += ",";
        }
        resp = resp.substring(0, resp.length() - 1);
        resp += "]";
        return resp;
    }

    public static String toArrayJSon(ArrayList<Empleado> empleado) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        return gson.toJson(empleado);
    }
}
