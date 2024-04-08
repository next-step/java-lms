package nextstep.sessions.infrastructore;


import nextstep.sessions.domain.CoverImage;
import nextstep.sessions.domain.EnableExtension;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(CoverImage coverImage) {
        String sql = "INSERT INTO cover_image(session_id, width, height, file_size, file_name, extension, file_path)\n" +
                "VALUES (?, ?, ?, ?, ?, ? ,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, coverImage.getSessionId());
            ps.setInt(2, coverImage.getWidth());
            ps.setInt(3, coverImage.getHeight());
            ps.setLong(4, coverImage.getFileSize());
            ps.setString(5, coverImage.getFileName());
            ps.setString(6, coverImage.getExtension().name());
            ps.setString(7, coverImage.getFilePath());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void saveAll(List<CoverImage> coverImages) {
        String sql = "INSERT INTO cover_image(session_id, width, height, file_size, file_name, extension, file_path)\n" +
                "VALUES (?, ?, ?, ?, ?, ? ,?)";
        jdbcTemplate.batchUpdate(sql, coverImages, coverImages.size(), values());
    }

    private PreparedStatementSetter values(CoverImage coverImage) {
        return ps -> {
            ps.setLong(1, coverImage.getSessionId());
            ps.setInt(2, coverImage.getWidth());
            ps.setInt(3, coverImage.getHeight());
            ps.setLong(4, coverImage.getFileSize());
            ps.setString(5, coverImage.getFileName());
            ps.setString(6, coverImage.getExtension().name());
            ps.setString(7, coverImage.getFilePath());
        };
    }

    private ParameterizedPreparedStatementSetter<CoverImage> values() {
        return (ps, coverImage) -> {
            ps.setLong(1, coverImage.getSessionId());
            ps.setInt(2, coverImage.getWidth());
            ps.setInt(3, coverImage.getHeight());
            ps.setLong(4, coverImage.getFileSize());
            ps.setString(5, coverImage.getFileName());
            ps.setString(6, coverImage.getExtension().name());
            ps.setString(7, coverImage.getFilePath());
        };
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, session_id, width, height, file_size, file_name, extension, file_path from cover_image where id = ?";
        return jdbcTemplate.queryForObject(sql, coverImageRowMapper(), id);
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, width, height, file_size, file_name, extension, file_path from cover_image where session_id = ?";
        return jdbcTemplate.query(sql, coverImageRowMapper(), sessionId);
    }

    private RowMapper<CoverImage> coverImageRowMapper() {
        return (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getLong(5),
                rs.getString(6),
                EnableExtension.find(rs.getString(7)),
                rs.getString(8)
        );
    }

}
