package Kite;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

public class KiteApp {
    private final String rootUrl = "https://api.kite.trade";
    private final Map<String, String> headers;

    public KiteApp(String enctoken) {
        headers = new HashMap<>();
        headers.put("Authorization", "enctoken " + enctoken);
    }

    public List<Map<String, Object>> instruments(String exchange) throws IOException {
        URL url = new URL(rootUrl + "/instruments");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        List<Map<String, Object>> instrumentList = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            if (exchange == null || exchange.equals(row[11])) {
                Map<String, Object> instrument = new HashMap<>();
                instrument.put("instrument_token", Integer.parseInt(row[0]));
                instrument.put("exchange_token", row[1]);
                instrument.put("tradingsymbol", row[2]);
                instrument.put("name", row[3].substring(1, row[3].length() - 1));
                instrument.put("last_price", Float.parseFloat(row[4]));
                instrument.put("expiry", parseDate(row[5]));
                instrument.put("strike", Float.parseFloat(row[6]));
                instrument.put("tick_size", Float.parseFloat(row[7]));
                instrument.put("lot_size", Integer.parseInt(row[8]));
                instrument.put("instrument_type", row[9]);
                instrument.put("segment", row[10]);
                instrument.put("exchange", row[11]);

                instrumentList.add(instrument);
            }
        }

        reader.close();
        return instrumentList;
    }

    public Map<String, Object> quote(String instruments) throws IOException {
        URL url = new URL(rootUrl + "/quote?i=" + instruments);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the data
        // ...

        return null;
    }

    public void ltp(String instruments) throws IOException {
        URL url = new URL(rootUrl + "/quote/ltp?i=" + instruments);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the data
        // ...

        return null;
    }

    public List<Map<String, Object>> historicalData(int instrumentToken, String fromDate, String toDate,
            String interval, boolean continuous, boolean oi) throws IOException {
        URL url = new URL(rootUrl + "/instruments/historical/" + instrumentToken + "/" + interval +
                "?from=" + fromDate + "&to=" + toDate + "&continuous=" + (continuous ? "1" : "0") + "&oi="
                + (oi ? "1" : "0"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        List<Map<String, Object>> candles = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] candleData = line.split(",");
            Map<String, Object> candle = new HashMap<>();
            candle.put("date", parseDate(candleData[0]));
            candle.put("open", Float.parseFloat(candleData[1]));
            candle.put("high", Float.parseFloat(candleData[2]));
            candle.put("low", Float.parseFloat(candleData[3]));
            candle.put("close", Float.parseFloat(candleData[4]));
            candle.put("volume", Float.parseFloat(candleData[5]));
            if (candleData.length == 7) {
                candle.put("oi", Float.parseFloat(candleData[6]));
            }
            candles.add(candle);
        }
        reader.close();
        return candles;
    }

    public Map<String, Object> margins() throws IOException {
        URL url = new URL(rootUrl + "/user/margins");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the data
        // ...

        return null;
    }

    public Map<String, Object> orders() throws IOException {
        URL url = new URL(rootUrl + "/orders");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the data
        // ...

        return null;
    }

    public Map<String, Object> positions() throws IOException {
        URL url = new URL(rootUrl + "/portfolio/positions");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the data
        // ...

        return null;
    }

    public String placeOrder(String variety, String exchange, String tradingSymbol, String transactionType,
            int quantity, String product, String orderType, Float price, String validity,
            Integer disclosedQuantity, Float triggerPrice, Float squareoff, Float stoploss,
            Float trailingStoploss, String tag) throws IOException {
        URL url = new URL(rootUrl + "/orders/" + variety);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        // Set the request parameters and payload
        // ...

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the order ID
        // ...

        return null;
    }

    public String modifyOrder(String variety, String orderId, String parentOrderId, Integer quantity, Float price,
            String orderType, Float triggerPrice, String validity, Integer disclosedQuantity) throws IOException {
        URL url = new URL(rootUrl + "/orders/" + variety + "/" + orderId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        // Set the request parameters and payload
        // ...

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the order ID
        // ...

        return null;
    }

    public String cancelOrder(String variety, String orderId, String parentOrderId) throws IOException {
        URL url = new URL(rootUrl + "/orders/" + variety + "/" + orderId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Authorization", headers.get("Authorization"));

        // Set the request parameters and payload
        // ...

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = reader.readLine();
        reader.close();

        // Parse the JSON response and return the order ID
        // ...

        return null;
    }

    private Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
