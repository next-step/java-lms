package nextstep.courses.infrastructure;


import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.courses.domain.coverimage.LectureCoverImageMappingRepository;
import nextstep.courses.domain.lectures.LectureEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("LectureCoverImageMappingRepository")
public class JDBCLectureCoverImageMappingRepository implements LectureCoverImageMappingRepository {

  private JdbcOperations jdbcTemplate;

  public JDBCLectureCoverImageMappingRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void save(LectureEntity lecture, CoverImages coverImages) {
    String sql = "insert into cover_image_lecture_mapping (lecture_id, cover_image_id, created_at) values(?,?,?)";
    List<CoverImage> coverImagesList = coverImages.coverImages();
    Long lectureId = lecture.id();
    jdbcTemplate.batchUpdate(sql
        , coverImagesList
        , coverImagesList.size()
        , (PreparedStatement ps, CoverImage coverImage) -> {
          ps.setLong(1, lectureId);
          ps.setLong(2, coverImage.getId());
          ps.setTimestamp(3, Timestamp.valueOf(coverImage.getCreatedAt()));
        });
  }
}
