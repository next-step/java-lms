package nextstep.courses.infrastructure;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.metadata.Period;
import nextstep.courses.domain.session.metadata.coverImage.CoverImage;
import nextstep.courses.domain.session.metadata.coverImage.CoverImageRepository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session(id, cover_image_id, start_at, end_at, status, price, max_capacity, enrolled_count) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            session.getId(),
            session.getCoverImage() != null ? session.getCoverImage().getId() : null,
            session.startAt(),
            session.endAt(),
            session.getStatus().toString(),
            session.price(),
            session.getMaxCapacity(),
            0
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, cover_image_id, start_at, end_at, status, price, max_capacity, enrolled_count from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rownum) -> {
            Long sessionId = rs.getLong(1);
            Long coverImageId = rs.getObject(2, Long.class);
            CoverImage coverImage = coverImageId != null
                ? getCoverImage(coverImageId)
                : null;
            Period period = new Period(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4)));
            SessionStatus status = SessionStatus.valueOf(rs.getString(5));
            Amount price = Amount.of(rs.getObject(6, BigInteger.class));
            Long maxCapacity = rs.getObject(7, Long.class);
            int enrolledCount = rs.getInt(8);
            return Session.restoreSession(sessionId, status, period, coverImage, price, maxCapacity, enrolledCount);
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private CoverImage getCoverImage(Long coverImageId) {
        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        return coverImageRepository.findById(coverImageId);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
