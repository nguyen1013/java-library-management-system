

abstract class LibraryItem {
    private final int id;
    protected boolean availability;
    protected int dueToDate;

    public LibraryItem(int id) {
        this.id = id;
        this.availability = true;
        this.dueToDate = 0;
    }

    public int getId(){
       return this.id;
    }

    public boolean isAvailability(){
        return this.availability;
    }
    public abstract void borrowItem();

    public void returnItem(){
        this.dueToDate = 0;
        this.availability = true;
    }

    @Override
    public String toString() {
        return "LibraryItem{id=" + id + ", availability=" + availability + ", dueToDate=" + dueToDate + "}";
    }
}
