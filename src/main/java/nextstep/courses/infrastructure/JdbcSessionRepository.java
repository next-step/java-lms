package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
  private JdbcOperations jdbcTemplate;
  private SessionImageRepository sessionImageRepository;

  public JdbcSessionRepository(JdbcOperations jdbcTemplate, SessionImageRepository sessionImageRepository) {
    this.jdbcTemplate = jdbcTemplate;
    this.sessionImageRepository = sessionImageRepository;
  }

  @Override
  public int save(Session session) {
    Long imageId = sessionImageRepository.saveAndGetGeneratedKey(session.image());

    String sql = "INSERT INTO session (course_id, start_date, end_date, image_id, status, session_type, max_size, tuition) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

    if (session instanceof FreeSession) {
      return jdbcTemplate.update(sql, session.getCourseId(), session.getStartDate(), session.getEndDate(), imageId, session.getStatus().toString(),
              session.getType(), null, null);
    } else if (session instanceof ChargedSession) {
      ChargedSession chargedSession = (ChargedSession) session;
      return jdbcTemplate.update(sql, session.getCourseId(), session.getStartDate(), session.getEndDate(), imageId, session.getStatus().toString(),
              session.getType(), chargedSession.getMaxSize(), chargedSession.getTuition());
    }

    throw new IllegalArgumentException("존재하지 않는 Session 타입입니다.");
  }

  @Override
  public Session findById(Long id) {
    String sql = "SELECT * FROM session WHERE id = ?";

    RowMapper<Session> rowMapper = (rs, rowNum) -> {
      if ("FREE".equals(rs.getString("session_type"))) {
        return new FreeSession(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDate(rs.getTimestamp("start_date")),
                toLocalDate(rs.getTimestamp("end_date")),
                this.sessionImageRepository.findById(rs.getLong("image_id")),
                SessionStatus.valueOf(rs.getString("status"))
        );
      } else if ("CHARGED".equals(rs.getString("session_type"))) {
        return new ChargedSession(
                rs.getLong("id"),
                rs.getLong("course_id"),
                toLocalDate(rs.getTimestamp("start_date")),
                toLocalDate(rs.getTimestamp("end_date")),
                this.sessionImageRepository.findById(rs.getLong("image_id")),
                SessionStatus.valueOf(rs.getString("status")),
                rs.getInt("max_size"),
                rs.getLong("tuition")
        );
      }
      throw new IllegalArgumentException("존재하지 않는 Session 타입입니다.");
    };

    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }

  private LocalDate toLocalDate(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime().toLocalDate();
  }
}
