package ioc.dam.m9.uf3.eac2.ex1;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author evelasco
 */
public class MajusculaClient {

    private static String HOST = "localhost";
    private static int PUERTO = 9999;

    public static void main(String[] args) {
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;

        Scanner inConsola=new Scanner(System.in);
        try {
            //crea el socket
            cliente = new Socket(HOST, PUERTO);

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            System.out.println("INTRODUZCA TEXTO A CONVERTIR EN MAYUSCULA:");
            String texto = inConsola.next();

            String lineaRecibida;
            salida.writeBytes(texto+"\n");
            lineaRecibida = entrada.readLine();
            System.out.println("RESPUESTA DEL SERVIDOR: " + lineaRecibida);
            salida.close();
            entrada.close();
            cliente.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
