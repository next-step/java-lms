package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    private Answers answers;
    public static LocalDataTimeHolder localDataTimeHolder;

    @BeforeEach
    void setUp() {
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        answers = Answers.from(List.of(A1, A2));

        localDataTimeHolder = new LocalDateTimeTestHolder(LocalDateTime.of(2023, 11,
                21, 0, 0));
    }

    @Test
    @DisplayName("답변들의 작성자와 삭제를 요청한 대상이 다르면 예외 처리를 한다")
    void deleteNotCorrectUser() {
        Assertions.assertThrows(CannotDeleteException.class, () -> answers.delete(NsUserTest.SANJIGI, localDataTimeHolder), "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("삭제 처리를 하면 history가 생성된다")
    void deleteReturn() {
        Answers answers = Answers.from(List.of(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1")));
        List<DeleteHistory> actual = answers.delete(NsUserTest.JAVAJIGI, new LocalDateTimeNowHolder());
        List<DeleteHistory> expected = List.of(new DeleteHistory(ContentType.ANSWER, 0L, NsUserTest.JAVAJIGI,
                localDataTimeHolder.now()));

        assertThat(actual).isEqualTo(expected);
    }

}
