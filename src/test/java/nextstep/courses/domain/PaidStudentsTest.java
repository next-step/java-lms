package nextstep.courses.domain;

import nextstep.courses.exception.SessionFullException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaidStudentsTest {

    @Test
    @DisplayName("수강생 수를 올릴 수 있다")
    void add() {
        PaidStudents actual = new PaidStudents(1);
        actual.add(new NsUser());
        PaidStudents expected = new PaidStudents(1, List.of(new NsUser()));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("모집 인원이 초과하면 신청 할 수 없다.")
    void add2() {
        PaidStudents actual = new PaidStudents(0);
        assertThrows(SessionFullException.class, () -> actual.add(new NsUser()), "수강 신청 인원이 마감 되었습니다.");
    }
}
