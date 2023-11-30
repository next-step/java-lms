package nextstep.qna.validate;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class QnAValidator {


    public void validateDelete(Question question, NsUser nsUser) throws CannotDeleteException {
        if (!Objects.equals(question.getWriter(), nsUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }
}
