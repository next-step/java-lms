package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class AnswersTest {

    public static final Answers AS1 = Answers.from(List.of(AnswerTest.A1, AnswerTest.A2));
    public static final Answers AS2 = Answers.from(List.of(AnswerTest.A2, AnswerTest.A2));
    @Test
    void from() {
        assertThat(AS1).isEqualTo(new Answers(List.of(AnswerTest.A1, AnswerTest.A2)));
    }

    @Test
    void isNotOnlyWriter() {
        assertThat(AS1.isNotOnlyWriter(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void delete() {
        List<DeleteHistory> delete = AS2.delete(NsUserTest.SANJIGI);

        assertThat(delete).hasSize(2);
    }
}
