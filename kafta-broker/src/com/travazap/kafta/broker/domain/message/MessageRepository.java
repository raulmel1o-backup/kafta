package com.travazap.kafta.broker.domain.message;

import com.travazap.kafta.broker.domain.topic.Topic;
import com.travazap.kafta.broker.domain.topic.TopicRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MessageRepository {

    private final Logger log;
    private final Connection connection;

    public MessageRepository() throws SQLException {
        this.log = Logger.getLogger(TopicRepository.class.getName());
        this.connection = DriverManager.getConnection("jdbc:sqlite:kafta-broker/broker.db");

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS 'message' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'headers' VARCHAR(512), 'body' VARCHAR(512))");
        }
    }

    public List<Message> findAll() {
        final List<Message> messages = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM 'message'");

            while (rs.next()) {
                messages.add(new Message(rs.getString("headers") + ";" + rs.getString("body")));
            }

            return messages;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public Topic findByById(final Integer id) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM messages WHERE messages.id = '" + id + "'");
            rs.next();

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(final Message message) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("INSERT INTO messages (headers, body) VALUES('" + message.getHeadersAsString() + "," + message.getBody() + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Topic deleteMessage(final Integer id) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM messages WHERE messages.id = '" + id + "'");
            rs.next();

            st.executeUpdate("DELETE FROM messages WHERE message.id = '"+ id + "'");

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
