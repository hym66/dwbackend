package cn.edu.tongji.dwbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieQuery {
    private String movieTitle;
    private String genreTitle;
    private String startTime;
    private String endTime;
    private List<String> directorList;
    private List<String> starList;
    private List<String> actorList;
    private float minScore;
    private float maxScore;
}
