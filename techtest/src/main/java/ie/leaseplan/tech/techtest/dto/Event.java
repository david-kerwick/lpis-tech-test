package ie.leaseplan.tech.techtest.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Event {
    private int id;
    private int peril;
    private int region;
    private int lossFromEvent;
}
