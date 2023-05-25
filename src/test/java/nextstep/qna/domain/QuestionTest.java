package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {

    private Question question;
    private Answers answers;
    private Answer answer;


    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
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
    @DisplayName("Question 객체 생성 테스트 작성자가 null 인경우")
    void Question_객체_생성_테스트_작성자_null() {


        Throwable exception = Assertions.assertThrows(UnAuthorizedException.class, () -> Question.of(null, "title1", "contents1"));
        assertEquals("작성자에 값이 입력되질 않았어요 :(", exception.getMessage());
    }

    @Test
    @DisplayName("Question 객체 생성 테스트 id가 0 인경우")
    void Question_객체_생성_테스트_아이디가_0() {


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Question.of(0, NsUserTest.JAVAJIGI, "title1", "contents1", null));
        assertEquals("유요하지 않는 아이디에요 :( [ 입력 값 : 0]", exception.getMessage());
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
        assertEquals("글 작성자만 삭제 가능해요 :( (요청 사용자 : " + NsUserTest.SANJIGI.getUserId() + ")", exception.getMessage());
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
    @DisplayName("Question 답변글에 질문 작성자외에 다른 답변글 작성자가 존재하는지 확인")
    void 질문자외_답변자들이_존재하는지_확인() {
        Answer answerOne = Answer.of(NsUserTest.SANJIGI, question, "Answers Contents1");
        Answer answerTwo = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents1");

        answers = Answers.of(List.of(answerOne, answerTwo));
        question.loadAnswers(answers);
        assertThat(question.hasAnotherOwner()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("답변이_없는_경우_삭제_메소드")
    @DisplayName("Question 삭제시 답글이 없는경우 삭제 상태(deleted - boolean type)로 변경되는지 확인 ")
    void 답변이_없는_경우_삭제(Answers answers) {

        question.loadAnswers(answers);
        question.remove(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    static Stream<Arguments> 답변이_없는_경우_삭제_메소드() {
        return Stream.of(
                Arguments.arguments((Object) null),
                Arguments.arguments(Answers.create())
        );
    }

    @Test
    @DisplayName("질문 삭제시 삭제 이력이 반환되는지 확인 (답변이 없는경우)")
    void 삭제_이력_답변이_없는경우() {

        question.loadAnswers(Answers.create());

        DeleteHistory deleteHistory = DeleteHistory.of(1L, ContentType.QUESTION, question.getId(), question.getWriter(), null);


        DeleteHistories deleteHistories = question.remove(NsUserTest.JAVAJIGI);


        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistory);
    }

    @Test
    @DisplayName("질문 삭제시 삭제 이력이 반환되는지 확인 (답변이 있는경우)")
    void 삭제_이력_답변이_있는경우() {

        DeleteHistory deleteHistoryOfQuestion = DeleteHistory.of(1L, ContentType.QUESTION, question.getId(), question.getWriter(), null);
        DeleteHistory deleteHistoryOfAnswer = DeleteHistory.of(2L, ContentType.ANSWER, answer.getId(), answer.getWriter(), null);

        DeleteHistories deleteHistories = question.remove(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistoryOfQuestion, deleteHistoryOfAnswer);
    }


}
