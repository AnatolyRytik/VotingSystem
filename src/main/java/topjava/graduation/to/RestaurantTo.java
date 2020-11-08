package topjava.graduation.to;

import topjava.graduation.model.Dish;
import topjava.graduation.model.Vote;

import java.util.List;

public class RestaurantTo {

    private Integer id;

    private String name;

    private List<Dish> dishList;

    private List<Vote> votes;

    private int countVote;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, List<Dish> dishList, List<Vote> votes, int countVote) {
        this.id = id;
        this.name = name;
        this.dishList = dishList;
        this.votes = votes;
        this.countVote = countVote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setMeals(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public int getCountVote() {
        return countVote;
    }

    public void setCountVote(int countVote) {
        this.countVote = countVote;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", meals=" + dishList +
                ", votes=" + votes +
                ", countVote=" + countVote +
                '}';
    }
}