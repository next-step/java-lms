package nextstep.session.infrastructure;

import nextstep.session.domain.ImageType;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionImage;
import nextstep.session.domain.SessionImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static nextstep.common.domain.utils.JdbcConvertUtils.toLocalDateTime;

@Repository
public class JdbcSessionImageRepository implements SessionImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Session session) {
        String sql = "insert into session_image (created_at, updated_at, image_url, image_size, image_type, width, height, session_id) values(?,?,?,?,?,?,?,?)";
        SessionImage sessionImage = session.getSessionImage();
        jdbcTemplate.update(sql, sessionImage.getCreatedAt(), sessionImage.getUpdatedAt(), sessionImage.getImageURL(), sessionImage.getImageSize(), sessionImage.getImageType().toString(), sessionImage.getWidth(), sessionImage.getHeight(), session.getId());
    }

    @Override
    public SessionImage findById(Long id) {
        String sql = "select id, created_at, updated_at, image_url, image_size, image_type, width, height, session_id from session_image where id = ?";
        RowMapper<SessionImage> rowMapper = (rs, rowNum) -> new SessionImage(
                rs.getLong("id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")),
                rs.getString("image_url"), rs.getInt("image_size"),
                ImageType.valueOf(rs.getString("image_type")), rs.getInt("width"),
                rs.getInt("height"),
                rs.getLong("session_id")
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
