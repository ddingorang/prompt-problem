import java.util.Objects;

public class Seat {

    private final String id;
    private final Grade grade;

    public Seat(String id, Grade grade) {
        this.id = id;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public Grade getGrade() {
        return grade;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Seat seat)) {
            return false;
        }

        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public enum Grade {
        VIP,
        STANDARD,
        ECONOMY
    }
}