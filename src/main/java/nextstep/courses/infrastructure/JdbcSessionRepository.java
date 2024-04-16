package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.type.SessionStatus;
import nextstep.courses.domain.session.user.SessionUser;
import nextstep.courses.domain.session.user.SessionUsers;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcOperations;
    private JdbcImageRepository imageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcOperations, JdbcImageRepository imageRepository) {
        this.jdbcOperations = jdbcOperations;
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Session> findByCourseId(Long id) {
        String sessionSql = "select id, course_id, title, type, status, start_date,end_date,max_size ,amount, creator_id from session where course_id = ?";

        RowMapper<Session> seessionRowMapper = (rs, rowNum) -> {
            String type = rs.getString(4);
            if (type.equals("FREE")) {
                return new FreeSession(rs.getLong(1),
                        rs.getString(3),
                        new Course(rs.getLong(2)),
                        new Period(rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()),
                        new ArrayList<>(),
                        SessionStatus.valueOf(rs.getString(5)),
                        SessionUsers.from(new ArrayList<>())
                        , rs.getLong(9));
            }

            return new PaidSession(rs.getLong(1),
                    rs.getString(3),
                    new Course(rs.getLong(2)),
                    new Period(rs.getDate(6).toLocalDate(), rs.getDate(7).toLocalDate()),
                    new ArrayList<>(),
                    SessionStatus.valueOf(rs.getString(5)),
                    SessionUsers.from(new ArrayList<>()),
                    rs.getInt(8),
                    rs.getLong(9),
                    rs.getLong(10)
            );
        };

        List<Session> sessions = jdbcOperations.query(sessionSql, seessionRowMapper, id);
        sessions.forEach(session -> {
            addNsUser(session);
            addImage(session);
        });

        return sessions;
    }

    private void addNsUser(Session session) {
        Long sessionId = session.getId();
        String userSql = "SELECT user_id FROM session_users where session_id = ?";
        RowMapper<SessionUser> nsUserRowMapper = (rs, rowNumber) -> new SessionUser(sessionId, rs.getLong(1));
        List<SessionUser> sessionUsers = jdbcOperations.query(userSql, nsUserRowMapper, sessionId);
        session.addSessionUser(sessionUsers);
    }

    private void addImage(Session session) {
        List<Image> images = imageRepository.findBySession(session.getId());
        images.stream()
                .forEach(image -> image.addSession(session));
        session.addImages(images);
    }

    @Override
    public Session findById(Long id) {
        return null;
    }
}
