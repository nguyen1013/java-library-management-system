public class Book extends LibraryItem{
    private final String title;

    public Book(int id, String title){
        super(id);
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    @Override
    public void borrowItem(){
        this.availability = false;
        this.dueToDate = 28;
    }

    @Override
    public String toString() {
        return "Book: " + this.getTitle() +" - id: " + this.getId();
    }
}
