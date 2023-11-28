package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.courses.repository.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("FreeSessionRepository")
public class JdbcFreeSessionRepository implements FreeSessionRepository {

    private static final String DEFAULT_FREE_SESSION_TYPE = "free";

    private JdbcOperations jdbcTemplate;
    private ImageRepository imageRepository;

    public JdbcFreeSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);

    }

    @Override
    public int save(FreeSession session, Course course) {
        String sql = "insert into session (course_id, type, start_date, end_date, image_id, status, max_student, price, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            course.id(),
            DEFAULT_FREE_SESSION_TYPE,
            session.duration().start(),
            session.duration().end(),
            session.image().id(),
            session.status().name(),
            null,
            null,
            session.createdAt());
    }

    @Override
    public FreeSession findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, image_id, status, created_at, updated_at " +
            "from session where id = ? and type = ?";
        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
            rs.getLong(1),
            new Duration(toLocalDate(rs.getDate(3)), toLocalDate(rs.getDate(4))),
            image(rs.getLong(5)),
            SessionStatus.valueOf(rs.getString(6)),
            toLocalDateTime(rs.getTimestamp(7)),
            toLocalDateTime(rs.getTimestamp(8)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id, DEFAULT_FREE_SESSION_TYPE);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "select count(*) > 0 from session where id = ? and type = ?";
        return jdbcTemplate.queryForObject(sql, Boolean.class, id, DEFAULT_FREE_SESSION_TYPE);
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
