package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    //삭제가 가능한 경우
    //1. 로그인한 사용자와 글작성자가 같은 가?
    //1-1. if 답변이 없는가? 삭제o
    //1-2. elseif 질문자, 답변자가 모두 같은가? 삭제0

    @Test
    public void 로그인한_사용자와_글작성자가_같아야_질문_삭제_가능() {
        assertThatThrownBy(() -> {
            Q2.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

}
