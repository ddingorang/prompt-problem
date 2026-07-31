import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationSessionTest {

    @Test
    void 새로운_세션은_빈_선택_목록으로_시작한다() {
        ReservationSession firstSession = new ReservationSession();
        firstSession.select(new Seat("A-1", Seat.Grade.VIP));

        ReservationSession newSession = new ReservationSession();

        assertTrue(newSession.getSelectedSeats().isEmpty(),
                "새 세션은 이전 세션의 선택 좌석을 물려받으면 안 됩니다.");
        assertEquals(0, newSession.calculateTotalPrice());
    }

    @Test
    void 한_세션의_좌석_선택이_다른_세션에_보이지_않는다() {
        ReservationSession firstSession = new ReservationSession();
        ReservationSession secondSession = new ReservationSession();

        Seat seat = new Seat("A-1", Seat.Grade.VIP);
        firstSession.select(seat);

        assertEquals(1, firstSession.getSelectedSeats().size());
        assertEquals(0, secondSession.getSelectedSeats().size());
    }

    @Test
    void 한_세션의_선택_해제가_다른_세션에_영향을_주지_않는다() {
        ReservationSession firstSession = new ReservationSession();
        ReservationSession secondSession = new ReservationSession();

        Seat seat = new Seat("A-1", Seat.Grade.VIP);
        firstSession.select(seat);

        assertFalse(secondSession.deselect(seat),
                "선택한 적 없는 좌석의 해제는 실패해야 합니다.");
        assertEquals(1, firstSession.getSelectedSeats().size(),
                "다른 세션의 해제로 기존 세션의 선택이 사라지면 안 됩니다.");
        assertEquals(150_000, firstSession.calculateTotalPrice());
    }

    @Test
    void 총_금액은_세션별로_선택한_좌석만_기준으로_계산한다() {
        ReservationSession firstSession = new ReservationSession();
        ReservationSession secondSession = new ReservationSession();

        firstSession.select(new Seat("A-1", Seat.Grade.VIP));
        firstSession.select(new Seat("B-1", Seat.Grade.STANDARD));
        secondSession.select(new Seat("C-1", Seat.Grade.ECONOMY));

        assertEquals(250_000, firstSession.calculateTotalPrice());
        assertEquals(70_000, secondSession.calculateTotalPrice());
    }

    @Test
    void 기존_선택_및_해제_동작을_유지한다() {
        ReservationSession session = new ReservationSession();
        Seat seat = new Seat("A-1", Seat.Grade.VIP);

        assertTrue(session.select(seat));
        assertFalse(session.select(seat), "이미 선택한 좌석의 재선택은 실패해야 합니다.");
        assertFalse(session.select(null), "null 좌석 선택은 실패해야 합니다.");
        assertFalse(session.deselect(null), "null 좌석 해제는 실패해야 합니다.");

        Set<Seat> selectedSeats = session.getSelectedSeats();
        assertThrows(UnsupportedOperationException.class,
                () -> selectedSeats.add(new Seat("B-1", Seat.Grade.STANDARD)),
                "getSelectedSeats()는 수정 불가능한 Set을 반환해야 합니다.");

        assertTrue(session.deselect(seat));
        assertEquals(0, session.calculateTotalPrice());
    }
}
