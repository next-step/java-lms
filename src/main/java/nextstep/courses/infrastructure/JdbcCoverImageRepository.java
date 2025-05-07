package nextstep.courses.infrastructure;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.session.metadata.coverImage.CoverImage;
import nextstep.courses.domain.session.metadata.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.metadata.coverImage.Dimensions;
import nextstep.courses.domain.session.metadata.coverImage.ImageType;
import nextstep.courses.domain.session.metadata.coverImage.Size;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (id, size_bytes, width, height, type) values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, coverImage.getId(), coverImage.getSize(), coverImage.getWidth(), coverImage.getHeight(), coverImage.getExtension());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, size_bytes, type, width, height from cover_image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
            rs.getLong(1),
            Size.ofBytes(rs.getLong(2)),
            ImageType.fromExtension(rs.getString(3)),
            new Dimensions(rs.getInt(4), rs.getInt(5))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
