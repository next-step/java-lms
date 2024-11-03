package nextstep.courses.infrastructure;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.session.Category;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageSize;
import nextstep.courses.domain.session.image.ImageType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static nextstep.courses.infrastructure.util.LocalDateTimeFormatter.toLocalDateTime;

public class JdbcSessionRepository implements SessionRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int save(PaidSession session) {
        String sql = "insert into session (" +
                "id, course_id, category, start_at, end_at, " +
                "cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, status, " +
                "max_register_count, amount, creator_id, created_at " +
                ") " +
                "values(" +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, ? " +
                ")";
        Category category = session.getCategory();
        DateRange dateRange = session.getDateRange();
        CoverImage coverImage = session.getCoverImage();
        ImageFileSize imageFileSize = coverImage.getImageFileSize();
        ImageSize imageSize = coverImage.getImageSize();
        ImageType imageType = coverImage.getImageType();
        Status status = session.getStatus();
        return jdbcTemplate.update(sql,
                session.getId(), session.getCourseId(), category.name(), dateRange.getStartAt(), dateRange.getEndAt(),
                imageFileSize.getSize(), imageType.name(), imageSize.getWidth(), imageSize.getHeight(), status.name(),
                session.getMaxRegisterCount(), session.getAmount(), session.getCreatorId(), session.getCreatedAt()
        );
    }

    @Override
    public PaidSession findPaidById(long sessionId) {
        String sql = "select id, course_id, category, start_at, end_at, cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, status, max_register_count, amount, creator_id, created_at, updated_at " +
                "from session " +
                "where id = ? ";
        RowMapper<PaidSession> rowMapper = (rs, rowNum) -> new PaidSession(
                rs.getLong("id"),
                rs.getLong("course_id"),
                Category.valueOf(rs.getString("category")),
                new DateRange(
                        toLocalDateTime(rs.getTimestamp("start_at")),
                        toLocalDateTime(rs.getTimestamp("end_at"))
                ),
                new CoverImage(
                        rs.getInt("cover_image_file_size"),
                        rs.getString("cover_image_type"),
                        rs.getInt("cover_image_width"),
                        rs.getInt("cover_image_height")
                ),
                Status.valueOf(rs.getString("status")),
                rs.getInt("max_register_count"),
                rs.getInt("amount"),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public int save(FreeSession session) {
        String sql = "insert into session (" +
                "id, course_id, category, start_at, end_at, " +
                "cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, status, " +
                "creator_id, created_at " +
                ") " +
                "values(" +
                "?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, " +
                "?, ? " +
                ")";
        Category category = session.getCategory();
        DateRange dateRange = session.getDateRange();
        CoverImage coverImage = session.getCoverImage();
        ImageFileSize imageFileSize = coverImage.getImageFileSize();
        ImageSize imageSize = coverImage.getImageSize();
        ImageType imageType = coverImage.getImageType();
        Status status = session.getStatus();
        return jdbcTemplate.update(sql,
                session.getId(), session.getCourseId(), category.name(), dateRange.getStartAt(), dateRange.getEndAt(),
                imageFileSize.getSize(), imageType.name(), imageSize.getWidth(), imageSize.getHeight(), status.name(),
                session.getCreatorId(), session.getCreatedAt()
        );
    }

    @Override
    public FreeSession findFreeById(long sessionId) {
        String sql = "select id, course_id, category, start_at, end_at, cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, status, creator_id, created_at, updated_at " +
                "from session " +
                "where id = ? ";
        RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
                rs.getLong("id"),
                rs.getLong("course_id"),
                Category.valueOf(rs.getString("category")),
                new DateRange(
                        toLocalDateTime(rs.getTimestamp("start_at")),
                        toLocalDateTime(rs.getTimestamp("end_at"))
                ),
                new CoverImage(
                        rs.getInt("cover_image_file_size"),
                        rs.getString("cover_image_type"),
                        rs.getInt("cover_image_width"),
                        rs.getInt("cover_image_height")
                ),
                Status.valueOf(rs.getString("status")),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

}
