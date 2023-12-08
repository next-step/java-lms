package nextstep.courses.infrastructure;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.enums.ApplyStatus;
import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.ProgressStatus;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.Image;
import nextstep.courses.domain.session.registration.Registration;
import nextstep.courses.domain.session.registration.RegistrationRepository;
import nextstep.courses.domain.session.registration.SessionCapacity;
import nextstep.courses.domain.session.registration.SessionRegistration;
import nextstep.courses.domain.session.registration.Students;
import nextstep.courses.domain.session.registration.Tuition;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
	private final JdbcOperations jdbcTemplate;
	private final RegistrationRepository registrationRepository;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
	}

	@Override
	public int save(Session session) {
		List<Registration> registrations = session.getSessionRegistration().getStudents().getRegistrations();
		for (Registration registration : registrations) {
			registrationRepository.save(registration);
		}

		String sql
			= "insert into session ("
			+ "course_id, width, height, type, capacity, "
			+ "start_at, end_at, title, progress_status, apply_status, maximum_capacity, "
			+ "paid_type, tuition, created_at, updated_at"
			+ ") values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(
			sql,
			session.getCourse().getId(),
			session.getImage().getSize().getWidth(),
			session.getImage().getSize().getHeight(),
			session.getImage().getType().name(),
			session.getImage().getImageCapacity().getCapacity(),
			session.getPeriod().getStartAt(),
			session.getPeriod().getEndAt(),
			session.getTitle(),
			session.getProgressStatus().name(),
			session.getApplyStatus().name(),
			session.getSessionRegistration().getMaximumCapacity().getMaximumCapacity(),
			session.getSessionRegistration().getPaidType().name(),
			session.getSessionRegistration().getTuition().getTuition(),
			session.getCreatedAt(),
			session.getUpdatedAt()
		);
	}

	@Override
	public Optional<Session> findById(Long id) {
		List<Registration> registrations = registrationRepository.findRegistrationsBySessionId(id);

		String sql = "select id, course_id, width, height, type, capacity,"
			+ "start_at, end_at, title, progress_status, apply_status, maximum_capacity,"
			+ "paid_type, tuition, created_at, updated_at from session where id = ?";
		RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
			rs.getLong(1),
			rs.getString(9),
			new Period(
				toLocalDate(rs.getDate(7)),
				toLocalDate(rs.getDate(8))
			),
			new Image(
				rs.getDouble(6),
				rs.getString(5),
				rs.getInt(3),
				rs.getInt(4)
			),
			ProgressStatus.valueOf(rs.getString(10)),
			ApplyStatus.valueOf(rs.getString(11)),
			new SessionRegistration(
				PaidType.valueOf(rs.getString(13)),
				new Tuition(rs.getInt(14)),
				new SessionCapacity(12),
				new Students(registrations)
			),
			null,
			toLocalDateTime(rs.getTimestamp(15)),
			toLocalDateTime(rs.getTimestamp(16))
		);

		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
	}

	private LocalDateTime toLocalDateTime(Timestamp timestamp) {
		if (timestamp == null) {
			return null;
		}
		return timestamp.toLocalDateTime();
	}

	private LocalDate toLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		return date.toLocalDate();
	}
}
