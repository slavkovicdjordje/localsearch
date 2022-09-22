package ch.localsearch.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkingHours {
    private String start;
    private String end;
}
