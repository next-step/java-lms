package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("Answer 객체가 Answers 객체에 성공적으로 추가되는지 확인한다")
    void Answer_추가_테스트() {
        Answers answers = new Answers(new ArrayList<>());

        answers.add(A1);
        answers.add(A2);

        assertThat(answers.getNumberOfAnswers()).isEqualTo(2);
    }

    @Test
    @DisplayName("작성한 답변 중 내가 작성한 답변이 존재하지 않을 시 예외를 발생시킨다")
    void 내가_작성하지않은_답변_있을_시_예외발생() {
        Answers answers = new Answers(new ArrayList<>());

        answers.add(A1);
        answers.add(A2);

        assertThatThrownBy(()
                -> answers.delete(A2.getWriter(),new ArrayList<>())).
                isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("작성한 답변이 내가 작성한 답변이면 성공적으로 삭제하고 삭제기록에 저장한다")
    void 내가_작성한_답변일_시_답변삭제() throws CannotDeleteException {
        Answers answers = new Answers(new ArrayList<>());

        answers.add(A1);
        answers.add(A1);

        List<DeleteHistory> deleteHistories = answers.delete(A1.getWriter(),new ArrayList<>());

        assertThat(deleteHistories).hasSize(2);
    }

}
