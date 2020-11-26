package main.api.response;

import lombok.Data;

import java.util.ArrayList;
@Data
public class CalendarResponse {
    private ArrayList<Integer> years;
    private ArrayList<String> posts;
}
