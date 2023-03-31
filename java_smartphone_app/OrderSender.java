import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


public class OrderSender {

    private final Date datum = new Date();

    private String getAuthConnectionString(){
        final var USERNAME = "admin";
        final var PASSWORD = "admin";
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
    private void sendOrder(Order order, String url) throws IOException {
        var orderText = order.toJSON();
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

        System.out.println("statusCode: " + activeMQOrderConnection.getResponseCode());
    }

    public void sendHardwareOrder(int articleId, int articleAmount) throws IOException {
        var hardwareOrder = new HardwareOrder(5, articleAmount, articleId);
        var hardwareOrdersUrl = "http://activemq:8161/api/message/HardwareOrders?type=queue";
        System.out.println("Sende Hardware Bestellung !");
        sendOrder(hardwareOrder, hardwareOrdersUrl);
    }

    public void sendSoftwareOrder(int articleId) throws IOException {
        var softwareOrder = new SoftwareOrder(articleId, LicenceType.BASIC);
        var softwareOrdersUrl = "http://activemq:8161/api/message/SoftwareOrders?type=queue";
        System.out.println("Sende Software Bestellung");
        sendOrder(softwareOrder, softwareOrdersUrl);
    }
}
