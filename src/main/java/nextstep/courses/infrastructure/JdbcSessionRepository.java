package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (fee, count, status, course_id, start_date, end_date, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getEnrollmentManager().getFee(),
                session.getEnrollmentManager().getCount(),
                session.getEnrollmentManager().getStatus(),
                session.getCourseId(),
                session.getSessionPeriod().getStartDate(),
                session.getSessionPeriod().getEndDate(),
                session.getCreatedAt(),
                session.getUpdatedAt()
        );
    }

    @Override
    public Optional<Session> findById(Long sessionId) {
        String sql = "select s.*, ci.* " +
                "from session as s " +
                "left join cover_image as ci on s.id = ci.session_id where s.id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            CoverImage coverImage = new CoverImage(
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("cover_image.created_at")),
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("cover_image.updated_at")),
                    rs.getLong("cover_image.capacity"),
                    rs.getString("cover_image.type"),
                    rs.getLong("cover_image.width"),
                    rs.getLong("cover_image.height"),
                    rs.getLong("cover_image.session_id"),
                    rs.getLong("cover_image.id")
            );
            return new Session(
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("created_at")),
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("updated_at")),
                    coverImage,
                    rs.getLong("fee"),
                    rs.getInt("count"),
                    rs.getString("status"),
                    LocalDateMappingUtil.toLocalDate(rs.getTimestamp("start_date")),
                    LocalDateMappingUtil.toLocalDate(rs.getTimestamp("end_date")),
                    rs.getLong("course_id"),
                    rs.getLong("id")
            );
        };
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }
}
