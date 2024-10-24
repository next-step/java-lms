package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

class AnswerTest {
    public static Answer createAnswer(NsUser user, Question question) {
        return new Answer(user, question, "Answers Contents1");
    }
}
