package com.example.vuun.description.activity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vuun on 10/18/2015.
 */
public class getPlace {
    class ClassTest {
        private String PlaceName;
        private String Detail;
    }

    public static void main(String[] arg0) throws IOException {
        eiei();
    }


    public static void eiei() throws IOException {
        Gson gson = new Gson();
        URL url = new URL("http://inzzpk.in.th/a.php");
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("PlaceId", "tl_01"); // ส่งพารามิเตอร์แก้ตรงนี้

        StringBuilder postData = addParam(params);
        String urlParameters = postData.toString();

        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(urlParameters);
        writer.flush();

        String output;
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        output = reader.readLine();
        ClassTest obj = gson.fromJson(output, ClassTest.class);
        System.out.println(obj.PlaceName); // obj.PlaceName เก็บค่า PlaceName
        System.out.println(obj.Detail); // obj.PlaceName เก็บค่า Detail
        writer.close();
        reader.close();
    }

    public static StringBuilder addParam(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        return postData;
    }
}
