package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.domain.SessionProgressState;
import nextstep.courses.repository.CoverImageRepository;
import nextstep.courses.repository.FreeSessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository("freeSessionRepository")
public class JdbcFreeSessionRepository implements FreeSessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final CoverImageRepository coverImageRepository;

    public JdbcFreeSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Course course, FreeSession session) {
        LocalDate startDate = session.getProgressPeriod().getStartDate();
        LocalDate endDate = session.getProgressPeriod().getEndDate();
        String sql = "insert into session (course_id, start_date, end_date, progress_state, recruit_state, type, fee, max_apply, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, course.id(), startDate, endDate, session.progressState(), session.recruitState(), "FREE", 0, 0, session.createdAt());
    }

    @Override
    public FreeSession findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, progress_state, recruit_state, type, created_at, updated_at from session where id = ? and type = 'FREE'";
        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
                rs.getLong(1),
                findCoverImages(rs.getLong(1)),
                toLocalDate(rs.getDate(3)),
                toLocalDate(rs.getDate(4)),
                SessionProgressState.valueOf(rs.getString(5)),
                rs.getBoolean(6),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<FreeSession> findByCourseId(Long courseId) {
        String sql = "select id, course_id, start_date, end_date, progress_state, recruit_state, type, created_at, updated_at from session where course_id = ? and type = 'FREE'";
        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
                rs.getLong(1),
                findCoverImages(rs.getLong(1)),
                toLocalDate(rs.getDate(3)),
                toLocalDate(rs.getDate(4)),
                SessionProgressState.valueOf(rs.getString(5)),
                rs.getBoolean(6),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9))
        );
        return jdbcTemplate.query(sql, rowMapper, courseId);
    }

    private List<CoverImage> findCoverImages(Long sessionId) {
        return coverImageRepository.findBySessionId(sessionId);
    }

    private LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }


}
