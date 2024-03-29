import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

public class OrderReceiver {
    private HttpURLConnection getActiveMQConnection(String url){
        try {
            var urlCon = new URL(url);
            return (HttpURLConnection) urlCon.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private String getAuthString(){
        var username = "admin";
        var password = "admin";
        var auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        return "Basic " + new String(encodedAuth);
    }
    public void getNewHardwareOrders(String url) throws IOException {
        var activeMQConnection = getActiveMQConnection(url);
        activeMQConnection.setRequestProperty("Authorization", getAuthString());
        activeMQConnection.setRequestMethod("GET");
        activeMQConnection.setRequestProperty("Content-Type", "application/json");

        var in = new BufferedReader(new InputStreamReader(activeMQConnection.getInputStream()));
        String inputLine;
        var content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        if(activeMQConnection.getResponseCode() == 200) {
            System.out.println("Neue Hardware Order: " + content);
        }
    }
}
