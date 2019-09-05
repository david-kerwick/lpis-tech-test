package ie.leaseplan.tech.techtest.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Deal {
    private int id;
    private int retention;
    private int limit;
    private List<Integer> perils;
    private List<Integer> regions;

}
