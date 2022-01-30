/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;
import ec.edu.espol.model.*;
import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Issac Maza
 */
public class Mascota {

    
    private int id;
    private int idDueno;
    private String nombre;
    private String raza;
    private String tipo;
    private LocalDate fechaNacimiento;
    private Dueno dueno;
    private ArrayList<Inscripcion> inscripciones = new ArrayList();
    
    public Mascota(int id,int idDueno,String nombre,String raza,String tipo,LocalDate fechaNacimiento){
        this.id=id;
        this.idDueno = idDueno;
        this.nombre=nombre;
        this.raza = raza;
        this.tipo=tipo;
        this.fechaNacimiento = fechaNacimiento;
        
    } 

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdDueno(int idDueno) {
        this.idDueno = idDueno;
    }

    public void setDueno(Dueno dueno) {
        this.dueno = dueno;
    }

    public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdDueno() {
        return idDueno;
    }

    public Dueno getDueno() {
        return dueno;
    }
    
    

    public ArrayList<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    Scanner sc= new Scanner(System.in);
    @Override
    public String toString() {
        StringBuilder bui_mascota = new StringBuilder();
        bui_mascota.append("Mascota| id= ");
        bui_mascota.append(this.id);
        bui_mascota.append(", Id_dueño= ");
        bui_mascota.append(this.idDueno);
        bui_mascota.append(", Nombre= ");
        bui_mascota.append(this.nombre);
        bui_mascota.append(", Raza= ");
        bui_mascota.append(this.raza);
        bui_mascota.append(", Tipo= ");
        bui_mascota.append(this.tipo);
        bui_mascota.append(", Fecha de Nacimiento= ");
        bui_mascota.append(this.fechaNacimiento);
        bui_mascota.append(", Dueño= ");
        bui_mascota.append(this.dueno);
        bui_mascota.append(", Inscripciones= ");
        for (Inscripcion inscrip: inscripciones){
            bui_mascota.append(inscrip.toString());
            if(this.inscripciones.size()!=this.inscripciones.size()-1)
                bui_mascota.append(";");
                
            }
        bui_mascota.append("]");
        return bui_mascota.toString();
    }
    
    public void saveFile(String nomfile) {
        try ( PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile), true))) {
            pw.println(this.id + "|" + this.idDueno + "|" + this.nombre + "|" + this.raza + "|" + this.tipo + "|" + this.fechaNacimiento);
            for (Inscripcion inscrip: this.getInscripciones()){
                pw.println(inscrip.getId() + ";");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void  saveFile( ArrayList<Mascota> mascotas , String nombre){
        try(PrintWriter pw= new PrintWriter(new FileOutputStream(new File(nombre),true))){
            for (   Mascota v:  mascotas ){
                pw.println(v.getId() + "|"+ v.getNombre()+ "|" + v.getRaza() + "|"+ v.getTipo()+ "|"+ v.getFechaNacimiento()+ "|"+ v.getIdDueno());
                for (Inscripcion m: v.getInscripciones()){
                    pw.println(m.getId() + ";");
                }
            }    
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
  
    public static ArrayList<Mascota> readFromFile(String nomFile) {
        ArrayList<Mascota> mascots = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomFile))){
            while(sc.hasNextLine()){
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Mascota masc = new Mascota(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), tokens[2], tokens[3], tokens[4], LocalDate.parse(tokens[5]));
                mascots.add(masc);
            }  
        }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
        return mascots;
    }
   
    
    public static Mascota nextMascota(Scanner sc){
        sc.useDelimiter("\n");
        //int id = listmascotas.size() + 1;
        System.out.println("Su id es:");
        int id = Util.nextID("mascotas.txt");
        System.out.println(id);
        
        System.out.println("Nombre de la mascota:");
        String nombre = sc.next();
        
        System.out.println("Ingrese la raza de su mascota : ");
        String raza = sc.next();
        
        System.out.println("Tipo de mascota(gato,perro,cocdrilo,serpiente,etc): ");
        String tipo = sc.next();
        
        System.out.println("Ingrese la fecha de nacimiento de su mascota");
        System.out.println("Recuerde que la fecha esta en este formato year-month-day ");
        LocalDate fechanacimiento = LocalDate.parse(sc.next());
        
        Dueno dueno;
        do{
            dueno = Util.nextDuenoe(sc);
        }while(dueno == null);
        Mascota masacota = new Mascota(id, dueno.getId(), nombre, raza, tipo, fechanacimiento);
        ArrayList<Dueno> lista_duenos = Dueno.readFromFile("dueños.txt");
//        dueno.saveFile("mascotas.txt");
        return masacota;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Mascota other = (Mascota) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idDueno != other.idDueno) {
            return false;
        }
        return true;
    }
    public static ArrayList<Inscripcion>  GenerarListInscripMascota(String nombre,int id){
        ArrayList<Inscripcion> mocot=new ArrayList<>();
        ArrayList<Inscripcion> mocot1= Inscripcion.readFromFile(nombre);
        
        for (Inscripcion moscon: mocot1){
            
                if (moscon.getIdMascotas()==id){
                    mocot.add(moscon);
                }
        }
        return mocot;
    }
    
  
     
    
}
