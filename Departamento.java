/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.poo.modelo;

import espol.poo.empadminjar.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Carlos
 */
public class Departamento implements Serializable{
    private String nombre;
    private String descripcion;

    public Departamento(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return  nombre ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nombre);
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
        final Departamento other = (Departamento) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    

    public static ArrayList<Departamento> cargarDepartamentos(String ruta) {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        
       
        try(InputStream input = Departamento.class.getClassLoader().getResourceAsStream(ruta);
                BufferedReader br = new BufferedReader(new InputStreamReader(input))) {
            String linea = null;
            
            while ((linea = br.readLine()) != null) //iterar mientras haya lineas
            {
                String[] info = linea.split(",");//separar los datos por coma
                //crear objeto y agregar a la lista
                departamentos.add(new Departamento(info[0],info[1]));
                        
            }
        }  catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        }  catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } 
        
       return departamentos;
    }
}
