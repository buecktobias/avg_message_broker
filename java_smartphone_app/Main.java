import java.io.IOException;

public class Main {

    private static final String hardwareBestellung1 = "sendeHardwareBestellung1";
    private static final String hardwareBestellung2 = "sendeHardwareBestellung2";
    private static final String softwareBestellung1 = "sendeSoftwareBestellung1";
    private static final String softwareBestellung2 = "sendeSoftwareBestellung2";
    private static final String dockerContainer = "startDockerContainer";
    public static void runDocker() throws InterruptedException {
        while(true){
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpRequestSender sender = new HttpRequestSender();
        String arg = args[0];
        System.out.println("POST wird gesendet...");

        switch (arg) {
            case hardwareBestellung1 -> sender.hardwareBestellung("1", "5");
            case hardwareBestellung2 -> sender.hardwareBestellung("2", "3");
            case softwareBestellung1 -> sender.softwareBestellung("1", "1");
            case softwareBestellung2 -> sender.softwareBestellung("2", "2");
            case dockerContainer -> Main.runDocker();
            default -> System.out.println("Fehler, bitte geben Sie eine Bestellung an.");
        }
    }
}
