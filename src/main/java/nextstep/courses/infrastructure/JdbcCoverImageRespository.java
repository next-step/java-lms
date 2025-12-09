package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRespository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRespository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage coverImage) {
        String sql = "insert into cover_image (size, type, width, height) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                coverImage.getSize(),
                coverImage.getType().toString(),
                coverImage.getWidth(),
                coverImage.getHeight());
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "select * from cover_image where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new CoverImage(
                    rs.getLong("id"),
                    rs.getLong("session_id"),
                    rs.getLong("size"),
                    rs.getString("type"),
                    rs.getInt("width"),
                    rs.getInt("height")
            );
        }, sessionId);
    }
}
