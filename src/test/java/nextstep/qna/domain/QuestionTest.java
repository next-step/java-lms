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

    @DisplayName("질문을 삭제한다.")
    @Test
    void 질문을_삭제한다() {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        //when
        question.delete(NsUserTest.JAVAJIGI);
        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문 작성자가 아니라서 삭제할 수 없다.")
    @Test
    void 질문_작성자가_아니라서_삭제할_수_없다() {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        //when
        //then
        assertThatThrownBy(() -> {
            question.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("질문과 답변을 삭제한다.")
    @Test
    void 질문과_답변을_삭제한다() {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);
        //when
        question.delete(NsUserTest.JAVAJIGI);
        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문과 답변을 삭제 할 수 없다.")
    @Test
    void 질문과_답변을_삭제_할_수_없다() {
        //given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer);
        //when
        //then
        assertThatThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }
}
