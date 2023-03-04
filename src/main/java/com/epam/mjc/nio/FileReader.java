package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class FileReader {
    HashMap<String, String> lineMap = new HashMap<>();

    public Profile getDataFromFile(File file) {
        Path path = file.toPath();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                parse(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Profile profile = new Profile();
        profile.setName(lineMap.get("Name:"));
        profile.setEmail(lineMap.get("Email:"));
        profile.setAge(Integer.parseInt(lineMap.get("Age:")));
        profile.setPhone(Long.parseLong(lineMap.get("Phone:")));
        return profile;
    }

    public void parse(String line) {
        String[] lines = line.split(" ");
        lineMap.put(lines[0], lines[1]);
    }
}
