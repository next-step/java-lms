package nextstep.qna.domain;

public class QuestionBody {
    private String title;

    private String contents;

    public QuestionBody() {
    }

    public QuestionBody(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(obj == this) {
            return true;
        }

        if(obj.getClass() == getClass()) {
            QuestionBody questionBody = (QuestionBody) obj;
            return title.equals(questionBody.title)
                && contents.equals(questionBody.contents);
        }

        return false;
    }
}
