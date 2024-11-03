package nextstep.courses.tobe.infrastructure;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.tobe.domain.TobeCoverImage;
import nextstep.courses.tobe.domain.TobeCoverImagesRepository;
import nextstep.courses.tobe.domain.session.TobeCoverImages;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static nextstep.courses.infrastructure.util.LocalDateTimeFormatter.toLocalDateTime;

public class TobeJdbcCoverImagesRepository implements TobeCoverImagesRepository {
    private final JdbcTemplate jdbcTemplate;

    public TobeJdbcCoverImagesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAll(TobeCoverImages images) {
        String sql = "insert into cover_image (session_id, cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, created_at) values(?, ?, ?, ?, ?, ?) ";

        return images.getCoverImages()
                .stream()
                .mapToInt(coverImage -> jdbcTemplate.update(sql,
                        coverImage.getSessionId(),
                        coverImage.getImageFileSize().getSize(),
                        coverImage.getImageType().name(),
                        coverImage.getImageSize().getWidth(),
                        coverImage.getImageSize().getHeight(),
                        coverImage.getCreatedAt()))
                .sum();
    }

    @Override
    public TobeCoverImages findAllBySessionId(long sessionId) {
        String sql = "select id, session_id, cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, created_at, updated_at " +
                "from cover_image " +
                "where session_id = ?";
        RowMapper<TobeCoverImage> rowMapper = (rs, rowNum) -> new TobeCoverImage(
                rs.getLong("id"),
                rs.getLong("session_id"),
                new ImageFileSize(rs.getInt("cover_image_file_size")),
                ImageType.toImageType(rs.getString("cover_image_type")),
                new ImageSize(
                        rs.getDouble("cover_image_width"),
                        rs.getDouble("cover_image_height")
                ),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
                );
        List<TobeCoverImage> students = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new TobeCoverImages(students);
    }

}
