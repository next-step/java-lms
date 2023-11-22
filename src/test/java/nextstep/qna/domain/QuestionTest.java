package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1", LocalDateTime.of(2023, 11,
            21, 0, 0));
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2", LocalDateTime.of(2023, 11,
            21, 0, 0));

    public static LocalDataTimeHolder localDataTimeHolder;

    @BeforeEach
    void setUp() {
        localDataTimeHolder = new LocalDateTimeTestHolder(LocalDateTime.of(2023, 11,
                21, 0, 0));
    }

    @Test
    @DisplayName("질문의 작성자와 삭제를 요청한 대상이 다르면 예외 처리를 한다")
    void deleteNotCorrectUser() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(NsUserTest.SANJIGI, localDataTimeHolder), "질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("삭제 처리를 한다")
    void delete() {
        Q1.delete(NsUserTest.JAVAJIGI, localDataTimeHolder);

        Question expected = new Question(NsUserTest.JAVAJIGI, "title1", "contents1", true, LocalDateTime.of(2023, 11,
                21, 0, 0));

        assertThat(Q1).isEqualTo(expected);
    }

    @Test
    @DisplayName("삭제 처리를 하면 history가 생성된다")
    void deleteReturn() {
        List<DeleteHistory> actual = Q1.delete(NsUserTest.JAVAJIGI, new LocalDateTimeNowHolder());
        List<DeleteHistory> expected = List.of(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI,
                localDataTimeHolder.now()));

        assertThat(actual).isEqualTo(expected);
    }
}
