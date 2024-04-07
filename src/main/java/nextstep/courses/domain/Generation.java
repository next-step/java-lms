package nextstep.courses.domain;

public class Generation {

    public static final int MINIMUM_GENERATION = 1;
    private final int generation;

    public Generation(int generation) {
        validate(generation);
        this.generation = generation;
    }

    private static void validate(int generation) {
        if (generation < MINIMUM_GENERATION) {
            throw new IllegalArgumentException("기수는 0이하일 수 없습니다.");
        }
    }

    public int generationNumber() {
        return this.generation;
    }
}
