package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        // given
        Image image = new Image(100.0f, "png", "https://example.com/image.png", 300, 200);
        Images images = new Images(List.of(image));
        Session session = new Session(
                "도메인 주도 설계",
                0, // 저장 전에 id는 무의미함
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(30),
                10000L,
                0,
                20,
                images,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING
        );

        // when
        int savedSessionId = sessionRepository.save(session, 1L); // courseId = 1L
        Session saved = sessionRepository.findById((long) savedSessionId);

        // then
        assertThat(saved.getTitle()).isEqualTo(session.getTitle());
        assertThat(saved.getTuition()).isEqualTo(session.getTuition());
        assertThat(saved.getCapacity()).isEqualTo(session.getCapacity());

        // 이미지 검증
        List<Image> savedImages = saved.getCoverImages().getImages();
        assertThat(savedImages).hasSize(1);

        Image savedImage = savedImages.get(0);
        assertThat(savedImage.getImageUrl()).isEqualTo(image.getImageUrl());
        assertThat(savedImage.getSize()).isEqualTo(image.getSize());
        assertThat(savedImage.getType()).isEqualTo(image.getType());
        assertThat(savedImage.getWidth()).isEqualTo(image.getWidth());
        assertThat(savedImage.getHeight()).isEqualTo(image.getHeight());

        LOGGER.debug("Session: {}", saved);
    }
}
