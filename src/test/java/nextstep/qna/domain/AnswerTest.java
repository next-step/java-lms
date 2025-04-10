package nextstep.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete를 호출하면 Answer의 deleted가 true로 변경된다.")
    void delete() throws CannotDeleteException {
        Answer answer = A1;
        answer.delete(NsUserTest.JAVAJIGI);
        Assertions.assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 유저가 delete를 호출하면 CannotDeleteException이 발생한다.")
    void deleteByOtherUser() {
        Assertions.assertThatThrownBy(()-> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
