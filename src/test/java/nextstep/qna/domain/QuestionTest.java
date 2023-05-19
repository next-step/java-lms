package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void setUp() {
        Q1.addAnswer(AnswerTest.A1);

        Q2.addAnswer(AnswerTest.A2);
        Q2.addAnswer(AnswerTest.A1);
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
        assertThatThrownBy(Q2::validateHasAnswerByOtherUser).isInstanceOf(CannotDeleteException.class);
    }


    @Test
    void 질문_삭제_상태로_변경_후_삭제_상태_확인() {
        Q1.deleted();
        assertThat(Q1.isDeleted()).isTrue();
    }



}
