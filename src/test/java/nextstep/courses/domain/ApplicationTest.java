package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApplicationTest {
    @Test
    void 수강신청_생성자_테스트() {
        assertThat(new Application(1L, 1L)).isInstanceOf(Application.class);
    }

    @Test
    void 수강신청_승인_테스트() {
        Application application = new Application(1L, 1L);
        assertThat(application.approve().getStatus()).isEqualTo(ApplicationStatus.APPROVED);
    }

    @Test
    void 수강신청_승인_실패_테스트_NOT_PENDING() {
        Application application = new Application(1L, 1L);
        assertThatThrownBy(() -> application.approve().approve())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("승인할 수 없는 상태입니다.");
    }

    @Test
    void 수강신청_거절_테스트() {
        Application application = new Application(1L, 1L);
        assertThat(application.reject().getStatus()).isEqualTo(ApplicationStatus.REJECTED);
    }

    @Test
    void 수강신청_거절_실패_테스트_NOT_PENDING() {
        Application application = new Application(1L, 1L);
        assertThatThrownBy(() -> application.reject().reject())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("거절할 수 없는 상태입니다.");
    }
}
