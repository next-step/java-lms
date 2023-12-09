package nextstep.courses.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;

public class TestUtil {
    protected void autoincrementReset(JdbcTemplate jdbcTemplate) {
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE image ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE enrollment ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("ALTER TABLE ns_user ALTER COLUMN id RESTART WITH 1");
    }
}
