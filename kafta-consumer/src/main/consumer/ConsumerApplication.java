package main.consumer;

import main.consumer.infra.ConnectionHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsumerApplication {

//    private static final String BANNER_FILE_PATH = "./kafta-consumer/resources/banner.txt"; // run on IDE
    private static final String BANNER_FILE_PATH = "./resources/banner.txt"; // run on terminal

//    private static final String LMI_FILE_PATH = "./kafta-consumer/resources/lmi.txt"; // run on IDE
    private static final String LMI_FILE_PATH = "./resources/lmi.txt"; // run on terminal

    private static final Scanner sc = new Scanner(System.in);
    
    private static String host = "localhost";
    private static String port = "6667";
    private static String topic = "default";
    private static Long lastMessageIndex = 0L;

    public static void main(String[] args) throws IOException {
        printBanner();
        lastMessageIndex = getLastMessageIndexFromFile();

        getHostFromTerminalInput();
        getPortFromTerminalInput();
        getTopicFromTerminalInput();
        getLMIFromTerminalInput();

        final ConnectionHandler connectionHandler = new ConnectionHandler(host, port, topic, lastMessageIndex);
        connectionHandler.handleConnection();
    }

    private static void getHostFromTerminalInput() {
        final String input;

        System.out.println("Insert host (localhost): ");
        input = sc.nextLine();

        if (!input.trim().isEmpty()) host = input;
    }

    private static void getPortFromTerminalInput() {
        final String input;

        System.out.println("Insert broker port (6667): ");
        input = sc.nextLine();

        if (!input.trim().isEmpty()) port = input;
    }

    private static void getTopicFromTerminalInput() {
        final String input;

        System.out.println("Insert topic (default): ");
        input = sc.nextLine();

        if (!input.trim().isEmpty()) topic = input;
    }

    private static void getLMIFromTerminalInput() {
        final String input;

        System.out.println("Insert last message index: ");
        input = sc.nextLine();

        if (!input.trim().isEmpty()) lastMessageIndex = Long.parseLong(input);
    }

    private static Long getLastMessageIndexFromFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(LMI_FILE_PATH))) {
            return Long.parseLong(reader.readLine());
        }
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
