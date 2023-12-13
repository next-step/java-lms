package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImages;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.Status;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    private final CoverImageRepository coverImageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session2 (id, course_id, type, status, start_date, end_date, max_students, fee, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.id(), session.courseId(), session.type(), session.status(), session.startDate(), session.endDate(), session.maxStudents(), session.fee(), session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, type, status, start_date, end_date, max_students, fee, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                rs.getLong(2),
                SessionType.findByCode(rs.getString(3)),
                coverImages(id),
                Status.findByName(rs.getString(4)),
                toLocalDate(rs.getDate(5)),
                toLocalDate(rs.getDate(6)),
                rs.getInt(7),
                rs.getLong(8),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));

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

    private CoverImages coverImages(Long id) {
        return new CoverImages(coverImageRepository.findAllBySessionId(id));
    }
}
