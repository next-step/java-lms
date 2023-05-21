package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    Answers answers;

    @BeforeEach
    void init() {
        answers = new Answers();

        answers.add(AnswerTest.A1);
        answers.add(AnswerTest.A2);
    }

    @Test
    void 답변_모두_삭제() {

        List<DeleteHistory> deleteHistories = answers.deleteAll();

        Assertions.assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
        Assertions.assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), NsUserTest.SANJIGI, LocalDateTime.now()));
    }

    @Test
    void 로그인_유저와_답변_작성자가_다를_경우_예외() {

        assertThatThrownBy(() -> answers.checkOwner(NsUser.GUEST_USER))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
