package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문과 답변들의 유저가 동일하면 질문을 삭제할 수 있다.")
    @Test
    void deleteSuccessTest() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문과 답변들의 유저가 다르면 CannotDeleteException을 던진다.")
    @Test
    void deleteFailTest() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "Answers Contents1"));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문을 삭제하면 해당 질문과 답변들에 대한 History 리스트 객체를 반환한다.")
    @Test
    void deleteTest() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).isInstanceOf(List.class);
        assertThat(deleteHistories.get(0)).isInstanceOf(DeleteHistory.class);
        assertThat(question.isDeleted()).isTrue();
    }
}
