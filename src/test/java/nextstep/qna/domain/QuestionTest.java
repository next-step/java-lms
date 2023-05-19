package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuestionTest {
    public static Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void tearDown() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }


    @Test
    void 로그인_사용자와_질문한_사람이_다른경우_예외_확인() {
        assertThatThrownBy(() -> {
            Q1.validateOwner(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 로그인_사용자와_질문한_사람이_같은지_확인() {
        assertThatNoException().isThrownBy(() ->
            Q1.validateOwner(NsUserTest.JAVAJIGI)
        );
    }

    @Test
    void 해당_질문에_다른_사람이_작성한_답변이_없으면_예외_없음_확인() {
        assertThatNoException().isThrownBy(Q1::validateHasAnswerByOtherUser);
    }

    @Test
    void 해당_질문에_다른_사람이_작성한_답변이_있으면_예외_확인() {
        Q2.addAnswer(AnswerTest.A2);
        Q2.addAnswer(AnswerTest.A1);

        assertThatThrownBy(Q2::validateHasAnswerByOtherUser).isInstanceOf(CannotDeleteException.class);
    }


    @Test
    void 질문_삭제_상태로_변경_후_삭제_상태_확인() {
        Q1.deleted();
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 질문_작성자와_로그인한_사람이_같고_다른사람_답변도_없는_경우_삭제_가능_검증() throws CannotDeleteException {
        Q1.deleteBy(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void 질문_작성자와_로그인한_사람이_다르고_다른사람_답변은_없는_경우_삭제_불가_검증() {
        assertThatThrownBy(() -> {
            Q1.deleteBy(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_작성자와_로그인한_사람이_같고_다른사람_답변이_있는_경우_삭제_불가_검증() {
        Q2.addAnswer(AnswerTest.A2);
        Q2.addAnswer(AnswerTest.A1);

        assertThatThrownBy(() -> {
            Q2.deleteBy(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_작성자와_로그인한_사람이_다르고_다른사람_답변이_있는_경우_삭제_불가_검증() {
        assertThatThrownBy(() -> {
            Q2.deleteBy(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문을_삭제할_경우_히스토리_생성_검증() {
        DeleteHistory deleted = Q1.deleted();

        DeleteHistory expected = new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleted).isEqualTo(expected);
    }

    @Test
    void 답변이_있는_질문을_삭제_성공_경우_질문과_답변_히스토리_목록_생성_검증() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> deleted = Q1.deleteBy(NsUserTest.JAVAJIGI);


        DeleteHistory expectedQuestion = new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI);
        DeleteHistory expectedAnswer1 = new DeleteHistory(ContentType.ANSWER, 0L, NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleted).hasSize(2);
        assertThat(deleted).contains(expectedQuestion, expectedAnswer1);
    }

    @Test
    void 답변이_없는_질문을_삭제_성공_경우_질문_히스토리_목록_생성_검증() throws CannotDeleteException {
        List<DeleteHistory> deleted = Q1.deleteBy(NsUserTest.JAVAJIGI);

        DeleteHistory expectedQuestion = new DeleteHistory(ContentType.QUESTION, 0L, NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleted).hasSize(1);
        assertThat(deleted).contains(expectedQuestion);
    }





}
