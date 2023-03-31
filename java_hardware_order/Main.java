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

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        if(statusCode == 200) {
            System.out.println("Server-Antwort: " + content.toString());
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
        if(args.length > 0 && Objects.equals(args[0], "startDockerContainer")){
            Main.runDocker();
        }else {
            String url = "http://activemq:8161/api/message/SoftwareOrders?type=queue&oneShot=true";
            loadMessages(url, 100);
        }
    }
}
