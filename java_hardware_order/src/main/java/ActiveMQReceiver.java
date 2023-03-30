package src.main.java;

import org.apache.activemq.ActiveMQConnectionFactory;


public class ActiveMQReceiver {

    // evlt url ausstauschen: "http://localhost:8161/api/message/SoftwareOrders?type=queue&clientId=1"
    private static String url = "tcp://localhost:8616";
    private static String username = "admin";
    private static String password = "admin";


    public static void main(String[] args) throws JMSException {

        // Erstelle eine Verbindungsfabrik für ActiveMQ
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // Setze die Anmeldedaten für die Verbindung
        connectionFactory.setUserName(username);
        connectionFactory.setPassword(password);

        // Erstelle eine Verbindung zum ActiveMQ-Server
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Erstelle eine Sitzung für den Empfang von Nachrichten
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Erstelle ein Ziel für den Empfang von Nachrichten (hier: eine Queue mit dem Namen "example")
        Destination destination = session.createQueue("example");

        // Erstelle einen Empfänger für das Ziel
        MessageConsumer consumer = session.createConsumer(destination);

        // Empfange Nachrichten und gebe sie auf der Konsole aus
        while (true) {
            Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Empfangene Bestellung: " + textMessage.getText());
            } else {
                System.out.println("Keine Bestellungen erhalten.");
            }
        }

        // Schließe die Sitzung und die Verbindung
        // (normalerweise wird dies in einem try-with-resources-Block gemacht, um sicherzustellen, dass sie immer geschlossen werden)
        // session.close();
        // connection.close();
    }
}
