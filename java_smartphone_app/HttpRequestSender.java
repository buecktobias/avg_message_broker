import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import org.json.Object;

public class HttpRequestSender {
    private final Date datum = new Date();
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    private final String formatiertesDatum = formatter.format(datum);
    private Random rand = new Random();
    private long min = 100000000000000000L; // 18-stellige Zahl
    private long max = 999999999999999999L; // 18-stellige Zahl
    private long generatedId = min + (long)(rand.nextDouble() * (max - min));

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
       // String hardwareBestellung = "BestellungsID: "+ generatedId + "\nDatum: " + formatiertesDatum + "\n" +
         //       "Stückzahl: " + anzahl "\nArtikelID: " + artikel;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("BestellungsID", generatedId);
        jsonObj.put("Datum", formatiertesDatum);
        jsonObj.put("Stückzahl", anzahl);
        jsonObj.put("ArtikelID", artikel);

        String jsonStr = jsonObj.toString();
        String hardwareOrdersUrl = "http://activemq:8161/api/message/HardwareOrders?type=queue";
        RequestSender(jsonStr, hardwareOrdersUrl);
    }

    public void softwareBestellung(String artikel, String anzahl) throws IOException {
       // String softwareBestellung = "BestellungsID: " + generatedId + "\nDatum: " + formatiertesDatum + "\n" +
         //       "Stückzahl: " + anzahl "\nArtikelID: " + artikel;

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("BestellungsID", generatedId);
        jsonObj.put("Datum", formatiertesDatum);
        jsonObj.put("Stückzahl", anzahl);
        jsonObj.put("ArtikelID", artikel);

        String jsonStr = jsonObj.toString();

        String softwareOrdersUrl = "http://activemq:8161/api/message/SoftwareOrders?type=queue";
        RequestSender(jsonStr, softwareOrdersUrl);
    }
}
