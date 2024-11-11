public class Magazine extends LibraryItem{
    private final int issue;

    public Magazine(int id, int issue){
        super(id);
        this.issue = issue;
    }

    public int getIssue(){
        return this.issue;
    }

    @Override
    public void borrowItem(){
        this.availability = false;
        this.dueToDate = 14;
    }

    @Override
    public String toString() {
        return "Magazine issue: " + this.getIssue() +" - id: " + this.getId();
    }
}
