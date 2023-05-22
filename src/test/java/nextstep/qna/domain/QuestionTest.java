package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("Question_delete_test")
    public void Question_delete_test() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);
        
    }
    @Test
    @DisplayName("Question_owner_test")
    public void Question_owner_test(){
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                        .hasMessage("질문을 삭제할 권한이 없습니다.");
    }
}
