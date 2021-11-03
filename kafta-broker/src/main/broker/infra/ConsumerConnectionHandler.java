package main.broker.infra;

import main.broker.domain.message.MessageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConsumerConnectionHandler extends Thread {

    private final Logger log;
    private final MessageService service;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ConsumerConnectionHandler(final Socket socket) throws IOException, SQLException {
        this.log = Logger.getLogger(ProducerConnectionHandler.class.getName());
        this.service = new MessageService();
        this.socket = socket;
        this.in = openInputStream();
        this.out = openOutputStream();
    }

    @Override
    public void run() {

        try {
            String a = in.readLine();

            System.out.println(a);

            String b = in.readLine();
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        final String topic = getConsumerTopic();
//
//        log.info("Connection with consumer started on topic: ");
//
//        try {
//            while (true) {
//                final String input = in.readLine();
//
//                if (input != null && input.equals("exit()")) break;
//
//                if (input != null && input.contains("lastMessageIndex=")) {
//                    String index = input.substring(input.indexOf("lastMessageIndex=") + "lastMessageIndex=".length());
//
////                    System.out.println(index);
//                }
//
//                System.out.println(input);
//
//                out.println("dale");
//            }
//
//            closeConnection();
//        } catch (final IOException e) {
//            e.printStackTrace();
//        }
//
    }

    private String getConsumerTopic() {
        try {
            return in.readLine().substring(6);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return "default";
    }

    private BufferedReader openInputStream() throws IOException {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Could not open input stream");
        }
    }

    private PrintWriter openOutputStream() throws IOException {
        try {
            return new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Could not open output stream");
        }
    }

    private void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
