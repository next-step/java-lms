package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionProgressStatus;
import nextstep.courses.domain.session.SessionRecruitmentStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.dto.SessionDto;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<SessionDto> findById(Long id) {
        String sql = "SELECT id, course_id, title, session_type, progress_status, recruitment_status, " +
                "start_date, end_date, maximum_enrollment, created_at, updated_at " +
                "FROM session WHERE id = ?";
        RowMapper<SessionDto> rowMapper = (rs, rowNum) -> SessionDto.builder()
                .id(rs.getLong("id"))
                .courseId(rs.getLong("course_id"))
                .title(rs.getString("title"))
                .sessionType(SessionType.valueOf(rs.getString("session_type")))
                .progressStatus(SessionProgressStatus.valueOf(rs.getString("progress_status")))
                .recruitmentStatus(SessionRecruitmentStatus.valueOf(rs.getString("recruitment_status")))
                .startDate(rs.getDate("start_date").toLocalDate())
                .endDate(rs.getDate("end_date").toLocalDate())
                .maximumEnrollment(rs.getInt("maximum_enrollment"))
                .createdAt(toLocalDateTime(rs.getTimestamp("created_at")))
                .updatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
                .build();
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}