package Entities;

public abstract class AbstractEntity {
   private int id;

    public AbstractEntity(int id) {
        this.id = id;
    }

    public AbstractEntity() {
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
