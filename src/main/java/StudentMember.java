import java.util.ArrayList;

public class StudentMember extends Member{

    public StudentMember(String id, String name){
        super(id, name);
        this.borrowItems = new ArrayList<>(3);
    }

    @Override
    public boolean borrowItem(LibraryItem item) {
        if (this.borrowItems.size() < 3){
            return true;
        } else {
            return false;
        }
    }
}
