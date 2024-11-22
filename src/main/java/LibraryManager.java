import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LibraryManager {
    private static final JPanel listItemsPanel = new JPanel(); // list of available items
    private static final List<LibraryItem> bookItems = new ArrayList<>();
    private static final List<LibraryItem> magazineItems = new ArrayList<>();
    private static List<LibraryItem> availableItems = new ArrayList<>(); // to store available items for displaying in the list
    private static final List<LibraryItem> selectedItems = new ArrayList<>(); // to store selected checkbox items for borrowing or removing

    public LibraryManager() {
        JFrame frame = new JFrame("Library Manager");
        JLabel label = new JLabel("Library Management System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel listOfItemsLabel = new JLabel("List of available items");
        listOfItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        listOfItemsLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(150, 180);
        buttonsPanel.setLayout(new GridLayout(3, 1, 10, 10));

        buttonsPanel.add(createButtons("Add item"));
        buttonsPanel.add(createButtons("Borrow item"));
        buttonsPanel.add(createButtons("Remove item"));

        JPanel leftPanel = new JPanel(); // list items panel
        JPanel rightPanel = new JPanel(); // contain buttons panel
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 80));
        rightPanel.add(buttonsPanel);

        leftPanel.setLayout(new BorderLayout());
        listItemsPanel.setLayout(new FlowLayout());
        leftPanel.add(listOfItemsLabel, BorderLayout.NORTH);
        leftPanel.add(listItemsPanel, BorderLayout.CENTER);

        JPanel panel = new JPanel(); // main panel
        panel.setLayout(new GridLayout(1, 2, 10, 10));
        panel.add(leftPanel);
        panel.add(rightPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.add(label, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LibraryManager();
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

    public static void updateAvailableItems() {
        List<LibraryItem> availableBookItems = bookItems.stream().filter(LibraryItem::isAvailability).toList();
        List<LibraryItem> availableMagazineItems = magazineItems.stream().filter(LibraryItem::isAvailability).toList();
        availableItems = new ArrayList<>();
        availableItems.addAll(availableBookItems);
        availableItems.addAll(availableMagazineItems);
    }

    public static void displayItems(List<LibraryItem> items) {
        items.forEach((item)->{
            JCheckBox checkBox = new JCheckBox(item.toString());
            checkBox.setPreferredSize(new Dimension(300, 30));
            listItemsPanel.add(checkBox);
            checkBox.addItemListener((e)->{
                if (checkBox.isSelected()) {
                    selectedItems.add(item);
                } else {
                    selectedItems.remove(item);
                }
            });
        });
    }

    public static void addItem() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();

        String[] options = {"Book", "Magazine"};
        String selectedItem = (String) JOptionPane.showInputDialog(null, "Choose item type", "Add item", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (selectedItem.equals("Book")) {
            String title = JOptionPane.showInputDialog("Enter book title");
            Book book = new Book(id, title);
            addLibraryItem(book);
        } else if (selectedItem.equals("Magazine")) {
            String issue = JOptionPane.showInputDialog("Enter magazine issue");
            Magazine magazine = new Magazine(id, issue);
            addLibraryItem(magazine);
        }
    }

    public static void updateItems() {
        selectedItems.forEach(LibraryItem::borrowItem);
    }

    public static void removeItem() {
        selectedItems.forEach(LibraryManager::removeLibraryItem);
    }

    public static JButton createButtons(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener((e)->{
            switch (text) {
                case "Add item" -> addItem();
                case "Borrow item" -> updateItems();
                case "Remove item" -> removeItem();
            }
            listItemsPanel.removeAll();
            updateAvailableItems();
            displayItems(availableItems);
            listItemsPanel.revalidate();
            listItemsPanel.repaint();
            System.out.println(bookItems);
            System.out.println(magazineItems);
            System.out.println(availableItems);
            System.out.println();
        });
        return button;
    }
}
