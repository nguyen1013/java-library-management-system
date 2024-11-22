public class Magazine extends LibraryItem{
    private final String issue;

    public Magazine(String id, String issue){
        super(id);
        this.issue = issue;
    }

    public String getIssue(){
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
