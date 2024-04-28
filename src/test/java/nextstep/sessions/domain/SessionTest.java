package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.sessions.domain.builder.SessionRegisterDetailsBuilder;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private Session tddCleanCodeJava;

    @BeforeEach
    void setUp() {
        tddCleanCodeJava = new SessionBuilder()
                .withSessionName("TDD, CleanCode")
                .withSessionRegisterDetails(new SessionRegisterDetailsBuilder().withPrice(new Price(30000L)).build())
                .build();
    }

    @Test
    void enroll() {
        List<Student> students = new ArrayList<>();
        Payment payment = new Payment("javajigi", 1L, 1L, 30000L);
        Session tddCleanCode = new SessionBuilder()
                .withSessionName("TDD, CleanCode")
                .withSessionRegisterDetails(new SessionRegisterDetailsBuilder().withPrice(new Price(30000L)).build())
                .build();

        Student student = tddCleanCode.enroll(NsUserTest.JAVAJIGI, students, payment);
        assertThat(student).isEqualTo(new Student(NsUserTest.JAVAJIGI.getId(), tddCleanCode.getId()));
    }

    @DisplayName("강의의 가격과 결제 금액이 같지 않으면 예외를 반환한다")
    @Test
    void enrollException() {
        Payment payment = new Payment("javajigi", 1L, 1L, 10000L);

        assertThatThrownBy(() -> tddCleanCodeJava.enroll(NsUserTest.JAVAJIGI, new ArrayList<>(), payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제한 금액이 강의의 가격과 일치하지 않습니다.");
    }

}
