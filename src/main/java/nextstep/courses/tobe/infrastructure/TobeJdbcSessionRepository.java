package nextstep.courses.tobe.infrastructure;

import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.tobe.domain.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static nextstep.courses.infrastructure.util.LocalDateTimeFormatter.toLocalDateTime;

public class TobeJdbcSessionRepository implements TobeSessionRepository {
    private final JdbcTemplate jdbcTemplate;

    public TobeJdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int save(TobePaidSession session) {
        String sql = "insert into tobe_session (" +
                "id, course_id, category, start_at, end_at, " +
                "instructor_id, process_status, recruitment_status, " +
                "max_register_count, amount, creator_id, created_at " +
                ") " +
                "values(" +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, " +
                "?, ?, ?, ? " +
                ")";
        Category category = session.getCategory();
        DateRange dateRange = session.getDateRange();
        ProcessStatus processStatus = session.getProcessStatus();
        RecruitmentStatus recruitmentStatus = session.getRecruitmentStatus();
        return jdbcTemplate.update(sql,
                session.getId(), session.getCourseId(), category.name(), dateRange.getStartAt(), dateRange.getEndAt(),
                session.getInstructorId(), processStatus.name(), recruitmentStatus.name(),
                session.getMaxRegisterCount(), session.getAmount(), session.getCreatorId(), session.getCreatedAt()
        );
    }

    @Override
    public TobePaidSession findPaidById(long sessionId) {
        String sql = "select id, course_id, start_at, end_at, instructor_id, process_status, recruitment_status, max_register_count, amount, creator_id, created_at, updated_at " +
                "from tobe_session " +
                "where id = ? ";
        RowMapper<TobePaidSession> rowMapper = (rs, rowNum) -> new TobePaidSession(
                rs.getLong("id"),
                rs.getLong("course_id"),
                new DateRange(
                        toLocalDateTime(rs.getTimestamp("start_at")),
                        toLocalDateTime(rs.getTimestamp("end_at"))
                ),
                rs.getLong("instructor_id"),
                ProcessStatus.valueOf(rs.getString("process_status")),
                RecruitmentStatus.valueOf(rs.getString("recruitment_status")),
                rs.getInt("max_register_count"),
                rs.getInt("amount"),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public int save(TobeFreeSession session) {
        String sql = "insert into tobe_session (" +
                "id, course_id, category, start_at, end_at, " +
                "instructor_id, process_status, recruitment_status, " +
                "creator_id, created_at " +
                ") " +
                "values(" +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, " +
                "?, ? " +
                ")";
        Category category = session.getCategory();
        DateRange dateRange = session.getDateRange();
        ProcessStatus processStatus = session.getProcessStatus();
        RecruitmentStatus recruitmentStatus = session.getRecruitmentStatus();
        return jdbcTemplate.update(sql,
                session.getId(), session.getCourseId(), category.name(), dateRange.getStartAt(), dateRange.getEndAt(),
                session.getInstructorId(), processStatus.name(), recruitmentStatus.name(),
                session.getCreatorId(), session.getCreatedAt()
        );
    }

    @Override
    public TobeFreeSession findFreeById(long sessionId) {
        String sql = "select id, course_id, start_at, end_at, instructor_id, process_status, recruitment_status, creator_id, created_at, updated_at " +
                "from tobe_session " +
                "where id = ? ";

        RowMapper<TobeFreeSession> rowMapper = (rs, rowNum) -> new TobeFreeSession(
                rs.getLong("id"),
                rs.getLong("course_id"),
                new DateRange(
                        toLocalDateTime(rs.getTimestamp("start_at")),
                        toLocalDateTime(rs.getTimestamp("end_at"))
                ),
                List.of(),
                rs.getLong("instructor_id"),
                ProcessStatus.valueOf(rs.getString("process_status")),
                RecruitmentStatus.valueOf(rs.getString("recruitment_status")),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

}
