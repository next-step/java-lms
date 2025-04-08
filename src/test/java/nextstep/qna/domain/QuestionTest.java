package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

@ExtendWith(MockitoExtension.class)
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete() throws CannotDeleteException {
        final LocalDateTime createdDate = LocalDateTime.now();
        assertThat(Q1.delete(NsUserTest.JAVAJIGI, createdDate)).isEqualTo(List.of(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, createdDate)));
    }

    @Test
    void 다른_사람이_쓴_질문이_있어_삭제할_수_없습니다() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, LocalDateTime.now()));
    }
}
