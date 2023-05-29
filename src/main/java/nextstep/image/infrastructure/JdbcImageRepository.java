package nextstep.image.infrastructure;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("ImageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcImageRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Image save(Image image) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("image").usingGeneratedKeyColumns("image_id");

        Map<String, Object> params = new HashMap<>() {{
            this.put("image_id", image.getImageId());
            this.put("image_url", image.getImageUrl());
        }};

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new Image(key.longValue(), image.getImageUrl());
    }

    @Override
    public Optional<Image> findByImageId(Long imageId) {
        return jdbcTemplate.query("SELECT * FROM image WHERE image_id = ?", rowMapper(), imageId)
                .stream()
                .findAny();
    }

    @Override
    public List<Image> findAll() {
        return jdbcTemplate.query("SELECT * FROM IMAGE", rowMapper());
    }

    private RowMapper<Image> rowMapper() {
        return (resultSet, rowNumber) -> {
            return new Image(
                    resultSet.getLong("image_id"),
                    resultSet.getString("image_url")
            );
        };
    }
}
