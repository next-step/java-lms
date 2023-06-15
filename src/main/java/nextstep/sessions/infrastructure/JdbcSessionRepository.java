package nextstep.sessions.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionBody;
import nextstep.sessions.domain.SessionDate;
import nextstep.sessions.domain.SessionRegistration;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.Students;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(Session session) {
    String sessionInsertSql = "insert into session (start_date_time, end_date_time, title, contents, cover_image, capacity, session_status_id) values (?, ?, ?, ?, ?, ?, ?)";

    return jdbcTemplate.update(sessionInsertSql, session.getStartDate(), session.getEndDate(),
        session.getTitle(), session.getContents(), session.getCoverImage(), session.getCapacity(),
        session.getStatus().getOrder());
  }

  @Override
  public Session findById(Long id) {
    SessionDao sessionDao = getSessionDao(id);
    SessionStatus status = SessionStatus.from(sessionDao.sessionStatusId);

    // Session이 가지는 Users를 찾아오는 쿼리를 작성한다
    Students students = hasSessionUser(id) ? getStudents(id) : new Students(new HashSet<>());

    return new Session(
        sessionDao.id,
        new SessionDate(sessionDao.startDateTime, sessionDao.endDateTime),
        new SessionBody(sessionDao.title, sessionDao.contents, sessionDao.coverImage),
        new SessionRegistration(sessionDao.capacity, status, students)
    );
  }

  @Override
  public void update(Session session) {
    String sql = "update session set start_date_time = ?, end_date_time = ?, title = ?, contents = ?, cover_image = ?, capacity = ?, session_status_id = ? where id = ?";
    jdbcTemplate.update(sql, session.getStartDate(), session.getEndDate(), session.getTitle(),
        session.getContents(), session.getCoverImage(), session.getCapacity(),
        session.getStatus().getOrder(), session.getId());

    updateSessionUser(session);
  }

  private void updateSessionUser(Session session) {
    String sql = "insert into session_ns_user (session_id, user_id, created_at) values (?, ?, ?)";
    LocalDateTime now = LocalDateTime.now();

    session.getStudents().getStudents()
        .stream().filter(student -> student.getSessionUserId().equals(0L))
        .forEach(student -> jdbcTemplate.update(sql, session.getId(), student.getNsUserId(), now));
  }

  private SessionDao getSessionDao(Long id) {
    String sql = "select id, start_date_time, end_date_time, title, contents, cover_image, capacity, session_status_id from session where id = ?";
    SessionDao sessionDao = jdbcTemplate.queryForObject(sql,
        (rs, rowNum) -> new SessionDao(rs.getLong(1),
            toLocalDateTime(rs.getTimestamp(2)), toLocalDateTime(rs.getTimestamp(3)),
            rs.getString(4), rs.getString(5), rs.getBytes(6),
            rs.getInt(7), rs.getLong(8)),
        id
    );

    return sessionDao;
  }

  private Students getStudents(Long sessionId) {
    String sessionUserSelectSql = "select id, session_id, user_id, created_at, updated_at from session_ns_user where session_id = ?";

    List<SessionUserDao> sessionUserEntities = jdbcTemplate.query(sessionUserSelectSql,
        (rs, rowNum) -> new SessionUserDao(rs.getLong(1), rs.getLong(2), rs.getLong(3),
            toLocalDateTime(rs.getTimestamp(4)), toLocalDateTime(rs.getTimestamp(5))),
        sessionId
    );

    Set<Student> students = sessionUserEntities.stream()
        .map(su -> new Student(su.id, su.sessionId, su.userId))
        .collect(Collectors.toSet());

    return new Students(students);
  }

  private boolean hasSessionUser(Long sessionId) {
    String sql = "select count(*) from session_ns_user where session_id = ?";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, sessionId);

    return !count.equals(0);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }

    return timestamp.toLocalDateTime();
  }

  class SessionUserDao {

    private Long id;
    private Long sessionId;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    SessionUserDao(Long id, Long sessionId, Long userId, LocalDateTime createdAt,
        LocalDateTime updatedAt) {
      this.id = id;
      this.sessionId = sessionId;
      this.userId = userId;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
    }
  }

  class SessionDao {

    private Long id;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String title;
    private String contents;
    private byte[] coverImage;
    private int capacity;
    private Long sessionStatusId;

    public SessionDao(Long id, LocalDateTime startDateTime, LocalDateTime endDateTime, String title,
        String contents, byte[] coverImage, int capacity, Long sessionStatusId) {
      this.id = id;
      this.startDateTime = startDateTime;
      this.endDateTime = endDateTime;
      this.title = title;
      this.contents = contents;
      this.coverImage = coverImage;
      this.capacity = capacity;
      this.sessionStatusId = sessionStatusId;
    }
  }
}
