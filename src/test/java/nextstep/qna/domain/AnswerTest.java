package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 삭제히스토리_저장_테스트() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(A1.makeDeleteHistory());
        deleteHistories.add(A2.makeDeleteHistory());
        assertThat(deleteHistories).hasSize(2);
    }

}
