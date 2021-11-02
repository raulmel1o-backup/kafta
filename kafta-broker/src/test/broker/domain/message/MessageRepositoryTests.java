package test.broker.domain.message;

import main.broker.domain.message.Message;
import main.broker.domain.message.MessageRepository;

import java.sql.SQLException;
import java.util.List;

public class MessageRepositoryTests {

    private static final String textMessage1 = "mode=producer,user=raulmello,topic=xesque;hello world!";
    private static final String textMessage2 = "mode=producer,user=Raulmello;spaciba!";
    private static final String textMessage3 = "mode=producer,user=rAulmello,topic=xesque;cyka blyat!";
    private static final String textMessage4 = "mode=producer,user=raUlmello;dale!";
    private static final String textMessage5 = "mode=producer,user=rauLmello;xesque dele!";

    private final MessageRepository repository;

    private MessageRepositoryTests() throws SQLException {
        this.repository = new MessageRepository("testBroker.db");
    }

    public void Should_Find_All_Messages() {
        repository.wipeDatabase();
        buildAndSaveMessages();

        final List<Message> messages = repository.findAll();

        if (messages.size() != 5) {
            throw new AssertionError();
        }

        if (!messages.get(0).getBody().equals("hello world!")) {
            throw new AssertionError();
        }
    }

    public void Should_Find_All_Messages_In_Topic() {
        repository.wipeDatabase();
        buildAndSaveMessages();

        final List<Message> messages = repository.findAllByTopic("xesque");

        if (messages.get(0).getId() != 1) {
            throw new AssertionError();
        }

        if (messages.size() != 2) {
            throw new AssertionError();
        }

        if (!messages.get(0).getBody().equals("hello world!")) {
            throw new AssertionError();
        }
    }

    public void Should_Get_All_Messages__From_Topic_After_Id() {
        repository.wipeDatabase();
        buildAndSaveMessages();

        final List<Message> messageList = repository.findByTopicAndIdGreaterThan("default", 2L);

        if (messageList.size() != 2) {
            throw new AssertionError();
        }

        if (!messageList.get(0).getBody().equals("dale!")) {
            throw new AssertionError();
        }
    }

    public void Should_Find_Message_By_Id() {
        repository.wipeDatabase();
        buildAndSaveMessages();

        final Message message = repository.findById(2);

        if (!message.getBody().equals("spaciba!")) {
            throw new AssertionError();
        }
    }

    public void Should_Save_Message() {
        repository.wipeDatabase();

        final Message message = new Message(textMessage1);

        if (!repository.findAll().isEmpty()) {
            throw new AssertionError();
        }

        repository.save(message);

        if (repository.findAll().size() != 1) {
            throw new AssertionError();
        }

        if (!repository.findById(1).getBody().equals("hello world!")) {
            throw new AssertionError();
        }
    }

    public void Should_Delete_Message() {
        repository.wipeDatabase();
        buildAndSaveMessages();

        if (repository.findAll().size() != 5) {
            throw new AssertionError();
        }

        final Message message = repository.deleteMessage(2);

        if (!message.getBody().equals("spaciba!")) {
            throw new AssertionError();
        }

        if (repository.findAll().size() != 4) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) throws SQLException {
        final MessageRepositoryTests tests = new MessageRepositoryTests();

        tests.Should_Find_All_Messages();
        tests.Should_Find_All_Messages_In_Topic();
        tests.Should_Get_All_Messages__From_Topic_After_Id();
        tests.Should_Find_Message_By_Id();
        tests.Should_Save_Message();
        tests.Should_Delete_Message();
    }

    private void buildAndSaveMessages() {
        final Message msg1 = new Message(textMessage1);
        final Message msg2 = new Message(textMessage2);
        final Message msg3 = new Message(textMessage3);
        final Message msg4 = new Message(textMessage4);
        final Message msg5 = new Message(textMessage5);

        repository.save(msg1);
        repository.save(msg2);
        repository.save(msg3);
        repository.save(msg4);
        repository.save(msg5);
    }
}
