package com.travazap.kafta.broker.domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MessageService {

    private static final String TEST_FILE_PATH = "./kafta-broker/resources";
    private static final String PROD_FILE_PATH = "../../../kafta-broker/resources";

    private final Logger log;
    private final String dirPath;
    private final File directory;
    private final TopicService topicService;

    public MessageService() {
        final String path = getDirectoryPath();

        this.log = Logger.getLogger(MessageService.class.getName());
        this.dirPath = path;
        this.directory = new File(path);
        this.topicService = TopicService.topicServiceBuilder();
    }

    public void save(final Message message, final String topic) {
        if (!topicService.findAll().contains(topic)) {
            log.warning(MessageFormat.format("Topic {0} does not exists", topic));
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dirPath + "/" + topic + ".txt"))) {
            writer.write(message.toString());
        } catch (IOException e) {

        }
    }

    private String getDirectoryPath() {
        String path = TEST_FILE_PATH;

        final File file = new File(path);

        if (!file.isDirectory()) {
            path = PROD_FILE_PATH;
        }

        return path;
    }
}
