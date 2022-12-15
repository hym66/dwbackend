package cn.edu.tongji.dwbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class MovieQuery {
    private String movieTitle;
    private String genreTitle;
    private BasicTime startDate;
    private BasicTime endDate;
    private List<String> directorName;
    private List<String> starName;
    private List<String> actorName;
    private float minScore;
    private float maxScore;

    public MovieQuery(String movieTitle, String genreTitle, BasicTime startDate, BasicTime endDate, List<String> directorName, List<String> starName, List<String> actorName, float minScore, float maxScore) {
        this.movieTitle = movieTitle;
        this.genreTitle = genreTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.directorName = directorName;
        this.starName = starName;
        this.actorName = actorName;
        this.minScore = minScore;
        this.maxScore = maxScore;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getGenreTitle() {
        return genreTitle;
    }

    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }

    public BasicTime getStartDate() {
        return startDate;
    }

    public void setStartDate(BasicTime startDate) {
        this.startDate = startDate;
    }

    public BasicTime getEndDate() {
        return endDate;
    }

    public void setEndDate(BasicTime endDate) {
        this.endDate = endDate;
    }

    public List<String> getDirectorName() {
        return directorName;
    }

    public void setDirectorName(List<String> directorName) {
        this.directorName = directorName;
    }

    public List<String> getStarName() {
        return starName;
    }

    public void setStarName(List<String> starName) {
        this.starName = starName;
    }

    public List<String> getActorName() {
        return actorName;
    }

    public void setActorName(List<String> actorName) {
        this.actorName = actorName;
    }

    public float getMinScore() {
        return minScore;
    }

    public void setMinScore(float minScore) {
        this.minScore = minScore;
    }

    public float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(float maxScore) {
        this.maxScore = maxScore;
    }
}
