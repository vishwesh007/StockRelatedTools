import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class KiteApp {
    private final String enctoken;
    private final OkHttpClient client;

    public KiteApp(String enctoken) {
        this.enctoken = enctoken;
        this.client = new OkHttpClient();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String post(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String put(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .put(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String delete(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .delete(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String login(String userId, String password) throws IOException {
        FormBody requestBody = new FormBody.Builder()
                .add("user_id", userId)
                .add("password", password)
                .build();

        String loginUrl = "https://kite.zerodha.com/api/login";

        try (Response response = client.newCall(new Request.Builder()
                .url(loginUrl)
                .post(requestBody)
                .build()).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String requestId = response.json().getString("data.request_id");
            String twofa = ""; // Replace with your 2FA value

            FormBody twofaBody = new FormBody.Builder()
                    .add("request_id", requestId)
                    .add("twofa_value", twofa)
                    .add("user_id", response.json().getString("data.user_id"))
                    .build();

            String twofaUrl = "https://kite.zerodha.com/api/twofa";

            try (Response twofaResponse = client.newCall(new Request.Builder()
                    .url(twofaUrl)
                    .post(twofaBody)
                    .build()).execute()) {

                if (!twofaResponse.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + twofaResponse);
                }

                String enctoken = twofaResponse.cookies().get("enctoken");

                if (enctoken == null) {
                    throw new IOException("Invalid login details!");
                }

                return enctoken;
            }
        }
    }

    // Rest of the code...
}
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

public class KiteApp {
    private final String enctoken;
    private final OkHttpClient client;

    public KiteApp(String enctoken) {
        this.enctoken = enctoken;
        this.client = new OkHttpClient();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String post(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String put(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .put(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String delete(String url, RequestBody requestBody) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "enctoken " + enctoken)
                .delete(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            return Objects.requireNonNull(response.body()).string();
        }
    }

    public String login(String userId, String password) throws IOException {
        FormBody requestBody = new FormBody.Builder()
                .add("user_id", userId)
                .add("password", password)
                .build();

        String loginUrl = "https://kite.zerodha.com/api/login";

        try (Response response = client.newCall(new Request.Builder()
                .url(loginUrl)
                .post(requestBody)
                .build()).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }

            String requestId = response.json().getString("data.request_id");
            String twofa = ""; // Replace with your 2FA value

            FormBody twofaBody = new FormBody.Builder()
                    .add("request_id", requestId)
                    .add("twofa_value", twofa)
                    .add("user_id", response.json().getString("data.user_id"))
                    .build();

            String twofaUrl = "https://kite.zerodha.com/api/twofa";

            try (Response twofaResponse = client.newCall(new Request.Builder()
                    .url(twofaUrl)
                    .post(twofaBody)
                    .build()).execute()) {

                if (!twofaResponse.isSuccessful()) {
                    throw new IOException("Unexpected response code: " + twofaResponse);
                }

                String enctoken = twofaResponse.cookies().get("enctoken");

                if (enctoken == null) {
                    throw new IOException("Invalid login details!");
                }

                return enctoken;
            }
        }
    }

    public static void main(String[] args) {
        try {
            String userId = ""; // Replace with your user ID
            String password = ""; // Replace with your password

            KiteApp kiteApp = new KiteApp(userId, password);
            String enctoken = kiteApp.login(userId, password);

            // Use the enctoken to perform API requests
            // For example:
            String instrumentsUrl = "https://api.kite.trade/instruments";
            String instrumentsResponse = kiteApp.get(instrumentsUrl);
            System.out.println(instrumentsResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
