package ch.localsearch.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Day {
    private String days;
    private String status;
    private List<WorkingHours> workingHours;
}
