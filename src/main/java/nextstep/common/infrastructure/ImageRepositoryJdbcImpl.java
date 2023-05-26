package nextstep.common.infrastructure;

import nextstep.common.domain.Image;
import nextstep.common.domain.ImageRepository;
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
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ImageRepositoryJdbcImpl implements ImageRepository {
    private final JdbcTemplate jdbc;
    private AtomicLong


    public ImageRepositoryJdbcImpl(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @Override
    public Image save(Image image) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbc);
        jdbcInsert.withTableName("image").usingGeneratedKeyColumns("image_id");

        Map<String, Object> params = new HashMap<>() {{
            this.put("name", image.getImageUrl());
        }};

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(params));
        return new Image(key.longValue(), image.getImageUrl());
    }

    @Override
    public Optional<Image> findByImageId(Long imageId) {
        return jdbc.query("SELECT * FROM IMAGE WHERE ID = ?", rowMapper())
                .stream()
                .findAny();
    }

    @Override
    public List<Image> findAll() {
        return jdbc.query("SELECT * FROM IMAGE", rowMapper());
    }

    private RowMapper<Image> rowMapper() {
        return (resultSet, rowNumber) -> {
            return new Image(
                    resultSet.getLong("imageId"),
                    resultSet.getString("imageLink")
            );
        };
    }
}
