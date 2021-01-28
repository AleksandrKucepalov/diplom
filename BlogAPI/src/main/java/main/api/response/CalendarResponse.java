package main.api.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

@Data
public class CalendarResponse {

    private ArrayList<Integer> years;
    private Map<String,Integer> posts;
}
