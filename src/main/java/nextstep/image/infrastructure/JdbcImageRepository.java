package nextstep.image.infrastructure;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcImageRepository implements ImageRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcImageRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(final Image image) {
		return 0;
	}

	@Override
	public Image findById(final Long id) {
		return null;
	}
}
