package nextstep.courses.infrastructure.session;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.infrastructure.session.mapper.NsSessionRowMapper;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(NsSession nsSession) {
        String sql = "insert into nssession (course_id, image_size, image_width, image_height, image_type, type, status, start_date, end_date, quota, fee, created_at, updated_at) " +
                     "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                                   nsSession.getCourseId(),
                                   nsSession.getCoverImage().getSize(),
                                   nsSession.getCoverImage().getWidth(),
                                   nsSession.getCoverImage().getHeight(),
                                   nsSession.getCoverImage().getImageType().getExtension(),
                                   nsSession.getSessionType().getType(),
                                   nsSession.getSessionStatus().getType(),
                                   nsSession.getStartDate(),
                                   nsSession.getEndDate(),
                                   nsSession.getQuota(),
                                   nsSession.getFee(),
                                   nsSession.getCreatedAt(),
                                   nsSession.getUpdatedAt());
    }

    @Override
    public NsSession findById(Long id) {
        String sql = "select id, course_id, image_size, image_height, image_type, image_width, type, status, start_date, end_date, quota, fee, created_at, updated_at from nssession where id = ?";
        return jdbcTemplate.queryForObject(sql, new NsSessionRowMapper(), id);
    }
}
