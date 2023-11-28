package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.repository.ChargedSessionRepository;
import nextstep.courses.repository.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("ChargedSessionRepository")
public class JdbcChargedSessionRepository implements ChargedSessionRepository {

    private static final String DEFAULT_CHARGED_SESSION_TYPE = "charged";

    private JdbcOperations jdbcTemplate;
    private ImageRepository imageRepository;

    public JdbcChargedSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);

    }

    @Override
    public int save(Long id, ChargedSession session, Course course) {
        String sql = "insert into session (id, course_id, type, start_date, end_date, image_id, status, max_student, price, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            id,
            course.id(),
            DEFAULT_CHARGED_SESSION_TYPE,
            session.duration().start(),
            session.duration().end(),
            session.image().id(),
            session.status().name(),
            session.maxNumberOfStudent(),
            session.price().price(),
            session.createdAt());
    }

    @Override
    public ChargedSession findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, image_id, status, max_student, price, created_at, updated_at " +
            "from session where id = ? and type = ?";
        RowMapper<ChargedSession> rowMapper = (rs, rowNum) -> new ChargedSession(
                rs.getLong(1),
                new Duration(toLocalDate(rs.getDate(3)), toLocalDate(rs.getDate(4))),
                image(rs.getLong(5)),
                SessionStatus.valueOf(rs.getString(6)),
                rs.getInt(7),
                rs.getBigDecimal(8),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id, DEFAULT_CHARGED_SESSION_TYPE);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "select count(*) > 0 from session where id = ? and type = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id, DEFAULT_CHARGED_SESSION_TYPE);
    }

    private Image image(Long id) {
        return imageRepository.findById(id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }
}
