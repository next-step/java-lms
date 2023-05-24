package nextstep.lms.domain;

public enum Status {
    READY("Ready"), RECRUIT("Recruid"), END("End");

    private String name;

    Status(String name){
        this.name = name;
    }

    public static boolean isNotRecruit(Status status) {
        return RECRUIT != status;
    }

}
