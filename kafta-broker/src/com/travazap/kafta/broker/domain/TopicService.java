package com.travazap.kafta.broker.domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class TopicService {

    private static final String TEST_FILE_PATH = "./kafta-broker/resources/";
    private static final String PROD_FILE_PATH = "../../../kafta-broker/resources/";

    private final Logger log;
    private final File topicDirectory;
    private final File topicsFile;
    private final List<String> topics;

    public TopicService() throws FileNotFoundException {
        final String path = getDirectoryPath();

        this.log = Logger.getLogger(TopicService.class.getName());
        this.topicDirectory = new File(path);
        this.topicsFile = new File(path + "topics.txt");
        this.topics = getTopicsFromFile(topicsFile);
    }

    public static TopicService topicServiceBuilder() {
        TopicService topicService = null;

        try {
            topicService = new TopicService();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return topicService;
    }

    public List<String> findAll() {
        return this.topics;
    }

    public void save(final String topic) {
        if (topics.contains(topic)) {
            log.info(MessageFormat.format("Topic {0} already exists", topic));
            return;
        }

        boolean result = writeTopicToFile(topic);

        if (result) {
            this.topics.add(topic);
        }
    }

    public String delete(final String topic) {
        if (!topics.contains(topic)) {
            log.info(MessageFormat.format("Topic {0} not found", topic));
            return null;
        }

        boolean result = deleteTopicFromFile(topic);

        if (result) {
            topics.remove(topic);
            return topic;
        }

        return null;
    }

    private String getDirectoryPath() {
        String path = TEST_FILE_PATH;

        final File file = new File(path);

        if (!file.isDirectory()) {
            path = PROD_FILE_PATH;
        }

        return path;
    }

    private boolean writeTopicToFile(final String topic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.topicsFile))) {
            log.info("Saving new topic to file");
            writer.append(topic);

            Files.createFile(Path.of(this.topicDirectory + topic + ".txt"));

            return true;
        } catch (IOException e) {
            log.warning("Cannot save new topic to file");
            e.printStackTrace();

            return false;
        }
    }

    private boolean deleteTopicFromFile(final String topic) {
        try {
            final File temp = File.createTempFile("topic", ".txt");
            final BufferedReader reader = new BufferedReader(new FileReader(topicsFile));
            final BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

            String line = reader.readLine();
            while (line != null) {
                if (line.trim().equals(topic.trim())) continue;

                writer.write(line + System.getProperty("line.separator"));
            }

            reader.close();
            writer.close();
            return temp.renameTo(topicsFile);

        } catch (IOException e) {
            log.warning("Could not delete topic");
            e.printStackTrace();
            return false;
        }
    }

    private List<String> getTopicsFromFile(final File file) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(file)) {
            final List<String> topicsList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                topicsList.add(scanner.nextLine());
            }

            return topicsList;

        } catch (FileNotFoundException e) {
            log.warning("Could not get topics from file");
            e.printStackTrace();
            throw new FileNotFoundException();
        }
    }




}
