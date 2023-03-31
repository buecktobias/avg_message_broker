import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


public class HttpRequestSender {

    private final Date datum = new Date();

    private void RequestSender(String bestellung, String url) throws IOException {
        URL urlCon = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlCon.openConnection();
        String username = "admin";
        String password = "admin";
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        String authHeaderValue = "Basic " + new String(encodedAuth);
        con.setRequestProperty("Authorization", authHeaderValue);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Content-Length", Integer.toString(bestellung.length()));
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = bestellung.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int statusCode = con.getResponseCode();
        System.out.println("statusCode: " + statusCode);
    }

    public void hardwareBestellung(String artikel, String anzahl) throws IOException {
        UUID uuid = UUID.randomUUID();
        String hardwareBestellung = "{\n" +
                "  \"BestellungsID\": \"" + uuid + "\",\n" +
                "  \"Datum\": \"" + datum + "\",\n" +
                "  \"Stückzahl\": \"" + anzahl + "\",\n" +
                "  \"ArtikelID\": \"" + artikel + "\",\n" +
                "  \"KundenID\": \"5\"\n" +
                "}";

        String hardwareOrdersUrl = "http://activemq:8161/api/message/HardwareOrders?type=queue";
        System.out.println("Sende Hardware Bestellung !");
        RequestSender(hardwareBestellung, hardwareOrdersUrl);
    }

    public void softwareBestellung(String artikel, String anzahl) throws IOException {
        UUID uuid = UUID.randomUUID();
        String softwareBestellung = "{\n" +
                "  \"BestellungsID\": \"" + uuid + "\",\n" +
                "  \"Datum\": \"" + datum + "\",\n" +
                "  \"Stückzahl\": " + anzahl + ",\n" +
                "  \"ArtikelID\": \"" + artikel + "\"\n" +
                "  \"KundenID\": \"3\"\n" +
                "}";

        String softwareOrdersUrl = "http://activemq:8161/api/message/SoftwareOrders?type=queue";
        System.out.println("Sende Software Bestellung");
        RequestSender(softwareBestellung, softwareOrdersUrl);
    }
}
