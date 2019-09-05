package ie.leaseplan.tech.techtest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResult {
    private int eventId;
    private int dealId;
    private int loss;
}
