package nextstep.qna.domain;

public enum ContentType {
    QUESTION("질문"),
    ANSWER("답변");

    private final String name;

    ContentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
