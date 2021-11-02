package main.broker.domain.message;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class MessageService {

    private final Logger log;
    private final MessageRepository repository;

    public MessageService() throws SQLException {
        this.log = Logger.getLogger(MessageService.class.getName());
        this.repository = new MessageRepository();
    }

    public List<Message> findAllMessagesFromTopicAfterAnId(final String topic, final Long id) {
        return repository.findByTopicAndIdGreaterThan(topic, id);
    }

    public void save(final Message message, final String topic) {
        repository.save(message);

        log.info("New message stored in topic " + message.getTopic());
    }
}
