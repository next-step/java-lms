package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.CoverRepository;
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

    private JdbcOperations jdbcTemplate;

    public JdbcCoverRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Cover cover) {
        String sql = "insert into cover (width, height, file_path, file_name, file_extension, byte_size, writer_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CoverVO coverVO = cover.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
    public CoverVO findById(long coverId) {
        String sql = "select id, width, height, file_path, file_name, file_extension, byte_size, writer_id, deleted, created_at, last_modified_at from cover where id = ?";
        RowMapper<CoverVO> rowMapper = (rs, rowNum) -> new CoverVO(
                rs.getLong(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getLong(7),
                rs.getString(8),
                rs.getBoolean(9),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(10)),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(11)));
        return jdbcTemplate.queryForObject(sql, rowMapper, coverId);
    }

    @Override
    public int updateDeleteStatus(long coverId, boolean deleteStatus) {
        String sql = "UPDATE cover SET deleted = ? WHERE id = ?";
        return jdbcTemplate.update(sql, deleteStatus, coverId);
    }
}
