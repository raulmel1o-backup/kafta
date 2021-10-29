package com.travazap.kafta.broker.infra;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

public class ConsumerConnectionHandler extends Thread {

    private final Logger log;
    private final Socket socket;
    private final PrintWriter out;

    public ConsumerConnectionHandler(final Socket socket) throws IOException {
        this.log = Logger.getLogger(ProducerConnectionHandler.class.getName());
        this.socket = socket;
        this.out = openOutputStream();
    }

    @Override
    public void run() {
        log.info("Connection with consumer started");

        while (true) {
            out.println("dale");
            out.flush();
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
