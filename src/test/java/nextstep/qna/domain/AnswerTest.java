package nextstep.qna.domain;

import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {

    private Question question;
    private Answer answer;

    @BeforeEach
    public void initialize() {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1", null);
        answer = Answer.of(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answers answers = Answers.of(List.of(answer));
        question.loadAnswers(answers);
    }

    @Test
    @DisplayName("Answer 객체 생성 테스트")
    void Answer_객체_생성_테스트() {

        Answer answer = Answer.of(1L, NsUserTest.JAVAJIGI, question, "Answers Contents1", null);

        assertAll(
                () -> assertThat(answer).isNotNull(),
                () -> assertThat(answer).isEqualTo(this.answer)
        );

    }

    @Test
    @DisplayName("Answer 객체 생성 시 작성자가 유효하지 않을경우 UnAuthorizedException 예외를 던진다")
    void 객체_생성시_작성자_유효하지_않은경우_예외를_던진다() {

        Assertions.assertThrows(UnAuthorizedException.class, () -> {
            Answer.of(null, question, "Answers Contents1");
        });
    }

    @Test
    @DisplayName("Answer 객체 생성 시 질문이 유효하지 않을경우 NotFoundException 예외를 던진다")
    void 객체_생성시_질문_유효하지_않은경우_예외를_던진다() {
        Assertions.assertThrows(NotFoundException.class, () -> {
            Answer.of(NsUserTest.JAVAJIGI, null, "Answers Contents1");
        });
    }

    @Test
    @DisplayName("답변 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인")
    void delete_삭제_상태_변경() {
        answer.remove();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 시 삭제 이력이 반환되는지 확인")
    void delete_삭제_이력() {
        assertThat(answer.remove()).isEqualTo(DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter()));
    }

}
