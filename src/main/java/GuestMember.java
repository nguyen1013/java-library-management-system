import java.util.ArrayList;

public class GuestMember extends Member {

    public GuestMember(String id, String name) {
        super(id, name);
        this.borrowItems = new ArrayList<>(1);
    }

    @Override
    public boolean borrowItem(LibraryItem item) {
        if (this.borrowItems.size() < 1) {
            return true;
        } else {
            return false;
        }
    }
}
