package cz.rgates.first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdventSolver {
    public static void main(String[] args) {

        String urlString = "https://adventofcode.com/2024/day/1/input";
        String sessionCookie = "53616c7465645f5f281a05e93408f74ece6ac85e4ea7dcc64a1eed18b0a90c64e" +
                "085d04ecec23e00d3018975697dce4f1389897e20b6015a3364edc695263c23";

        List<Integer> leftNumbers = new ArrayList<>();
        List<Integer> rightNumbers = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", "session=" + sessionCookie);

            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {

                    String[] parts = line.trim().split("\\s+");
                    if (parts.length == 2) {
                        leftNumbers.add(Integer.parseInt(parts[0]));
                        rightNumbers.add(Integer.parseInt(parts[1]));
                    }
                }
                reader.close();
            } else {
                System.err.println("Error connecting to server: " + connection.getResponseMessage());
                return;
            }

        } catch (Exception e) {
            System.err.println("Error loading data: " + e.getMessage());
            return;
        }

        Collections.sort(leftNumbers);
        Collections.sort(rightNumbers);


        int totalDistance = 0;
        for (int i = 0; i < leftNumbers.size(); i++) {
            totalDistance += Math.abs(leftNumbers.get(i) - rightNumbers.get(i));
        }

        System.out.println("Total distance between lists: " + totalDistance);

    }
}