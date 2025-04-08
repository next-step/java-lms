package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("delete 호출 전/후 isDeleted() 값 확인 &  delete() 메소드 리턴값 DeleteHistory 확인")
    void delete_test() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test");
        assertThat(answer.isDeleted()).isFalse();
        assertThat(answer.delete()).isInstanceOf(DeleteHistory.class);
        assertThat(answer.isDeleted()).isTrue();
    }
}
