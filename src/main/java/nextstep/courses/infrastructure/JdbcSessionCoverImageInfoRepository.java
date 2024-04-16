package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository("SessionCoverImageInfoRepository")
public class JdbcSessionCoverImageInfoRepository implements SessionCoverImageInfoRepository {
    private final JdbcOperations jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcSessionCoverImageInfoRepository(JdbcOperations jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("session_cover_image_info")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Long saveAndGetId(SessionCoverImageInfo sessionCoverImageInfo) {
        Number numberKey = simpleJdbcInsert.executeAndReturnKey(Map.of(
                "session_id", sessionCoverImageInfo.getSessionId(),
                "cover_image_info_id", sessionCoverImageInfo.getCoverImageInfoId()
        ));

        return numberKey.longValue();
    }
}
