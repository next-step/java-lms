package nextstep.courses.infrastructure;

import nextstep.courses.domain.cource.ImageRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.StudentsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    private final ImageRepository imageRepository;
    private final StudentsRepository studentsRepository;


    public JdbcSessionRepository(JdbcOperations jdbcTemplate, ImageRepository imageRepository, StudentsRepository studentsRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.imageRepository = imageRepository;
        this.studentsRepository = studentsRepository;
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (type, state, start_date, end_date, amount, enrollment_max, image_id) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.type(), session.state(), session.startDate(), session.endDate(), session.amount(), session.enrollmentMax(), session.imageId());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, type, state, start_date, end_date, amount, enrollment_max, image_id from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4).toLocalDate(),
                rs.getDate(5).toLocalDate(),
                rs.getLong(6),
                rs.getLong(7),
                imageRepository.findById(rs.getLong(8)),
                studentsRepository.findBySessionId(rs.getLong(1)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
