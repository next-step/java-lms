package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session, Long courseId) {
        String sql = "INSERT INTO session " +
                "(title, start_date, end_date, tuition, current_count, capacity, " +
                "status, recruitment_status, course_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        var keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, session.getTitle());
            ps.setTimestamp(2, java.sql.Timestamp.valueOf(session.getStartDate()));
            ps.setTimestamp(3, java.sql.Timestamp.valueOf(session.getEndDate()));
            ps.setLong(4, session.getTuition());
            ps.setInt(5, session.getCurrentCount());
            ps.setInt(6, session.getCapacity());
            ps.setString(7, session.getSessionStatus().name());
            ps.setString(8, session.getRecruitmentStatus().name());
            ps.setLong(9, courseId);
            return ps;
        }, keyHolder);

        var generatedId = keyHolder.getKey();
        if (generatedId == null) {
            throw new IllegalStateException("Session 저장 실패 - id 생성 실패");
        }

        int sessionId = generatedId.intValue();

        // 이미지 저장
        for (Image image : session.getCoverImages().getImages()) {
            saveImage(sessionId, image);
        }

        return sessionId;
    }


    @Override
    public void saveImage(int sessionId, Image image){
        String sql = "INSERT INTO image (session_id, url, file_type, file_size, width, height) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                sessionId,
                image.getImageUrl(),
                image.getType(),
                image.getSize(),
                image.getWidth(),
                image.getHeight()
        );
    }


    @Override
    public Session findById(Long id) {
        String sql = "select * from session where id = ?";

        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), id);
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> {
            int sessionId = rs.getInt("id");

            List<Image> images = jdbcTemplate.query(
                    "SELECT * FROM image WHERE session_id = ?",
                    (irs, irow) -> new Image(
                            irs.getLong("id"),
                            irs.getFloat("file_size"),
                            irs.getString("file_type"),
                            irs.getString("url"),
                            irs.getInt("width"),
                            irs.getInt("height")
                    ),
                    sessionId
            );

            return new Session(
                    rs.getString("title"),
                    sessionId,
                    rs.getTimestamp("start_date").toLocalDateTime(),
                    rs.getTimestamp("end_date").toLocalDateTime(),
                    rs.getLong("tuition"),
                    rs.getInt("current_count"),
                    rs.getInt("capacity"),
                    new Images(images),
                    SessionStatus.valueOf(rs.getString("status")),
                    RecruitmentStatus.valueOf(rs.getString("recruitment_status"))
            );
        };
    }
}
