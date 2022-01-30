/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto;

import ec.edu.espol.model.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Eduardo Vasquez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main.menu();
    }
    
    public static void menu(){
        Scanner sc=new Scanner(System.in);
        int op=0;
        while(op!=9){
            System.out.println("Menu de opciones");
            System.out.println("1. Dueño");
            System.out.println("2. Mascota");
            System.out.println("3. Concurso");
            System.out.println("4. Premio");
            System.out.println("5. Criterio");
            System.out.println("6. Incripcion");
            System.out.println("7. MiembroJurado");
            System.out.println("8. Evaluacion");
            System.out.println("9. Salir");
            op=sc.nextInt();
            if(op==1){
                System.out.println("Ingresa los datos del dueño");
                Dueno d=Dueno.nextDueno(sc);
                d.saveFile("dueños.txt");
            }
            if(op==2){
                System.out.println("Ingresa los datos de la mascota");
                Mascota m=Mascota.nextMascota(sc);
                m.saveFile("mascotas.txt");
            }
            if(op==3){
                System.out.println("Ingresa los datos del concurso");
                Concurso c=Concurso.nextConcurso(sc);
                c.saveFile("concursos.txt");
            }
            if(op==4){
                System.out.println("Ingresa los datos de los premios");
                System.out.println("Ingresa la cantidad de premios:");
                int cantidad=sc.nextInt();
                for(int i=0;i<cantidad;i++){
                    Premio p=Premio.nextPremio(sc);
                    p.saveFile("premios.txt");
                }
            }
            if(op==5){
                System.out.println("Ingresa los datos de los criterios");
                System.out.println("Ingresa la cantidad de criterios:");
                int cantidad=sc.nextInt();
                for(int i=0;i<cantidad;i++){
                    Criterio c=Criterio.nextCriterio(sc);
                    c.saveFile("criterios.txt");
                }
            }
            if(op==6){
                System.out.println("Ingresa los datos de la incripcion");
                Inscripcion i=Inscripcion.nextIncripcion(sc);
                i.saveFile("inscripciones.txt");
            }
            if(op==7){
                System.out.println("Ingresa los datos del jurado");
                MiembroJurado mJ=MiembroJurado.nextMiembroJ(sc);
                mJ.saveFile("miembroJurados.txt");
            }
            if(op==8){
                System.out.println("Ingresa los datos de la evaluacion");
                Evaluacion e=Evaluacion.nextEvaluacion(sc);
                e.saveFile("evaluaciones.txt");
            }
        }
    }
    
}
