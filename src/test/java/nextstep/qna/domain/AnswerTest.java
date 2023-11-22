package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1", LocalDateTime.of(2023, 11,
            21, 0, 0));
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2", LocalDateTime.of(2023, 11,
            21, 0, 0));

    public static LocalDataTimeHolder localDataTimeHolder;

    @BeforeEach
    void setUp() {
        localDataTimeHolder = new LocalDateTimeTestHolder(LocalDateTime.of(2023, 11,
                21, 0, 0));
    }

    @Test
    @DisplayName("답변의 작성자와 삭제를 요청한 대상이 다르면 예외 처리를 한다")
    void deleteNotCorrectUser() {
        Assertions.assertThrows(CannotDeleteException.class, () -> A1.delete(NsUserTest.SANJIGI, localDataTimeHolder), "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("삭제 처리를 한다")
    void delete() {
        A1.delete(NsUserTest.JAVAJIGI, localDataTimeHolder);

        Answer expected = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1", true, LocalDateTime.of(2023, 11,
                21, 0, 0));

        assertThat(A1).isEqualTo(expected);
    }

    @Test
    @DisplayName("삭제 처리를 하면 history가 생성된다")
    void deleteReturn() {
        DeleteHistory actual = A1.delete(NsUserTest.JAVAJIGI, new LocalDateTimeNowHolder());
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, 0L, NsUserTest.JAVAJIGI, localDataTimeHolder.now());

        assertThat(actual).isEqualTo(expected);
    }
}
