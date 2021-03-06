package com.shoppingapp.apis.search.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.google.common.annotations.VisibleForTesting;
import com.shoppingapp.apis.search.model.LocationInfo;
import com.shoppingapp.apis.search.model.TransportMode;
import com.shoppingapp.apis.search.model.Units;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class LocationServiceImpl {
    private
    @Value("${googleUrl}")
    String googleUrl;

    private
    @Value("${apiKey}")
    String apiKey;

    private String output = "json";


    public LocationInfo getDistance(String source, String destination, TransportMode mode) {
        source = source.replace(" ", "+");
        destination = destination.replace(" ", "+");
        String args = "?key=" + apiKey + "&mode=" + mode + "&origins=" + source + "&destinations=" + destination;
        String urlString = googleUrl + output + args;
        String res = callURL(urlString);
        System.out.println(urlString);

        return getLocation(res);

    }


    public String callURL(String urlString) {


        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(urlString)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
            String line;
            String buff = "";
            while ((line = reader.readLine()) != null) {
                buff += line;
            }

            return buff;
        } catch (IOException e) {

        }
        return null;

    }

    private LocationInfo getLocation(String res) {

        if (res == null || res.isEmpty()) {
            return null;
        }

        JsonNode node = null;

        try {
            node = JsonLoader.fromString(res);
            LocationInfo locationInfo = new LocationInfo();

            String distanceString = node.get("rows").get(0).get("elements").get(0).get("distance").get("text").toString();
            locationInfo.setDistance(distanceString);

            String distanceMeters = node.get("rows").get(0).get("elements").get(0).get("distance").get("value").toString();
            locationInfo.setDistanceMeters(Double.valueOf(distanceMeters));

            String durationString = node.get("rows").get(0).get("elements").get(0).get("duration").get("text").toString();
            locationInfo.setDuration(durationString);

            String durationMinutes = node.get("rows").get(0).get("elements").get(0).get("duration").get("value").toString();
            locationInfo.setDurationMinutes(Double.valueOf(durationMinutes));

            return locationInfo;
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        return null;
    }

}

