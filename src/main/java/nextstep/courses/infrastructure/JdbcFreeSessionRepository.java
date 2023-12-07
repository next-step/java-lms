package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("freeSessionRepository")
public class JdbcFreeSessionRepository implements FreeSessionRepository {
    private JdbcOperations jdbcTemplate;
    private final ImageRepository imageRepository;

    public JdbcFreeSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Override
    public int save(Long courseId, FreeSession freeSession) {
        SessionPeriod sessionPeriod = freeSession.sessionPeriod();
        CoverImage coverImage = freeSession.coverImage();
        SessionStatus sessionStatus = freeSession.sessionStatus();

        String sql = "insert into session (title, start_data_time, end_date_time, status, course_id, image_id, created_at) " +
                     " values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, freeSession.title(), sessionPeriod.startDateTime(), sessionPeriod.endDateTime(),
                sessionStatus.name(), courseId, coverImage.id(),freeSession.createdAt());
    }

    @Override
    public Optional<FreeSession> findById(final Long id) {
        String sql = "select id, title, start_data_time, end_date_time, status, course_id, image_id, created_at, updated_at " +
                     " from session where id = ?";
        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
                rs.getLong(1),
                rs.getString(2),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(3)), toLocalDateTime(rs.getTimestamp(4))),
                SessionStatus.valueOf(rs.getString(5)),
                findByCoverImage(rs.getLong(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return  Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private CoverImage findByCoverImage(final Long id) {
        return imageRepository.findById(id).get();
    }
}
