package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    private Question question;
    private Answer answer;

    @BeforeEach
    public void initialize() {
        SimpleIdGenerator.initialize();
        question = Question.of(NsUserTest.JAVAJIGI.getUserId(), "title1", "contents1");
        answer = Answer.of(NsUserTest.JAVAJIGI.getUserId(), question.getId(), "Answers Contents1");
    }

    @Test
    @DisplayName("Answer 객체 생성 테스트")
    void Answer_객체_생성_테스트() {

        Answer answer = Answer.of(1L, NsUserTest.JAVAJIGI.getUserId(), question.getId(), "Answers Contents1", null);

        assertAll(
                () -> assertThat(answer).isNotNull(),
                () -> assertThat(answer).isEqualTo(this.answer)
        );

    }

    @Test
    @DisplayName("Answer 객체 생성 시 작성자가 유효하지 않을경우 UnAuthorizedException 예외를 던진다")
    void 객체_생성시_작성자_유효하지_않은경우_예외를_던진다() {

        Assertions.assertThrows(UnAuthorizedException.class, () -> {
            Answer.of(null, question.getId(), "Answers Contents1");
        });
    }

    @Test
    @DisplayName("Answer 객체 생성 시 질문이 유효하지 않을경우 NotFoundException 예외를 던진다")
    void 객체_생성시_질문_유효하지_않은경우_예외를_던진다() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            Answer.of(NsUserTest.JAVAJIGI.getUserId(), 0, "Answers Contents1");
        });
    }

    @Test
    @DisplayName("답변 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인")
    void 삭제_상태_변경() {
        answer.remove();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 시 삭제 이력이 반환되는지 확인")
    void 삭제_이력() {
        assertThat(answer.remove()).isEqualTo(DeleteHistory.of(1L, ContentType.ANSWER, answer.getId(), answer.getWriter(), null));
    }

    @Test
    @DisplayName("Answer 객체 생성 테스트 id가 0 인경우")
    void Answer_객체_생성_테스트_아이디가_0() {


        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> Answer.of(0, NsUserTest.JAVAJIGI.getUserId(), question.getId(), "contents1", null));
        assertEquals("유효하지 않는 아이디에요 :( [입력 값 : 0]", exception.getMessage());
    }

    @Test
    @DisplayName("Answer 삭제시  질문이_이미_삭제된_경우 예외를 던진다 ")
    void 답변_이미_삭제된_경우() {

        answer = Answer.of(1L, NsUserTest.JAVAJIGI.getUserId(), question.getId(), "Answers Contents1", null, DeleteStatus.DELETED);

        Throwable exception = Assertions.assertThrows(CannotDeleteException.class, () -> answer.remove());
        assertEquals("이미 삭제된 답변이에요 :(", exception.getMessage());

    }


}
