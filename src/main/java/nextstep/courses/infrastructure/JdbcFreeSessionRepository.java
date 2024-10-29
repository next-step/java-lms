package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.FreeSessionRepository;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcFreeSessionRepository extends JdbcSessionRepository implements FreeSessionRepository {
    public JdbcFreeSessionRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public int save(FreeSession freeSession) {
        saveSession(freeSession);
        String sql = "insert into free_session (session_id) values(?)";
        return jdbcTemplate.update(sql, freeSession.toFreeParameters());
    }

    @Override
    public FreeSession findById(long sessionId) {
        String sql = "select s.id, s.creator_id, s.course_id, s.start_at, s.end_at, s.cover_image_file_size, s.cover_image_type, s.cover_image_width, s.cover_image_height, s.status, s.created_at, s.updated_at " +
                "from session s " +
                "inner join free_session fs " +
                "on s.id = fs.session_id " +
                "where s.id = ? ";
        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
                rs.getLong("id"),
                rs.getLong("creator_id"),
                rs.getLong("course_id"),
                new DateRange(
                        toLocalDateTime(rs.getTimestamp("start_at")),
                        toLocalDateTime(rs.getTimestamp("end_at"))
                ),
                new CoverImage(
                        rs.getInt("cover_image_file_size"),
                        rs.getString("cover_image_type"),
                        rs.getInt("cover_image_width"),
                        rs.getInt("cover_image_height")
                ),
                Status.valueOf(rs.getString("status")),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }
}
