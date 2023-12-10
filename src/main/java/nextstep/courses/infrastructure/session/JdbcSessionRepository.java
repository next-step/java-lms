package nextstep.courses.infrastructure.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionFee;
import nextstep.courses.domain.session.SessionFeeType;
import nextstep.courses.domain.session.SessionPeriod;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionState;
import nextstep.courses.infrastructure.image.JdbcImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Optional;

import static nextstep.common.utils.DateTimeUtils.toLocalDateTime;
import static nextstep.common.utils.DateTimeUtils.toTimeStamp;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final ImageRepository imageRepository;
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Override
    public long save(Long courseId, Session session) {
        String sql = "insert into session (title, start_at, end_at, state, course_id, image_id, fee_type, " +
                     " session_fee, available_count, created_at) " +
                     " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        SessionPeriod sessionPeriod = session.getSessionPeriod();
        EnrollmentCount enrollmentCount =
                (session.getSessionFeeType() == SessionFeeType.PAID) ? session.getEnrollmentCount()
                        : new EnrollmentCount(0);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, session.getTitle());
            ps.setTimestamp(2, toTimeStamp(sessionPeriod.getStartAt()));
            ps.setTimestamp(3, toTimeStamp(sessionPeriod.getEndAt()));
            ps.setString(4, session.getSessionState().name());
            ps.setLong(5, courseId);
            ps.setLong(6, session.getCoverImageId());
            ps.setString(7, session.getSessionFeeType().name());
            ps.setLong(8, session.getSessionFee().getFee());
            ps.setInt(9, enrollmentCount.getAvailableCount());
            ps.setTimestamp(10, toTimeStamp(LocalDateTime.now()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : -1;
    }

    @Override
    public Optional<Session> findById(final Long id) {
        String sql = "select id, title, start_at, end_at, state, course_id, image_id, fee_type," +
                     " session_fee, available_count, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(3)), toLocalDateTime(rs.getTimestamp(4))),
                SessionState.valueOf(rs.getString(5)),
                findByCoverImage(rs.getLong(7)),
                SessionFeeType.valueOf(rs.getString(8)),
                SessionFee.of(rs.getLong(9)),
                new EnrollmentCount(rs.getInt(10)),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private CoverImage findByCoverImage(final Long id) {
        return imageRepository.findById(id).get();
    }
}
