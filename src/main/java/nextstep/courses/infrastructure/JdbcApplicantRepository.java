package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.repository.ApplicantRepository;
import nextstep.courses.infrastructure.entity.JdbcApplicant;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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

        return entity.toDomain(nsUser);
    }

    private NsUser findNsUserById(Long nsUserId) {
        String userSql = "SELECT * FROM ns_user WHERE id = ?";
        NsUserEntity nsUser = jdbcTemplate.queryForObject(userSql, new BeanPropertyRowMapper<>(NsUserEntity.class), nsUserId);
        return nsUser.toDomain();
    }

}
