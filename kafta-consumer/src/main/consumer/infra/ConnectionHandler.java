package main.consumer.infra;

import main.consumer.domain.Message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.util.logging.Logger;

public class ConnectionHandler {

    //    private static final String LMI_FILE_PATH = "./kafta-broker/resources/lmi.txt";
    private static final String LMI_FILE_PATH = "./resources/lmi.txt";

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
            out.println("topic=" + topic + ";lastMessageIndex=" + lastMessageIndex);
            out.flush();

            final String input = in.readLine();

            if (!input.equals("no new messages")) {
                final Message message = new Message(input);

                if ((long) message.getId() <= lastMessageIndex) continue;

                lastMessageIndex = updateLastMessageIndex(message.getId());

                log.info(message.toString());
            }
        }
    }

    private Long updateLastMessageIndex(final Integer lmi) {
        try (PrintWriter writer = new PrintWriter(LMI_FILE_PATH)) {
            writer.write(lmi.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return (long) lmi;
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
}
