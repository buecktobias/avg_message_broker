import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class HttpRequestSender {
    private final Date datum = new Date();
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private final String formatiertesDatum = formatter.format(datum);

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

    public void hardwareBestellung(String id) throws IOException {
        String hardwareBestellung = "BestellungsID: 00"+ id + "\nDatum: " + formatiertesDatum + "\n" +
                "Stückzahl: 3\nArtikelID: 005";

        String hardwareOrdersUrl = "http://localhost:8161/api/message/HardwareOrders?type=queue";
        RequestSender(hardwareBestellung, hardwareOrdersUrl);
    }

    public void softwareBestellung(String id) throws IOException {
        String softwareBestellung = "BestellungsID: 00" + id + "\nDatum: " + formatiertesDatum + "\n" +
                "Stückzahl: 1\nArtikelID: 005";

        String softwareOrdersUrl = "http://localhost:8161/api/message/SoftwareOrders?type=queue";
        RequestSender(softwareBestellung, softwareOrdersUrl);
    }
}
