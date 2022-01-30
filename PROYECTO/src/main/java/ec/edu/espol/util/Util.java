/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.model.Concurso;
import ec.edu.espol.model.Criterio;
import ec.edu.espol.model.Dueno;
import ec.edu.espol.model.Inscripcion;
import ec.edu.espol.model.Mascota;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author eduardo
 */
public class Util {
    
    // el constructor se lo ha declarado privado
    // ya que esta clase solo va a contener comportamientos estáticos
    // por lo tanto, no se van a permitir crear instancia de la clase Util
    private Util(){}
    
    public static int nextID(String nomfile)
    {
        int id = 0;
        try(Scanner sc = new Scanner(new File(nomfile)))
        {
           while(sc.hasNextLine())
           {
               String linea = sc.next();
               String[] tokens = linea.split("\\|");
               id = Integer.parseInt(tokens[0]);
           }
        }
        catch(Exception e)
        {
        }
        return id+1;
    }
    
    public static int next_Idmascota(Scanner sc){
    System.out.println("Nombre de la mascota: ");
    String nombre_mascota = sc.next();
    
    ArrayList<Mascota> mascotas = Mascota.readFromFile("mascotas.txt");
    for (Mascota m: mascotas){
        if (nombre_mascota.equals(m.getNombre())){
            return m.getId();
            }  
        }
    return 0;
    }
    
    public static Dueno nextDuenoe(Scanner sc){
        
        System.out.println("Ingrese un correo electrocino de un dueño existente: ");
        String email = sc.next();
        ArrayList<Dueno> duenos = Dueno.readFromFile("dueños.txt");
        for (Dueno duenito: duenos){
            if (email.equals(duenito.getEmail())){
               
                return duenito;
            }  
        }
        return null; 
    }
    
    public static Inscripcion next_Inscripcion(Scanner sc){
        System.out.println("Ingrese el id de la inscripcion: ");
        int id= sc.nextInt();
        ArrayList<Inscripcion> inscripciones = Inscripcion.readFromFile("inscripciones.txt");
        for (Inscripcion inscritos: inscripciones){
            if (id==inscritos.getId()){
                return inscritos;
            }  
        }
    return null;
    }
    
    public static int nextConcurso(Scanner sc){
    System.out.println("Ingrese el nombre del concurso: ");
    String concurso = sc.next();
    ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
    for (Concurso concurs: concursos){
        if (concurso.equals(concurs.getNombre())){
            return concurs.getId();
            }  
        }
    return 0;
    }
    
    public static LocalDate FechaInicio(int id){
        ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
        for (Concurso concurs: concursos){
            if(id==concurs.getId()){
                return concurs.getFechaInscripcion();
            }
        }
        return null;       
    }
    
    public static LocalDate FechaFinal(int id){
        ArrayList<Concurso> concursos = Concurso.readFromFile("concursos.txt");
        for (Concurso i: concursos){
            if(id==i.getId()){
                return i.getFechaCierreInscripcion();
            }
        }
        return null;       
    }
    public static Criterio IDCriterio(Scanner sc){
        System.out.println("Ingrese el id del criterio: ");
        int id= sc.nextInt();
        ArrayList<Criterio> criterios = Criterio.readFromFile("criterios.txt");
        for (Criterio subcriterio: criterios){
            if (id==subcriterio.getId()){
                return subcriterio;
            }  
        }
    return null;
    }
}
 
    
