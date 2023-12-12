package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.coverimage.CoverImage;
import nextstep.courses.domain.session.coverimage.CoverImages;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.exception.ImageFileInfoException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CoverImages findAllBySession(Long sessionId) {
        String sql = "select * from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = coverImageRowMapper();

        List<CoverImage> coverImages = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new CoverImages(coverImages);
    }

    private RowMapper<CoverImage> coverImageRowMapper() {
        return (rs, rowNum) -> {
            try {
                 return new CoverImage(
                    rs.getLong(1),
                    rs.getString(3),
                    rs.getInt(4),
                    rs.getInt(5),
                    rs.getLong(6)
                );
            } catch (ImageFileInfoException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
