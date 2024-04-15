package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.Optional;

public class JdbcSelectionRepository implements SelectionRepository {
    public static final String FIND_BY_ID_SQL = "select id, user_id, session_id, has_paid from selection where id = ?";
    public static final String IS_SELECT_SQL = "select count(id) from selection where user_id = ? and session_id = ?";

    private JdbcOperations jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    public JdbcSelectionRepository(JdbcOperations jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("selection")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Long saveAndGetId(Selection selection) {
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(selection);

        Number number = simpleJdbcInsert.executeAndReturnKey(sqlParameterSource);
        return number.longValue();
    }

    @Override
    public Optional<Selection> findById(Long id) {
        RowMapper<Selection> rowMapper = (rs, rowNum) -> Selection.createFromData(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getLong("session_id"),
                    rs.getBoolean("has_paid")
            );

        return Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID_SQL, rowMapper, id));
    }

    @Override
    public boolean isSelected(Payment payment) {
        return jdbcTemplate.queryForObject(IS_SELECT_SQL, Integer.class, payment.getNsUserId(), payment.getSessionId()) > 0;
    }
}
