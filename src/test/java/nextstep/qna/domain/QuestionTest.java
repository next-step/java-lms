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
    @DisplayName("질문자와 로그인 유저가 같을 경우 삭제를 성공한다")
    void deleteQuestion() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        // when
        question.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 로그인 유저가 다를 경우 삭제를 실패한다")
    void deleteQuestionFail() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        // when
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .message().isEqualTo("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("다른 사람의 답변이 포함된 질문은 삭제할 수 없다")
    void deleteQuestionFailWithAnswer() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.SANJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        // when
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .message().isEqualTo("자기 자신의 답변만 삭제할 수 있습니다.");
    }

    @Test
    @DisplayName("답변이 모두 질문자의 것일 경우 삭제를 성공한다")
    void updateQuestion() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        question.addAnswer(answer);

        // when
        question.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 모두 질문자의 것일경우 답변전체 삭제를 성공한다")
    void deleteQuestionWithAllAnswers() {
        // given
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answer answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");
        Answer answer3 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents3");
        question.addAnswer(answer1);
        question.addAnswer(answer2);
        question.addAnswer(answer3);

        // when
        question.getAnswersObj().deleteAll(NsUserTest.JAVAJIGI);


        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(answer3.isDeleted()).isTrue();
    }
}
