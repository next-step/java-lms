package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        final String sqlForEnrollmentSave = "insert into enrollment (user_id, session_id, status, created_at) values (?, ?, ?, ?)";
        KeyHolder keyHolderForEnrollment = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlForEnrollmentSave, new String[]{"id"});
            ps.setLong(1, enrollment.getEnrolledUser().getId());
            ps.setLong(2, enrollment.getIdOfSession());
            ps.setString(3, enrollment.getStatus().name());
            ps.setTimestamp(4, Timestamp.valueOf(enrollment.getCreatedAt()));
            return ps;
        }, keyHolderForEnrollment);
        enrollment.updateAsSavedEnrollment(keyHolderForEnrollment.getKey().longValue());

        return enrollment;
    }

    @Override
    public Enrollment findById(Long id) {
        try {
            final String sqlForEnrollment = "select E.id" +
                    ", S.id, S.title, S.description, S.max_number_of_enrollment, S.fee, S.status, S.gathering_status, S.started_at, S.ended_at" +
                    ", C.id, C.title, C.creator_id, C.created_at, C.updated_at" +
                    ", S.created_at, S.updated_at" +
                    ", U.id, U.user_id, U.password, U.name, U.email, U.created_at, U.updated_at" +
                    ", E.status, E.created_at, E.updated_at" +
                    " from enrollment as E" +
                    " inner join session as S on S.id = E.session_id" +
                    " inner join course as C on C.id = S.course_id" +
                    " inner join ns_user as U on U.id = E.user_id" +
                    " where E.id = ?";
            RowMapper<Enrollment> rowMapperForEnrollment = (rs, rowNum) -> new Enrollment(
                    rs.getLong(1),
                    new Session(rs.getLong(2), rs.getString(3), rs.getString(4), new SessionType(rs.getInt(5), rs.getLong(6)),
                            SessionStatus.valueOf(rs.getString(7)), SessionGatheringStatus.valueOf(rs.getString(8)),
                            new Period(toLocalDateTime(rs.getTimestamp(9)), toLocalDateTime(rs.getTimestamp(10))),
                            new Course(rs.getLong(11), rs.getString(12), rs.getLong(13), toLocalDateTime(rs.getTimestamp(14)), toLocalDateTime(rs.getTimestamp(15))),
                            toLocalDateTime(rs.getTimestamp(16)), toLocalDateTime(rs.getTimestamp(17))),
                    new NsUser(rs.getLong(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), toLocalDateTime(rs.getTimestamp(23)), toLocalDateTime(rs.getTimestamp(24))),
                    EnrollmentStatus.valueOf(rs.getString(25)),
                    toLocalDateTime(rs.getTimestamp(26)),
                    toLocalDateTime(rs.getTimestamp(27)));
            return jdbcTemplate.queryForObject(sqlForEnrollment, rowMapperForEnrollment, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Enrollment update(Enrollment enrollment) {
        final String sqlForSessionForUpdate = "update enrollment set status = ?, updated_at = ? where id = ?";
        jdbcTemplate.update(sqlForSessionForUpdate, enrollment.getStatus().name(), enrollment.getUpdatedAt(), enrollment.getId());

        return enrollment;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
