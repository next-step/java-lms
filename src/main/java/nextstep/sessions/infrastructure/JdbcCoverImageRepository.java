package nextstep.sessions.infrastructure;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.sessions.domain.data.coverimage.CoverImage;
import nextstep.sessions.domain.data.coverimage.ImageType;
import nextstep.sessions.domain.data.coverimage.ImageSize;
import nextstep.sessions.repository.CoverImageRepository;

@Repository("sessionRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CoverImage> findAllBySessionId(int sessionId) {
        String sql =
            "    select " +
                "  session_id, " +
                "  type, " +
                "  file_size, " +
                "  width, " +
                "  height " +
                "from cover_image " +
                "where session_id = ? ";

        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
            ImageType.valueOf(rs.getString(2)),
            new ImageSize(rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5))
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int saveAll(int sessionId, List<CoverImage> coverImages) {
        int count = 0;

        for (CoverImage coverImage: coverImages) {
            String sql =
                "    insert into cover_image (" +
                    "  session_id, " +
                    "  type, " +
                    "  file_size, " +
                    "  width, " +
                    "  height " +
                    ") values( " +
                    "  ?, " +
                    "  ?, " +
                    "  ?, " +
                    "  ?, " +
                    "  ?" +
                    ") ";

            count += jdbcTemplate.update(sql,
                sessionId,
                coverImage.imageTypeName(),
                coverImage.fileSize(),
                coverImage.width(),
                coverImage.height()
            );
        }

        return count;
    }
}
