package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nextstep.courses.domain.Cohort;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.session.CohortRepository;
import nextstep.courses.domain.session.Session;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcCohortRepository implements CohortRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCohortRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Cohort cohort) {
        String cohortInsertSql = "insert into cohort(course_id, title) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int cohortInsertCount = jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(cohortInsertSql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, cohort.course().id());
            ps.setString(2, cohort.title());
            return ps;
        }, keyHolder);

        String cohortSessionsInsertSql = "insert into cohort_sessions(cohort_id, sessions_id) values(?, ?)";
        List<Session> sessions = cohort.sessions();
        int cohortSessionsInsertCount = IntStream.of(jdbcTemplate.batchUpdate(cohortSessionsInsertSql, sessions.stream()
            .map(session -> List.of(keyHolder.getKey().longValue(), session.id()).toArray())
            .collect(Collectors.toList()))).sum();
        return cohortInsertCount + cohortSessionsInsertCount;
    }

    @Override
    public Cohort findById(Long id) {
        String cohortSessionsIdListSql = "select sessions_id from cohort_sessions where cohort_id = ?";
        List<Long> sessionIdList = jdbcTemplate.query(cohortSessionsIdListSql,
            (rs, rowNum) -> rs.getLong(1), id);

        List<Session> sessionList = sessionIdList.stream()
            .map(sessionId -> new JdbcSessionRepository(jdbcTemplate).findById(sessionId))
            .collect(Collectors.toList());

        String cohortCourseIdSql = "select course_id from cohort where id = ?";
        Optional<Long> courseId = jdbcTemplate.query(cohortCourseIdSql, (rs, rowNum) -> rs.getLong(1), id)
            .stream()
            .findAny();
        if (courseId.isEmpty()) {
            return null;
        }
        Course course = new JdbcCourseRepository(jdbcTemplate).findById(courseId.get());

        String cohortSql = "select id, title from cohort where id = ?";
        return jdbcTemplate.query(cohortSql, (rs, rowNum)
                -> Cohort.of(rs.getLong(1), course, rs.getString(2), sessionList), id)
            .stream()
            .findAny()
            .orElse(null);
    }

    @Override
    public int update(Cohort cohort) {
        String updateSql = "update cohort set course_id = ?, title = ? where id = ?";
        int updateCount = jdbcTemplate.update(updateSql, cohort.course().id(), cohort.title(),
            cohort.id());

        String sessionIdListSql = "select sessions_id from cohort_sessions where cohort_id = ?";
        List<Long> sessionIdList = jdbcTemplate.query(sessionIdListSql, (rs, rowNum) -> rs.getLong(1),
            cohort.id());
        List<Session> previousSessionList = sessionIdList.stream()
            .map(sessionId -> new JdbcSessionRepository(jdbcTemplate).findById(sessionId))
            .collect(Collectors.toList());

        List<Session> updatedSessionList = cohort.sessions();

        List<Session> removedSessionList = new ArrayList<>(previousSessionList);
        removedSessionList.removeAll(updatedSessionList);
        List<Session> addedSessionList = new ArrayList<>(updatedSessionList);
        addedSessionList.removeAll(previousSessionList);

        String removedSessionListDeleteSql = "delete from cohort_sessions where cohort_id = ? and sessions_id = ?";
        int[] removedResults = jdbcTemplate.batchUpdate(removedSessionListDeleteSql,
            removedSessionList.stream()
                .map(session -> List.of(cohort.id(), session.id()).toArray())
                .collect(Collectors.toList()));
        int removedCount = IntStream.of(removedResults).sum();

        String addedSessionListInsertSql = "insert into cohort_sessions(cohort_id, sessions_id) values(?, ?)";
        int[] addedResults = jdbcTemplate.batchUpdate(addedSessionListInsertSql,
            addedSessionList.stream()
                .map(session -> List.of(cohort.id(), session.id()).toArray())
                .collect(Collectors.toList()));
        int addedCount = IntStream.of(addedResults).sum();
        return updateCount + removedCount + addedCount;
    }

    @Override
    public int delete(Cohort cohort) {
        String cohortDeleteSql = "delete cohort where id = ?";
        int deleteCohortCount = jdbcTemplate.update(cohortDeleteSql, cohort.id());

        String cohortSessionsDeleteSql = "delete cohort_sessions where cohort_id = ?";
        int deleteCohortSessionsCount = jdbcTemplate.update(cohortSessionsDeleteSql, cohort.id());

        return deleteCohortCount + deleteCohortSessionsCount;
    }
}
