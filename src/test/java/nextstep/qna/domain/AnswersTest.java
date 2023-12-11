package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 로그인사용자와_일부_답변의_답변작성자가_같지_않은_경우() {
        Answers answers = new Answers(Arrays.asList(A1, A2));

        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 로그인사용자와_모든_답변의_답변작성자가_같은_경우() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(A1, A1));

        answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answers.stream().allMatch(answer -> answer.isDeleted())).isTrue();
    }
}
