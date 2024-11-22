import java.util.ArrayList;

public class FacultyMember extends Member{

    public FacultyMember(String id, String name) {
        super(id, name);
        this.borrowItems = new ArrayList<>(5);
    }

    @Override
    public boolean borrowItem(LibraryItem item) {
        if (this.borrowItems.size() < 5){
            return true;
        } else {
            return false;
        }
    }
}
