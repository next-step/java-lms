package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 주어진 사용자가 답변자라면 -> 삭제")
    public void deleteTest() throws CannotDeleteException {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");
        answer.deleteIfWriter(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 주어진 사용자와 답변자가 다를 경우에 삭제를 요청하면 -> CannotDeleteException을 던진다.")
    public void deleteByWrongWriterTest() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");

        assertThatThrownBy(() -> {
            answer.deleteIfWriter(NsUserTest.SANJIGI);
        })
        .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("[Answer.deleteIfWriter()] 삭제를 요청하면 -> 자기 자신의 삭제 정보를 준다.")
    public void deleteInfoTest() throws CannotDeleteException {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "hello");

        assertThat(answer.deleteIfWriter(NsUserTest.JAVAJIGI))
                .isEqualTo(new DeleteHistory(
                        ContentType.ANSWER,
                        answer.getId(),
                        NsUserTest.JAVAJIGI
                ));
    }
}