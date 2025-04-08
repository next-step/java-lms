package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public Question Q1;
    public Question Q2;

    @BeforeEach
    public void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    public void delete() throws Exception {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른사람이쓴글() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void getDeleteHistories() throws Exception {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).isEqualTo(List.of(new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI, LocalDateTime.now())));
    }
}
