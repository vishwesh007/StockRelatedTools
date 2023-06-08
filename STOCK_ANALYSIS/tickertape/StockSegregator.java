package verzion;

import java.io.*;

public class StockSegregator {
    public static void main(String[] args) {
        String inputFilePath = "/storage/emulated/0/#mods/stockdatapredictorvalue.txt";
        String outputFilePath = "/storage/emulated/0/#mods/stockpredictions.txt";

        // Read the input file
        StringBuilder inputBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                inputBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String input = inputBuilder.toString();

        // Define the regex pattern
        String regexPattern = ".*title>*\n.*100<span";

        // Create a pattern object
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regexPattern);
        java.util.regex.Matcher matcher = pattern.matcher(input);

        // Write the matches to the output file
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            while (matcher.find()) {
                String match = matcher.group(0); // Get the matched string
                writer.println(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
