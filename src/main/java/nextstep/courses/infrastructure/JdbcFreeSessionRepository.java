package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionType;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.FreeSessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("freeSessionRepository")
public class JdbcFreeSessionRepository implements FreeSessionRepository {

    private final JdbcOperations jdbcTemplate;

    private final CoverImageRepository coverImageRepository;

    public JdbcFreeSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Session session, Long courseId) {
        String sql = "insert into session (id, course_id, image_id, type, status, start_date, end_date, max_students, fee, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.id(), courseId, session.ImageId(), session.type(), session.status(), session.startDate(), session.endDate(), 0, 0, session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, type, image_id, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new FreeSession(
                rs.getLong(1),
                SessionType.findByCode(rs.getString(2)),
                coverImage(rs.getLong(3)),
                toLocalDate(rs.getDate(4)),
                toLocalDate(rs.getDate(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
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

    private CoverImage coverImage(Long id) {
        return coverImageRepository.findById(id);
    }
}
