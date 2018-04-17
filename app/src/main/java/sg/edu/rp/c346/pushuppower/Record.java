package sg.edu.rp.c346.pushuppower;

import java.io.Serializable;

public class Record implements Serializable{
    private String score;
    private String date;
    private String time;
    private String id;

    public Record() {

    }

    public Record(String score, String date, String time) {
        this.score = score;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return score;
    }
}
