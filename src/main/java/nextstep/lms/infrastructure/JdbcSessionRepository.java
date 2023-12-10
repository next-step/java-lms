package nextstep.lms.infrastructure;

import nextstep.lms.domain.*;
import nextstep.lms.repository.CoverImageRepository;
import nextstep.lms.repository.SessionRepository;
import nextstep.lms.repository.StudentsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final CoverImageRepository coverImageRepository;
    private final StudentsRepository studentsRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        this.studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (pricing_type, tuition_fee, session_progress, session_recruitment, capacity, start_date, end_date, created_at) values(?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,
                session.getPricingType(),
                session.getTuitionFee(),
                session.getSessionProgressEnum(),
                session.getSessionRecruitment(),
                session.getCapacity(),
                session.getStartDate(),
                session.getEndDate(),
                LocalDateTime.now()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, pricing_type, tuition_fee, session_progress, session_recruitment, capacity, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                getCoverImages(id),
                rs.getString(2),
                rs.getLong(3),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                getStudents(id),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private List<CoverImage> getCoverImages(Long sessionId) {
        return coverImageRepository.findBySessionId(sessionId);
    }

    private Students getStudents(Long SessionId) {
        return studentsRepository.findBySession(SessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
