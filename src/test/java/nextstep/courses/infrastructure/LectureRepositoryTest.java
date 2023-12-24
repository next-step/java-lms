package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImageRepository;
import nextstep.courses.domain.coverimage.CoverImageType;
import nextstep.courses.domain.coverimage.CoverImages;
import nextstep.courses.domain.coverimage.ImageFileSize;
import nextstep.courses.domain.coverimage.ImageSize;
import nextstep.courses.domain.coverimage.LectureCoverImageMappingRepository;
import nextstep.courses.domain.lectures.LectureEntity;
import nextstep.courses.domain.lectures.LectureRepository;
import nextstep.courses.domain.lectures.LectureRecruitingStatus;
import nextstep.courses.domain.lectures.LectureStatus;
import nextstep.courses.domain.lectures.PaidLecture;
import nextstep.courses.domain.lectures.RegistrationPeriod;
import nextstep.users.domain.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class LectureRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LectureRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private LectureRepository lectureRepository;
    private CoverImageRepository coverImageRepository;
    private LectureCoverImageMappingRepository lectureCoverImageMappingRepository;

    @BeforeEach
    void setUp() {
        lectureRepository = new JdbcLectureRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
        lectureCoverImageMappingRepository = new JDBCLectureCoverImageMappingRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        CoverImage coverImage = CoverImage.defaultOf(1L,"test", CoverImageType.GIF,new ImageFileSize(50),new ImageSize(300,200),
            LocalDateTime.now() ,null);
        CoverImages coverImages = new CoverImages(coverImage);
        coverImageRepository.saveAll(coverImages);

        PaidLecture lecture = new PaidLecture(
            1L
            , "test"
            , coverImages
            , LectureRecruitingStatus.PREPARING
            , new RegistrationPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1))
            , new Price(BigDecimal.TEN)
            , 10
        );

        lectureCoverImageMappingRepository.save(lecture.toEntity(), coverImages);
        LectureEntity entity = lecture.toEntity();
        int count = lectureRepository.save(entity);
        assertThat(count).isEqualTo(1);
        LectureEntity savedLecture = lectureRepository.findById(1L);
        assertThat(savedLecture).extracting("title").isEqualTo(lecture.getTitle());
        LOGGER.debug("LectureEntity: {}", savedLecture);
    }

}
