package ioc.dam.m9.uf3.eac2.ex3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author evelasco
 */
public class ConnexioHttp {

    public static void main(String[] args) throws Exception {

        ConnexioHttp http = new ConnexioHttp();
        String urlGet = "https://www.caixabank.cat/index_ca.html";
        String urlPost = "http://www.plasticoselche.es/es/login ";


        try {
            System.out.println("Test 1 - Enviant petició GET");
            http.enviaGet(urlGet);

        }catch (Exception e) {
            e.printStackTrace();
        }


        try{
            System.out.println("\nTest 2 - Enviant petició POST");
            http.enviaPost(urlPost);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    // petició GET

    /**
     * Hace una peticion GET a una url
     * @param urlCall
     * @throws IOException
     */
    private void enviaGet(String urlCall) throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlCall);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        System.out.println(result);
    }

    // petició POST

    /**
     * Hace una peticion POST para un login, se asigna un Map con los parametros,
     * se codifican los parametros a UTF-8,
     * Hace la peticion y recibe el codigo XML
     * @param urlCall
     * @throws IOException
     */
    private void enviaPost(String urlCall) throws IOException {
        URL url = new URL(urlCall);

        //Montar los parametros que se envian
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("kt_login_user", "albert");
        params.put("kt_login_password", "ac@nela2#17");

        //Asignar las codificaciones
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0)
                postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }

        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read())
            System.out.print((char) c);

    }
}
