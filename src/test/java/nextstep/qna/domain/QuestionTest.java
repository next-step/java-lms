package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("실패 - delete 메서드가 질문을 삭제할 권한이 없을 때 예외가 발생한다.")
    void throwExceptionWhen() {
        Assertions.assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("실패 - delete 메서드가 다른 사람이 쓴 답변이 있을 때 예외가 발생한다.")
    void throwExceptionWhen2() {
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, Q1, "답변1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, Q1, "답변2");
        Answer answer3 = new Answer(NsUserTest.SANJIGI, Q1, "답변3");
        Q1.getAnswers().add(answer1);
        Q1.getAnswers().add(answer2);
        Q1.getAnswers().add(answer3);

        Assertions.assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
