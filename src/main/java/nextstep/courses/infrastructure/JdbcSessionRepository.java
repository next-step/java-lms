package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Session> findById(Long id) {
    String sql = "select id, title, img"
        + ", start_date, end_date"
        + ", session_status, session_type, max_recruitment"
        + ", creator_id, created_at, updated_at "
        + "from session "
        + "where id = ?";
    RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
        rs.getLong(1),
        rs.getString(2),
        rs.getString(3),
        toLocalDateTime(rs.getTimestamp(4)),
        toLocalDateTime(rs.getTimestamp(5)),
        SessionStatus.valueOf(rs.getString(6)),
        SessionType.valueOf(rs.getString(7)),
        rs.getInt(8),
        rs.getLong(9),
        toLocalDateTime(rs.getTimestamp(10)),
        toLocalDateTime(rs.getTimestamp(11)));
    return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
  }

  @Override
  public Long save(Session session) {
    if (session.getId() != null && findById(session.getId()).isPresent()) {
      String sql = "update session set title = ?, img = ?"
          + ", start_date = ?, end_date = ?"
          + ", session_status = ?, session_type = ?, max_recruitment = ?"
          + ", updated_at = ? where id = ?";
      jdbcTemplate
          .update(sql, session.getTitle(), session.getImg()
              , session.getStartDate(), session.getEndDate()
              , session.getSessionStatus().name(), session.getSessionType().name(), session.getMaxRecruitment()
              , LocalDateTime.now(), session.getId());
      return session.getId();
    }

    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "insert into session (title, img"
        + ", start_date, end_date"
        + ", session_status, session_type, max_recruitment"
        + ", creator_id, created_at, updated_at) "
        + "values(?, ?, ?, ?, ?, ? ,? ,? ,? ,?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
      ps.setString(1, session.getTitle());
      ps.setString(2, session.getImg());
      ps.setTimestamp(3, Timestamp.valueOf(session.getStartDate()));
      ps.setTimestamp(4, Timestamp.valueOf(session.getEndDate()));
      ps.setString(5, session.getSessionStatus().name());
      ps.setString(6, session.getSessionType().name());
      ps.setInt(7, session.getMaxRecruitment());
      ps.setLong(8, session.getCreatorId());
      ps.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
      ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
      return ps;
    }, keyHolder);

    return getId(keyHolder);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }

  private Long getId(KeyHolder keyHolder) {
    return keyHolder.getKey().longValue();
  }
}
