package nextstep.sessions.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nextstep.image.domain.Dimension;
import nextstep.sessions.domain.EnrollmentManager;
import nextstep.policy.domain.FreePolicy;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageMeta;
import nextstep.image.domain.ImageType;
import nextstep.policy.domain.PaidPolicy;
import nextstep.sessions.domain.Period;
import nextstep.policy.domain.Policy;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionInformation;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcSessionRepository implements SessionRepository {
  private final JdbcTemplate jdbcTemplate;

  public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Session save(Session session, Long courseId) {
    String sql = "INSERT INTO session (course_id, title, start_date, end_date, image_name, image_type, image_size, image_width, image_height, policy_type, max_size, fee, status, created_at) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    SessionInformation info = session.info();
    ImageMeta meta = info.image().meta();
    Dimension dim = info.image().dimension();
    Policy policy = session.enrollment().policy();

    jdbcTemplate.update(sql,
        courseId,
        info.title(),
        info.period().startDate(),
        info.period().endDate(),
        meta.fileName(),
        meta.contentType().name(),
        meta.size(),
        dim.width(),
        dim.height(),
        policy instanceof PaidPolicy ? "PAID" : "FREE",
        policy instanceof PaidPolicy ? ((PaidPolicy) policy).max() : null,
        policy instanceof PaidPolicy ? ((PaidPolicy) policy).fee() : null,
        session.enrollment().status().name(),
        LocalDateTime.now()
    );

    return session;
  }

  @Override
  public Optional<Session> findById(Long id) {
    String sql = "SELECT * FROM session WHERE id = ?";
    return jdbcTemplate.query(sql, sessionRowMapper(), id).stream().findFirst();
  }

  @Override
  public List<Session> findAllByCourseId(Long courseId) {
    String sql = "SELECT * FROM session WHERE course_id = ?";
    return jdbcTemplate.query(sql, sessionRowMapper(), courseId);
  }

  @Override
  public void deleteById(Long id) {
    jdbcTemplate.update("DELETE FROM session WHERE id = ?", id);
  }

  private RowMapper<Session> sessionRowMapper() {
    return (rs, rowNum) -> {
      SessionInformation info = new SessionInformation(
          rs.getString("title"),
          new Period(
              rs.getDate("start_date").toLocalDate(),
              rs.getDate("end_date").toLocalDate()
          ),
          new Image(
              new ImageMeta(
                  rs.getString("image_name"),
                  ImageType.valueOf(rs.getString("image_type")),
                  rs.getLong("image_size")
              ),
              new Dimension(
                  rs.getInt("image_width"),
                  rs.getInt("image_height")
              )
          )
      );

      Policy policy = rs.getString("policy_type").equals("PAID")
          ? new PaidPolicy(rs.getInt("max_size"), rs.getLong("fee"))
          : new FreePolicy();

      EnrollmentManager manager = new EnrollmentManager(policy);
      manager.updateStatus(SessionStatus.valueOf(rs.getString("status")));

      return new Session(info, manager);
    };
  }
}
