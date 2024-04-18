package nextstep.session.infrastructure;

import nextstep.session.domain.EnrollmentPolicy;
import nextstep.session.domain.PriceType;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionSchedule;
import nextstep.session.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id,course_id, title,start_date,end_date,status,price_type,max_enrollment, fee from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) ->
            new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                new SessionSchedule(rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate()),
                SessionStatus.convert(rs.getString(6)),
                new EnrollmentPolicy(PriceType.valueOf(rs.getString(7)), rs.getInt(8),
                    rs.getInt(9)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session(title,course_id, start_date, end_date,status,price_type,  max_enrollment,fee ) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, session.getTitle(), session.getCourseId(),
            session.getStartDate(), session.getEndDate(), session.getSessionStatus(),
            session.getPriceType(), session.getMaxEnrollment(), session.getFee());
    }
}
