package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void 다른사용자의질문삭제시_에러발생테스트() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.deleted(NsUserTest.SANJIGI));
    }

    @Test
    public void 질문삭제시_다른사용자답변존재하면_에러발생테스트() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    Q2.addAnswer(AnswerTest.A1);
                    Q2.deleted(NsUserTest.SANJIGI);
                });
    }
}
