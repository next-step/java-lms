package nextstep.courses.infrastructure;

import java.util.List;
import nextstep.courses.domain.session.teacher.SessionTeacher;
import nextstep.courses.domain.session.teacher.SessionTeacherRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import utils.LocalDateTimeUtils;

@Repository
public class JdbcSessionTeacherRepository implements SessionTeacherRepository {

  private final JdbcOperations jdbcTemplate;

  public JdbcSessionTeacherRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private static final RowMapper<SessionTeacher> rowMapper = (rs, rowNum) -> new SessionTeacher (
      rs.getLong("id"),
      rs.getLong("session_id"),
      rs.getLong("ns_user_id"),
      rs.getBoolean("is_active"),
      LocalDateTimeUtils.of(rs.getTimestamp("create_at")),
      LocalDateTimeUtils.of(rs.getTimestamp("update_at"))
  );

  @Override
  public Long saveTeacher (Long sessionId, Long nsUserId) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    final String sql = "INSERT INTO session_teacher (session_id, ns_user_id) VALUES (?, ?)";

    jdbcTemplate.update(connection -> {
      var pstmt = connection.prepareStatement(sql, new String[]{"id"});
      pstmt.setLong(1, sessionId);
      pstmt.setLong(2, nsUserId);
      return pstmt;
    }, keyHolder);

    return keyHolder.getKeyAs(Long.class);
  }

  @Override
  public List<SessionTeacher> getTeachers (Long sessionId) {
    final String sql = "SELECT id, session_id, ns_user_id, is_active, create_at, update_at FROM session_teacher WHERE session_id = ?";
    return jdbcTemplate.query(sql, rowMapper, sessionId);
  }
}
