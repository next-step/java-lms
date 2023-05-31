package nextstep.courses.domain;

import java.util.regex.Pattern;

public class Student {
    private final String studentId;
    private final String name;
    private final String email;

    public Student(String studentId, String name, String email) {
//        validateInput(studentId, name, email);

        this.studentId = studentId.trim();
        this.name = name.trim();
        this.email = email.trim();
    }

//    private void validateInput(String studentId, String name, String email) {
//        if (studentId == null || studentId.trim().isEmpty()) {
//            throw new IllegalArgumentException("입력된 학생ID가 Null 또는 빈값 입니다.");
//        }
//
//        if (name == null || name.trim().isEmpty()) {
//            throw new IllegalArgumentException("입력된 이름이 Null 또는 빈값 입니다.");
//        }
//
//        if (email == null || email.trim().isEmpty()) {
//            throw new IllegalArgumentException("입력된 이메일주소가 Null 또는 빈값 입니다.");
//        }
//
//        if (!Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", email)) {
//            throw new IllegalArgumentException("입력된 이메일주소가 이메일주소 형식이 아닙니다.");
//        }
//    }

    @Override
    public boolean equals(Object other) {
        return this.studentId.equals(((Student)other).studentId);
    }
}

