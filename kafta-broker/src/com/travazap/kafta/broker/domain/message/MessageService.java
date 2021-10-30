package com.travazap.kafta.broker.domain.message;

import com.travazap.kafta.broker.domain.topic.TopicService;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.logging.Logger;

public class MessageService {

    private final Logger log;
    private final TopicService topicService;
    private final MessageRepository repository;

    public MessageService() throws SQLException {
        this.log = Logger.getLogger(MessageService.class.getName());
        this.topicService = new TopicService();
        this.repository = new MessageRepository();
    }

    public void save(final Message message, final String topic) {
        if (!topicService.findAll().contains(topic)) {
            log.warning(MessageFormat.format("Topic {0} does not exists", topic));
            return;
        }
    }
}
