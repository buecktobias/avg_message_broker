
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class Main {

    // evlt url ausstauschen: "http://localhost:8161/api/message/SoftwareOrders?type=queue&clientId=1"
    private static String url = "http://localhost:8161/api/message/SoftwareOrders?type=queue&clientId=1";

    private static void requestSender(String url) throws IOException {
        URL urlCon = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlCon.openConnection();
        String username = "admin";
        String password = "admin";
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeaderValue = "Basic " + new String(encodedAuth);
        con.setRequestProperty("Authorization", authHeaderValue);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int statusCode = con.getResponseCode();
        System.out.println("statusCode: " + statusCode);
    }

    public static void main(String[] args) throws IOException {
        requestSender(url);
    }
}
