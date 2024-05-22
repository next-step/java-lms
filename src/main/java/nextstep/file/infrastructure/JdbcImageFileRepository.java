package nextstep.file.infrastructure;

import nextstep.file.domain.ImageFile;
import nextstep.file.domain.ImageFileRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("imageFileRepository")
public class JdbcImageFileRepository implements ImageFileRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcImageFileRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Long save(ImageFile imageFile) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		String sql = "insert into image(size, width, height, type) values (?, ?, ?, ?)";
		jdbcTemplate.update(conn -> {
			PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"id"});
			pstmt.setInt(1, imageFile.getSize());
			pstmt.setInt(2, imageFile.getWidth());
			pstmt.setInt(3, imageFile.getHeight());
			pstmt.setString(4, imageFile.getTypeString());

			return pstmt;
		}, keyHolder);

		return keyHolder.getKey().longValue();
	}
}
