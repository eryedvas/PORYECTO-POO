/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eduardo Vasquez
 */
public class Evaluacion {
    private int id;
    private int idInscripcion;
    private int idMiembroJurado;
    private int idCriterio;
    private double nota;

    public Evaluacion(int id, String eMiembroJurado, int idInscripcion, int idCriterio, double nota) {
        this.id = id;
        this.idInscripcion = idInscripcion;
        ArrayList<MiembroJurado> jurados=MiembroJurado.readFromFile("miembroJurados.txt");
        for(MiembroJurado mJ:jurados){
            if(mJ.getEmail() == eMiembroJurado)
                this.idMiembroJurado = mJ.getId();
        }
        this.idCriterio = idCriterio;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }



    public int getIdMiembroJurado() {
        return idMiembroJurado;

    }

    public int getIdCriterio() {
        return idCriterio;
    }

    public double getNota() {
        return nota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public void setIdMienbroJurado(int idMiembroJurado) {
        this.idMiembroJurado = idMiembroJurado;
    }

    public void setIdCriterio(int idCriterio) {
        this.idCriterio = idCriterio;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return id + "|" + idInscripcion + "|" + idMiembroJurado + "|" + idCriterio + "|" + nota;
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
        Evaluacion r=(Evaluacion)obj;
        return this.id==r.id;
    }
    
    public void saveFile(String nomFile){
        try(PrintWriter pw=new PrintWriter(new FileOutputStream(new File(nomFile),true))){
            pw.println(this.toString());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        } 
    }
    
    public static ArrayList<Evaluacion> readFromFile(String nomFile){
        ArrayList<Evaluacion> lista=new ArrayList<>();
        try(Scanner sc=new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea=sc.nextLine();
                String[] tokens=linea.split("|");
                Evaluacion e=new Evaluacion(Integer.parseInt(tokens[0]),tokens[1],Integer.parseInt(tokens[2]),Integer.parseInt(tokens[3]),Double.parseDouble(tokens[4]));
                lista.add(e);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return lista;
    }
        
    public static Evaluacion nextEvaluacion(Scanner sc){
        int id=Util.nextID("evaluaciones.txt");
        System.out.println("email del Jurado");
        String eMiembroJurado=sc.next();
        System.out.println("id de la inscripcion");
        int idInscripcion=sc.nextInt();
        System.out.println("id del Criterio");
        int idCriterio=sc.nextInt();
        System.out.println("Nota");
        double nota=sc.nextDouble();
        Evaluacion r=new Evaluacion(id,eMiembroJurado,idInscripcion,idCriterio,nota);
        return r;
    }
}