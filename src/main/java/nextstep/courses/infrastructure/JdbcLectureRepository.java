package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImageType;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.courses.domain.coverimage.ImageFileSize;
import nextstep.courses.domain.coverimage.ImageSize;
import nextstep.courses.domain.lectures.LectureEntity;
import nextstep.courses.domain.lectures.LectureRepository;
import nextstep.courses.domain.lectures.LectureRecruitingStatus;
import nextstep.courses.domain.lectures.LectureType;
import nextstep.courses.domain.lectures.RegistrationPeriod;
import nextstep.users.domain.Price;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("LectureRepository")
public class JdbcLectureRepository implements LectureRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcLectureRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(LectureEntity lectureEntity) {
    String sql = "insert into lecture (title, lecture_status, started_at, ended_at, price, limit_student_count, lecture_type, created_at, updated_at) values(?, ?, ?, ?, ? ,? ,? ,? ,?)";
    return jdbcTemplate.update(sql,
        lectureEntity.title(),
        lectureEntity.lectureStatus().getName(),
        lectureEntity.registrationPeriod().getStartedAt(),
        lectureEntity.registrationPeriod().getEndedAt(),
        lectureEntity.price().getPrice(),
        lectureEntity.limitStudentCount(),
        lectureEntity.lectureType().getName(),
        lectureEntity.getCreatedAt(),
        lectureEntity.getUpdatedAt()
    );
  }

  @Override
  public LectureEntity findById(Long id) {
    String sql ="select "
        + "l.id"
        + ", l.title "
        + ", c.id"
        + ", c.name"
        + ", c.cover_image_type"
        + ", c.image_file_size"
        + ", c.width"
        + ", c.height"
        + ", c.created_at"
        + ", c.updated_at"
        + ", l.lecture_type"
        + ", l.lecture_status"
        + ", l.started_at"
        + ", l.ended_at"
        + ", l.price"
        + ", l.limit_student_count"
        + ", l.created_at"
        + ", l.updated_at "
        + "from lecture as l"
        + " inner join cover_image_lecture_mapping as lcim"
            + " on l.id = lcim.lecture_id"
        + " inner join cover_image as c"
        +   " on lcim.cover_image_id = c.id "
        + " where l.id = ?";
    RowMapper<LectureEntity> rowMapper = (rs, rowNum) -> new LectureEntity(
        rs.getLong(1),
        rs.getString(2),
        new CoverImages(
        CoverImage.defaultOf(
            rs.getLong(3)
            , rs.getString(4)
            , CoverImageType.valueOf(rs.getString(5))
            , new ImageFileSize(rs.getLong(6))
            , new ImageSize(rs.getLong(7), rs.getLong(8))
            , toLocalDateTime(rs.getTimestamp(9))
            , toLocalDateTime(rs.getTimestamp(10))
        )),
        LectureType.valueOf(rs.getString(11)),
        LectureRecruitingStatus.valueOf(rs.getString(12)),
        new RegistrationPeriod(
            toLocalDateTime(rs.getTimestamp(13))
            , toLocalDateTime(rs.getTimestamp(14))
        ),
        new Price(rs.getBigDecimal(15)),
        rs.getInt(16),
        toLocalDateTime(rs.getTimestamp(17)),
        toLocalDateTime(rs.getTimestamp(18))
    );
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
