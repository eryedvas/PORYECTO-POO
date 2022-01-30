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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Issac Maza
 */
public class Inscripcion {
    private int id;
    private int idMascotas;
    private int idConcurso;
    private double valor;
    private double descuento;
    private LocalDate fechainscripcion;
    private Mascota mascotas;
    private Concurso concurso;
    private ArrayList<Evaluacion> evaluaciones;
    
    public Inscripcion(int id, int idMascotas, int idConcurso, double valor, LocalDate fechainscripcion) {
        this.id = id;
        this.idMascotas = idMascotas;
        this.idConcurso = idConcurso;
        this.valor = valor;
        this.fechainscripcion = fechainscripcion;
    }
    public Inscripcion(int id, LocalDate fecha_inscripcion, double valor) {
        this.id = id;
        this.fechainscripcion = fechainscripcion;
        this.valor = valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdMascotas(int idMascotas) {
        this.idMascotas = idMascotas;
    }

    public void setIdConcurso(int idConcurso) {
        this.idConcurso = idConcurso;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setFechainscripcion(LocalDate fechainscripcion) {
        this.fechainscripcion = fechainscripcion;
    }

    public void setMascotas(Mascota mascotas) {
        this.mascotas = mascotas;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public int getId() {
        return id;
    }

    public int getIdMascotas() {
        return idMascotas;
    }

    public int getIdConcurso() {
        return idConcurso;
    }

    public double getValor() {
        return valor;
    }

    public double getDescuento() {
        return descuento;
    }

    public LocalDate getFechainscripcion() {
        return fechainscripcion;
    }

    public Mascota getMascotas() {
        return mascotas;
    }

    public Concurso getConcurso() {
        return concurso;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    @Override
    public String toString() {
        return "Inscripcion{" + "id=" + id + ", idMascotas=" + idMascotas + ", idConcurso=" + idConcurso + ", valor=" + valor + ", descuento=" + descuento + ", fechainscripcion=" + fechainscripcion + ", mascotas=" + mascotas + ", concurso=" + concurso + ", evaluaciones=" + evaluaciones + '}';
    }
    
    public void saveFile (String nomfile){
       try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
           pw.println(this.id + "|"+ this.fechainscripcion+ "|" + this.valor + "|"+ this.idMascotas + "|"+ this.idConcurso);
       }catch(Exception e){
           System.out.println(e.getMessage());
       }  
    }
    
    public static void saveFile(ArrayList<Inscripcion> lista_i, String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true))){
            for (Inscripcion inscri : lista_i)
                pw.println(inscri.id + "|"+ inscri.fechainscripcion+ "|" + inscri.valor + "|"+ inscri.idMascotas + "|"+ inscri.idConcurso);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }       
    }
    
    public static ArrayList<Inscripcion> readFromFile(String nomfile) {
        ArrayList<Inscripcion> lista_i = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Inscripcion lista_inscrip = new Inscripcion(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),Double.parseDouble(tokens[3]),LocalDate.parse(tokens[4]));
                lista_i.add(lista_inscrip);
            }  
        }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        return lista_i;
    }
    
    public static Inscripcion nextIncripcion(Scanner sc){
        int idMascotas,idConcurso;
        LocalDate fechainscripcion;
        sc.useDelimiter("\n");
        //ArrayList<Inscripcion> listainscripciones = Inscripcion.readFromFile("inscripciones.txt");
        //int id = listainscripciones.size()+1;;
        int id = Util.nextID("inscripciones.txt");
        System.out.println("Su id es:");
        System.out.println(id);
        
        do{
            System.out.println("Registe el id de la mascota ");
            idMascotas = Util.next_Idmascota(sc);
            
        }while(idMascotas==0);
        
        do{
            System.out.println("Registe el id del concurso ");
            idConcurso = Util.nextConcurso(sc); 
            
        }while(idConcurso==0);
        
        do{
            System.out.println("Ingrese la fecha de inscripcion: ");
            fechainscripcion = LocalDate.parse(sc.next());
            
            }while((fechainscripcion.isBefore(Util.FechaInicio(idConcurso))) || (fechainscripcion.isAfter(Util.FechaFinal(idConcurso))));
        
        while (idConcurso!= 0 && idMascotas != 0)         
        {         
            System.out.println("Ingrese el costo de la inscripcion: ");
            Double valor = sc.nextDouble();
            
            Inscripcion nuevo = new Inscripcion(id,idMascotas,idConcurso,valor,fechainscripcion);
            nuevo.saveFile("inscripciones.txt");
            
            return nuevo;      
        }
        return null;
    }
    
}
