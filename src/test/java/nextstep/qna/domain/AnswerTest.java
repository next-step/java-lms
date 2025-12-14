package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete_다른사람이_쓴_답변이_있으면_삭제불가() {
        NsUser user = NsUserTest.JAVAJIGI;
        Answer answer = AnswerTest.A2;
        assertThatThrownBy(() -> answer.delete(user))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        NsUser user = NsUserTest.JAVAJIGI;
        Answer answer = AnswerTest.A1;

        answer.delete(user);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void deleteHistory_생성() {
        Answer answer = AnswerTest.A1;

        DeleteHistory history = answer.deleteHistory();

        assertThat(history).isEqualTo(DeleteHistory.from(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }
}
