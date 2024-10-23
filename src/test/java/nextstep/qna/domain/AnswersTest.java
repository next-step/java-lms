package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    public static final Answers ANSWERS1 = new Answers(List.of(
            new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
            new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2")
    ));
    public static final Answers ANSWERS2 = new Answers(List.of(
            new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
            new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2")
    ));

    @DisplayName("답변들을 모두 삭제 후 삭제 이력을 반환한다.")
    @Test
    void delete() {
        List<DeleteHistory> actual = ANSWERS1.delete(NsUserTest.JAVAJIGI);

        assertThat(ANSWERS1.getAnswers()).allMatch(Answer::isDeleted);
        assertThat(actual).extracting("contentType", "deletedBy")
                .containsExactlyInAnyOrder(
                        new Tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI),
                        new Tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI)
                );
    }

    @DisplayName("답변들 중 다른 사용자도 있는 경우 예외를 발생한다.")
    @Test
    void throwExceptionWhenDeletingWithOtherUser() {
        assertThatThrownBy(() -> ANSWERS2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(RuntimeException.class);
    }
}
