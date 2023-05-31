package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    private final static String APPLY_ERROR_MESSAGE = "모집 중일때만 수강신청이 가능합니다.";

    @Test
    @DisplayName("모집중일때 수강신청 가능")
    void test01() {
        //given
        Capacity capacity = new Capacity(9, 10);
        CoverImage coverImage = new CoverImage(1L, "강의1사진", "http://");
        SessionStatus sessionStatus = SessionStatus.RECRUTING;
        Session session = new Session(1L, "강의1", "20230531", "20230605", capacity, coverImage, SessionType.FREE, sessionStatus, 1L);

        //when
        session.apply();

        //then
        Assertions.assertThat(session.getCapacity().getCurCapacity()).isEqualTo(10);

    }

    @Test
    @DisplayName("준비중일때 수강신청 불가능")
    void test02() {
        //given
        Capacity capacity = new Capacity(9, 10);
        CoverImage coverImage = new CoverImage(1L, "강의1사진", "http://");
        SessionStatus sessionStatus = SessionStatus.PREPARING;
        Session session = new Session(1L, "강의1", "20230531", "20230605", capacity, coverImage, SessionType.FREE, sessionStatus, 1L);

        //when //then
        Assertions.assertThatThrownBy(session::apply)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(APPLY_ERROR_MESSAGE);

    }

    @Test
    @DisplayName("종료중일때 수강신청 불가능")
    void test03() {
        //given
        Capacity capacity = new Capacity(9, 10);
        CoverImage coverImage = new CoverImage(1L, "강의1사진", "http://");
        SessionStatus sessionStatus = SessionStatus.COMPLETED;
        Session session = new Session(1L, "강의1", "20230531", "20230605", capacity, coverImage, SessionType.FREE, sessionStatus, 1L);

        //when //then
        Assertions.assertThatThrownBy(session::apply)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(APPLY_ERROR_MESSAGE);

    }

}
