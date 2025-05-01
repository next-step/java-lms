package nextstep.session.infrastructure;

import nextstep.payments.domain.FreePaymentPolicy;
import nextstep.payments.domain.PaidPaymentPolicy;
import nextstep.payments.domain.PaymentPolicy;
import nextstep.session.domain.image.CoverImage;
import nextstep.session.domain.image.CoverImageRepository;
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
        String sql = "insert into session (id, title, cover_image_id, start_at, end_at, fee, enrollment_limit, " +
                "session_status, recruitment_status, " +
                "created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Long coverImageId = session.getCoverImage() == null ? null : session.getCoverImage().getId();
        return jdbcTemplate.update(sql,
                session.getId(), session.getTitle(), coverImageId,
                session.getDuration().getStartAt(), session.getDuration().getEndAt(),
                session.getPaymentPolicy().fee(), session.getPaymentPolicy().enrollmentLimit(),
                session.getSessionStatus().toString(), session.getRecruitmentStatus().toString(),
                session.getCreatedAt(), session.getUpdatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, cover_image_id, start_at, end_at, fee, enrollment_limit, " +
                "session_status, recruitment_status, " +
                "created_at, updated_at " +
                "from session " +
                "where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            Long coverImageId = rs.getLong(3);
            CoverImage coverImage = getCoverImage(coverImageId);
            EnrolledStudents enrolledStudents = getEnrolledStudents(id);

            Duration duration = new Duration(toLocalDate(rs.getTimestamp(4)), toLocalDate(rs.getTimestamp(5)));

            long fee = rs.getLong(6);
            int enrollment_limit = rs.getInt(7);
            PaymentPolicy policy = getPaymentPolicy(fee, enrollment_limit);

            return new SessionBuilder()
                    .id(rs.getLong(1))
                    .title(rs.getString(2))
                    .duration(duration)
                    .coverImage(coverImage)
                    .paymentPolicy(policy)
                    .enrolledStudents(enrolledStudents)
                    .sessionStatus(SessionStatus.valueOf(rs.getString(8)))
                    .recruitmentStatus(RecruitmentStatus.valueOf(rs.getString(9)))
                    .createdAt(toLocalDateTime(rs.getTimestamp(10)))
                    .updatedAt(toLocalDateTime(rs.getTimestamp(11)))
                    .build();
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private CoverImage getCoverImage(Long coverImageId) {
        if ( coverImageId == null || coverImageId <= 0) {
            return null;
        }

        CoverImageRepository coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        return coverImageRepository.findById(coverImageId);
    }

    private EnrolledStudents getEnrolledStudents(Long sessionId) {
        if ( sessionId == null || sessionId <= 0) {
            return null;
        }

        EnrolledStudentsRepository enrolledStudentsRepository = new JdbcEnrolledStudentsRepository(jdbcTemplate);
        return enrolledStudentsRepository.findById(sessionId);


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
