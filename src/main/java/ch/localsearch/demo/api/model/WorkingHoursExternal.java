package ch.localsearch.demo.api.model;

import lombok.Data;

@Data
public class WorkingHoursExternal {
    private String start;
    private String end;
    private String type;
}
