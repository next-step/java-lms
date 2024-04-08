package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.RecruitmentState;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionState;
import nextstep.sessions.domain.SessionTypeFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    private final CoverImageRepository coverImageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CoverImageRepository coverImageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = coverImageRepository;
    }

    @Override
    public long save(Session session) {
        String sql = "INSERT INTO class_session (course_id, title, state, recruitment, capacity, amount, start_date, end_date, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, ? ,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            preparedStatementSetter(ps, session);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void saveAll(List<Session> sessions) {
        String sql = "INSERT INTO class_session (course_id, title, state, recruitment, capacity, amount, start_date, end_date, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, ? ,?)";

        jdbcTemplate.batchUpdate(sql, sessions, sessions.size(), (ps, session) -> preparedStatementSetter(ps, session));
    }

    private void preparedStatementSetter(PreparedStatement ps, Session session) throws SQLException {
        ps.setLong(1, session.getCourseId());
        ps.setString(2, session.getTitle());
        ps.setString(3, session.getState().name());
        ps.setString(4, session.getRecruitment().name());
        ps.setInt(5, session.getCapacity());
        ps.setLong(6, session.getAmount());
        ps.setTimestamp(7, Timestamp.valueOf(session.getStartDate()));
        ps.setTimestamp(8, Timestamp.valueOf(session.getEndDate()));
        ps.setTimestamp(9, Timestamp.valueOf(session.getCreatedAt()));
    }

    @Override
    public Optional<Session> findById(long sessionId) {
        String sql = "SELECT id, course_id, title, state, recruitment, capacity, amount, start_date, end_date, created_at FROM class_session WHERE id = ?";

        Optional<Session> optionalSession = Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> buildSession(rs), sessionId));

        if (optionalSession.isPresent()) {
            setCoverImages(optionalSession.get());
        }

        return optionalSession;
    }

    @Override
    public List<Session> findByIds(List<Long> ids) {
        String sql = "SELECT id, course_id, title, state, recruitment, capacity, amount, start_date, end_date, created_at FROM class_session WHERE id IN (%s)";
        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));

        List<Session> sessions = jdbcTemplate.query(String.format(sql, inSql), ids.toArray(), (rs, rowNum) -> buildSession(rs));

        for (Session session : sessions) {
            setCoverImages(session);
        }

        return sessions;
    }

    @Override
    public List<Session> findByCourseId(long courseId) {
        String sql = "SELECT id, course_id, title, state, recruitment, capacity, amount, start_date, end_date, created_at FROM class_session WHERE course_id = ?";

        List<Session> sessions = jdbcTemplate.query(sql, (rs, rowNum) -> buildSession(rs), courseId);

        for (Session session : sessions) {
            setCoverImages(session);
        }

        return sessions;
    }

    private void setCoverImages(Session session) {
        session.addAllCoverImages(coverImageRepository.findBySessionId(session.getId()));
    }

    private Session buildSession(ResultSet rs) throws SQLException {
        return Session.builder()
                .id(rs.getLong(1))
                .courseId(rs.getLong(2))
                .title(rs.getString(3))
                .state(SessionState.valueOf(rs.getString(4)))
                .recruitment(RecruitmentState.valueOf(rs.getString(5)))
                .sessionType(SessionTypeFactory.of(rs.getInt(6), rs.getLong(7)))
                .startDate(rs.getTimestamp(8).toLocalDateTime())
                .endDate(rs.getTimestamp(9).toLocalDateTime())
                .createdAt(rs.getTimestamp(10).toLocalDateTime())
                .build();
    }

    @Override
    public int update(Session session) {
        String sql = "UPDATE class_session " +
                "SET title = ?, state = ?, recruitment = ?, capacity = ?, amount = ?, start_date = ?, end_date = ?, updated_at = ? " +
                "WHERE id = ? AND course_id = ?";
        return jdbcTemplate.update(sql, ps -> {
            ps.setString(1, session.getTitle());
            ps.setString(2, session.getState().name());
            ps.setString(3, session.getRecruitment().name());
            ps.setInt(4, session.getCapacity());
            ps.setLong(5, session.getAmount());
            ps.setTimestamp(6, Timestamp.valueOf(session.getStartDate()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getEndDate()));
            ps.setTimestamp(8, Timestamp.valueOf(session.getUpdatedAt()));

            ps.setLong(9, session.getId());
            ps.setLong(10, session.getCourseId());
        });
    }
}
