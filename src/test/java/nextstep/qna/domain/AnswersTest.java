package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    private final Question questionByJavajigi = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    private final Answer answerByJavajigi_1 = new Answer(NsUserTest.JAVAJIGI, questionByJavajigi, "Answers Contents1");
    private final Answer answerByJavajigi_2 = new Answer(NsUserTest.JAVAJIGI, questionByJavajigi, "Answers Contents1_2");
    private final Answer answerBySanjigi = new Answer(NsUserTest.SANJIGI, questionByJavajigi, "Answers Contents2");
    private final Answers answersBySameWriter = new Answers(Arrays.asList(answerByJavajigi_1, answerByJavajigi_2));


    @Test
    public void delete_답변글들_작성자_동일시_삭제목록확인() throws CannotDeleteException {
        List<DeleteHistory> expected = Arrays.asList(
                new DeleteHistory(ContentType.ANSWER, answerByJavajigi_1.getId(), answerByJavajigi_1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answerByJavajigi_2.getId(), answerByJavajigi_2.getWriter(), LocalDateTime.now()));

        assertThat(answersBySameWriter.delete(NsUserTest.JAVAJIGI))
                .isEqualTo(expected);
    }

    @Test
    public void delete_답변글들_작성자와_로그인유저_다를시_에러_확인_테스트() {
        assertThatThrownBy(() -> answersBySameWriter.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_답변글들_작성자들이_다를_시_에러_확인_테스트() {
        Answers answersByDifferentWriter = new Answers(Arrays.asList(answerByJavajigi_1, answerBySanjigi));

        assertThatThrownBy(() -> answersByDifferentWriter.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
