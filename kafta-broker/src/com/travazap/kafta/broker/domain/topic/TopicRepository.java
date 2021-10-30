package com.travazap.kafta.broker.domain.topic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TopicRepository {

    private final Logger log;
    private final Connection connection;

    public TopicRepository() throws SQLException {
        this.log = Logger.getLogger(TopicRepository.class.getName());
        this.connection = DriverManager.getConnection("jdbc:sqlite:kafta-broker/broker.db");

        try (Statement st = connection.createStatement()) {
            st.executeUpdate("CREATE TABLE IF NOT EXISTS 'topics' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR(255) UNIQUE)");
        }
    }

    public List<Topic> findAll() {
        final List<Topic> topics = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM 'topics'");

            while (rs.next()) {
                topics.add(new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name")));
            }

            return topics;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return topics;
    }

    public Topic findByTopicId(final Integer id) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM topics WHERE topics.id = '" + id + "'");
            rs.next();

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Topic findByTopicName(final String name) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM topics WHERE topics.name = '" + name + "'");
            rs.next();

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void save(final Topic topic) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("INSERT INTO topics (name) VALUES('" + topic.getName() + "')");
            final ResultSet rs = st.executeQuery("SELECT id FROM topics WHERE topics.name = '" + topic.getName() + "'");

            rs.next();
            String id = rs.getString("id");
            topic.setId(Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Topic deleteTopic(final Topic topic) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM topics WHERE topics.id = '" + topic.getId() + "'");
            rs.next();

            st.executeUpdate("DELETE FROM topics WHERE topics.id = '"+ topic.getId() + "'");

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Topic deleteTopicById(final Integer id) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM topics WHERE topics.id = '" + id + "'");
            rs.next();

            st.executeUpdate("DELETE FROM topics WHERE topics.id = '"+ id + "'");

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Topic deleteTopicByName(final String name) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery("SELECT * FROM topics WHERE topics.name = '" + name + "'");
            rs.next();

            st.executeUpdate("DELETE FROM topics WHERE topics.name = '"+ name + "'");

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
