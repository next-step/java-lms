package nextstep.fixture;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class TestFixture {

    private static Question BADAJIGI_QUESTION;
    private static Question JAVAJIGI_QUESTION;
    private static Question SANJIGI_QUESTION;
    private static Answer JAVAJIGI_ANSWER;
    private static Answer SANJIGI_ANSWER;

    public static NsUser JAVAJIGI;
    public static NsUser SANJIGI;
    public static NsUser BADAJIGI;

    static {
        init();
    }
    public static void init() {
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
        BADAJIGI = new NsUser(3L, "badajigi", "pacific", "Poseidon", "Poseidon@marine.io");

        JAVAJIGI_QUESTION = new Question(1L, JAVAJIGI, "title1", "contents1");
        SANJIGI_QUESTION = new Question(2L, SANJIGI, "title1", "contents1");
        BADAJIGI_QUESTION = new Question(2L, BADAJIGI, "deep deep sea", "giant octopus");

        JAVAJIGI_ANSWER = new Answer(11L, JAVAJIGI, JAVAJIGI_QUESTION, "Answers Contents1");
        SANJIGI_ANSWER = new Answer(22L, SANJIGI, SANJIGI_QUESTION, "클린코드는 요구사항을 빠르게 추가하기 위한 유일한 방법이다");
    }
}
