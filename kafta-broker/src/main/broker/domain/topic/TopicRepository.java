package main.broker.domain.topic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Deprecated
public class TopicRepository {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS 'topics' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR(255) UNIQUE)";
    private static final String SELECT_ALL = "SELECT * FROM topics";
    private static final String SELECT_BY_NAME = "SELECT * FROM topics WHERE topics.name = '";
    private static final String INSERT_BY_NAME = "INSERT INTO topics (name) VALUES('";
    private static final String DELETE_BY_NAME = "DELETE FROM topics WHERE topics.name = '";

    private final Connection connection;

    public TopicRepository() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:kafta-broker/resources/broker.db");

        try (Statement st = connection.createStatement()) {
            st.executeUpdate(CREATE_TABLE);
        }
    }

    public List<Topic> findAll() {
        return executeSelectMultipleInDB(SELECT_ALL);
    }

    public Topic findByTopicName(final String name) {
        return executeSelectOneInDB(SELECT_BY_NAME + name + "'");
    }

    public void save(final Topic topic) {
        executeInsertIntoDB(INSERT_BY_NAME + topic.getName() + "')", topic);
    }

    public Topic deleteTopicByName(final String name) {
        return executeDeleteInDB(DELETE_BY_NAME + name + "'", name);
    }

    private Topic executeSelectOneInDB(final String query) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);
            rs.next();

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<Topic> executeSelectMultipleInDB(final String query) {
        final List<Topic> topics = new ArrayList<>();

        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                topics.add(new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name")));
            }

            return topics;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void executeInsertIntoDB(final String query, final Topic topic) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(query);
            final ResultSet rs = st.executeQuery(SELECT_BY_NAME + "'" + topic.getName() + "'");

            rs.next();
            String id = rs.getString("id");
            topic.setId(Integer.parseInt(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Topic executeDeleteInDB(final String query, final String topicName) {
        try (Statement st = connection.createStatement()) {
            final ResultSet rs = st.executeQuery(SELECT_BY_NAME + topicName + "'");
            rs.next();

            st.executeUpdate(query);

            return new Topic(Integer.parseInt(rs.getString("id")), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
