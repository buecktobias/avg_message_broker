import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;

public class Main {

    public static void runDocker() throws InterruptedException {
        while(true){
            Thread.sleep(1000);
        }
    }

    private static void requestSender(String url) throws IOException {
        var urlCon = new URL(url);
        var con = (HttpURLConnection) urlCon.openConnection();
        var username = "admin";
        var password = "admin";
        var auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
        var authHeaderValue = "Basic " + new String(encodedAuth);
        con.setRequestProperty("Authorization", authHeaderValue);
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        var statusCode = con.getResponseCode();

        var in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        var content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        if(statusCode == 200) {
            System.out.println("Neue Hardware Order: " + content.toString());
        }
    }

    public static void loadMessages(String url, int delay) throws IOException, InterruptedException {
        while(true){
            requestSender(url);
            Thread.sleep(delay);
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        // evlt url ausstauschen: "http://localhost:8161/api/message/SoftwareOrders?type=queue&clientId=1"
        System.out.println("Java Hardware Order Started!");
        if (args.length > 0 && Objects.equals(args[0], "startDockerContainer")) {
            Main.runDocker();
        }else if(args.length > 0 && Objects.equals(args[0], "startListening")){
            System.out.println("Java Hardware Order Listening");
            var url = "http://activemq:8161/api/message/HardwareOrders?type=queue&oneShot=true";
            loadMessages(url, 100);
        }else {
            System.out.println("Java Hardware Order Listening");
            var url = "http://activemq:8161/api/message/HardwareOrders?type=queue&oneShot=true";
            loadMessages(url, 100);
        }
    }
}
