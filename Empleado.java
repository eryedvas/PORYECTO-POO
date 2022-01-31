/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.poo.modelo;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Carlos
 */
public class Empleado implements Serializable,Comparable<Empleado>{
    private String cedula;
    private String nombre;
    private Departamento departamento;
    private Genero genero;

    public Empleado(String cedula) {
        this.cedula = cedula;
    }
    public Empleado(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
       
    }
    

    public Empleado(String cedula, String nombre, Departamento departamento) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.departamento = departamento;
    }
    public Empleado(String cedula, String nombre, Departamento departamento, Genero gen) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.departamento = departamento;
        this.genero = gen;
    }
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }


    public Departamento getDepartamento() {
        return departamento;
    }

    public Genero getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return  cedula + "-" + nombre + "-" + departamento ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.cedula);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Empleado other = (Empleado) obj;
        if (!Objects.equals(this.cedula, other.cedula)) {
            return false;
        }
        return true;
    }
    

    @Override
    public int compareTo(Empleado o) {
        
        return nombre.compareToIgnoreCase(o.nombre);
    }
    
    
    
    public static ArrayList<Empleado> cargarEmpleados(String ruta) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        System.out.println("xxxxxxxxxxxxx");
       //leer la lista de personas del archivo serializado
        try (ObjectInputStream oi = new ObjectInputStream(new FileInputStream(ruta))) {
            empleados = (ArrayList<Empleado>) oi.readObject();
            System.out.println("=============");
            // System.out.println(empleados);
        } catch (FileNotFoundException ex) {
            System.out.println("archivo no existe");
        } catch (IOException   ex) {
            System.out.println("error io:"+ex.getMessage());
        } catch (ClassNotFoundException  ex) {
            System.out.println("error class:"+ex.getMessage());
        } 
        return empleados;
    }
}