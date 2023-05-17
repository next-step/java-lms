package nextstep.qna.domain;

public enum ContentType {
    QUESTION, ANSWER;

    public static ContentType findByContent(Object object) {
        if (object instanceof Question) {
            return QUESTION;
        }
        return ANSWER;
    }

}
