package com.travazap.kafta.broker.infra;

import com.travazap.kafta.broker.domain.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ConnectionStarter {

    private static final String PRODUCER_MODE = "mode=producer";
    private static final String CONSUMER_MODE = "mode=consumer";

    private final Logger log;
    private final Integer port;
    private final ServerSocket serverSocket;

    public ConnectionStarter() throws IOException {
        this.log = Logger.getLogger(ConnectionStarter.class.getName());
        this.port = 6666;
        this.serverSocket = startSocket(port);
    }

    public ConnectionStarter(final Integer port) throws IOException {
        this.log = Logger.getLogger(ConnectionStarter.class.getName());
        this.port = port;
        this.serverSocket = startSocket(this.port);
    }

    public void runSocket() throws IOException {
        while (true) {
            final Socket socket = serverSocket.accept();
            final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            log.info("Waiting for connection");

            final String mode = in.readLine();

            if (mode.equals(CONSUMER_MODE)) {
                // TODO: implement consumer thread
                Thread t = new ConsumerConnectionHandler(socket);
                t.start();
            } else if (mode.equals(PRODUCER_MODE)) {
                // TODO: implement producer thread
                Thread t = new ProducerConnectionHandler(socket);
                t.start();
            } else {
                log.warning("Connection not succeeded");
            }
        }



//        while (true) {
//            log.info("Waiting for connection");
//
//            final String mode = in.readLine();
//
//            if (mode.equals(CONSUMER_MODE)) {
//                // TODO: implement consumer thread
//                Thread t = new ConsumerConnectionHandler(socket);
//                t.start();
//            } else if (mode.equals(PRODUCER_MODE)) {
//                // TODO: implement producer thread
//                Thread t = new ProducerConnectionHandler(socket);
//                t.start();
//            } else {
//                log.warning("Connection not succeeded");
//            }
//        }
    }

    private ServerSocket startSocket(final Integer port) throws IOException {
        ServerSocket socket;

        try {
            socket = new ServerSocket(port);
        } catch (IOException e) {
            log.severe("Server socket not available");
            throw new IOException(e.getMessage());
        }

        return socket;
    }


}
