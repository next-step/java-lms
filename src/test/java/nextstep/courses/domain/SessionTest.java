package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.constant.SessionTypeEnum;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class SessionTest {
    private Session atdd;
    private Session tdd;

    @BeforeEach
    void setUp() {
        atdd = new Session(
                LocalDateTime.now().plusDays(2),
                SessionTypeEnum.FREE,
                1,
                ImageTest.IMAGE);
        tdd = new Session(
                LocalDateTime.now().plusDays(3),
                SessionTypeEnum.PAID,
                10000,
                1,
                ImageTest.IMAGE);
    }

    /**
     * 1. 무료 강의를 생성한다.
     * 2. 강의를 오픈한다.
     * 3. 수강 신청에 따라 강의에 수강 인원을 추가한다.
     */
    @Test
    void enrollment_무료_강의() {
        atdd.open();
        atdd.enroll(NsUserTest.JAVAJIGI, 0);
        Assertions.assertThat(atdd.getEnrolledCount()).isEqualTo(1);
    }

    /**
     * 1. 유료 강의를 생성한다.
     * 2. 강의를 오픈한다.
     * 3. 수강 신청에 따라 강의에 수강 인원을 추가한다.
     */
    @Test
    void enrollment_유료_강의() {
        tdd.open();
        tdd.enroll(NsUserTest.JAVAJIGI, 10000);
        Assertions.assertThat(tdd.getEnrolledCount()).isEqualTo(1);
    }

    /**
     * 1. 유료 강의를 생성한다.
     * 2. 강의를 오픈한다.
     * 3. 수강 신청에 따라 강의에 수강 인원을 추가한다.
     * 4. 수강 인원이 초과하여 수강 인원을 등록할 수 없다.
     */
    @Test
    void enrollment_유료_강의_수강인원_초과() {
        tdd.open();
        tdd.enroll(NsUserTest.JAVAJIGI, 10000);
        Assertions.assertThatThrownBy(() -> tdd.enroll(NsUserTest.SANJIGI, 10000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * 1. 유료 강의를 생성한다.
     * 2. 강의를 오픈한다.
     * 3. 수강 신청에 따라 강의에 수강 인원을 추가한다.
     * 4. 결제 금액이 수강료보다 작아 수강 신청을 할 수 없다.
     */
    @Test
    void enrollment_유료_강의_금액_부족() {
        tdd.open();
        Assertions.assertThatThrownBy(() -> tdd.enroll(NsUserTest.SANJIGI, 9000))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
