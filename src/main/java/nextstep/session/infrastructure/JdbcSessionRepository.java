package nextstep.session.infrastructure;

import nextstep.session.domain.Cover;
import nextstep.session.domain.SessionRepository;
import nextstep.session.dto.SessionVO;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.utils.DbTimestampUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    public static final String SESSION_NAME_FIELD = "session_name";
    public static final String START_DATE_FIELD = "start_date";
    public static final String END_DATE_FIELD = "end_date";
    public static final String QUESTION_CONDITION = " = ?, ";
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(SessionVO sessionVO) {
        String sql = "insert into session (start_date, end_date, session_status, course_id, max_capacity, enrolled, price, tutor_id, cover_id, session_name, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, DbTimestampUtils.toTimestamp(sessionVO.getStartDate()));
            ps.setTimestamp(2, DbTimestampUtils.toTimestamp(sessionVO.getEndDate()));
            ps.setString(3, sessionVO.getSessionStatus());
            ps.setLong(4, sessionVO.getCourseId());
            ps.setInt(5, sessionVO.getMaxCapacity());
            ps.setInt(6, sessionVO.getEnrolled());
            ps.setLong(7, sessionVO.getPrice());
            ps.setString(8, sessionVO.getTutorId());
            ps.setLong(9, sessionVO.getCoverId());
            ps.setString(10, sessionVO.getSessionName());
            ps.setBoolean(11, sessionVO.isDeleted());
            ps.setTimestamp(12, DbTimestampUtils.toTimestamp(sessionVO.getCreatedAt()));
            ps.setTimestamp(13, DbTimestampUtils.toTimestamp(sessionVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public SessionVO findById(long sessionId) {
        String sql = "select id, start_date, end_date, session_status, course_id, max_capacity, enrolled, price, tutor_id, cover_id, session_name, deleted, created_at, last_modified_at from session where id = ?";
        RowMapper<SessionVO> rowMapper = (rs, rowNum) -> new SessionVO(
                rs.getLong(1),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(2)),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(3)),
                rs.getString(4),
                rs.getLong(5),
                rs.getInt(6),
                rs.getInt(7),
                rs.getLong(8),
                rs.getString(9),
                rs.getLong(10),
                rs.getString(11),
                rs.getBoolean(12),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(13)),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp(14))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public int updateSessionBasicProperties(SessionVO sessionVO, SessionUpdateBasicPropertiesVO sessionUpdateDto) {
        StringBuilder sql = new StringBuilder("UPDATE session SET ");

        addSetCondition(sessionUpdateDto, sql);

        deleteLastComma(sql);

        sql.append(" WHERE ").append("id").append(" = ?");

        Object[] params = getWhereParams(sessionVO, sessionUpdateDto);

        return jdbcTemplate.update(sql.toString(), params);
    }

    private Object[] getWhereParams(SessionVO sessionVO, SessionUpdateBasicPropertiesVO sessionUpdateDto) {
        Object[] params = new Object[sessionUpdateDto.countNotNullProperties() + 1];
        int i = 0;
        if (sessionUpdateDto.hasSessionName()) {
            params[i++] = sessionUpdateDto.getSessionName();
        }

        if (sessionUpdateDto.hasStartDate()) {
            params[i++] = sessionUpdateDto.getStartDate();
        }

        if (sessionUpdateDto.hasEndDate()) {
            params[i++] = sessionUpdateDto.getEndDate();
        }
        params[i] = sessionVO.getId();
        return params;
    }

    private void addSetCondition(SessionUpdateBasicPropertiesVO sessionUpdateDto, StringBuilder sql) {
        if (sessionUpdateDto.hasSessionName()) {
            sql.append(SESSION_NAME_FIELD).append(QUESTION_CONDITION);
        }

        if (sessionUpdateDto.hasStartDate()) {
            sql.append(START_DATE_FIELD).append(QUESTION_CONDITION);
        }

        if (sessionUpdateDto.hasEndDate()) {
            sql.append(END_DATE_FIELD).append(QUESTION_CONDITION);
        }
    }

    private void deleteLastComma(StringBuilder sql) {
        sql.delete(sql.length() - 2, sql.length());
    }

    @Override
    public int updateCover(SessionVO sessionVO, Cover newCover) {
        String sql = "UPDATE session SET cover_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, newCover.getId(), sessionVO.getId());
    }
}
