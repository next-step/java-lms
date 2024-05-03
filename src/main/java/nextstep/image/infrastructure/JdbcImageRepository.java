package nextstep.image.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.image.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class JdbcImageRepository implements ImageRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcImageRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Image image) {
		String sql = "insert into image (session_id, url, image_type, image_height, image_width, size) values(?, ?, ?,?, ?, ?)";
		return jdbcTemplate.update(sql, image.getSessionId(), image.getUrl(), image.getType(), image.getImageHeight(), image.getImageWidth(), image.getSize());
	}

	@Override
	public Image findById(Long id) {
		String sql = "select id, session_id, url, image_type, image_width, image_height, size from image where id = ?";
		RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
				rs.getLong(1),
				rs.getLong(2),
				rs.getString(3),
				ImageType.valueOf(rs.getString(4)),
				new ImageShape(rs.getInt(5), rs.getInt(6)),
				rs.getInt(7));
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}
}
