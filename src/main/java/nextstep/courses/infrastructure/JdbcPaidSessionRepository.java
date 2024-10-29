package nextstep.courses.infrastructure;

import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.PaidSessionRepository;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcPaidSessionRepository extends JdbcSessionRepository implements PaidSessionRepository {

    public JdbcPaidSessionRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public int save(PaidSession paidSession) {
        saveSession(paidSession);
        String sql = "insert into paid_session (session_id, max_register_count, amount) values(?, ?, ?)";
        return jdbcTemplate.update(sql, paidSession.toPaidParameters());
    }

    @Override
    public PaidSession findById(long sessionId) {
        String sql = "select s.id, s.creator_id, s.course_id, s.start_at, s.end_at, s.cover_image_file_size, s.cover_image_type, s.cover_image_width, s.cover_image_height, s.status, ps.max_register_count, ps.amount, s.created_at, s.updated_at " +
                "from session s " +
                "inner join paid_session ps " +
                "on s.id = ps.session_id " +
                "where s.id = ? ";
        RowMapper<PaidSession> rowMapper = (rs, rowNum) -> new PaidSession(
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
                rs.getInt("max_register_count"),
                rs.getInt("amount"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }
}
