package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionType;
import nextstep.courses.domain.Thumbnail;
import nextstep.courses.domain.ThumbnailRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;
    private final ThumbnailRepository thumbnailRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, ThumbnailRepository thumbnailRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.thumbnailRepository = thumbnailRepository;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, name, start_date, end_date, thumbnail_id, session_status"
                + " free.session_type_id, paid.session_type_id, max_students, session_fee"
                + " from session "
                + " left join session_type_free free on session.session_type_id = free.session_type_id"
                + " left join session_type_paid paid on session.session_type_id = paid.session_type_id"
                + "where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(toLocalDate(rs.getTimestamp(3)),
                        toLocalDate(rs.getTimestamp(4))),
                thumbnailRepository.findById(rs.getInt(5)),
                SessionType.determineSessionTypeByDB(
                        rs.getLong(7),
                        rs.getLong(8),
                        rs.getInt(9),
                        rs.getInt(10)
                ),
                SessionStatus.valueOf(rs.getString(6))
                );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
