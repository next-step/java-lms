package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NextStepUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Session save(Session session, Long courseId) {
    String sql = "insert into session (session_payment, session_status, session_cover_url, max_enrollment, start_date, end_date, created_at, updated_at, course_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(connection -> {
      PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
      preparedStatement.setString(1, session.getSessionPayment().getPaymentType());
      preparedStatement.setString(2, session.getSessionStatus().status());
      preparedStatement.setString(3, session.getSessionCoverUrl().getSessionCoverUrl());
      preparedStatement.setInt(4, session.getSessionUsers().getMaxUserEnrollment());
      preparedStatement.setTimestamp(5, Timestamp.valueOf(session.getSessionPeriod().getStartDate()));
      preparedStatement.setTimestamp(6, Timestamp.valueOf(session.getSessionPeriod().getEndDate()));
      preparedStatement.setTimestamp(7, Timestamp.valueOf(session.getCreatedAt()));
      preparedStatement.setTimestamp(8, Timestamp.valueOf(session.getUpdatedAt()));
      preparedStatement.setLong(9, courseId);
      return preparedStatement;
    }, keyHolder);
    long key = keyHolder.getKey().longValue();
    session.setId(key);
    return session;
  }

  @Override
  public Session findById(Long sessionId) {
    String sql = "select id, session_payment, session_status, max_enrollment, start_date, end_date, session_cover_url, created_at, updated_at from session where id = ?";

    return jdbcTemplate.queryForObject(sql, sessionRowMapper(), sessionId);
  }

  @Override
  public List<Session> findByCourseId(Long courseId) {
    String sql = "select id, session_payment, session_status, max_enrollment, start_date, end_date, session_cover_url, created_at, updated_at from session where course_id = ?";

    return jdbcTemplate.query(sql, sessionRowMapper(), courseId);
  }

  @Override
  public void saveSessionUser(Session session, NextStepUser nextStepUser) {
    String sql = "insert into session_ns_user (session_id, user_id, created_at, updated_at) values (?, ?, ?, ?)";

    jdbcTemplate.update(sql, session.getId(), nextStepUser.getId(), session.getCreatedAt(), session.getUpdatedAt());
  }

  @Override
  public List<SessionUser> findAllSessionUserBySessionId(Long sessionId) {
    String sql = "select id, session_id, user_id, created_at, updated_at from session_ns_user where session_id = ?";

    RowMapper<SessionUser> sessionUserRowMapper = (rs, rowNum) ->
            new SessionUser(
                    rs.getLong(1),
                    findById(rs.getLong(2)),
                    new NextStepUser(),
                    toLocalDateTime(rs.getTimestamp(4)),
                    toLocalDateTime(rs.getTimestamp(5)));

    return jdbcTemplate.query(sql, sessionUserRowMapper, sessionId);
  }


  private RowMapper<Session> sessionRowMapper() {
    return ((rs, rowNum) -> new Session(
            rs.getLong(1),
            SessionPayment.valueOfPaymentType(rs.getString(2)),
            SessionStatus.valueOfSessionStatus(rs.getString(3)),
            rs.getInt(4),
            toLocalDateTime(rs.getTimestamp(5)),
            toLocalDateTime(rs.getTimestamp(6)),
            rs.getString(7),
            toLocalDateTime(rs.getTimestamp(8)),
            toLocalDateTime(rs.getTimestamp(9))
    ));
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
