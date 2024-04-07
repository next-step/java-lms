package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CoverImageInfo;
import nextstep.courses.domain.CoverImageInfoRepository;
import nextstep.courses.domain.enums.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("CoverImageInfoRepository")
public class JdbcCoverImageInfoRepository implements CoverImageInfoRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageInfoRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImageInfo coverImageInfo) {
        String sql = "insert into cover_image_info (size, width, height, type) values(?, ?, ?, ?)";
        ImageType imageType = coverImageInfo.getImageType();
        return jdbcTemplate.update(sql, coverImageInfo.getSize(), coverImageInfo.getWidth(), coverImageInfo.getHeight(), imageType.name());
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
