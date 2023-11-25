package nextstep.qna.domain;

import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    public static final Question Q3 = new Question(NsUserTest.JAVAJIGI, new Answers(List.of(AnswerTest.A1, AnswerTest.A3)));
    public static final Question Q4 = new Question(NsUserTest.JAVAJIGI, new Answers(List.of(AnswerTest.A1, AnswerTest.A2)));

    @Test
    void 로그인_유저와_질문자가_다름() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.delete(NsUserTest.SANJIGI));
    }

    @Test
    void 질문자와_답변자가_다름() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q4.delete(NsUserTest.JAVAJIGI));
    }

    @Test
    void 답변_없음() throws Exception {
        Q2.delete(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void 질문_작성자_로그인_유저_답변_작성자_모두_같음() throws Exception {
        Q3.delete(NsUserTest.JAVAJIGI);
        assertThat(Q3.isDeleted()).isTrue();
        assertThat(Q3.getAnswers().isAllDeleted()).isTrue();
    }
    
    @Test
    void 삭제_이력_있음() throws Exception {
        Q3.delete(NsUserTest.JAVAJIGI);
        assertThat(Q3.getDeleteHistories()).hasSize(3);
    }

}
