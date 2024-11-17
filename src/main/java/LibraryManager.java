import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private static JFrame frame = new JFrame("Library Manager");
    private static JPanel panel = new JPanel(); // main panel
    private static JPanel leftPanel = new JPanel(); // list items panel
    private static JPanel rightPanel = new JPanel(); // buttons panel
    private static JPanel listItemsPanel = new JPanel(); // list of available items

    private static JLabel label = new JLabel("Library Management System");
    private static JLabel listOfItemsLabel = new JLabel("Library Management System");

    public static int idCounter = 0;

    private static List<LibraryItem> bookItems = new ArrayList<>();
    private static List<LibraryItem> magazineItems = new ArrayList<>();
    private static List<LibraryItem> availableBookItems = new ArrayList<>();
    private static List<LibraryItem> availableMagazineItems = new ArrayList<>();
    private static List<LibraryItem> availableItems = new ArrayList<>();
    private static List<LibraryItem> selectedItems = new ArrayList<>();

    public LibraryManager() {
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        panel.setLayout(new GridLayout(1, 2, 10, 10));
        panel.add(leftPanel);
        panel.add(rightPanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(150, 180);
        buttonsPanel.setLayout(new GridLayout(3, 1, 10, 10));

        buttonsPanel.add(createButtons("Add item"));
        buttonsPanel.add(createButtons("Borrow item"));
        buttonsPanel.add(createButtons("Remove item"));

        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 80));
        rightPanel.add(buttonsPanel);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(listOfItemsLabel, BorderLayout.NORTH);
        leftPanel.add(listItemsPanel, BorderLayout.CENTER);

        listOfItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        listOfItemsLabel.setFont(new Font("Arial", Font.BOLD, 18));

        listItemsPanel.setLayout(new FlowLayout());


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
        LibraryManager libraryManager = new LibraryManager();

    }

    public static void addItem() {
        idCounter++;
        int id = idCounter;
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
        selectedItems.forEach((item)->{
            item.borrowItem();
        });

    }

    public static void removeItem() {
        selectedItems.forEach((item)->{
            removeLibraryItem(item);
        });
    }

    public static JButton createButtons(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.addActionListener((e)->{
            if (text.equals("Add item")) {
                addItem();
            } else if (text.equals("Borrow item")) {
                updateItems();
            } else if (text.equals("Remove item")) {
                removeItem();
            }
            listItemsPanel.removeAll();
            updateAvailableItems();
            displayItems(availableItems);
            listItemsPanel.revalidate();
            listItemsPanel.repaint();
        });
        return button;
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
        availableBookItems = bookItems.stream().filter((item)->item.isAvailability()).toList();
        availableMagazineItems = magazineItems.stream().filter((item)->item.isAvailability()).toList();
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

}
