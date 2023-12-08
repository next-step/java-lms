package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
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
        String sql = "insert into session (id, course_id, image_id, type, status, start_date, end_date, max_students, fee, created_at) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.id(), session.courseId(), session.imageId(), session.type(), session.status(), session.startDate(), session.endDate(), session.maxStudents(), session.fee(), session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, type, image_id, status, start_date, end_date, max_students, fee, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                rs.getLong(2),
                SessionType.findByCode(rs.getString(3)),
                coverImage(rs.getLong(4)),
                Status.findByName(rs.getString(5)),
                toLocalDate(rs.getDate(6)),
                toLocalDate(rs.getDate(7)),
                rs.getInt(8),
                rs.getLong(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11)));

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
