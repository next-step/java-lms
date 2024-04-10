package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcOperations;

    public JdbcSessionRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Session> findByCourseId(Long id) {
        String sessionSql = "select id, course_id, title, type, status, start_date,end_date, image_size, image_height,image_width, image_name, image_extension, max_size, amount from session where course_id = ?";

        RowMapper<Session> seessionRowMapper = (rs, rowNum) -> {
            String type = rs.getString(4);
            if (type.equals("FREE")) {
                return new FreeSession(rs.getLong(1),
                        rs.getString(3),
                        new Course(rs.getLong(2)),
                        new Period(rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()),
                        new Image(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12)),
                        SessionStatus.valueOf(rs.getString(5)),
                        NsUsers.from(new ArrayList<>()));
            }

            return new PaidSession(rs.getLong(1),
                    rs.getString(3),
                    new Course(rs.getLong(2)),
                    new Period(rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()),
                    new Image(rs.getInt(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getString(12)),
                    SessionStatus.valueOf(rs.getString(5)),
                    NsUsers.from(new ArrayList<>()),
                    rs.getInt(13),
                    rs.getLong(14)
            );
        };

        List<Session> query = jdbcOperations.query(sessionSql, seessionRowMapper, id);
        query.forEach(item -> {
            Long sessionId = item.getId();
            String userSql = "SELECT user_id FROM session_users where session_id = ?";
            RowMapper<NsUser> nsUserRowMapper = (rs, rowNumber) -> new NsUser(rs.getLong(1));
            List<NsUser> nsUsers = jdbcOperations.query(userSql, nsUserRowMapper, sessionId);
            item.addNsUser(nsUsers);
        });

        return query;
    }

    @Override
    public Session findById(Long id) {
        return null;
    }
}
