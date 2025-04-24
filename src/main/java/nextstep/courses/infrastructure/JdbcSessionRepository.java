package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.FreeSession;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcSessionRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Transactional
    public void save(Session session, Long courseId) {
        Long imageId = null;
        if (session.getCoverImage() != null) {
            imageId = insertImage(session.getCoverImage());
        }

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("session")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("name", session.getName());
        params.put("type", session.getType().name());
        params.put("start_date", session.getPeriod().getStartDate());
        params.put("end_date", session.getPeriod().getEndDate());
        params.put("status", session.getStatus().name());
        params.put("capacity", session instanceof PaidSession ? ((PaidSession) session).getCapacity().getValue() : null);
        params.put("tuition_fee", session instanceof PaidSession ? ((PaidSession) session).getTuitionFee().getValue() : null);
        params.put("image_id", imageId);
        params.put("course_id", courseId);

        insert.execute(params);
    }

    @Transactional
    public void registerStudent(Long sessionId, Long studentId) {
        String sql = "insert into session_student (session_id, student_id) values (:sessionId, :studentId)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("sessionId", sessionId)
                .addValue("studentId", studentId);
        namedParameterJdbcTemplate.update(sql, params);
    }

    public List<Session> findAll() {
        String sql = "select s.*, i.file_name, i.content_type, i.size_in_bytes, i.width, i.height " +
                "from session s left join image i on s.image_id = i.id";
        List<Session> sessions = namedParameterJdbcTemplate.query(sql, sessionRowMapper());
        
        // 각 세션에 대한 수강생 정보를 함께 조회
        for (Session session : sessions) {
            List<Long> studentIds = findStudentIdsBySessionId(session.getId());
            for (Long studentId : studentIds) {
                session.register(studentId, null);
            }
        }
        return sessions;
    }

    public Session findById(Long id) {
        String sql = "select s.*, i.file_name, i.content_type, i.size_in_bytes, i.width, i.height " +
                "from session s left join image i on s.image_id = i.id where s.id = :id";
        Session session = namedParameterJdbcTemplate.queryForObject(sql, 
            new MapSqlParameterSource("id", id), 
            sessionRowMapper());
            
        if (session != null) {
            // 세션의 수강생 정보를 함께 조회
            List<Long> studentIds = findStudentIdsBySessionId(id);
            for (Long studentId : studentIds) {
                session.register(studentId, null);
            }
        }
        return session;
    }

    @Transactional
    public void update(Session session) {
        String sql = "update session set name = :name, start_date = :startDate, end_date = :endDate, " +
                "status = :status, capacity = :capacity, tuition_fee = :tuitionFee where id = :id";
        
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", session.getName())
                .addValue("startDate", session.getPeriod().getStartDate())
                .addValue("endDate", session.getPeriod().getEndDate())
                .addValue("status", session.getStatus().name())
                .addValue("capacity", session instanceof PaidSession ? ((PaidSession) session).getCapacity().getValue() : null)
                .addValue("tuitionFee", session instanceof PaidSession ? ((PaidSession) session).getTuitionFee().isSameAmount(0) ? 0 : null : null)
                .addValue("id", session.getId());

        namedParameterJdbcTemplate.update(sql, params);
    }

    @Transactional
    public void deleteById(Long id) {
        deleteAllStudentsBySessionId(id);
        String sql = "delete from session where id = :id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private Long insertImage(Image image) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("image")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();
        params.put("file_name", image.getFileName());
        params.put("content_type", image.getContentType());
        params.put("size_in_bytes", image.getSizeInBytes());
        params.put("width", image.getWidth());
        params.put("height", image.getHeight());

        return insert.executeAndReturnKey(params).longValue();
    }

    private List<Long> findStudentIdsBySessionId(Long sessionId) {
        String sql = "select student_id from session_student where session_id = :sessionId";
        return namedParameterJdbcTemplate.query(sql,
                new MapSqlParameterSource("sessionId", sessionId),
                (rs, rowNum) -> rs.getLong("student_id"));
    }

    private void deleteAllStudentsBySessionId(Long sessionId) {
        String sql = "delete from session_student where session_id = :sessionId";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("sessionId", sessionId));
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> {
            SessionStatus status = SessionStatus.valueOf(rs.getString("status"));
            Period period = new Period(rs.getDate("start_date").toLocalDate(), rs.getDate("end_date").toLocalDate());

            Image image = null;
            if (rs.getString("file_name") != null) {
                image = new Image(
                        rs.getString("file_name"),
                        rs.getString("content_type"),
                        rs.getLong("size_in_bytes"),
                        rs.getInt("width"),
                        rs.getInt("height")
                );
            }

            String type = rs.getString("type");
            if (type.equals("FREE")) {
                return new FreeSession(rs.getLong("id"), rs.getString("name"), period, image, status);
            } else {
                return new PaidSession(rs.getLong("id"), rs.getString("name"), period, image, status,
                        rs.getInt("capacity"), rs.getInt("tuition_fee"));
            }
        };
    }
}
