package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
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

public class AnswersTest {
    private Answers answers1;
    private Answers answers2;
    private LocalDateTime now;

    @BeforeEach
    public void beforeEach() {
        answers1 = Answers.create();
        answers2 = Answers.create();
        answers2.add(AnswerTest.A2);
        now = LocalDateTime.now();
    }

    @DisplayName("Answers 객체가 잘 생성되는지 확인")
    @Test
    void 객체가_정상적으로_생성되는지_확인() {
        assertThat(Answers.create()).isInstanceOf(Answers.class);
    }

    @DisplayName("Answer 객체가 잘 추가되는지 확인")
    @Test
    void Answer_객체가_정상적으로_추가되는지_확인() {
        answers1.add(AnswerTest.A1);
        assertThat(answers1.getAnswers()).hasSize(1);
    }

    @DisplayName("로그인 사용자와 답변한 사람이 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_로그인_사용자와_답변자가_같은_경우() {
        assertThatCode(() -> answers2.deleteAnswers(NsUserTest.SANJIGI, now))
                .doesNotThrowAnyException();
    }

    @DisplayName("로그인 사용자와 답변한 사람이 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_로그인_사용자와_답변자가_다른_경우() {
        assertThatThrownBy(() -> answers2.deleteAnswers(NsUserTest.JAVAJIGI, now))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인")
    @Test
    void delete_삭제_상태_변경() throws CannotDeleteException {
        answers2.deleteAnswers(NsUserTest.SANJIGI, now);
        assertThat(answers2.getAnswers().get(0).getDeleteStatus()).isEqualTo(DeleteStatus.YES);
    }

    @DisplayName("답변 삭제 시 삭제 이력이 반환되는지 확인")
    @Test
    void delete_삭제_이력() throws CannotDeleteException {
        DeleteHistories deleteHistories = answers2.deleteAnswers(NsUserTest.SANJIGI, now);
        DeleteHistory deleteHistory = DeleteHistory.of(ContentType.ANSWER, AnswerTest.A2.getId(), AnswerTest.A2.getWriter(), now);

        assertThat(deleteHistories.getDeleteHistories()).containsExactly(deleteHistory);
    }
}
