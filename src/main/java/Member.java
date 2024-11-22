import java.util.ArrayList;
import java.util.List;

abstract class Member implements MemberInterface {
    private final String id;
    private String name;
    protected List<LibraryItem> borrowItems;

    public Member(String id, String name){
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return this.id;
    }
    public String getInfo() {
        return "Member{id=" + id + ", name=" + name + ", borrowedItems=" + borrowItems + ".";
    };

    public abstract boolean borrowItem(LibraryItem item);

    public void returnItem(LibraryItem item) {
        this.borrowItems.remove(item);
    }
}
