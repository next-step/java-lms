package nextstep.courses.infrastructure.session.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.ImageType;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;

public class NsSessionRowMapper implements RowMapper<NsSession> {

    @Override
    public NsSession mapRow(ResultSet rs, int rowNum) throws SQLException {
        CoverImage coverImage = new CoverImage(rs.getLong("image_size"),
                                               rs.getLong("image_width"),
                                               rs.getLong("image_height"),
                                               ImageType.getType(rs.getString("image_type")));
        NsSession nsSession = new NsSession();
        nsSession.setId(rs.getLong("id"));
        nsSession.setCourseId(rs.getLong("course_id"));
        nsSession.setCoverImage(coverImage);
        nsSession.setSessionType(SessionType.getType(rs.getString("type")));
        nsSession.setSessionStatus(SessionStatus.getType(rs.getString("status")));
        nsSession.setStartDate(toLocalDate(rs.getTimestamp(9)));
        nsSession.setEndDate(toLocalDate(rs.getTimestamp(10)));
        nsSession.setQuota(rs.getInt("quota"));
        nsSession.setFee(rs.getInt("fee"));
        nsSession.setCreatedAt(toLocalDateTime(rs.getTimestamp(13)));
        nsSession.setUpdatedAt(toLocalDateTime(rs.getTimestamp(14)));

        return nsSession;
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
