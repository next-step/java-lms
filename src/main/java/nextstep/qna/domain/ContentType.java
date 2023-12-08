package nextstep.qna.domain;

public enum ContentType {
    QUESTION, ANSWER;

    public static ContentType findTypeBy(Object object) {
        if (object instanceof Question) {
            return QUESTION;
        }
        return ANSWER;
    }
}
