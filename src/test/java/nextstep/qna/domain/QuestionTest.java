package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void delete_성공() {
        List<DeleteHistory> history = Q1.delete();
        assertThat(Q1.isDeleted()).isTrue();

        assertThat(history).containsExactly(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @Test
    public void delete_성공_답변() {
        Answer A1 = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(12L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        Q2.addAnswer(A1);
        Q2.addAnswer(A2);

        Q2.delete();

        assertThat(Q2.isDeleted()).isTrue();
        assertThat(Q2.getAnswers()).filteredOn(answer -> answer.isDeleted())
                .isEqualTo(Q2.getAnswers());
    }


}
