package ch.localsearch.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Restaurant {
    private String name;
    private String address;
    private List<Day> days = new ArrayList<>();
}
