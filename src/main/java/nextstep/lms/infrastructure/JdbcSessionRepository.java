package nextstep.lms.infrastructure;

import nextstep.lms.domain.*;
import nextstep.lms.repository.CoverImageRepository;
import nextstep.lms.repository.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    private CoverImageRepository coverImageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (image_id, pricing_type, tuition_fee, session_status, capacity, start_date, end_date, created_at) values(?,?,?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,
                session.getImageId(),
                session.getPricingType(),
                session.getTuitionFee(),
                session.getSessionStatus(),
                session.getCapacity(),
                session.getStartDate(),
                session.getEndDate(),
                LocalDateTime.now()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, image_id, pricing_type, tuition_fee, session_status, capacity, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                getCoverImage(rs.getLong(2)),
                rs.getString(3),
                rs.getLong(4),
                rs.getString(5),
                rs.getInt(6),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private CoverImage getCoverImage(Long id) {
        return coverImageRepository.findById(id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
