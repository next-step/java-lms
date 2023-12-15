package nextstep.session.infrastructure;

import nextstep.common.BaseTimeEntity;
import nextstep.image.domain.Image;
import nextstep.image.domain.ImageRepository;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import nextstep.session.domain.Users;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, UserRepository userRepository, ImageRepository imageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (amount, session_type, session_status, image_id, start_at, end_at, number_of_maximum_members) values (?, ?, ?, ?, ?, ?, ?)";
        Long imageId = null;
        if (session.getCoverImage() != null) {
            imageId = session.getCoverImage().getId();
        }

        int sessionId = jdbcTemplate.update(sql, session.getAmount(), session.getSessionType().name(), session.getStatus().name(), imageId, session.getBaseTime().getStartAt(), session.getBaseTime().getEndAt(), session.getMembers().getNumberOfMaximumMembers());
        refreshMembers(session, sessionId);

        return sessionId;
    }

    private void refreshMembers(Session session, int sessionId) {
        String deleteMembersSql = "delete from session_member where session_id = ?";
        jdbcTemplate.update(deleteMembersSql, sessionId);

        String memberSql = "insert into session_member (ns_user_id, session_id) values (?, ?)";
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
        String selectSessionSql = "select id, amount, number_of_maximum_members, session_type, session_status, image_id, start_at, end_at " +
                "from session where id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(
                selectSessionSql,
                (rs, rowNum) -> new Session(
                        rs.getLong(1),
                        members(rs.getInt(3), rs.getLong(1)),
                        rs.getLong(2),
                        SessionType.valueOf(rs.getString(4)),
                        SessionStatus.valueOf(rs.getString(5)),
                        image(rs.getLong(6)),
                        new BaseTimeEntity(
                                toLocalDateTime(rs.getTimestamp(7)),
                                toLocalDateTime(rs.getTimestamp(8)))
                ),
                id));
    }

    private Users members(int numberOfMaximumMembers, long sessionId) {
        String selectMemberIdsSql = "select ns_user_id from session_member where session_id = ?";
        List<Long> memberIds = jdbcTemplate.queryForList(selectMemberIdsSql, Long.class, sessionId);

        List<NsUser> members = userRepository.findAllByIds(memberIds);
        return new Users(numberOfMaximumMembers, new HashSet<>(members));
    }

    private Image image(long imageId) {
        return imageRepository.findById(imageId)
                .orElse(null);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
