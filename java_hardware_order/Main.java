import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Objects;

public class Main {

    /**
     * Is needed for the Docker Container; method which runs indefinitely
     */
    private static void runDocker() throws InterruptedException {
        while(true){
            Thread.sleep(1000);
        }
    }

    private static void loadMessages(String url, int delay) throws IOException, InterruptedException {
        var orderReceiver = new OrderReceiver();
        while(true){
            orderReceiver.getNewHardwareOrders(url);
            Thread.sleep(delay);
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Java Hardware Order Started!");
        if (args.length > 0 && Objects.equals(args[0], "startDockerContainer")) {
            Main.runDocker();
        }else {
            var url = "http://activemq:8161/api/message/HardwareOrders?type=queue&oneShot=true";
            System.out.println("Java Hardware Order Listening");
            loadMessages(url, 100);
        }
    }
}
