package nextstep.courses.infrastructure;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.enums.ApplyStatus;
import nextstep.courses.domain.enums.PaidType;
import nextstep.courses.domain.enums.ProgressStatus;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.image.Image;
import nextstep.courses.domain.session.image.ImageRepository;
import nextstep.courses.domain.session.image.Images;
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
	private final ImageRepository imageRepository;

	public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
		this.imageRepository = new JdbcImageRepository(jdbcTemplate);
	}

	@Override
	public int save(Session session) {
		List<Registration> registrations = session.getSessionRegistration().getStudents().getRegistrations();
		for (Registration registration : registrations) {
			registrationRepository.save(registration);
		}

		String sql
			= "insert into session ("
			+ "course_id, start_at, end_at, title, progress_status, apply_status, maximum_capacity, "
			+ "paid_type, tuition, created_at, updated_at"
			+ ") values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(
			sql,
			session.getCourse().getId(),
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
		List<Registration> registrations = registrationRepository.findAllBySessionId(id);
		List<Image> images = imageRepository.findAllBySessionId(id);

		String sql = "select id, course_id, start_at, end_at, title, progress_status, apply_status, maximum_capacity,"
			+ "paid_type, tuition, created_at, updated_at from session where id = ?";
		RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
			rs.getLong(1),
			rs.getString(5),
			new Period(
				toLocalDate(rs.getDate(3)),
				toLocalDate(rs.getDate(4))
			),
			ProgressStatus.valueOf(rs.getString(6)),
			ApplyStatus.valueOf(rs.getString(7)),
			new SessionRegistration(
				PaidType.valueOf(rs.getString(9)),
				new Tuition(rs.getInt(10)),
				new SessionCapacity(8),
				new Students(registrations)
			),
			null,
			new Images(images),
			toLocalDateTime(rs.getTimestamp(11)),
			toLocalDateTime(rs.getTimestamp(12))
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
