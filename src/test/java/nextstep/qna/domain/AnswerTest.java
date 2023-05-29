package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    public static Answer answer1;
    public static Answer answer2;

    @BeforeEach
    void init(){
        answer1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.QUESTION_1, "Answers Contents1");
        answer2 = new Answer(NsUserTest.SANJIGI, QuestionTest.QUESTION_2, "Answers Contents2");
    }

    @Test
    void isOwnerTest() {
        assertThat(answer1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(answer1.isOwner(NsUserTest.SANJIGI)).isFalse();
        assertThat(answer2.isOwner(NsUserTest.JAVAJIGI)).isFalse();
        assertThat(answer2.isOwner(NsUserTest.SANJIGI)).isTrue();
    }

    @Test
    void deleteTest() {
        assertThat(answer1.delete())
                .isInstanceOf(DeleteHistory.class)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(answer1.isDeleted()).isTrue();
    }
}
