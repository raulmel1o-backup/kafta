package main.producer;

import main.producer.infra.ConnectionHandler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProducerApplication {

//    private static final String BANNER_FILE_PATH = "./kafta-broker/resources/banner.txt"; // run on ide
    private static final String BANNER_FILE_PATH = "./resources/banner.txt"; // run on terminal

    private static final Scanner sc = new Scanner(System.in);
    
    private static String host = "localhost";
    private static String port = "6666";

    public static void main(String[] args) throws IOException {
        printBanner();

        getHostFromTerminalInput();
        getPortFromTerminalInput();

        final ConnectionHandler connectionHandler = new ConnectionHandler(host, port);
        connectionHandler.handleConnection();
    }

    private static void getHostFromTerminalInput() {
        String input;

        System.out.println("Insert host (localhost): ");

        input = sc.nextLine();
        if (!input.trim().isEmpty()) host = input;
    }

    private static void getPortFromTerminalInput() {
        String input;

        System.out.println("Insert port (6666): ");

        input = sc.nextLine();
        if (!input.trim().isEmpty()) port = input;
    }

    private static void printBanner() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(BANNER_FILE_PATH)))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException("Banner file was not found");
        }
    }
}
