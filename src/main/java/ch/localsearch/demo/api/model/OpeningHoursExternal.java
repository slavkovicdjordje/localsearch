package ch.localsearch.demo.api.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class OpeningHoursExternal {
    private HashMap<String, List<WorkingHoursExternal>> days;
}
