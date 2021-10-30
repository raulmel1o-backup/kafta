package com.travazap.kafta.broker.domain.topic;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class TopicService {

    private final Logger log;
    private final TopicRepository repository;

    public TopicService() throws SQLException {
        this.log = Logger.getLogger(TopicService.class.getName());
        this.repository = new TopicRepository();
    }

    public List<Topic> findAll() {
        return repository.findAll();
    }

    public void save(final Topic topic) {
        repository.save(topic);
    }

    public Topic delete(final Topic topic) {
        return repository.deleteTopic(topic);
    }
}
