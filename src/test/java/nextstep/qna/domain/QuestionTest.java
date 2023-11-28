package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {

        public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

        @Test
        void 로그인_사용자와_질문_작성자_같을_경우_질문_삭제_성공() throws CannotDeleteException {
                NsUser loginUser = JAVAJIGI;
                Q1.deleteQuestion(loginUser);
        }

        @Test
        void 로그인_사용자와_질문_작성자_다를_경우_질문_삭제_실패() {
                NsUser loginUser = JAVAJIGI;
                assertThatThrownBy(() -> Q2.deleteQuestion(loginUser)).isInstanceOf(CannotDeleteException.class)
                    .hasMessage("질문을 삭제할 권한이 없습니다.");
        }
}
