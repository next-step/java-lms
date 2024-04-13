package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.CoverRepository;
import nextstep.session.domain.ImageFilePath;
import nextstep.session.domain.Resolution;
import nextstep.session.dto.CoverVO;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository("coverRepository")
public class JdbcCoverRepository implements CoverRepository {

    public static final String COVER_INSERT_QUERY = "insert into cover (width, height, file_path, file_name, file_extension, byte_size, writer_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String COVER_FIND_QUERY =
            "select id as id, " +
                    "width as width, " +
                    "height as height, " +
                    "file_path as filePath, " +
                    "file_name as fileName, " +
                    "file_extension as fileExtension, " +
                    "byte_size as byteSize, " +
                    "writer_id as writerId, " +
                    "deleted as deleted, " +
                    "created_at as createdAt, " +
                    "last_modified_at as lastModifiedAt " +
            "from cover " +
            "where id = ?";
    private JdbcOperations jdbcTemplate;

    public JdbcCoverRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Cover cover) {
        CoverVO coverVO = cover.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(COVER_INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, coverVO.getWidth());
            ps.setInt(2, coverVO.getHeight());
            ps.setString(3, coverVO.getFilePath());
            ps.setString(4, coverVO.getFileName());
            ps.setString(5, coverVO.getFileExtension());
            ps.setLong(6, coverVO.getByteSize());
            ps.setString(7, coverVO.getWriterId());
            ps.setBoolean(8, coverVO.isDeleted());
            ps.setTimestamp(9, DbTimestampUtils.toTimestamp(coverVO.getCreatedAt()));
            ps.setTimestamp(10, DbTimestampUtils.toTimestamp(coverVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Cover findById(long coverId) {
        RowMapper<Cover> rowMapper = (rs, rowNum) -> new Cover(
                rs.getLong("id"),
                new Resolution(rs.getInt("width"), rs.getInt("height")),
                new ImageFilePath(rs.getString("filePath"), rs.getString("fileName"), rs.getString("fileExtension")),
                rs.getLong("byteSize"),
                rs.getString("writerId"),
                rs.getBoolean("deleted"),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("createdAt")),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("lastModifiedAt")));
        return jdbcTemplate.queryForObject(COVER_FIND_QUERY, rowMapper, coverId);
    }

    @Override
    public int updateDeleteStatus(long coverId, boolean deleteStatus) {
        String sql = "UPDATE cover SET deleted = ? WHERE id = ?";
        return jdbcTemplate.update(sql, deleteStatus, coverId);
    }
}
