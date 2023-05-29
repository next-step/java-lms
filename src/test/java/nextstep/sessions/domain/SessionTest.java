package nextstep.sessions.domain;

import nextstep.courses.domain.CourseTest;
import nextstep.images.domain.ImageTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionTest {
    public static final Session S1 = Session.of(1L, CourseTest.C);
    public static final Session S2 = Session.of(2L, CourseTest.C);

    @DisplayName("Session 객체가 잘 생성되는지 확인")
    @Test
    void Session_객체가_정상적으로_생성되는지_확인() {
        assertThat(Session.of(1L, CourseTest.C)).isInstanceOf(Session.class);
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(null, LocalDateTime.now()),
                Arguments.of(LocalDateTime.now(), null),
                Arguments.of(null, null)
        );
    }

    @DisplayName("시작일 또는 종료일의 값이 유효하지 않은경우 IllegalArgumentException 발생하는지 확인")
    @ParameterizedTest(name = "from : {0}, to : {1}")
    @MethodSource("provideArguments")
    void 시작일_또는_종료일의_값이_유효하지_않은경우_IllegalArgumentException(LocalDateTime from, LocalDateTime to) {
        assertThatThrownBy(() -> Session.of(1L, CourseTest.C, from, to))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일 또는 종료일은 null 일 수 없습니다.");
    }

    @DisplayName("시작일이 종료일과 같거나 늦는 경우 IllegalArgumentException 발생하는지 확인")
    @Test
    void 시작일이_종료일과_같거나_늦는_경우_IllegalArgumentException() {
        LocalDateTime now = LocalDateTime.now();

        assertThatThrownBy(() -> Session.of(1L, CourseTest.C, now, now))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("시작일이 종료일과 같거나 이후일 수 없습니다.");
    }

    @DisplayName("강의 생성 시 커버 이미지가 없는경우 default 커버 이미지로 적용되는지 확인")
    @Test
    void 강의_생성_시_커버_이미지가_없는경우_default_커버_이미지로_적용되는지_확인() {
        Session session = Session.of(1L, CourseTest.C);
        assertThat(session.getCoverImage()).isEqualTo(ImageTest.DEFAULT_IMAGE);
    }
}
