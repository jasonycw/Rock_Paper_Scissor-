package cs4280.model;

public class Rank {
    private int rank;
    private String name;
    private String value;

    public Rank(int rank, String name, String value) {
        this.rank=rank;
        this.name = name;
        this.value = value;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
