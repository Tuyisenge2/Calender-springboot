package com.example.demoJava.repository;

import com.example.demoJava.models.Content;
import com.example.demoJava.models.Status;
import com.example.demoJava.models.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class contentJdbcTemplateRespository {

    private  JdbcTemplate jdbcTemplate;

    public contentJdbcTemplateRespository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    /*
  * This private static method maps a ResultSet row to a Content object.
     It extracts the data from the result set using appropriate getter methods and constructs a Content object.
     SQL exceptions may be thrown if issues occur while accessing the database.
  *
  */
    private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Content(rs.getInt("id"),
                rs.getString("title"),
                rs.getString("desc"),
//                rs.getString("status"),
//                rs.getString("contentType"),
//                rs.getTimestamp("dateCreated"),
//                rs.getTimestamp("dateUpdated"),
//                rs.getString("url"));
                Status.valueOf(rs.getString("status")),
                Type.valueOf(rs.getString("content_type")),
                rs.getObject("date_created", LocalDateTime.class),
                rs.getObject("date_updated",LocalDateTime.class),
                rs.getString("url"));
    }

    public List<Content> getAllContent() {
        String sql = "SELECT * FROM Content";
        List<Content> contents = jdbcTemplate.query(sql, contentJdbcTemplateRespository::mapRow);
        return contents;
    }
    /*
     * Executes a SQL query to retrieve all content records from the Content table.
     Uses jdbcTemplate.query with the mapRow method to convert each row in the
     result set to a Content object.
     */
    public void createContent(String title, String desc, Status status, Type contentType, String URL) {
        String sql = "INSERT INTO Content (title, desc, status, content_type, date_created, URL) VALUES (?, ?, ?, ?, NOW(), ?)";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL);
    }

    public void updateContent(int id, String title, String desc, Status status, Type contentType, String URL) {
        String sql = "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=? WHERE id=?";
        jdbcTemplate.update(sql, title, desc, status, contentType, URL, id);
    }

    public void deleteContent(int id) {
        String sql = "DELETE FROM Content WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    public Content getContent(int id) {
        String sql = "SELECT * FROM Content WHERE id=?";
        Content content = jdbcTemplate.queryForObject(sql, new Object[]{id}, contentJdbcTemplateRespository::mapRow);
        return content;
    }

}
