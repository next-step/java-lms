package nextstep.courses.infrastructure.enrollment.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import nextstep.courses.domain.entity.Enrollment;
import nextstep.courses.domain.entity.NsCourse;
import nextstep.courses.domain.entity.NsSession;
import nextstep.users.domain.NsUser;

public class EnrollmentRowMapper implements RowMapper<Enrollment> {

    @Override
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setNsCourse(new NsCourse());
        enrollment.setNsUser(new NsUser());
        enrollment.setNsSession(new NsSession());
        enrollment.getNsCourse().setId(rs.getLong("course_id"));
        enrollment.getNsUser().setId(rs.getLong("user_id"));
        enrollment.getNsSession().setId(rs.getLong("session_id"));
        enrollment.setCreatedAt(toLocalDateTime(rs.getTimestamp(4)));
        enrollment.setUpdatedAt(toLocalDateTime(rs.getTimestamp(5)));

        return enrollment;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
