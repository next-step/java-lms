package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.Period;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.FreeSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcSessionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Session session, Long courseId) {
        Long imageId = null;
        if (session.getCoverImage() != null) {
            imageId = insertImage(session.getCoverImage());
        }

        String sql = "insert into session (name, type, start_date, end_date, status, capacity, tuition_fee, image_id, course_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                session.getName(),
                session.getType().name(),
                session.getPeriod().getStartDate(),
                session.getPeriod().getEndDate(),
                session.getStatus().name(),
                session instanceof PaidSession ? ((PaidSession) session).getCapacity() : null,
                session instanceof PaidSession ? ((PaidSession) session).getTuitionFee() : null,
                imageId,
                courseId
        );
    }

    public List<Session> findAll() {
        String sql = "select s.*, i.file_name, i.content_type, i.size_in_bytes, i.width, i.height " +
                "from session s left join image i on s.image_id = i.id";
        return jdbcTemplate.query(sql, sessionRowMapper());
    }

    public Session findById(Long id) {
        String sql = "select s.*, i.file_name, i.content_type, i.size_in_bytes, i.width, i.height " +
                "from session s left join image i on s.image_id = i.id where s.id = ?";
        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), id);
    }

    public void update(Session session) {
        String sql = "update session set name = ?, start_date = ?, end_date = ?, status = ?, capacity = ?, tuition_fee = ? where id = ?";
        jdbcTemplate.update(sql,
                session.getName(),
                session.getPeriod().getStartDate(),
                session.getPeriod().getEndDate(),
                session.getStatus().name(),
                session instanceof PaidSession ? ((PaidSession) session).getCapacity() : null,
                session instanceof PaidSession ? ((PaidSession) session).getTuitionFee() : null,
                session.getId()
        );
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("delete from session where id = ?", id);
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
