package test.broker.domain.message;

import main.broker.domain.message.Message;
import main.broker.domain.message.MessageRepository;
import main.broker.domain.message.MessageService;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceTests {

    private static final String textMessage1 = "mode=producer,user=raulmello,topic=xesque;hello world!";
    private static final String textMessage2 = "mode=producer,user=Raulmello;spaciba!";
    private static final String textMessage3 = "mode=producer,user=rAulmello,topic=xesque;cyka blyat!";
    private static final String textMessage4 = "mode=producer,user=raUlmello;dale!";
    private static final String textMessage5 = "mode=producer,user=rauLmello;xesque dele!";

    private final MessageRepository repository;
    private final MessageService service;

    private MessageServiceTests() throws SQLException {
        this.repository = new MessageRepository();
        this.service = new MessageService();
    }

    public void Should_Get_All_Messages_From_Topic() {
        repository.wipeDatabase();
        buildAndSaveMessages();

        final List<Message> messages = repository.findAllByTopic("default");

        if (messages.size() != 3) {
            throw new AssertionError();
        }

        if (!messages.get(0).getBody().equals("spaciba!")) {
            throw new AssertionError();
        }
    }

    public void Should_Get_All_Messages__From_Topic_After_Id() {

    }

    public void Should_Save_Message() {
        repository.wipeDatabase();

        final Message message = new Message(textMessage2);

        repository.save(message);

        final Message dbMessage = repository.findById(1);

        if (dbMessage == null) {
            throw new AssertionError();
        }

        if (!dbMessage.getBody().equals("spaciba!")) {
            throw new AssertionError();
        }
    }

    public static void main(String[] args) throws SQLException {
        final MessageServiceTests tests = new MessageServiceTests();

        tests.Should_Get_All_Messages_From_Topic();
        tests.Should_Get_All_Messages__From_Topic_After_Id();
        tests.Should_Save_Message();
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
