package main.producer;

import main.producer.infra.ConnectionHandler;

import java.io.IOException;

public class ProducerApplication {

    private static String host = "localhost";
    private static String port = "6666";

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-p" -> port = args[i + 1];
                case "-h" -> host = args[i + 1];
                default -> throw new IllegalArgumentException("Argument not expected");
            }
        }

        final ConnectionHandler connectionHandler = new ConnectionHandler(host, port);
        connectionHandler.handleConnection();
    }
}
