package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuestionTest {
    public static Question QUESTION_OF_JAVAJIGI = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static Question QUESTION_OF_SANJIGI = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void tearDown() {
        QUESTION_OF_JAVAJIGI = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        QUESTION_OF_SANJIGI = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }


    @Test
    void 로그인_사용자와_질문한_사람이_다른경우_예외_확인() {
        assertThatThrownBy(() -> {
            QUESTION_OF_JAVAJIGI.validateOwner(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 로그인_사용자와_질문한_사람이_같은지_확인() {
        assertThatNoException().isThrownBy(() ->
            QUESTION_OF_JAVAJIGI.validateOwner(NsUserTest.JAVAJIGI)
        );
    }



    @Test
    void 해당_질문에_답변이_없고_작성자까지_일치하면_정상동작_확인() {
        assertThatNoException().isThrownBy(() ->
            QUESTION_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI)
        );

        assertThat(QUESTION_OF_JAVAJIGI.isDeleted()).isTrue();
    }

    @Test
    void 해당_질문에_답변이_없고_작성자가_다르면_예외_확인() {
        assertThatThrownBy(() -> {
            QUESTION_OF_JAVAJIGI.deleteBy(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThat(QUESTION_OF_JAVAJIGI.isDeleted()).isFalse();
    }

    @Test
    void 해당_질문에_작성자가_남긴_답변과_질문작성자가_같으면_정상동작_확인() {
        QUESTION_OF_JAVAJIGI.addAnswer(AnswerTest.ANSWER_OF_JAVAJIGI);

        assertThatNoException().isThrownBy(() ->
            QUESTION_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI)
        );

        assertThat(QUESTION_OF_JAVAJIGI.isDeleted()).isTrue();
    }

    @Test
    void 해당_질문에_다른_사람이_작성한_답변이_있으면_예외_확인() {
        QUESTION_OF_JAVAJIGI.addAnswer(AnswerTest.ANSWER_OF_SANJIGI);

        assertThatThrownBy(() -> {
            QUESTION_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThat(QUESTION_OF_JAVAJIGI.isDeleted()).isFalse();
    }


    @Test
    void 답변이_있는_질문을_삭제_성공_경우_질문과_답변_히스토리_목록_생성_검증() throws CannotDeleteException {
        QUESTION_OF_JAVAJIGI.addAnswer(AnswerTest.ANSWER_OF_JAVAJIGI);
        List<DeleteHistory> deleted = QUESTION_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI);


        DeleteHistory expectedQuestion = DeleteHistory.ofQuestion(0L, NsUserTest.JAVAJIGI);
        DeleteHistory expectedAnswer1 = DeleteHistory.ofAnswer(0L, NsUserTest.JAVAJIGI);

        assertThat(QUESTION_OF_JAVAJIGI.isDeleted()).isTrue();
        assertThat(deleted).hasSize(2);
        assertThat(deleted).contains(expectedQuestion, expectedAnswer1);
    }

    @Test
    void 답변이_없는_질문을_삭제_성공_경우_질문_히스토리_목록_생성_검증() throws CannotDeleteException {
        List<DeleteHistory> deleted = QUESTION_OF_JAVAJIGI.deleteBy(NsUserTest.JAVAJIGI);

        DeleteHistory expectedQuestion = DeleteHistory.ofQuestion(0L, NsUserTest.JAVAJIGI);

        assertThat(QUESTION_OF_JAVAJIGI.isDeleted()).isTrue();
        assertThat(deleted).hasSize(1);
        assertThat(deleted).contains(expectedQuestion);
    }





}
