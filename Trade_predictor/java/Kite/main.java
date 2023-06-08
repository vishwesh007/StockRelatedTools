package Kite;

import Kite.KiteApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {
        // Replace with your API key
        String accessToken = "your_access_token"; // Replace with your access token

        KiteApp KiteApp = new KiteApp(accessToken);

        String inputFilePath = "nse_values.txt";

        List<String> nseValues = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                nseValues.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String concatenatedValues = String.join(", ", nseValues);

        Map<String, ltp> ltpValues = KiteApp.getLTP("NSE:" + concatenatedValues);

        for (Map.Entry<String, LTP> entry : ltpValues.entrySet()) {
            String symbol = entry.getKey();
            LTP ltp = entry.getValue();
            System.out.println(symbol + ": " + ltp.lastPrice);
        }
    }
}
