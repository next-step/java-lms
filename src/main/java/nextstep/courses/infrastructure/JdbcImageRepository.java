package nextstep.courses.infrastructure;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.session.image.Image;
import nextstep.courses.domain.session.image.ImageRepository;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
	private final JdbcOperations jdbcTemplate;

	public JdbcImageRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Image image) {
		String sql = "insert into image (capacity, type, width, height, session_id) values(?, ?, ?, ?, ?)";
		return jdbcTemplate.update(
			sql, image.getImageCapacity(),
			image.getType(),
			image.getSize().getWidth(),
			image.getSize().getHeight(),
			image.getSession().getId()
		);
	}

	@Override
	public List<Image> findAllBySessionId(Long sessionId) {
		String sql = "select id, capacity, type, width, height, session_id from image where session_id = ?";
		RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
			rs.getLong(1),
			rs.getDouble(2),
			rs.getString(3),
			rs.getInt(4),
			rs.getInt(5),
			null
		);

		return jdbcTemplate.query(sql, rowMapper, sessionId);
	}
}
