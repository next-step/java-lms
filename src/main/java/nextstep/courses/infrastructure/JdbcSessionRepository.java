package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.enroll.RecruitingStatus;
import nextstep.courses.domain.session.period.Period;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import nextstep.courses.domain.session.repository.EnrolmentRepository;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.repository.SessionStudentRepository;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.dto.EnrolmentInfo;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final CoverImageRepository coverImageRepository;
    private final EnrolmentRepository enrolmentRepository;
    private final SessionStudentRepository studentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CoverImageRepository coverImageRepository, EnrolmentRepository enrolmentRepository, SessionStudentRepository studentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = coverImageRepository;
        this.enrolmentRepository = enrolmentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<PaySession> findPaySessionById(Long sessionId) {
        String sql = "select * from session where id = ?";

        RowMapper<PaySession> rowMapper = (rs, rowNum) -> new PaySession(
            rs.getLong(1),
            PayType.valueOf(rs.getString(2)),
            SessionStatus.valueOf(rs.getString(3)),
            RecruitingStatus.valueOf(rs.getString(4)),
            coverImageRepository.findAllBySession(sessionId),
            enrolmentRepository.findBySession(sessionId).orElseThrow(),
            studentRepository.findAllBySession(sessionId),
            Period.from(
                toLocalDate(rs.getTimestamp(5)),
                toLocalDate(rs.getTimestamp(6))
            ),
            rs.getLong(7),
            rs.getInt(8)) {
            @Override
            public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
                return null;
            }
        };

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    @Override
    public Optional<FreeSession> findFreeSessionById(Long sessionId) {
        String sql = "select * from session where id = ?";

        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
            rs.getLong(1),
            PayType.valueOf(rs.getString(2)),
            SessionStatus.valueOf(rs.getString(3)),
            RecruitingStatus.valueOf(rs.getString(4)),
            coverImageRepository.findAllBySession(sessionId),
            enrolmentRepository.findBySession(sessionId).orElseThrow(),
            studentRepository.findAllBySession(sessionId),
            Period.from(
                toLocalDate(rs.getTimestamp(5)),
                toLocalDate(rs.getTimestamp(6))
            )) {
            @Override
            public SessionStudent enroll(EnrolmentInfo enrolmentInfo) {
                return null;
            }
        };

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime().toLocalDate();
    }
}
