package nextstep.courses.domain;

public class Generation {

    private final int generation;

    public Generation(int generation) {
        this.generation = generation;
    }

    public int generationNumber() {
        return this.generation;
    }
}
