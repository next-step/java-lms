package nextstep.users.domain;

public enum StudentStatus {
    REQUEST("REQUEST"), APPROVE("APPROVE"), REFUSE("REFUSE");

    private String name;

    StudentStatus (String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
