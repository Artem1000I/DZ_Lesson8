/*
package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Request;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class ExampleMain {

    static Properties prop = new Properties();

    public static void main(String[] args)throws IOException {
        loadProperties();
        OkHttpClient client = new OkHttpClient();

        //Сегментированное построение Url
        HttpUrl url = new  HttpUrl.Builder()
                .scheme("http")
                .host(prop.getProperty("BASE_HOST"))
                .addPathSegment(prop.getProperty("FORECAST"))
                .addPathSegment(prop.getProperty("API_VERSION"))
                .addPathSegment(prop.getProperty("FORECAST_TYPE"))
                .addPathSegment("apikey",prop.getProperty("SAINT_PETERSBURG_KEY"))
                .addQueryParametr("language","ru-ru")
                .addQueryParametr("metric","true")
                .build();

        System.out.println(url.toString());

        //При необходимости указать заголовки
        Request requesthttp = new Request.Builder()
                .addHeader("accept","application/json")
                .url(url)
                .build();

        String jsonResponse = client.newCall(requesthttp).execute().body().string(); //Вызываем реквэст приводим бади к формату стринг
        System.out.println(jsonResponse); //Выводим на экран
        ObjectMapper mapper = new ObjectMapper();
        StringReader reader = new StringReader(jsonResponse);

        Example example = mapper.readValue(reader,Example.class);
        System.out.println();


    }
}*/
