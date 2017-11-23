package ioc.dam.m9.uf3.eac2.ex2;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author evelasco
 */
public class ServidorMatematic extends ServeiMatematicImpl{
    private static int PUERTO = 9999;

    public void executa(Socket conexion) {
        String lineaRecibida;
        DataInputStream entrada;
        PrintStream salida;

        try {
            entrada = new DataInputStream(conexion.getInputStream());
            salida = new PrintStream(conexion.getOutputStream());
            lineaRecibida = entrada.readLine();
            double resultado = analitza(lineaRecibida);
            salida.println(resultado);

            salida.close();
            entrada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //El protocol per a la operació és operador:valor1:valor2
    private double analitza(String operacio){
        System.out.println("ENTRA EN ANALIZAR LA OPERACION: "+operacio);
        String[] parts = operacio.split(":");
        //ServeiMatematic seveiMatematic = new Se
        double resposta = 0;
        String operador = parts[0];
        double operant1 = Double.parseDouble(parts[1]);
        double operant2 = Double.parseDouble(parts[2]);

        switch (operador) {
            case "+":
                resposta = suma(operant1, operant2);
                //resposta = suma(operant1, operant2);
                break;
            case "-":
                resposta = resta(operant1, operant2);
                break;
            case "/":
                resposta = div(operant1, operant2);
                break;
            case "*":
                resposta = mult(operant1, operant2);
                break;
        }
            
        return resposta;
    }

    public static void main(String[] args) throws Exception {
        ServerSocket servidor;
        Socket conexion;

        System.out.println("El servidor matemàtic està executant...");
        servidor = new ServerSocket(PUERTO);
        System.out.println("ESPERANDO CLIENTE...");
        conexion = servidor.accept();
        System.out.println("CONEXION CLIENTE OK");
        //executa(conexion);
        ServidorMatematic sm = new ServidorMatematic();
        sm.executa(conexion);



        //salida.println();

        conexion.close();
        
        
        System.out.println("El servidor matemàtic s'ha tancat...");
    }
}
