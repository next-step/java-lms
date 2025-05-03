package nextstep.session.infrastructure;

import nextstep.payments.domain.FreePaymentPolicy;
import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImageRepository;
import nextstep.session.domain.image.CoverImages;
import nextstep.session.domain.session.*;
import nextstep.session.domain.student.EnrolledStudents;
import nextstep.session.domain.student.EnrolledStudentsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, title, start_at, end_at, fee, enrollment_limit, " +
                "session_status, recruitment_status, " +
                "created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql,
                session.getId(), session.getTitle(),
                session.getDuration().getStartAt(), session.getDuration().getEndAt(),
                session.getPaymentPolicy().fee(), session.getPaymentPolicy().enrollmentLimit(),
                session.getSessionStatus().toString(), session.getRecruitmentStatus().toString(),
                session.getCreatedAt(), session.getUpdatedAt());

        EnrolledStudentsRepository enrolledStudentsRepository = new JdbcEnrolledStudentsRepository(jdbcTemplate);
        enrolledStudentsRepository.save(session.getEnrolledStudents());

        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        coverImageRepository.save(session.getCoverImages());
        return result;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, start_at, end_at, fee, enrollment_limit, " +
                "session_status, recruitment_status, " +
                "created_at, updated_at " +
                "from session " +
                "where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            CoverImages coverImages = getCoverImages(id);
            EnrolledStudents enrolledStudents = getEnrolledStudents(id);

            Duration duration = new Duration(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4)));

            long fee = rs.getLong(5);
            int enrollment_limit = rs.getInt(6);
            PaymentPolicy policy = getPaymentPolicy(fee, enrollment_limit);

            return new SessionBuilder()
                    .id(rs.getLong(1))
                    .title(rs.getString(2))
                    .coverImages(coverImages)
                    .duration(duration)
                    .paymentPolicy(policy)
                    .enrolledStudents(enrolledStudents)
                    .sessionStatus(SessionStatus.valueOf(rs.getString(7)))
                    .recruitmentStatus(RecruitmentStatus.valueOf(rs.getString(8)))
                    .createdAt(toLocalDateTime(rs.getTimestamp(9)))
                    .updatedAt(toLocalDateTime(rs.getTimestamp(10)))
                    .build();
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private CoverImages getCoverImages(Long sessionId) {
        if ( sessionId == null || sessionId <= 0) {
            return null;
        }

        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);

        return new CoverImages(coverImageRepository.findBySessionId(sessionId));
    }

    private EnrolledStudents getEnrolledStudents(Long sessionId) {
        if ( sessionId == null || sessionId <= 0) {
            return null;
        }

        EnrolledStudentsRepository enrolledStudentsRepository = new JdbcEnrolledStudentsRepository(jdbcTemplate);
        return enrolledStudentsRepository.findBySessionId(sessionId);


    }

    private static PaymentPolicy getPaymentPolicy(long fee, int enrollment_limit) {
        PaymentPolicy policy;
        // TODO : policy factory 고려해보자.
        if ( enrollment_limit == 0 ) {
            policy = new FreePaymentPolicy();
        } else {
            policy = new PaidPaymentPolicy(fee, enrollment_limit);
        }
        return policy;
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime().toLocalDate();
    }
    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
