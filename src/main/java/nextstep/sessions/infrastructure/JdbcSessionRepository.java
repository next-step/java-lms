package nextstep.sessions.infrastructure;

import nextstep.image.domain.*;
import nextstep.sessions.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class JdbcSessionRepository implements SessionRepository {
	private JdbcOperations jdbcTemplate;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int save(Session session) {
		String sql = "insert into session (title, creator_id, price, charge_status, start_at, ended_at) values(?, ?, ?)";
		return jdbcTemplate.update(sql, session.getTitle(), session.getCreatorId(), session.getPrice(), session.getChargeStatus(), session.getStartedAt(), session.getEndedAt());
	}

	@Override
	public Session findById(Long id) {
		String sql = "select session.id, session.title, session.creator_id, image.id, image.session_id, image.url, image.image_type, image.width, image.height, image.size, session.created_at, session.updated_at " +
				"from session left outer join image on image.session_id = session.id" +
				" left outer join user on session.id = user.session_id" +
				" where id = ?";
		RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
				rs.getLong(1),
				new SessionInfo(
						rs.getString(2),
						rs.getLong(3),
						new Image(
								rs.getLong(4),
								rs.getLong(5),
								rs.getString(6),
								ImageType.valueOf(rs.getString(7)),
								new ImageShape(
										new ImageWidth(rs.getInt(8)),
										new ImageHeight(rs.getInt(9))
								),
								rs.getInt(10)
						)
				),
				new Charge(
						ChargeStatus.valueOf(rs.getString(11)),
						rs.getInt(12)
				),
				new Enrollment(
						SessionStatus.valueOf(rs.getString(13)),
						rs.getInt(14),
						rs.getObject(15, List.class)
				),
				new SessionDate(
						new StartedAt(toLocalDate(rs.getTimestamp(5))),
						new EndedAt(toLocalDate(rs.getTimestamp(6)))
				)

		);
		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	private LocalDate toLocalDate(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime().toLocalDate();
	}
}
