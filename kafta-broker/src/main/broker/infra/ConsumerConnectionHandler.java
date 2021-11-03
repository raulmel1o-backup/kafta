package main.broker.infra;

import main.broker.domain.message.Message;
import main.broker.domain.message.MessageService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class ConsumerConnectionHandler extends Thread {

    private final MessageService service;
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ConsumerConnectionHandler(final Socket socket) throws IOException, SQLException {
        this.service = new MessageService();
        this.socket = socket;
        this.in = openInputStream();
        this.out = openOutputStream();
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String input = in.readLine();

                if (input != null) {
                    final String[] messageParameters = parseInput(input);
                    final List<Message> newMessages = service.findAllMessagesFromTopicAfterAnId(messageParameters[0], Long.parseLong(messageParameters[1]));

                    if (newMessages.isEmpty()) {
                        out.println("no new messages");
                        out.flush();
                        continue;
                    }

                    for (Message message : newMessages) {
                        out.println(message);
                        out.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String[] parseInput(final String input) {
        final String[] inputArr = new String[2];

        inputArr[0] = input.split(";")[0].substring(6);
        inputArr[1] = input.split(";")[1].substring(17);

        return inputArr;
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
}
