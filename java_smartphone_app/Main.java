import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class Main {

    private static final String hardwareBestellung1 = "sendeHardwareBestellung1";
    private static final String hardwareBestellung2 = "sendeHardwareBestellung2";
    private static final String softwareBestellung1 = "sendeSoftwareBestellung1";
    private static final String softwareBestellung2 = "sendeSoftwareBestellung2";
    private static final String dockerContainer = "startDockerContainer";
    public static void runDocker() throws InterruptedException {
        System.out.println("Smartphone App Started!");
        while(true){
            Thread.sleep(1000);
        }
    }
    private static void start(String[] args) throws IOException, InterruptedException {
        var sender = new OrderSender();
        String arg = args[0];

        switch (arg) {
            case hardwareBestellung1 -> sender.sendHardwareOrder(1, 5);
            case hardwareBestellung2 -> sender.sendHardwareOrder(2, 3);
            case softwareBestellung1 -> sender.sendSoftwareOrder(1);
            case softwareBestellung2 -> sender.sendSoftwareOrder(2);
            case dockerContainer -> Main.runDocker();
            default -> System.out.println("Fehler, bitte geben Sie eine Bestellung an.");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        start(args);
    }
}
