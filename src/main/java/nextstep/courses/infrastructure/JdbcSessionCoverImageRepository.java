package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalTime.now;

@Repository("sessionCoverImageRepository")
public class JdbcSessionCoverImageRepository implements SessionCoverImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int[] saveAll(List<SessionCoverImage> coverImages) {
        String sql = "insert into session_cover_image (session_id, size, width, height, extension, created_at) " +
                "values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.batchUpdate(sql, batchArgs(coverImages));
    }

    private List<Object[]> batchArgs(List<SessionCoverImage> coverImages) {
        List<Object[]> batchArgs = new ArrayList<>();

        for (SessionCoverImage image : coverImages) {
            Object[] args = new Object[]{
                    image.getSessionId(),
                    image.getFileSize().get(),
                    image.getSize().getWidth(), image.getSize().getHeight(),
                    image.getExtension().get(), now()
            };
            batchArgs.add(args);
        }
        return Collections.unmodifiableList(batchArgs);
    }

    @Override
    public List<SessionCoverImage> findAllBySessionId(Long sessionId) {
        String sql = "select id, session_id, size, width, height, extension, created_at, updated_at " +
                "from session_cover_image " +
                "where session_id = ?";

        return jdbcTemplate.query(sql, coverImageRowMapper(), sessionId);
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
