package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import nextstep.courses.domain.session.repository.EnrolmentRepository;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.dto.EnrolmentInfo;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import static nextstep.courses.domain.session.PayType.*;
import static nextstep.courses.domain.session.SessionStatus.*;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final CoverImageRepository coverImageRepository;
    private final EnrolmentRepository enrolmentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CoverImageRepository coverImageRepository, EnrolmentRepository enrolmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = coverImageRepository;
        this.enrolmentRepository = enrolmentRepository;
    }

    @Override
    public Optional<PaySession> findPaySessionById(Long sessionId) {
        String sql = "select * from session where id = ?";

        RowMapper<PaySession> rowMapper = (rs, rowNum) -> new PaySession(
            rs.getLong(1),
            payType(rs.getString(2)),
            sessionStatus(rs.getString(3)),
            coverImageRepository.findAllBySession(sessionId),
            enrolmentRepository.findBySession(sessionId).orElseThrow(),
            toLocalDate(rs.getTimestamp(4)),
            toLocalDate(rs.getTimestamp(5)),
            rs.getLong(6),
            rs.getInt(7)) {
            @Override
            public Student enroll(EnrolmentInfo enrolmentInfo) {
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
            payType(rs.getString(2)),
            sessionStatus(rs.getString(3)),
            coverImageRepository.findAllBySession(sessionId),
            enrolmentRepository.findBySession(sessionId).orElseThrow(),
            toLocalDate(rs.getTimestamp(4)),
            toLocalDate(rs.getTimestamp(5))) {
            @Override
            public Student enroll(EnrolmentInfo enrolmentInfo) {
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
