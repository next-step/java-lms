package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.repository.CoverImageRepository;
import nextstep.courses.domain.session.repository.SessionRepository;
import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import static nextstep.courses.domain.session.PayType.*;
import static nextstep.courses.domain.session.Status.*;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final CoverImageRepository coverImageRepository;
    private final StudentRepository studentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CoverImageRepository coverImageRepository, StudentRepository studentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = coverImageRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Optional<PaySession> findPaySessionById(Long sessionId) {
        String sql = "select * from session where id = ?";

        RowMapper<PaySession> rowMapper = (rs, rowNum) -> new PaySession(
            rs.getLong(1),
            payType(rs.getString(2)),
            status(rs.getString(3)),
            coverImageRepository.findById(rs.getLong(4)).orElseThrow(),
            toLocalDate(rs.getTimestamp(5)),
            toLocalDate(rs.getTimestamp(6)),
            rs.getLong(7),
            rs.getInt(8),
            studentRepository.findAllBySession(sessionId)) {
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
            status(rs.getString(3)),
            coverImageRepository.findById(rs.getLong(4)).orElseThrow(),
            toLocalDate(rs.getTimestamp(5)),
            toLocalDate(rs.getTimestamp(6)),
            studentRepository.findAllBySession(sessionId)) {
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
