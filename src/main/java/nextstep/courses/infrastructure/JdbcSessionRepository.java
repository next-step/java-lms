package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import nextstep.courses.InvalidTimeRangeException;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Duration;
import nextstep.courses.domain.session.PriceType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.user.Name;
import nextstep.courses.domain.user.User;
import nextstep.courses.domain.user.Users;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into sessions(start_date, end_date, cover_image_id, price_type, status, maximum_capacity) "
            + "values(?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(con -> {
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setTimestamp(1, Timestamp.valueOf(session.duration().startDate()));
            stmt.setTimestamp(2, Timestamp.valueOf(session.duration().endDate()));
            stmt.setLong(3, session.coverImage().id());
            stmt.setString(4, session.priceType().name());
            stmt.setString(5, session.status().name());
            stmt.setLong(6, session.maximumCapacity());
            return stmt;
        }, keyHolder);

        String mapSql = "insert into sessions_users(sessions_id, users_id) values(?, ?)";
        int[] batchUpdate = jdbcTemplate.batchUpdate(mapSql, session.users().users().stream()
            .map(user -> List.of(keyHolder.getKey().longValue(), user.id()).toArray())
            .collect(Collectors.toList())
        );

        return update + IntStream.of(batchUpdate).sum();
    }

    @Override
    public Session findById(Long id) {
        String coverImageIdSql = "select cover_image_id from sessions where id = ?";
        Long coverImageId = jdbcTemplate.query(coverImageIdSql, (rs, rowNum) -> rs.getLong(1), id).stream()
            .findAny()
            .orElse(null);

        String coverImageSql = "select id, image_path from cover_image where id = ?";
        CoverImage coverImage = jdbcTemplate.query(coverImageSql
                , (rs, rowNum) -> CoverImage.of(rs.getLong(1), rs.getString(2)), coverImageId).stream()
            .findAny()
            .orElse(null);

        String userIdListSql = "select users_id from sessions_users where sessions_id = ?";
        List<Long> userIdList = jdbcTemplate.query(userIdListSql, (rs, rowNum) -> rs.getLong(1), id);
        List<User> userList = userListOf(userIdList);

        String sessionSql = "select id, start_date, end_date, price_type, status, maximum_capacity from sessions where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> sessionOf(rs, coverImage, Users.of(userList));
        return jdbcTemplate.query(sessionSql, rowMapper, id).stream()
            .findAny()
            .orElse(null);
    }

    @Override
    public int update(Session session) {
        String sessionSql = "update sessions set start_date = ? "
            + ", end_date = ?"
            + ", cover_image_id = ?"
            + ", price_type = ?"
            + ", status = ?"
            + ", maximum_capacity = ?"
            + " where id = ?";
        int sessionUpdateCount = jdbcTemplate.update(sessionSql, session.duration().startDate(), session.duration().endDate(),
            session.coverImage().id(), session.priceType().name(), session.status().name(),
            session.maximumCapacity(), session.id());

        String previousUserIdListSql = "select users_id from sessions_users where sessions_id = ?";
        List<Long> previousUserIdList = jdbcTemplate.query(previousUserIdListSql,
            (rs, rowNum) -> rs.getLong(1), session.id());

        List<User> previousUserList = userListOf(previousUserIdList);
        List<User> currentUserList = session.users().users();

        List<User> removedUserList = new ArrayList<>(previousUserList);
        removedUserList.removeAll(currentUserList);
        String removedUserSql = "delete from sessions_users where sessions_id = ? and users_id = ?";
        int removedUserCount = IntStream.of(jdbcTemplate.batchUpdate(removedUserSql, removedUserList.stream()
            .map(user -> List.of(session.id(), user.id()).toArray())
            .collect(Collectors.toList())))
            .sum();

        List<User> addedUserList = new ArrayList<>(currentUserList);
        addedUserList.removeAll(previousUserList);
        String addedUserSql = "insert into sessions_users(sessions_id, users_id) values(?, ?)";
        int addedUserCount = IntStream.of(jdbcTemplate.batchUpdate(addedUserSql, addedUserList.stream()
            .map(user -> List.of(session.id(), user.id()).toArray())
            .collect(Collectors.toList())))
            .sum();

        return sessionUpdateCount + removedUserCount + addedUserCount;
    }

    @Override
    public int delete(Session session) {
        String sessionDeleteSql = "delete from sessions where id = ?";
        int sessionDeleteCount = jdbcTemplate.update(sessionDeleteSql, session.id());

        String sessionUserDeleteSql = "delete from sessions_users where sessions_id = ?";
        int sessionUserDeleteCount = jdbcTemplate.update(sessionUserDeleteSql, session.id());
        return sessionDeleteCount + sessionUserDeleteCount;
    }

    private List<User> userListOf(List<Long> userIdList) {
        String sql = "select id, name from users where id = ?";
        return userIdList.stream()
            .map(userId -> jdbcTemplate.query(sql,
                    (rs, rowNum) -> User.of(rs.getLong(1), Name.of(rs.getString(2))), userId)
                .stream().findAny())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    private Session sessionOf(ResultSet rs, CoverImage coverImage, Users users) {
        try {
            return new Session.Builder()
                .id(rs.getLong(1))
                .duration(Duration.of(localDateTimeOf(rs.getTimestamp(2)), localDateTimeOf(rs.getTimestamp(3))))
                .coverImage(coverImage)
                .priceType(PriceType.valueOf(rs.getString(4)))
                .status(Status.valueOf(rs.getString(5)))
                .maximumCapacity(rs.getLong(6))
                .users(users)
                .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidTimeRangeException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

}
