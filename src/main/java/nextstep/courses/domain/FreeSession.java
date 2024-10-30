package nextstep.courses.domain;

public class FreeSession implements SessionStrategy {
    @Override
    public boolean canEnroll() {
        return true;
    }
}
