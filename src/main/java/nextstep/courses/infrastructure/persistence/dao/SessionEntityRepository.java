package nextstep.courses.infrastructure.persistence.dao;

import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.SessionEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionEntityRepository")
public class SessionEntityRepository {

  private final JdbcTemplate jdbcTemplate;

  public SessionEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<SessionEntity> findById(Long sessionId) {
    String sql = "select id, course_id, title, description, cover_image_id, session_type, session_status,"
        + " max_enrollment_size, start_date_time, end_date_time, created_at, updated_at from session where id = ?";
    return jdbcTemplate.queryForObject(sql, rowMapper(), sessionId);
  }

  private RowMapper<Optional<SessionEntity>> rowMapper() {
    return (rs, rowNum) -> {
      Long id = rs.getLong("id");
      Long courseId = rs.getLong("course_id");
      String title = rs.getString("title");
      String description = rs.getString("description");
      Long coverImageId = rs.getLong("cover_image_id");
      String sessionType = rs.getString("session_type");
      String sessionStatus = rs.getString("session_status");
      int maxEnrollmentSize = rs.getInt("max_enrollment_size");
      LocalDateTime startDateTime = rs.getTimestamp("start_date_time").toLocalDateTime();
      LocalDateTime endDateTime = rs.getTimestamp("end_date_time").toLocalDateTime();

      return Optional.of(
          new SessionEntity(id, courseId, title, description, coverImageId, sessionType,
              sessionStatus, maxEnrollmentSize, startDateTime, endDateTime));
    };
  }
}
