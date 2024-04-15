package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static java.time.LocalTime.now;

@Repository("sessionCoverImageRepository")
public class JdbcSessionCoverImageRepository implements SessionCoverImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionCoverImage coverImage) {
        String sql = "insert into session_cover_image (session_id, size, width, height, extension, created_at) " +
                "values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, coverImage.getSessionId(),
                coverImage.getFileSize().get(),
                coverImage.getSize().getWidth(),
                coverImage.getSize().getHeight(),
                coverImage.getExtension().get(),
                now());
    }

    @Override
    public SessionCoverImage findById(Long id) {
        String sql = "select id, session_id, size, width, height, extension, created_at, updated_at " +
                "from session_cover_image " +
                "where id = ?";
        return jdbcTemplate.queryForObject(sql, coverImageRowMapper(), id);
    }

    private RowMapper<SessionCoverImage> coverImageRowMapper() {
        return (rs, rowNum) -> new SessionCoverImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getString(6),
                LocalDateTimeConverter.convert(rs.getTimestamp(7)),
                LocalDateTimeConverter.convert(rs.getTimestamp(8))
        );

    }
}
