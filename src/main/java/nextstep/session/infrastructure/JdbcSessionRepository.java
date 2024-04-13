package nextstep.session.infrastructure;

import nextstep.common.domain.BaseEntity;
import nextstep.session.domain.*;
import nextstep.session.dto.CoverVO;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.session.dto.SessionVO;
import nextstep.session.dto.StudentVO;
import nextstep.session.type.SessionStatusType;
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

    public static final long FREE_PRICE = 0L;
    public static final String SESSION_NAME_FIELD = "session_name";
    public static final String START_DATE_FIELD = "start_date";
    public static final String END_DATE_FIELD = "end_date";
    public static final String QUESTION_CONDITION = " = ?, ";

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static boolean isFreeSession(long price) {
        return price == FREE_PRICE;
    }

    @Override
    public long save(Session session) {
        String insertSessionQuery = "insert into session (start_date, end_date, session_status, course_id, max_capacity, enrolled, price, tutor_id, session_name, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SessionVO sessionVO = session.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertSessionQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, DbTimestampUtils.toTimestamp(sessionVO.getStartDate()));
            ps.setTimestamp(2, DbTimestampUtils.toTimestamp(sessionVO.getEndDate()));
            ps.setString(3, sessionVO.getSessionStatus());
            ps.setLong(4, sessionVO.getCourseId());
            ps.setInt(5, sessionVO.getMaxCapacity());
            ps.setInt(6, sessionVO.getEnrolled());
            ps.setLong(7, sessionVO.getPrice());
            ps.setString(8, sessionVO.getTutorId());
            ps.setString(9, sessionVO.getSessionName());
            ps.setBoolean(10, sessionVO.isDeleted());
            ps.setTimestamp(11, DbTimestampUtils.toTimestamp(sessionVO.getCreatedAt()));
            ps.setTimestamp(12, DbTimestampUtils.toTimestamp(sessionVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        long sessionKey = Objects.requireNonNull(keyHolder.getKey()).longValue();

        for (Cover cover : session.getCovers().asList()) {
            saveCover(cover, sessionKey);
        }

        return sessionKey;
    }

    private long saveCover(Cover cover, long sessionId) {
        String sql = "insert into cover (session_id, width, height, file_path, file_name, file_extension, byte_size, writer_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        CoverVO coverVO = cover.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, sessionId);
            ps.setInt(2, coverVO.getWidth());
            ps.setInt(3, coverVO.getHeight());
            ps.setString(4, coverVO.getFilePath());
            ps.setString(5, coverVO.getFileName());
            ps.setString(6, coverVO.getFileExtension());
            ps.setLong(7, coverVO.getByteSize());
            ps.setString(8, coverVO.getWriterId());
            ps.setBoolean(9, coverVO.isDeleted());
            ps.setTimestamp(10, DbTimestampUtils.toTimestamp(coverVO.getCreatedAt()));
            ps.setTimestamp(11, DbTimestampUtils.toTimestamp(coverVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Session findById(long sessionId) {
        String findBySessionIdQuery =
                "select s.id as sessionId, " +
                        "s.start_date as startDate, " +
                        "s.end_date as endDate, " +
                        "s.session_status as sessionStatus, " +
                        "s.course_id as courseId, " +
                        "s.max_capacity as maxCapacity, " +
                        "s.enrolled as enrolled, " +
                        "s.price as price, " +
                        "s.tutor_id as tutorId, " +
                        "s.session_name as sessionName, " +
                        "s.deleted as sessionDeleted, " +
                        "s.created_at as sessionCreatedAt, " +
                        "s.last_modified_at as sessionLastModifiedAt " +
                        "from session s " +
                        "where s.id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            Covers covers = findCovers(rs.getLong("sessionId"));

            Students students = findStudentsBySessionId(rs.getLong("sessionId"));

            if (isFreeSession(rs.getLong("price"))) {
                return new FreeSession(
                        rs.getLong("sessionId"),
                        new Duration(
                                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("startDate")),
                                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("endDate"))
                        ),
                        covers,
                        SessionStatus.of(SessionStatusType.valueOf(rs.getString("sessionStatus"))),
                        rs.getString("sessionName"),
                        rs.getLong("courseId"),
                        new Tutor(rs.getString("tutorId")),
                        students,
                        new BaseEntity(
                                rs.getBoolean("sessionDeleted"),
                                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("sessionCreatedAt")),
                                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("sessionLastModifiedAt"))
                        )
                );
            }

            return new PaidSession(
                    rs.getLong("sessionId"),
                    new Duration(
                            DbTimestampUtils.toLocalDateTime(rs.getTimestamp("startDate")),
                            DbTimestampUtils.toLocalDateTime(rs.getTimestamp("endDate"))
                    ),
                    covers,
                    SessionStatus.of(SessionStatusType.valueOf(rs.getString("sessionStatus"))),
                    rs.getString("sessionName"),
                    rs.getLong("courseId"),
                    rs.getInt("maxCapacity"),
                    rs.getInt("enrolled"),
                    rs.getLong("price"),
                    new Tutor(rs.getString("tutorId")),
                    students,
                    new BaseEntity(
                            rs.getBoolean("sessionDeleted"),
                            DbTimestampUtils.toLocalDateTime(rs.getTimestamp("sessionCreatedAt")),
                            DbTimestampUtils.toLocalDateTime(rs.getTimestamp("sessionLastModifiedAt"))
                    )
            );
        };

        return jdbcTemplate.queryForObject(findBySessionIdQuery, rowMapper, sessionId);
    }

    private Covers findCovers(long sessionId) {
        String findListBySessionIdQuery =
                "select id as id, " +
                        "session_id as sessionId, " +
                        "width as width, " +
                        "height as height, " +
                        "file_path as filePath, " +
                        "file_name as fileName, " +
                        "file_extension as fileExtension, " +
                        "byte_size as byteSize, " +
                        "writer_id as writerId, " +
                        "deleted as deleted, " +
                        "created_at as createdAt, " +
                        "last_modified_at as lastModifiedAt " +
                        "from cover " +
                        "where session_id = ? and deleted = false";

        RowMapper<Cover> rowMapper = (rs, rowNum) -> new Cover(
                rs.getLong("id"),
                rs.getLong("sessionId"),
                new Resolution(rs.getInt("width"), rs.getInt("height")),
                new ImageFilePath(rs.getString("filePath"), rs.getString("fileName"), rs.getString("fileExtension")),
                rs.getLong("byteSize"),
                rs.getString("writerId"),
                new BaseEntity(
                        rs.getBoolean("deleted"),
                        DbTimestampUtils.toLocalDateTime(rs.getTimestamp("createdAt")),
                        DbTimestampUtils.toLocalDateTime(rs.getTimestamp("lastModifiedAt"))
                )
        );

        return new Covers(jdbcTemplate.query(findListBySessionIdQuery, new Object[]{sessionId}, rowMapper));
    }

    private Students findStudentsBySessionId(long sessionId) {
        String studentsFindQuery =
                "select id as id, " +
                        "session_id as sessionId, " +
                        "ns_user_id as userId, " +
                        "deleted as deleted, " +
                        "created_at as createdAt, " +
                        "last_modified_at as lastModifiedAt " +
                        "from student " +
                        "where session_id = ? and deleted = false";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getLong("sessionId"),
                rs.getString("userId"),
                rs.getBoolean("deleted"),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("createdAt")),
                DbTimestampUtils.toLocalDateTime(rs.getTimestamp("lastModifiedAt")));

        return new Students(jdbcTemplate.query(studentsFindQuery, new Object[]{sessionId}, rowMapper));
    }

    @Override
    public int updateSessionBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateDto) {
        StringBuilder sql = new StringBuilder("UPDATE session SET ");

        addSetCondition(sessionUpdateDto, sql);

        deleteLastComma(sql);

        sql.append(" WHERE ").append("id").append(" = ?");

        Object[] params = getWhereParams(sessionId, sessionUpdateDto);

        return jdbcTemplate.update(sql.toString(), params);
    }

    private Object[] getWhereParams(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateDto) {
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
        params[i] = sessionId;
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
    public long apply(long sessionId, Student student) {
        String insertSessionQuery = "insert into student (session_id, ns_user_id, deleted, created_at, last_modified_at) values(?, ?, ?, ?, ?)";

        StudentVO studentVO = student.toVO();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(insertSessionQuery, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, studentVO.getSessionId());
            ps.setString(2, studentVO.getUserId());
            ps.setBoolean(3, studentVO.isDeleted());
            ps.setTimestamp(4, DbTimestampUtils.toTimestamp(studentVO.getCreatedAt()));
            ps.setTimestamp(5, DbTimestampUtils.toTimestamp(studentVO.getLastModifiedAt()));
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long unapply(long sessionId, Student student) {
        String updateCoverSql = "update student set deleted = true where ns_user_id = ? and session_id = ?";
        return jdbcTemplate.update(updateCoverSql, student.getUserId(), sessionId);
    }

    @Override
    public void delete(long sessionId) {
        String updateDeleteStatusSessionSql = "update session set deleted = true where id = ?";
        jdbcTemplate.update(updateDeleteStatusSessionSql, sessionId);

        String updateDeleteStatusCoverSql = "update cover set deleted = true where session_id = ?";
        jdbcTemplate.update(updateDeleteStatusCoverSql, sessionId);

        String updateDeleteStatusStudentSql = "update student set deleted = true where session_id = ?";
        jdbcTemplate.update(updateDeleteStatusStudentSql, sessionId);
    }

    @Override
    public long addCover(long sessionId, Cover cover) {
        return saveCover(cover, sessionId);
    }
}
