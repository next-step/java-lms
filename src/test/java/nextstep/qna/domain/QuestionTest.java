package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문 삭제 테스트")
    @Test
    void 질문_삭제_테스트() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문 작성자 여부 판단 하여 삭제 가능 여부 확인.")
    @Test
    void 질문_작성자_여부_확인() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThatThrownBy(() -> {
            question.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("질문과 답변을 삭제 테스트.")
    @Test
    void 질문과_답변을_삭제() {

        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문과_답변을_삭제불가_테스트.")
    @Test
    void 질문과_답변을_삭제불가_테스트() {

        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer);
        assertThatThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }
}