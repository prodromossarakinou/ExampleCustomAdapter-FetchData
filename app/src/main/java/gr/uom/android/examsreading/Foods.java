package gr.uom.android.examsreading;

public class Foods {
    private String name;
    private int rank;
    private String id;

    public Foods(String name, int rank, String id) {
        this.name = name;
        this.rank = rank;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name is "+ name + " Rank is " + rank + " id is " +id;
    }
}
