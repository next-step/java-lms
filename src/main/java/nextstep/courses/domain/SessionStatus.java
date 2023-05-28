package nextstep.courses.domain;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class SessionStatus {
    private int maxCapacity;
    private List<String> studentIds = new ArrayList<>();

    public SessionStatus(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getSignedUpStatus() {
        return studentIds.size();
    }

    public void signUp(String studentId) {
        validateSignUp(studentId);

        studentIds.add(studentId);
    }

    private void validateSignUp(String studentId) {
        if (studentIds.contains(studentId)) {
            throw new RuntimeException("이미 등록된 학생입니다.");
        }

        if (studentIds.size() >= maxCapacity) {
            throw new RuntimeException("최대 수강 인원을 초과하여 신청이 불가합니다.");
        }
    }
}
