package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionImage;
import nextstep.courses.domain.repository.ApplicantRepository;
import nextstep.courses.infrastructure.entity.JdbcApplicant;
import nextstep.courses.infrastructure.entity.JdbcCourse;
import nextstep.courses.infrastructure.entity.JdbcSession;
import nextstep.courses.infrastructure.entity.JdbcSessionImage;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcApplicantRepository implements ApplicantRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcApplicantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Applicant applicant) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("applicant")
                .usingGeneratedKeyColumns("id");

        Number number = simpleJdbcInsert.executeAndReturnKey(applicant.getParameters());
        applicant.setId(number.longValue());
        return number.intValue();
    }


    @Override
    public Applicant findById(Long id) {
        String studentSql = "SELECT * FROM applicant WHERE id = ?";
        JdbcApplicant entity = jdbcTemplate.queryForObject(studentSql, new BeanPropertyRowMapper<>(JdbcApplicant.class), id);

        NsUser nsUser = findNsUserById(entity.getNsUserId());
        Session session = findSessionById(entity.getSessionId());

        return entity.toDomain(nsUser, session);
    }

    private NsUser findNsUserById(Long nsUserId) {
        String userSql = "SELECT * FROM ns_user WHERE id = ?";
        NsUserEntity nsUser = jdbcTemplate.queryForObject(userSql, new BeanPropertyRowMapper<>(NsUserEntity.class), nsUserId);
        return nsUser.toDomain();
    }

    private Session findSessionById(Long sessionId) {
        String sql = "SELECT * FROM session WHERE id = ?";
        JdbcSession entity = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcSession.class), sessionId);


        sql = "select * from session_image where session_id = ?";
        List<JdbcSessionImage> images = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(JdbcSessionImage.class), sessionId);
        List<SessionImage> sessionImages = images.stream()
                .map(JdbcSessionImage::toDomain)
                .collect(Collectors.toList());

        sql = "select * from course where id = ?";
        JdbcCourse course = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(JdbcCourse.class), entity.getCourseId());

        return entity.toDomain(course.toDomain(), sessionImages);
    }

}
