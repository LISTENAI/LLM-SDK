package com.listenai.llm;

import com.listenai.llm.models.response.HttpReturn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Helpers {
    protected static HttpReturn HttpOperation(String url, String method, String token) {
        if (method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT") || method.toUpperCase().equals("PATCH")) {
            throw new IllegalArgumentException("Please specify body and content-type");
        }

        return HttpOperation(url, method, token, null, null);
    }

    protected static HttpReturn HttpOperation(String url, String method, String token, String body, String contentType) {
        if ((method.toUpperCase().equals("POST") || method.toUpperCase().equals("PUT") || method.toUpperCase().equals("PATCH")) &&
                (body == null || contentType == null)) {
            throw new IllegalArgumentException("Please specify body and content-type");
        }

        try {
            var urlobj = new URL(url);
            var connection = (HttpURLConnection) urlobj.openConnection();
            connection.setConnectTimeout(5 * 1000);
            connection.setReadTimeout(30 * 1000);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "ListenAI LLM SDK/1.0.0");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            if (body != null && contentType != null) {
                connection.setRequestProperty("Content-Type", contentType);
                connection.setFixedLengthStreamingMode(body.getBytes("utf-8").length);
                connection.setDoOutput(true);

                var outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.write(body.getBytes("utf-8"));
                outputStream.flush();
                outputStream.close();
            }

            var responseCode = connection.getResponseCode();
            BufferedReader reader;
            if (responseCode != 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            }
            String line;
            var response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            connection.disconnect();

            return new HttpReturn(responseCode, response.toString());
        } catch (MalformedURLException e) {
            return new HttpReturn(402, e.toString());
        } catch (IOException e) {
            return new HttpReturn(402, e.toString());
        }
    }
}
