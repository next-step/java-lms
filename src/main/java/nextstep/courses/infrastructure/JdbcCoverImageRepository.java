package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.session.cover.CoverImage;
import nextstep.courses.domain.session.cover.CoverImages;
import nextstep.courses.repository.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcCoverImageRepository implements CoverImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage, Long sessionId) {
        String sql = "INSERT INTO session_cover_image (session_id, cover_image_size, cover_image_name, cover_image_width, cover_image_height, created_at )" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, coverImage.getImageSize(), coverImage.getImageName()
                , coverImage.getCoverImageWidth(), coverImage.getCoverImageHeight(), LocalDateTime.now());
    }

    @Override
    public CoverImages findBySessionId(Long sessionId) {
        String sql = "SELECT session_id, cover_image_size, cover_image_name, cover_image_width, cover_image_height " +
                "FROM session_cover_image WHERE session_id = ?";

        List<CoverImage> images = jdbcTemplate.query(sql, (rs, rowNum) ->
                new CoverImage(
                        rs.getInt("cover_image_size"),
                        rs.getString("cover_image_name"),
                        rs.getInt("cover_image_width"),
                        rs.getInt("cover_image_height")
                ), sessionId
        );

        return new CoverImages(images);
    }
}
