package nextstep.session.infrastructure;

import nextstep.session.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("ImageInfoRepository")
public class JdbcImageInfoRepository implements ImageInfoRepository {
	private final JdbcOperations jdbcTemplate;

	public JdbcImageInfoRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(ImageInfo imageInfo) {
		String sql = "insert into image_info (image_size, width, height, image_type, session_id) values(?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, imageInfo.getImageSize(), imageInfo.getImageWidth(), imageInfo.getImageHeight(), imageInfo.getImageType(), imageInfo.getSessionId());
	}

	@Override
	public Optional<ImageInfo> findById(long id) {
		String sql = "select id, image_size, width, height, image_type, session_id from image_info where id = ?";
		RowMapper<ImageInfo> rowMapper = (rs, rowNum) -> new ImageInfo(
				rs.getLong(1),
				new ImageSize(rs.getInt(2)),
				new ImageReSolution(rs.getInt(3), rs.getInt(4)),
				ImageType.getImageType(rs.getString(5)),
				rs.getLong(6));
		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
	}

	@Override
	public int update(ImageInfo imageInfo) {
		String sql = "update image_info set image_size = ?, width = ?, height = ?, image_type = ?, session_id = ? where id = ?";
		return jdbcTemplate.update(sql, imageInfo.getImageSize(), imageInfo.getImageWidth(), imageInfo.getImageHeight(), imageInfo.getImageType(), imageInfo.getSessionId(), imageInfo.getId());
	}

	@Override
	public int deleteById(long id) {
		String sql = "delete from image_info where id = ?";
		return jdbcTemplate.update(sql, id);
	}

}
