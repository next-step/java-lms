package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.coverimage.CoverImage;
import nextstep.courses.domain.coverimage.CoverImageType;
import nextstep.courses.domain.coverimage.ImageFileSize;
import nextstep.courses.domain.coverimage.ImageSize;
import nextstep.courses.domain.lectures.Lecture;
import nextstep.courses.domain.lectures.LectureRepository;
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

    @BeforeEach
    void setUp() {
        lectureRepository = new JdbcLectureRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        PaidLecture lecture = new PaidLecture(
            1L
            , "test"
            , CoverImage.defaultOf(1L, "test", CoverImageType.GIF, new ImageFileSize(5),
            new ImageSize(300, 200),
            LocalDateTime.now(), LocalDateTime.now())
            , LectureStatus.PREPARING
            , new RegistrationPeriod(LocalDateTime.now(), LocalDateTime.now().plusMonths(1))
            , new Price(BigDecimal.TEN)
            , 10
        );

        int count = lectureRepository.save(lecture);
        assertThat(count).isEqualTo(1);
        Lecture savedLecture = lectureRepository.findLectureById(1L);
        assertThat(savedLecture.isFree()).isEqualTo(lecture.getTitle());
        LOGGER.debug("Course: {}", savedLecture);
    }

}
