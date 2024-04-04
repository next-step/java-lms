package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    public static final Answers answers1 = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    @DisplayName("현재 로그인 계정과 다른 답변 작성자가 있다면 예외가 발생한다.")
    @Test
    void test01() {
        assertThatThrownBy(() -> answers1.deleteBy(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("현재 로그인 계정과 다른 답변 작성자가 있습니다.");
    }
}
