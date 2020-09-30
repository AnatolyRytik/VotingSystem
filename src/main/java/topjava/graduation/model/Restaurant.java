package topjava.graduation.model;

import java.util.List;

public class Restaurant {

    private String name;
    private List<Lunch> lunchList;
    private List<Vote> votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lunch> getLunchList() {
        return lunchList;
    }

    public void setLunchList(List<Lunch> lunchList) {
        this.lunchList = lunchList;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
