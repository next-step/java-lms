package nextstep.session.infrastructure;

import nextstep.common.BaseTimeEntity;
import nextstep.image.domain.Image;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.Users;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (amount, session_type, session_status, image_id, start_at, end_at, number_of_maximum_members) values (?, ?, ?, ?, ?, ?, ?)";
        Long imageId = null;
        if (session.getCoverImage() != null) {
            imageId = session.getCoverImage().getId();
        }

        int sessionId = jdbcTemplate.update(sql, session.getAmount(), session.getSessionType().name(), session.getStatus().name(), imageId, session.getBaseTime().getStartAt(), session.getBaseTime().getEndAt(), session.getMembers().getNumberOfMaximumMembers());
        refreshAttendees(session, sessionId);

        return sessionId;
    }

    private void refreshAttendees(Session session, int sessionId) {
        String deleteAttendeesSql = "delete from session_attendee where session_id = ?";
        jdbcTemplate.update(deleteAttendeesSql, sessionId);

        String memberSql = "insert into session_attendee (ns_user_id, session_id) values (?, ?)";
        List<NsUser> nsUsers = new ArrayList<>(session.getMembers().values());
        jdbcTemplate.batchUpdate(memberSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, nsUsers.get(i).getId());
                ps.setString(2, String.valueOf(sessionId));
            }

            @Override
            public int getBatchSize() {
                return nsUsers.size();
            }
        });
    }

    @Override
    public Optional<Session> findById(Long id) {
        // 세션 조회
        String selectSessionSql = "select * from session where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                findMembers(rs.getInt(8), rs.getLong(1)),
                rs.getLong(2),
                SessionType.valueOf(rs.getString(3)),
                SessionStatus.valueOf(rs.getString(4)),
                null,
                new BaseTimeEntity(
                        toLocalDateTime(rs.getTimestamp(6)),
                        toLocalDateTime(rs.getTimestamp(7)))
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(selectSessionSql, rowMapper, id));
        // 이미지 조회
        // 멤버 전체 조회
    }

    private Users findMembers(int numberOfMaximumMembers, long sessionId) {
        String selectMemberIdsSql = "select ns_user_id from session_attendee where session_id = ?";
        List<Long> memberIds = jdbcTemplate.queryForList(selectMemberIdsSql, Long.class, sessionId);

        /*
         TODO
            List<NsUser> members = userRepository.findAllByIds(memberIds);
            return new Users(members);
         */
        return null;
    }

    private Image findImage(long imageId) {
        /*
        TODO
            return imageRepository.findById(imageId);
         */
        return null;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
