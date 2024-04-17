package nextstep.courses.service;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class SessionServiceTest {
    private static final String SESSION_MUST_HAVE_COVER_IMAGE_MESSAGE = "세션에 하나 이상의 커버 이미지가 등록되어야 합니다.";

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("강의 저장 시 커버 이미지가 0개인 경우 예외 발생")
    void save_non_cover_image_exception() {
        // given
        Session session = preparedPaySession();
        // when
        assertThatThrownBy(() -> sessionService.save(session))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(SESSION_MUST_HAVE_COVER_IMAGE_MESSAGE);
        // then
    }

    @Test
    @DisplayName("강의 저장 시 커버 이미지가 1개 이상인 경우 정상 등록")
    void save() {
        // given
        Session session = preparedPaySession();
        session.addCoverImageInfo(preparedCoverImageInfo());
        // when
        Long savedId = sessionService.save(session);
        // then
        assertThat(savedId).isEqualTo(1L);
    }

    private static CoverImageInfo preparedCoverImageInfo() {
        return CoverImageInfo.createNewInstance(1000L, "jpg", 300L, 200L);
    }

    private static PaySession preparedPaySession() {
        SessionDate sessionDate = SessionDate.of(LocalDateTime.of(2024, 04, 07, 10, 11), LocalDateTime.now());

        return PaySession.createNewInstance(
                new Course(),
                SessionInfos.createWithDefault(sessionDate),
                20,
                20000L
        );
    }
}