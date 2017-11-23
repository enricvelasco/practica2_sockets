package ioc.dam.m9.uf3.eac2.ex1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author evelasco
 */
public class MajusculaServidor {

    private static int PUERTO = 9999;

    public String servei(String a) {
        System.out.println("ENTRA EN SERVICIO");
        return a.toUpperCase();
    }

    public static void main(String[] args) {
        MajusculaServidor majuscula = new MajusculaServidor();

        ServerSocket servidor;
        String lineaRecibida;
        DataInputStream entrada;
        PrintStream salida;
        Socket conexion;
        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("ESPERANDO CLIENTE...");
            conexion = servidor.accept();
            System.out.println("CONEXION CLIENTE OK");

            entrada = new DataInputStream(conexion.getInputStream());
            salida = new PrintStream(conexion.getOutputStream());
            lineaRecibida = entrada.readLine();
            //majuscula.servei(lineaRecibida);
            salida.println(majuscula.servei(lineaRecibida));
            salida.close();
            entrada.close();
            conexion.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
