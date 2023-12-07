package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class Contents {

    private Long id;

    private String writer;

    private String contents;

    public Contents(String writer, String contents){
        this(0L, writer, contents);
    }

    public Contents(Long id, String writer, String contents){
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public boolean isNotOwner(String loginUserId) {
        return !writer.equals(loginUserId);
    }
}
