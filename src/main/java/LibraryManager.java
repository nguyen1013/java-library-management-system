import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    JFrame frame = new JFrame("Library Manager");
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Library Manager");
    JPanel buttonPanel = new JPanel();
    JButton addButton = new JButton("Add item");
    JButton borrowButton = new JButton("Borrow item");
    JButton removeButton = new JButton("Remove item");
    JLabel availableItems = new JLabel("Available items");
    JLabel selectedItems = new JLabel("Selected items");
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();
    JPanel availablePanel = new JPanel();
    JPanel selectedPanel = new JPanel();

    private static List<LibraryItem> bookItems = new ArrayList<>();
    private static List<LibraryItem> magazineItems = new ArrayList<>();

    private static List<LibraryItem> availableList = new ArrayList<>();
    private static List<LibraryItem> selectedList = new ArrayList<>();

    public LibraryManager() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        panel.setLayout(new GridLayout(1, 2, 10, 10));
        panel.add(leftPanel);
        panel.add(rightPanel);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(availableItems, BorderLayout.NORTH);
        leftPanel.add(availablePanel, BorderLayout.CENTER);
        availableItems.setHorizontalAlignment(SwingConstants.CENTER);
        availablePanel.setLayout(new FlowLayout());

        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(selectedItems, BorderLayout.NORTH);
        rightPanel.add(selectedPanel, BorderLayout.CENTER);
        selectedPanel.setLayout(new FlowLayout());

        selectedPanel.setLayout(new FlowLayout());
        selectedPanel.add(selectedItems);

        createLibraryList();
        availableList = createListItems();
        displayItems(availableList, availablePanel);

        buttonPanel.add(addButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(removeButton);

        frame.setVisible(true);
    }


    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
    }

    public static void createLibraryList() {
        addLibraryItem(new Book(1, "Book 1"));
        addLibraryItem(new Book(2, "Book 2"));
        addLibraryItem(new Book(3, "Book 3"));
        addLibraryItem(new Magazine(4, 1));
        addLibraryItem(new Magazine(5, 2));
        addLibraryItem(new Magazine(6, 3));
    }

    public static List<LibraryItem> createListItems() {
        List<LibraryItem> items = new ArrayList<>();
        items.addAll(bookItems);
        items.addAll(magazineItems);
        return items;
    }

    public static void displayItems(List<LibraryItem> items, JPanel panel) {
        items.forEach((item)->{
            JCheckBox checkBox = new JCheckBox(item.toString());
            checkBox.setPreferredSize(new Dimension(300, 30));
            panel.add(checkBox);
            checkBox.addItemListener((e)->{
                if (checkBox.isSelected()) {
                    selectedList.add(item);
                } else {
                    selectedList.remove(item);
                }
            });
        });
    }

    public static void addLibraryItem(LibraryItem item) {
        if (item instanceof Book) {
            bookItems.add(item);
        }
        if (item instanceof Magazine) {
            magazineItems.add(item);
        }
    }

    public static void removeLibraryItem(LibraryItem item) {
        if (item instanceof Book) {
            bookItems.remove(item);
        }
        if (item instanceof Magazine) {
            magazineItems.remove(item);
        }
    }
}
