package consoles;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SlugParser {
    public static void main(String[] args) {
        String filePath = "/storage/emulated/0/#mods/response.txt";

        String jsonResponse = readFromFile(filePath);
        if (jsonResponse != null) {
            Set<String> slugSet = new HashSet<>();
            Set<String> sidSet = new HashSet<>();

            int startIndex = jsonResponse.indexOf("\"slug\":\"");
            while (startIndex != -1) {
                startIndex += 8; // Move the index to the beginning of the slug value
                int endIndex = jsonResponse.indexOf("\"", startIndex);
                if (endIndex != -1) {
                    String slug = jsonResponse.substring(startIndex, endIndex);
                    slugSet.add(slug);
                    startIndex = jsonResponse.indexOf("\"slug\":\"", endIndex);
                } else {
                    break;
                }
            }

            startIndex = jsonResponse.indexOf("\"sid\":\"");
            while (startIndex != -1) {
                startIndex += 7; // Move the index to the beginning of the sid value
                int endIndex = jsonResponse.indexOf("\"", startIndex);
                if (endIndex != -1) {
                    String sid = jsonResponse.substring(startIndex, endIndex);
                    sidSet.add(sid);
                    startIndex = jsonResponse.indexOf("\"sid\":\"", endIndex);
                } else {
                    break;
                }
            }

            String slugFilePath = "/storage/emulated/0/#mods/slug.txt";
            String sidFilePath = "/storage/emulated/0/#mods/sid.txt";
            writeToFile(slugFilePath, slugSet);
            writeToFile(sidFilePath, sidSet);
            System.out.println("Output written to files:\n- " + slugFilePath + "\n- " + sidFilePath);
        }
    }

    public static String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public static void writeToFile(String filePath, Set<String> values) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String value : values) {
                writer.write(value + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



