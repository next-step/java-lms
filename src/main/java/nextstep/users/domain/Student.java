package nextstep.users.domain;

import nextstep.users.application.model.req.ReqChangeUserProfile;

import java.util.Objects;

public class Student {

    private final String userId;
    private String name;
    private String email;

    public Student(String userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(userId, student.userId) && Objects.equals(name, student.name) && Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, email);
    }

    public Student update(ReqChangeUserProfile reqChangeUserProfile) {
        updateName(reqChangeUserProfile.getNameDefaultEmpty());
        updateEmail(reqChangeUserProfile.getEmailDefaultEmpty());

        return this;
    }

    private void updateName(String name) {

        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름에 빈값은 허용하지 않아요 :(");
        }
        this.name = name;
    }

    private void updateEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("이메일에 빈값은 허용하지 않아요 :(");
        }

        this.email = email;
    }

}
