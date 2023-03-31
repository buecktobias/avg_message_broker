import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


public class OrderSender {

    private final Date datum = new Date();
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private String getAuthConnectionString(){
        var auth = USERNAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }
    private HttpURLConnection getActiveMQConnection(String url){
        HttpURLConnection activeMQOrderConnection;
        URL orderActiveMQURL;
        try {
            orderActiveMQURL = new URL(url);
            activeMQOrderConnection = (HttpURLConnection) orderActiveMQURL.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return activeMQOrderConnection;
    }
    private void sendRequest(String orderText, String url) throws IOException {
        var activeMQOrderConnection = this.getActiveMQConnection(url);
        activeMQOrderConnection.setRequestProperty("Authorization", getAuthConnectionString());
        activeMQOrderConnection.setRequestMethod("POST");
        activeMQOrderConnection.setRequestProperty("Content-Type", "application/json");
        activeMQOrderConnection.setRequestProperty("Content-Length", Integer.toString(orderText.length()));
        activeMQOrderConnection.setDoOutput(true);

        try (OutputStream os = activeMQOrderConnection.getOutputStream()) {
            byte[] input = orderText.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        int statusCode = activeMQOrderConnection.getResponseCode();
        System.out.println("statusCode: " + statusCode);
    }

    public void hardwareBestellung(int artikel, int anzahl) throws IOException {
        var uuid = UUID.randomUUID();
        var hardwareBestellung = "{\n" +
                "  \"BestellungsID\": \"" + uuid + "\",\n" +
                "  \"Datum\": \"" + datum + "\",\n" +
                "  \"Stückzahl\": \"" + anzahl + "\",\n" +
                "  \"ArtikelID\": \"" + artikel + "\",\n" +
                "  \"KundenID\": \"5\"\n" +
                "}";

        var hardwareOrdersUrl = "http://activemq:8161/api/message/HardwareOrders?type=queue";
        System.out.println("Sende Hardware Bestellung !");
        sendRequest(hardwareBestellung, hardwareOrdersUrl);
    }

    public void softwareBestellung(int artikel, int anzahl) throws IOException {
        var uuid = UUID.randomUUID();
        var softwareBestellung = "{\n" +
                "  \"BestellungsID\": \"" + uuid + "\",\n" +
                "  \"Datum\": \"" + datum + "\",\n" +
                "  \"Stückzahl\": " + anzahl + ",\n" +
                "  \"ArtikelID\": \"" + artikel + "\"\n" +
                "  \"KundenID\": \"3\"\n" +
                "}";

        var softwareOrdersUrl = "http://activemq:8161/api/message/SoftwareOrders?type=queue";
        System.out.println("Sende Software Bestellung");
        sendRequest(softwareBestellung, softwareOrdersUrl);
    }
}
