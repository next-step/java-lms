package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImageInfo;
import nextstep.courses.domain.CoverImageInfoRepository;
import nextstep.courses.domain.enums.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository("CoverImageInfoRepository")
public class JdbcCoverImageInfoRepository implements CoverImageInfoRepository {
    private JdbcOperations jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public JdbcCoverImageInfoRepository(JdbcOperations jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("cover_image_info")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Long saveAndGetId(CoverImageInfo coverImageInfo) {
        ImageType imageType = coverImageInfo.getImageType();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("size", coverImageInfo.getSize())
                .addValue("width", coverImageInfo.getWidth())
                .addValue("height", coverImageInfo.getHeight())
                .addValue("type", imageType.name());

        Number number = simpleJdbcInsert.executeAndReturnKey(sqlParameterSource);
        return number.longValue();
    }

    @Override
    public CoverImageInfo findById(Long id) {
        String sql = "select id, size, width, height, type from cover_image_info where id = ?";
        RowMapper<CoverImageInfo> rowMapper = (rs, rowNum) -> CoverImageInfo.builder()
                .id(rs.getLong(1))
                .size(rs.getLong(2))
                .width(rs.getLong(3))
                .height(rs.getLong(4))
                .imageType(rs.getString(5))
                .build();

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
