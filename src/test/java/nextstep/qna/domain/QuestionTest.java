package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문 삭제 시 질문한 사람과 로그인한 사람이 같지 않은 경우 예외가 발생하는지")
    @Test
    void deleteTest_WhenUserIsNotOwner() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제 시 다른 사람의 답변이 있는 경우 예외가 발생하는지")
    @Test
    void deleteTest_WhenAnswersNotEmpty() {
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents1"));
        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
