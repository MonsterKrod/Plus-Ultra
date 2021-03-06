package Krod.com.company.Ultra.Methods;

import java.io.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

final public class Response extends InputOutput implements ParseRequest{

    static public void send(String response){
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            URI requestedUri = getHttpExchange().getRequestURI();
            String query = requestedUri.getRawQuery();
            ParseRequest.parseQuery(query, parameters);
            getHttpExchange().sendResponseHeaders(200, response.length());
            OutputStream os = getHttpExchange().getResponseBody();
            os.write(response.toString().getBytes());
            os.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public void sendHtml(String path){
        try {

            getHttpExchange().sendResponseHeaders(200, 0);
            OutputStream output = getHttpExchange().getResponseBody();
            FileInputStream fs = new FileInputStream(path);
            final byte[] buffer = new byte[0x10000];
            int count = 0;
            while ((count = fs.read(buffer)) >= 0) {
                output.write(buffer, 0, count);
            }
            output.flush();
            output.close();
            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public void json(){}
    static public void sendStatus(){}
    static public void download(){}
    static public void redirect(){}
    static public void render(){}


}
