package nextstep.courses.domain.session.recruit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class RecruitmentStatusTest {

    @Test
    void 비모집중에서_모집중으로_전환() {
        RecruitmentStatus notRecruiting = new NotRecruiting();
        RecruitmentStatus recruiting = notRecruiting.open();

        assertThat(recruiting).isInstanceOf(Recruiting.class);
        assertThat(recruiting.canEnroll()).isTrue();
    }

    @Test
    void 모집중에서_비모집중으로_전환() {
        RecruitmentStatus recruiting = new Recruiting();
        RecruitmentStatus notRecruiting = recruiting.close();

        assertThat(notRecruiting).isInstanceOf(NotRecruiting.class);
        assertThat(notRecruiting.canEnroll()).isFalse();
    }

    @Test
    void 모집중일때_open하면_예외() {
        RecruitmentStatus recruiting = new Recruiting();

        assertThatThrownBy(recruiting::open)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("이미 모집중입니다.");
    }

    @Test
    void 비모집중일때_close하면_예외() {
        RecruitmentStatus notRecruiting = new NotRecruiting();

        assertThatThrownBy(notRecruiting::close)
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("모집중이 아닙니다.");
    }

    @Test
    void 모집중일때만_수강신청_가능() {
        assertThat(new Recruiting().canEnroll()).isTrue();
        assertThat(new NotRecruiting().canEnroll()).isFalse();
    }
}