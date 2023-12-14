package nextstep.courses.infrastructure;

import nextstep.courses.domain.participant.SessionParticipants;
import nextstep.courses.domain.participant.SessionUserEnrolment;
import nextstep.courses.domain.participant.SessionUserEnrolmentRepository;
import nextstep.courses.type.ApprovalStatus;
import nextstep.courses.type.ParticipantSelectionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("SessionUserEnrolmentRepository")
public class JdbcSessionUserEnrolmentRepository implements SessionUserEnrolmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionUserEnrolmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionUserEnrolment enrolment) {
        String sql = "insert into session_user_enrolment (ns_user_id, session_id, subscription_status, approval_status) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enrolment.nsUserId(), enrolment.sessionId(), enrolment.subscriptionStatus().toString(), enrolment.approvalStatus().toString());
    }

    @Override
    public int updateSubscriptionStatus(SessionUserEnrolment enrolment) {
        String sql = "update session_user_enrolment set subscription_status = ? where ns_user_id = ? and session_id = ?";
        return jdbcTemplate.update(sql, enrolment.subscriptionStatus().toString(), enrolment.nsUserId(), enrolment.sessionId());
    }

    @Override
    public int updateApprovalStatus(SessionUserEnrolment enrolment) {
        String sql = "update session_user_enrolment set approval_status = ? where ns_user_id = ? and session_id = ?";
        return jdbcTemplate.update(sql, enrolment.approvalStatus().toString(), enrolment.nsUserId(), enrolment.sessionId());
    }

    @Override
    public Optional<SessionUserEnrolment> findBySessionIdAndUserId(Long findSessionId, Long userId) {
        String sql = "select ns_user_id, session_id, subscription_status, approval_status from session_user_enrolment where session_id = ? and ns_user_id = ?";
        RowMapper<SessionUserEnrolment> rowMapper = (rs, rowNum) -> {
            Long nsUserId = rs.getLong(1);
            Long sessionId = rs.getLong(2);
            String subscriptionStatus = rs.getString(3);
            String approvalStatus = rs.getString(4);
            return new SessionUserEnrolment(nsUserId, sessionId, ParticipantSelectionStatus.valueOf(subscriptionStatus), ApprovalStatus.valueOf(approvalStatus));
        };
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, findSessionId, userId));
    }

    @Override
    public SessionParticipants findBySessionId(Long findSessionId) {
        String sql = "select ns_user_id, session_id, subscriptionstatus, approval_status from session_user_enrolment where session_id = ?";
        RowMapper<SessionUserEnrolment> rowMapper = (rs, rowNum) -> {
            Long nsUserId = rs.getLong(1);
            Long sessionId = rs.getLong(2);
            String subscriptionStatus = rs.getString(3);
            String approvalStatus = rs.getString(4);
            return new SessionUserEnrolment(nsUserId, sessionId, ParticipantSelectionStatus.valueOf(subscriptionStatus), ApprovalStatus.valueOf(approvalStatus));
        };
        return new SessionParticipants(jdbcTemplate.query(sql, rowMapper, findSessionId));
    }
}
