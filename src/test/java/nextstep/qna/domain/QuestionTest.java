package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("질문 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    Question question;
    Answer answer1;
    Answer answer2;
    Answer answer3;

    @BeforeEach
    void init() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    }

    @DisplayName("답변이 없는 경우 질문을 삭제할 수 있다")
    @Test
    void deleteQuestion() throws CannotDeleteException {
        question.deleteQuestion(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("다른 사람의 질문을 삭제 할 수 없다")
    @Test
    void authorizationDeleteQuestion() {
        assertThatThrownBy(() -> {
            question.deleteQuestion(NsUserTest.SANJIGI);
        })
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제 할수 있다")
    @Test
    void deleteQuestionWriterEqualsAllAnswer() throws CannotDeleteException {
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        question.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("질문자와 다른 답변글의 답변자가 있는 경우 삭제 할 수 없다")
    @Test
    void deleteQuestionWriterNotEqualsAllAnswer() throws CannotDeleteException {
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        answer3 = new Answer(NsUserTest.SANJIGI, question, "answer3");
        question.addAnswer(answer1);
        question.addAnswer(answer3);

        assertThatThrownBy(() -> {
            question.deleteQuestion(NsUserTest.JAVAJIGI);
        })
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문을 삭제하면 답변도 같이 삭제된다")
    @Test
    void deleteQuestionWithAnswer() throws CannotDeleteException {
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        question.addAnswer(answer1);

        question.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
    }

    @DisplayName("질문을 삭제하면 삭제 이력이 저장된다")
    @Test
    void deleteQuestionHistory() throws CannotDeleteException {
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        List<DeleteHistory> deleteHistories = question.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories.size()).isEqualTo(3);
    }
}
