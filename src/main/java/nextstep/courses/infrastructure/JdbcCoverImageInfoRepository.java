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
import java.util.List;

@Repository("CoverImageInfoRepository")
public class JdbcCoverImageInfoRepository implements CoverImageInfoRepository {
    public static final String FIND_COVER_IMAGE_INFO_BY_ID_SQL = "select id, size, width, height, type from cover_image_info where id = ?";
    public static final String FIND_COVER_IMAGE_INFO_BY_SESSION_ID_SQL =
            "select " +
                    "scii.id as id" +
                    ", scii.session_id as sessionId" +
                    ", cii.id as coverImageId" +
                    ", cii.size as coverImageSize" +
                    ", cii.width as coverImageWidth" +
                    ", cii.height as coverImageHeight" +
                    ", cii.type as coverImageType" +
                    "from " +
                    "session_cover_image_info scii " +
                    "left join cover_image_info cii " +
                    "on cii.id = scii.cover_image_info_id " +
                    "where scii.session_id = ?";

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
        RowMapper<CoverImageInfo> rowMapper = (rs, rowNum) -> CoverImageInfo.createFromData(
                        rs.getLong("id"),
                        rs.getLong("size"),
                        rs.getString("type"),
                        rs.getLong("width"),
                        rs.getLong("height")
                );

        return jdbcTemplate.queryForObject(FIND_COVER_IMAGE_INFO_BY_ID_SQL, rowMapper, id);
    }

    @Override
    public List<CoverImageInfo> findBySessionId(Long sessionId) {
        RowMapper<CoverImageInfo> rowMapper = (rs, rowNum) -> CoverImageInfo.createFromData(
                rs.getLong("coverImageId"),
                rs.getLong("coverImageSize"),
                rs.getString("coverImageType"),
                rs.getLong("coverImageWidth"),
                rs.getLong("coverImageHeight")
        );

        return jdbcTemplate.query(FIND_COVER_IMAGE_INFO_BY_SESSION_ID_SQL, rowMapper, sessionId);
    }
}
