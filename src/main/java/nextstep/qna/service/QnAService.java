package nextstep.qna.service;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public interface QnAService {
    void deleteQuestion(NsUser loginUser, long questionId) throws CannotDeleteException;
}
