package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TicketTest {

    @Test
    @DisplayName("티켓을 구매 못하는 경우 확인")
    void validateMinimumTicketPrice() {
        assertThatThrownBy(() -> new Ticket(new Money(900)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(
                String.format(Ticket.TICKET_MINIMUM_PRICE_ERROR_MESSAGE, Ticket.TICKET_PRICE));
    }

    @Test
    @DisplayName("수동 구매가 가능한지 확인")
    void validateMaximumManualBuy() {
        Ticket totalTicket = new Ticket(new Money(14000));
        assertThatThrownBy(() -> new Ticket(15, totalTicket))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(
                String.format(Ticket.MANUAL_BUY_ERROR_MESSAGE, totalTicket.getCount())
            );
    }
}
