package Pojo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Usuarios {
    private String id;
    private String nombres;
    private String apellidosPaterno;
    private String apellidosMaterno;
    private String dni;
    private String telefono;
    private String direccion;
    private Bitmap imagen;
    private String dato;

    public Usuarios() {
    }

    public Usuarios(String id, String nombres, String apellidosPaterno, String apellidosMaterno, String dni, String telefono, String direccion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidosPaterno = apellidosPaterno;
        this.apellidosMaterno = apellidosMaterno;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
        this.imagen = imagen;
        this.dato = dato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidosPaterno() {
        return apellidosPaterno;
    }

    public void setApellidosPaterno(String apellidosPaterno) {
        this.apellidosPaterno = apellidosPaterno;
    }

    public String getApellidosMaterno() {
        return apellidosMaterno;
    }

    public void setApellidosMaterno(String apellidosMaterno) {
        this.apellidosMaterno = apellidosMaterno;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getDato() {
        return dato;
    }


    public void setDato(String dato) {
        this.dato = dato;
        try {
            byte[] bytecode= Base64.decode(dato, Base64.DEFAULT);
            imagen = BitmapFactory.decodeByteArray(bytecode, 0 , bytecode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
