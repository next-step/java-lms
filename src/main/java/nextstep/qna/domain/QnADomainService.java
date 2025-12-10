package nextstep.qna.domain;

import java.util.List;

public class QnADomainService {

    public List<DeleteHistory> deleteQuestion(long requesterId, Question question) {
        question.putOnDelete(requesterId);

        return question.bringAllDeleteHistories();
    }
}
