package main.broker.infra;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConsumerConnectionStarter extends Thread {

    private final Logger log;
    private final Integer port;
    private final ServerSocket serverSocket;

    public ConsumerConnectionStarter() throws IOException {
        this.log = Logger.getLogger(ProducerConnectionStarter.class.getName());
        this.port = 6667;
        this.serverSocket = startSocket(port);
    }

    public ConsumerConnectionStarter(final Integer port) throws IOException {
        this.log = Logger.getLogger(ProducerConnectionStarter.class.getName());
        this.port = port;
        this.serverSocket = startSocket(this.port);
    }

    @Override
    public void run() {
        try {
            runSocket();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void runSocket() throws IOException, SQLException {
        while (true) {
            final Socket socket = serverSocket.accept();
            Thread producer = new ConsumerConnectionHandler(socket);
            producer.start();
        }
    }

    private ServerSocket startSocket(final Integer port) throws IOException {
        ServerSocket ss;

        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            log.severe("Server socket not available");
            throw new IOException(e.getMessage());
        }

        return ss;
    }
}
