package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    void 답변을_삭제한다() {
        // given
        List<DeleteHistory> histories = new ArrayList<>();

        // when
        answer.delete(histories);

        // then
        assertThat(histories.get(0)).isEqualTo(
                new DeleteHistory(ContentType.ANSWER, 11L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
