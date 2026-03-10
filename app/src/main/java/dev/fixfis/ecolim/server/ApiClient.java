package dev.fixfis.ecolim.server;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class ApiClient {

    public static class ApiCalls<T> {
        private final Class<T> outputClass;
        private final String endpoint;
        private HttpURLConnection postConn;

        private ApiCalls(Class<T> outputClass, String endpoint) {
            this.outputClass = outputClass;
            this.endpoint = endpoint;
        }

        public ApiCalls<T> postter(T body) {
            return request(body, "POST");

        }
        public ApiCalls<T> putter(T body) {
            return request(body, "PUT");

        }
        public ApiCalls<T> deleter(T body) {
            return request(body, "DELETE");

        }
        public <S> S getter(Class<S> sClass)  {
            if (postConn != null) return null;
            try {
                URL url = new URI(endpoint).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.connect();

                return new Gson().fromJson(
                        gettingString(conn),
                        sClass
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        public Result getter()  {
            return getter(Result.class);

        }

        private ApiCalls<T> request(T body, String method) {
            try {
                String o = new Gson().toJson(body);
                System.out.println(method+"\n"+endpoint+"\n"+o+"\n");
                URL url = new URI(endpoint).toURL();
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod(method.toUpperCase());
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(o.getBytes());
                os.flush();
                os.close();

                postConn = conn;
                return this;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        public Result result() {
            if (postConn == null) return null;
            return new Gson().fromJson(
                    gettingString(postConn),
                    Result.class
            );
        }
        public JsonElement devResult() {
            if (postConn == null) return null;
            return new Gson().fromJson(
                    gettingString(postConn),
                    JsonElement.class
            );
        }

        @FunctionalInterface
        public interface Getting<T> {
            void run(T dato);
        }

    }
    public static <T> ApiCalls<T> createFromScratch(String endpoint, Class<T> clazz) {
        return new ApiCalls<>(
                clazz,
                endpoint
        );
    }
    public static ApiCalls<JsonObject> createFromScratch(String endpoint) {
        return createFromScratch(endpoint,JsonObject.class);
    }

    public static <T> ApiCalls<T> create(String endpoint, Class<T> clazz) {
        return createFromScratch(Metrics.url+endpoint, clazz);
    }

    public static ApiCalls<JsonObject> create(String endpoint) {
        return createFromScratch(Metrics.url+endpoint);
    }


    private static String gettingString(HttpURLConnection conn) {
        try {
            // la ia me ayudo en esta parte, masq nada para encotrar bugs
            int code = conn.getResponseCode();
            InputStreamReader isr;
            if (code >= 200 && code < 400) {
                isr = new InputStreamReader(conn.getInputStream());
            } else {
                InputStream es = conn.getErrorStream();
                if (es == null) {
                    return "{\"error\":10,\"message\":\"http error: " + code + " " + conn.getURL() + "\"}";
                }
                isr = new InputStreamReader(es);
            }

            BufferedReader reader = new BufferedReader(isr);
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } catch (Exception e) {
            return "{\"error\": 10,\"message\": \"" + e.getMessage() + "\"}";
        }
    }

}