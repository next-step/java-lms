package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    @Test
    @DisplayName("성공 : 질문 상태값 삭제로 변경")
    void successChangeQuestionStatusDeleteTest() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);
        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(AnswerTest.A1.isDeleted()).isTrue());

    }

    @Test
    @DisplayName("실패 : 질문 상태값 삭제로 변경 - 작성자와 로그인유저 다른 경우 : 질문을 삭제할 권한이 없습니다.")
    void failChangeQuestionStatusDeleteTest() throws CannotDeleteException {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("실패 : 질문 상태값 삭제로 변경 - 질문에 다른사람의 답변이 있는 경우  : 다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.")
    void failChangeQuestionStatusDeleteTest2() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

}
