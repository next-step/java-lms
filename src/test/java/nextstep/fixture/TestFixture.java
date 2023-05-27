package nextstep.fixture;

import nextstep.common.domain.Image;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Creator;
import nextstep.courses.domain.Enroll;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.Term;
import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class TestFixture {

    public static Question BADAJIGI_QUESTION;
    public static Question JAVAJIGI_QUESTION;
    public static Question SANJIGI_QUESTION;

    public static Answer JAVAJIGI_ANSWER;
    public static Answer SANJIGI_ANSWER;
    public static Answer BADAJIGI_ANSWER;

    public static NsUser JAVAJIGI;
    public static NsUser SANJIGI;
    public static NsUser BADAJIGI;

    public static Creator pobi;
    public static Creator watson;
    public static Creator winter;

    public static Course RUST_COURSE;
    public static Course K8S_COURSE;
    public static Course KOTLIN_COURSE;

    public static Term TERM16;
    public static Term TERM17;
    public static Session MINT_SESSION;
    public static Session LEMON_SESSION;
    public static Session LIME_SESSION;

    public static Enroll MALBEC_ENROL;
    public static Enroll SYRAH_ENROL;
    public static Enroll CARSO_ENROL;
    public static Enroll PINOT_ENROL;

    public static Image RED_IMAGE;
    public static Image GREEN_IMAGE;
    public static Image YELLOW_IMAGE;
    public static Image BLUE_IMAGE;


    static {
        fixtureInit();
    }

    public static void fixtureInit() {
        JAVAJIGI = NsUser.of(1L, "javajigi", "password", "king of clean code", "javajigi@slipp.net");
        SANJIGI = NsUser.of(2L, "sanjigi", "password", "sanjigi", "sanjigi@slipp.net");
        BADAJIGI = NsUser.of(3L, "badajigi", "pacific", "Poseidon", "Poseidon@marine.io");

        JAVAJIGI_QUESTION = Question.of(1L, JAVAJIGI, "성공하는 프로그래밍 학습법은 뭐가있을까", "제곧내");
        SANJIGI_QUESTION = Question.of(2L, SANJIGI, "관악산 연주대에 오르는 최단경로는 어디인가요", "등산허쉴?");
        BADAJIGI_QUESTION = Question.of(2L, BADAJIGI, "deep deep sea", "giant octopus");

        JAVAJIGI_ANSWER = Answer.of(11L, JAVAJIGI, JAVAJIGI_QUESTION, "Answers Contents1");
        SANJIGI_ANSWER = Answer.of(22L, SANJIGI, SANJIGI_QUESTION, "클린코드는 요구사항을 빠르게 추가하기 위한 유일한 방법이다");
        BADAJIGI_ANSWER = Answer.of(33L, BADAJIGI, BADAJIGI_QUESTION, "여름에는 바다를 가야한다");

        pobi = new Creator(1L, "pobi");
        watson = new Creator(2L, "watson");
        winter = new Creator(3L, "winter");

        RUST_COURSE = new Course("가볍게 시작하는 RUST", 1L);
        K8S_COURSE = new Course("재미있는 쿠버네티스", 2L);
        KOTLIN_COURSE = new Course("Hello Kotlin", 3L);

        TERM16 = new Term();
        TERM17 = new Term();

        MINT_SESSION = new Session(10000L, 100L);
        LEMON_SESSION = new Session(500L, 50L);
        LIME_SESSION = new Session(3000L, 30L);

        MALBEC_ENROL = new Enroll(1L, 1L);
        SYRAH_ENROL = new Enroll(2L, 2L);
        CARSO_ENROL = new Enroll(3L, 3L);
        PINOT_ENROL = new Enroll(4L, 4L);

        RED_IMAGE = new Image("https://user-images.githubusercontent.com/31065684/240915569-18af82dc-abd3-4e5c-ad13-a085ff4b926c.png");
        GREEN_IMAGE = new Image("https://user-images.githubusercontent.com/31065684/240915721-3052254f-f17d-477e-8203-45297b2b5471.png");
        YELLOW_IMAGE = new Image("https://user-images.githubusercontent.com/31065684/240916055-f04c5f5e-b1f8-4322-b146-5168fca95026.png");
        BLUE_IMAGE = new Image("https://user-images.githubusercontent.com/31065684/240916270-2345694d-514b-447a-8702-c31677e79648.png");
    }
}
