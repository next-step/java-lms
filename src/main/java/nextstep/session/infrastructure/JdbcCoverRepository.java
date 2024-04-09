package nextstep.session.infrastructure;

import nextstep.session.dto.CoverDto;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository("coverRepository")
public class JdbcCoverRepository implements CoverRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcCoverRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(CoverDto coverDto) {
        String sql = "insert into cover (width, height, file_path, file_name, file_extension, byte_size, writer_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, coverDto.getWidth());
            ps.setInt(2, coverDto.getHeight());
            ps.setString(3, coverDto.getFilePath());
            ps.setString(4, coverDto.getFileName());
            ps.setString(5, coverDto.getFileExtension());
            ps.setLong(6, coverDto.getByteSize());
            ps.setString(7, coverDto.getWriterId());
            ps.setBoolean(8, coverDto.isDeleted());
            ps.setTimestamp(9, DbTimestampUtils.toTimestamp(coverDto.getCreatedAt()));
            ps.setTimestamp(10, DbTimestampUtils.toTimestamp(coverDto.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public CoverDto findById(long coverId) {
        String sql = "select id, width, height, file_path, file_name, file_extension, byte_size, deleted, writer_id, created_at, last_modified_at from cover where id = ?";
        RowMapper<CoverDto> rowMapper = (rs, rowNum) -> new CoverDto(
                rs.getLong(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getString(4),
                rs.getString(5),
                rs.getString(6),
                rs.getLong(7),
                rs.getBoolean(8),
                rs.getString(9),
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
