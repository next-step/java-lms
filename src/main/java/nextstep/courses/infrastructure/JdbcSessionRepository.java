package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.image.CoverImage;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static nextstep.courses.domain.session.FreeSession.FREE_FEE;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;
    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session " +
                "(session_id, fee, max_enrollment," +
                "start_date, end_date, image_type," +
                "image_file_size, image_width, image_height," +
                "session_status)" +
                "values(?,?,?," +
                "?,?,?," +
                "?,?,?," +
                "?)";

        return jdbcTemplate.update(
                sql,
                session.getSessionId(),
                session.getFee(),
                session.getMaxEnrollments(),
                session.getSessionPeriod().getStartDate(),
                session.getSessionPeriod().getEndDate(),
                session.getCoverImage().getImageType(),
                session.getCoverImage().getImageFileSize(),
                session.getCoverImage().getImageSizeWidth(),
                session.getCoverImage().getImageSizeHeight(),
                session.getSessionStatus().toString()
        );
    }

    @Override
    public Session findBySessionId(Long id) {
        String sql = "select session_id, fee, max_enrollment, " +
                "start_date, end_date, image_type, image_file_size, " +
                "image_width, image_height, session_status " +
                "from session where session_id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            Long fee = rs.getLong("fee");
            SessionPeriod sessionPeriod = SessionPeriod.of(toLocalDateTime(rs.getTimestamp("start_date")), toLocalDateTime(rs.getTimestamp("end_date")));
            CoverImage coverImage = CoverImage.of(rs.getString("image_type"), rs.getInt("image_file_size"),
                    rs.getInt("image_width"), rs.getInt("image_height"));
            SessionStatusEnum sessionStatus = SessionStatusEnum.valueOf(rs.getString("session_status"));

            if (fee.equals(FREE_FEE)) {
                return new FreeSession(
                        rs.getLong("session_id"),
                        sessionPeriod,
                        coverImage,
                        sessionStatus
                );
            }

            return new PaidSession(
                    rs.getLong("session_id"),
                    sessionPeriod,
                    coverImage,
                    sessionStatus,
                    rs.getInt("max_enrollment"),
                    fee
            );
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
