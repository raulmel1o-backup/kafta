package main.consumer.infra;

import main.consumer.domain.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionHandler {

    private final Logger log;
    private final String host;
    private final Integer port;
    private final String topic;

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Long lastMessageIndex;

    public ConnectionHandler(final String host, final String port, final String topic, final Long lastMessageIndex) {
        this.log = Logger.getLogger(ConnectionHandler.class.getName());
        this.host = host;
        this.port = Integer.valueOf(port);
        this.topic = topic;
        this.lastMessageIndex = lastMessageIndex;
    }

    public void handleConnection() throws IOException {
        socket = createSocket (host, port);
        out = new PrintWriter(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        log.info("Connection established with broker");

        while (true) {
            out.println("topic=" + topic + ",lastMessageIndex=" + lastMessageIndex);
            out.flush();

            final String input = in.readLine();

            if (input != null) {
                final Message message = new Message(input);
                lastMessageIndex = (long) message.getId();
            }
        }
    }

    private Socket createSocket(final String host, final Integer port) {
        Socket clientSocket;

        try {
            clientSocket = new Socket(host, port);
        } catch (IOException e) {
            throw new IllegalStateException("Could not establish a connection to broker.");
        }

        return clientSocket;
    }

    private void startConnection() {
        out.println("topic=xesque");
        out.flush();
    }

    private void closeConnection() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
