package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.enums.ContentType;
import nextstep.qna.domain.enums.DeleteStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = Answer.of(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = Answer.of(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    private Answer answer;
    private LocalDateTime now;

    @BeforeEach
    public void beforeEach() {
        answer = Answer.of(NsUserTest.JAVAJIGI, QuestionTest.Q1, "내용1");
        this.now = LocalDateTime.now();
    }

    @DisplayName("Answer 객체가 잘 생성되는지 확인")
    @Test
    void 객체가_정상적으로_생성되는지_확인() {
        assertThat(Answer.of(NsUserTest.JAVAJIGI, QuestionTest.Q1, "내용1")).isInstanceOf(Answer.class);
    }

    @DisplayName("Answer 객체 생성 시 작성자가 유효하지 않을경우 UnAuthorizedException 예외가 발생되는지 확인")
    @Test
    void 객체_생성시_작성자_유효하지_않은경우() {
        assertThatThrownBy(() -> Answer.of(null, QuestionTest.Q1, "내용1"))
                .isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("Answer 객체 생성 시 질문이 유효하지 않을경우 NotFoundException 예외가 발생되는지 확인")
    @Test
    void 객체_생성시_질문_유효하지_않은경우() {
        assertThatThrownBy(() -> Answer.of(NsUserTest.JAVAJIGI, null, "내용1"))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("로그인 사용자와 답변한 사람이 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_로그인_사용자와_답변자가_같은_경우() {
        assertThatCode(() -> answer.delete(NsUserTest.JAVAJIGI, now))
                .doesNotThrowAnyException();
    }

    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_로그인_사용자와_답변자가_다른_경우() {
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI, now))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인")
    @Test
    void delete_삭제_상태_변경() throws CannotDeleteException {
        answer.delete(NsUserTest.JAVAJIGI, now);
        assertThat(answer.getDeleteStatus()).isEqualTo(DeleteStatus.YES);
    }

    @DisplayName("답변 삭제 시 삭제 이력이 반환되는지 확인")
    @Test
    void delete_삭제_이력() throws CannotDeleteException {
        assertThat(answer.delete(NsUserTest.JAVAJIGI, now)).isEqualTo(DeleteHistory.ofAnswer(answer.getId(), answer.getWriter(), now));
    }
}
