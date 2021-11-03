package main.broker.domain.message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageRepository {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS 'messages' (" +
            "'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "'datetime' TEXT, " +
            "'body' VARCHAR(512), " +
            "'topic' VARCHAR(512))";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS 'messages'";
    private static final String SELECT_ALL = "SELECT * FROM messages";
    private static final String SELECT_BY_ID = "SELECT * FROM messages WHERE messages.id = '";
    private static final String SELECT_BY_TOPIC_ID_GREATER_THAN = "SELECT * FROM messages WHERE messages.id > ";
    private static final String SELECT_BY_TOPIC = "SELECT * FROM messages WHERE messages.topic = '";
    private static final String INSERT = "INSERT INTO messages (datetime, body, topic) VALUES('";

    private final Connection connection;

    public MessageRepository() throws SQLException {
//        this.connection = DriverManager.getConnection("jdbc:sqlite:kafta-broker/resources/broker.db");
        this.connection = DriverManager.getConnection("jdbc:sqlite:./resources/broker.db");

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(CREATE_TABLE);
        }
    }

    public MessageRepository(final String path) throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + path);

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(CREATE_TABLE);
        }
    }

    public List<Message> findAll() {
        return executeSelectMultipleInDB(SELECT_ALL);
    }

    public List<Message> findAllByTopic(final String topic) {
        return executeSelectMultipleInDB(SELECT_BY_TOPIC + topic + "'");
    }

    public List<Message> findByTopicAndIdGreaterThan(final String topic, final Long id) {
        final List<Message> messages = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            String query = "SELECT * FROM messages WHERE messages.id > " + id + " AND messages.topic = '" + topic + "'";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                messages.add(new Message(rs.getInt("id"), rs.getString("topic"), rs.getString("body"), rs.getString("datetime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public Message findById(final Integer id) {
        return executeSelectOneInDB(SELECT_BY_ID + id + "'");
    }

    public void save(final Message message) {
        executeInsertIntoDB(INSERT + message.getDatetime().toString() + "', '" + message.getBody() + "', '" + message.getTopic() + "')");
    }

    public void wipeDatabase() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(DROP_TABLE);
            st.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Message executeSelectOneInDB(final String query) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);
            rs.next();

            return new Message(rs.getInt("id"), rs.getString("topic"), rs.getString("body"), rs.getString("datetime"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Message> executeSelectMultipleInDB(final String query) {
        final List<Message> messages = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                messages.add(new Message(rs.getInt("id"), rs.getString("topic"), rs.getString("body"), rs.getString("datetime")));
            }

            return messages;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void executeInsertIntoDB(final String query) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
