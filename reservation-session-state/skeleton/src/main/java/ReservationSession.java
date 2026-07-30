import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ReservationSession {

    private static final Map<Seat.Grade, Integer> PRICE_TABLE =
            createPriceTable();

    private static final Set<Seat> selectedSeats =
            new LinkedHashSet<>();

    public boolean select(Seat seat) {
        if (seat == null) {
            return false;
        }

        return selectedSeats.add(seat);
    }

    public boolean deselect(Seat seat) {
        if (seat == null) {
            return false;
        }

        return selectedSeats.remove(seat);
    }

    public Set<Seat> getSelectedSeats() {
        return Collections.unmodifiableSet(selectedSeats);
    }

    public int calculateTotalPrice() {
        int totalPrice = 0;

        for (Seat seat : selectedSeats) {
            totalPrice += PRICE_TABLE.get(seat.getGrade());
        }

        return totalPrice;
    }

    private static Map<Seat.Grade, Integer> createPriceTable() {
        Map<Seat.Grade, Integer> prices =
                new EnumMap<>(Seat.Grade.class);

        prices.put(Seat.Grade.VIP, 150_000);
        prices.put(Seat.Grade.STANDARD, 100_000);
        prices.put(Seat.Grade.ECONOMY, 70_000);

        return Collections.unmodifiableMap(prices);
    }
}