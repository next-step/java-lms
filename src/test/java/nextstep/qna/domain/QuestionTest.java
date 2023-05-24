package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {

    private Question question;
    private Answers answers;
    private Answer answer;


    @BeforeEach
    public void initialize() {
        question = Question.of(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        answers = Answers.of(List.of(answer));
        question.loadAnswers(answers);
    }

    @Test
    @DisplayName("Question 객체 생성 테스트")
    void Question_객체_생성_테스트() {

        Question question = Question.of(1L, NsUserTest.JAVAJIGI, "title1", "contents1", answers, null);

        assertAll(
                () -> assertThat(question).isNotNull(),
                () -> assertThat(question).isEqualTo(this.question)
        );

    }

    @Test
    @DisplayName("Question 삭제시 로그인 유저의 정보를 알수 없을 경우 IllegalStateException 예외를 던진다")
    void 질문_삭제시_로그인유저의_정보가_유효하지_않은경우() {
        Throwable exception = Assertions.assertThrows(IllegalStateException.class, () -> question.remove(null));
        assertEquals("인가 되지 않은 사용자 에요 :(", exception.getMessage());

    }

    @Test
    @DisplayName("Question 삭제시 로그인한 유저의 글이 아닌 경우  경우 CannotDeleteExceptin 예외를 던진다")
    void 질문_삭제시_로그인유저의_질문이_아닌_경우() {
        Throwable exception = Assertions.assertThrows(CannotDeleteException.class, () -> question.remove(NsUserTest.SANJIGI));
        assertEquals("글 작성자만 삭제 가능해요 :(", exception.getMessage());
    }

    @Test
    @DisplayName("Question 삭제시 다른 유저의 답글이 달린 경우  경우 CannotDeleteExceptin 예외를 던진다")
    void 질문_삭제시_다른유저의_답글이_달린_경우() {

        Answer answer = Answer.of(NsUserTest.SANJIGI, question, "Answers Contents1");
        answers = Answers.of(List.of(answer));
        question.loadAnswers(answers);

        Throwable exception = Assertions.assertThrows(CannotDeleteException.class, () -> question.remove(NsUserTest.JAVAJIGI));
        assertEquals("다른분이 작성한 답변글이 존재해서 삭제 불가능 해요 :(", exception.getMessage());

    }


    @Test
    @DisplayName("Question 삭제시 답글이 질문을 작성한 사람의 글만 있을 경우 예외를 던지지 않는다")
    void 질문자와_모든_답변자가_같은_경우() {
        Assertions.assertDoesNotThrow(() -> question.remove(NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("Question 삭제시 답글이 질문을 작성한 사람의 글만 있을 경우 삭제 상태(deleted - boolean type)로 변경되는지 확인 ")
    void 답변이_있는_경우_삭제() {
        question.remove(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("Question 삭제시 답글이 없는경우 삭제 상태(deleted - boolean type)로 변경되는지 확인 ")
    void 답변이_없는_경우_삭제() {

        question.loadAnswers(null);
        question.remove(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();


        question.loadAnswers(Answers.create());
        question.remove(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }


    @Test
    @DisplayName("질문 삭제시 삭제 이력이 반환되는지 확인 (답변이 없는경우)")
    void 삭제_이력_답변이_없는경우() {

        question.loadAnswers(Answers.create());

        DeleteHistory deleteHistory = DeleteHistory.of(ContentType.QUESTION, question.getId(), question.getWriter());


        DeleteHistories deleteHistories = question.remove(NsUserTest.JAVAJIGI);


        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistory);
    }

    @Test
    @DisplayName("질문 삭제시 삭제 이력이 반환되는지 확인 (답변이 있는경우)")
    void 삭제_이력_답변이_있는경우() {

        DeleteHistory deleteHistoryOfQuestion = DeleteHistory.of(ContentType.QUESTION, question.getId(), question.getWriter());
        DeleteHistory deleteHistoryOfAnswer = DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter());

        DeleteHistories deleteHistories = question.remove(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistoryOfQuestion, deleteHistoryOfAnswer);
    }


}
