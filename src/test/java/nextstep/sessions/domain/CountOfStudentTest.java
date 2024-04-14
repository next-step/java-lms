package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CountOfStudentTest {

    @DisplayName("현재 수강신청인원에서 수강 인원 한명을 추가했을 때, 최대 수강인원을 초과하면 예외를 반환한다")
    @Test
    void increaseCountOfStudentsException() {
        CountOfStudent countOfStudent = new CountOfStudent(3, 3, SessionType.PAID);

        Assertions.assertThatThrownBy(countOfStudent::increaseCountOfStudents)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", 4, 3));

    }
}
