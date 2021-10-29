package com.travazap.kafta.broker.infra;

import com.travazap.kafta.broker.domain.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

public class ProducerConnectionHandler extends Thread {

    private final Logger log;
    private final Socket socket;
    private final BufferedReader in;

    public ProducerConnectionHandler(final Socket socket) throws IOException {
        this.log = Logger.getLogger(ProducerConnectionHandler.class.getName());
        this.socket = socket;
        this.in = openInputStream();
    }

    @Override
    public void run() {
        log.info("Connection with producer started");

        try {
            while (true) {
                final String input = in.readLine();

                if (input != null && input.equals("exit()")) break;

                if (input != null) {
                    final Message message = new Message(input);
                }
            }

            closeConnection();
        } catch (IOException e) {
            log.severe("IO error");
        }
    }

    private BufferedReader openInputStream() throws IOException {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Could not open input stream");
        }
    }

    private void closeConnection() {
        try {
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
