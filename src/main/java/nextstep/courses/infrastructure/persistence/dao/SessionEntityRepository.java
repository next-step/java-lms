package nextstep.courses.infrastructure.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.SessionEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionEntityRepository")
public class SessionEntityRepository {

  private static final String ID = "id";
  private static final String COURSE_ID = "course_id";
  private static final String TITLE = "title";
  private static final String DESCRIPTION = "description";
  private static final String COVER_IMAGE_ID = "cover_image_id";
  private static final String SESSION_TYPE = "session_type";
  private static final String SESSION_STATUS = "session_status";
  private static final String MAX_ENROLLMENT_SIZE = "max_enrollment_size";
  private static final String START_DATE_TIME = "start_date_time";
  private static final String END_DATE_TIME = "end_date_time";
  private static final String CREATED_AT = "created_at";
  private static final String UPDATED_AT = "updated_at";

  private final JdbcTemplate jdbcTemplate;

  public SessionEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<SessionEntity> findById(Long sessionId) {
    String sql = "select id, course_id, title, description, cover_image_id, session_type, session_status,"
        + " max_enrollment_size, start_date_time, end_date_time, created_at, updated_at from session where id = ?";
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper(), sessionId));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private RowMapper<SessionEntity> rowMapper() {
    return (rs, rowNum) -> {
      Long id = rs.getLong(ID);
      Long courseId = rs.getLong(COURSE_ID);
      String title = rs.getString(TITLE);
      String description = rs.getString(DESCRIPTION);
      Long coverImageId = rs.getLong(COVER_IMAGE_ID);
      String sessionType = rs.getString(SESSION_TYPE);
      String sessionStatus = rs.getString(SESSION_STATUS);
      int maxEnrollmentSize = rs.getInt(MAX_ENROLLMENT_SIZE);
      LocalDateTime startDateTime = toLocalDateTime(rs.getTimestamp(START_DATE_TIME));
      LocalDateTime endDateTime = toLocalDateTime(rs.getTimestamp(END_DATE_TIME));
      LocalDateTime createAt = toLocalDateTime(rs.getTimestamp(CREATED_AT));
      LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp(UPDATED_AT));

      return new SessionEntity(id, courseId, title, description, coverImageId, sessionType,
              sessionStatus, maxEnrollmentSize, startDateTime, endDateTime, createAt, updatedAt);
    };
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
