package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    @DisplayName("작성자 확인 로직 테스트")
    public void isOwnerTest() {
        assertThat(Q1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(Q1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    @DisplayName("이미 삭제된 질문 삭제 요청 테스트")
    public void validateDeleteDeletedQuestionTest() {
        Q1.setDeleted(true);

        assertThatThrownBy(() -> Q1.validateDelete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("이미 삭제된 질문입니다.");

        Q1.setDeleted(false);
    }

    @Test
    @DisplayName("다른 작성자의 질문 삭제하기 테스트")
    public void validateDeleteNotOwnerTest() {
        assertThatThrownBy(() -> Q1.validateDelete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("타인의 답변이 달린 질문 삭제 테스트")
    public void validateDeleteWithOtherWriterAnswerTest() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.validateDelete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }






}
