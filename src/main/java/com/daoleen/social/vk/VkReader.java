package com.daoleen.social.vk;

import sun.net.www.http.HttpClient;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;

/**
 * Created by alex on 3/22/14.
 */
public class VkReader {
    private final Config config = Config.getInstance();

//    public String read(String uri) {
//        URL url = null;
//        String result = null;
//
//        try {
//            url = new URI(uri).toURL();
//            URLConnection connection = url.openConnection();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            result = reader.readLine();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }

    public String read(String uri, String params) {
        URL url = null;
        String result = null;

        try {
            url = new URI(uri).toURL();
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            try(DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(params);
                outputStream.flush();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            result = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
