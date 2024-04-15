package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.enums.SessionStatus;
import nextstep.courses.domain.enums.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {
	private JdbcOperations jdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate, DataSource dataSource) {
		this.jdbcTemplate = jdbcTemplate;
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
			.withTableName("session")
			.usingGeneratedKeyColumns("id");
	}

	@Override
	public Long saveSessionAndGetId(Session session) {
		Map<String, Object> sessionParameter = getSessionParameter(session);
		Number numberKey = simpleJdbcInsert.executeAndReturnKey(sessionParameter);

		return numberKey.longValue();
	}

	private Map<String, Object> getSessionParameter(Session session) {
		Map<String, Object> insertParam = new HashMap<>(getCommonSessionInsertParam(session));

		if (session instanceof PaySession) {
			PaySession paySession = (PaySession) session;
			insertParam.put("price", paySession.getPrice());
			insertParam.put("max_number_of_students", paySession.getMaxNumberOfStudents());
		}

		return insertParam;
	}

	private Map<String, Object> getCommonSessionInsertParam(Session session) {
		SessionDate sessionDate = session.getSessionDate();
		CoverImageInfo coverImageInfo = session.getCoverImageInfo();
		SessionStatus sessionStatus = session.getSessionStatus();
		SessionType sessionType = session.getType();

		return Map.of(
			"start_date", sessionDate.getStartDate(),
			"end_date", sessionDate.getEndDate(),
			"status", sessionStatus.name(),
			"number_of_students", session.getNumberOfStudents(),
			"cover_image_info_id", coverImageInfo.getId(),
			"type", sessionType.getType()
		);
	}

	@Override
	public Session findById(Long id) {
		String sql = "select " +
			"s.id as id" +
			", s.start_date as startDate" +
			", s.end_date as endDate" +
			", s.status as status" +
			", s.number_of_students as numberOfStudents" +
			", s.max_number_of_students as maxNumberOfStudents" +
			", s.price as price" +
			", s.type as type" +
			", c.id as coverImageId" +
			", c.size as coverImageSize" +
			", c.width as coverImageWidth" +
			", c.height as coverImageHeight" +
			", c.type as coverImageType " +
			"from " +
			"session s " +
			"join cover_image_info c " +
			"on s.cover_image_info_id = c.id " +
			"where s.id = ?";

		RowMapper<Session> rowMapper = (rs, rowNum) -> {
			CoverImageInfo coverImageInfo = CoverImageInfo.builder()
				.id(rs.getLong("coverImageId"))
				.size(rs.getLong("coverImageSize"))
				.width(rs.getLong("coverImageWidth"))
				.height(rs.getLong("coverImageHeight"))
				.imageType(rs.getString("coverImageType"))
				.build();

			String type = rs.getString("type");

			if ("P".equalsIgnoreCase(type)) {
				return PaySession.builder()
					.id(rs.getLong("id"))
					.sessionDate(SessionDate.of(toLocalDateTime(rs.getTimestamp("startDate")), toLocalDateTime(rs.getTimestamp("endDate"))))
					.sessionStatus(SessionStatus.findBySessionStr(rs.getString("status")).orElse(null))
					.numberOfStudents(rs.getInt("numberOfStudents"))
					.maxNumberOfStudents(rs.getInt("maxNumberOfStudents"))
					.coverImageInfo(coverImageInfo)
					.price(rs.getLong("price"))
					.type(SessionType.findByTypeStr(rs.getString("type")).orElse(null))
					.build();
			}

			return FreeSession.builder()
				.id(rs.getLong("id"))
				.sessionDate(SessionDate.of(toLocalDateTime(rs.getTimestamp("startDate")), toLocalDateTime(rs.getTimestamp("endDate"))))
				.sessionStatus(SessionStatus.findBySessionStr(rs.getString("status")).orElse(null))
				.numberOfStudents(rs.getInt("numberOfStudents"))
				.coverImageInfo(coverImageInfo)
				.type(SessionType.findByTypeStr(rs.getString("type")).orElse(null))
				.build();
		};

		return jdbcTemplate.queryForObject(sql, rowMapper, id);
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}
}
