package ioc.dam.m9.uf3.eac2.ex2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author evelasco
 */
public class ClientMatematic {

    private static String HOST = "localhost";
    private static int PUERTO = 9999;

    public static void main(String[] args) {
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;

        try {
            cliente = new Socket(HOST, PUERTO);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

            salida.writeBytes("/:22:2"+"\n");

            String lineaRecibida;
            lineaRecibida = entrada.readLine();
            System.out.println("RESPUESTA DEL SERVIDOR: " + lineaRecibida);
            salida.close();
            entrada.close();
            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
