/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ROMMEL ZAMORA
 */
public class Criterio {
    private int id;
    private String descripcion;
    private ArrayList<Evaluacion> evaluaciones;
    private int idConcurso;
    private Concurso concurso;

    public Criterio(int id, String descripcion,int idConcurso) {
        this.id = id;
        this.descripcion = descripcion;
        this.evaluaciones = new ArrayList<>();
        this.idConcurso = idConcurso;
        this.concurso = concurso;
    }
    

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    //##########Setters##########
    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
    
    public void addEvaluacion(Evaluacion e1){
        this.evaluaciones.add(e1);
    }
    
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        for (Evaluacion e : this.evaluaciones){
            sb.append("Evaluacion ");
            sb.append(e.getId());
            sb.append(":<Id de Inscripcion: ");
            sb.append(e.getIdInscripcion());
            sb.append(", Miembro de Jurado: ");
            sb.append(e.getIdMiembroJurado());

            sb.append(", Nota: ");
            sb.append(e.getNota());
            sb.append(">, "); 
        }
        
        return "Criterio "+this.id+" :{Descripcion: "+this.descripcion+", Evaluaciones:["+sb.toString()+"], ID de Concurso: "+this.idConcurso+"}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Criterio c=(Criterio)obj;
        return this.id==c.id;
    }
    
    public static Criterio nextCriterio(Scanner sc){
        System.out.println("Ingrese el ID del Criterio:");
        int id_next=sc.nextInt();
        System.out.println("Ingrese la descripcion del Criterio:");
        String descripcion_next = sc.next();
        System.out.println("Ingrese el ID del Concurso:");
        int idConcurso_next = sc.nextInt();
        
        Scanner sc1 = new Scanner(System.in);
        Concurso concurso_next = Concurso.nextConcurso(sc1);
        
        Criterio criterio_nuevo=new Criterio(id_next,descripcion_next,idConcurso_next);
        return criterio_nuevo;
    }
    
    //files
    
    public void saveFile(String nomFile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(this.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    public static ArrayList<Criterio> readFromFile(String nomFile){
        ArrayList<Criterio> lista=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea=sc.nextLine();
                String[] tokens=linea.split("|");
                Criterio c=new Criterio(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]));
                lista.add(c);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
}
