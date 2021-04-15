import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * GUI contains the core user interface management and
 * a list of all product being sold
 *
 * @author Evan Johns
 * @version 1.0
 * @since 2021-4-14
 */
public class GUI extends JFrame implements ActionListener {

    // path to text file with all product listings
    private final String PATHNAME = "product.txt";

    // imported list of product
    private ArrayList<Item> pList;

    // format string used for printing product information
    private final String STR_FORMAT = "%s:\n\nDescription:\n%s\n\nCost:\n$%s";

    // delimiter used in determining fields from the source text file
    private final String DELIM = "~ ";

    // GUI elements used in organization of the interface
    private JPanel container;
    private JPanel buttonPanel;
    private JPanel detailsPanel;
    private JTextArea detailsText;

    /**
     * GUI Sets up the user window and interface
     */
    public GUI() {

        // import the list from the given text file into pList
        initializeList();

        container = new JPanel();
        detailsPanel = new JPanel();
        detailsText = new JTextArea();
        buttonPanel = new JPanel();

        // setup product buttons and description panel
        initializePanels();

        setLayout(new GridLayout(1, 2));
        container.add(buttonPanel);
        container.add(detailsPanel);
        add(container);

        // final formatting for the frame
        setTitle("STEM Toys");
        setResizable(false);
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        pack();
    }

    /**
     * Imports the product list into pList
     */
    private void initializeList() {
        pList = new ArrayList<>();

        // counter used in error reporting and debugging
        int c = 0;
        try {
            File file = new File(PATHNAME);
            Scanner scnr = new Scanner(file);
            while (scnr.hasNextLine()) {

                String in = scnr.nextLine();

                // parse the line with the delimiter
                String[] data = in.split(DELIM);

                // import parsed fields into pList
                pList.add(new Item(data[0], data[1], Double.parseDouble(data[2])));
                c++;
            }
            scnr.close();
        } catch (FileNotFoundException e) {
            System.out.println("File read error occurred");
            e.printStackTrace();
            System.exit(1);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] : Double to String conversion error at line : " + c);
            System.exit(1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[ERROR] : No delimiter found at line : " + c);
            System.exit(1);
        }
    }

    /**
     * creates, formats, and displays all elements in the user interface
     */
    private void initializePanels() {

        // setup product buttons
        for (int i = 0; i < pList.size(); i++) {
            pList.get(i).getButton().addActionListener(this);
            buttonPanel.add(pList.get(i).getButton());
        }
        buttonPanel.setPreferredSize(new Dimension(250, 250));

        // set starting product description
        detailsText.setText(String.format(STR_FORMAT,
            pList.get(0).getItemName(),
            pList.get(0).getItemDesc(),
            pList.get(0).getItemCost()));

        // format product description viewing area
        detailsText.setEditable(false);
        detailsText.setLineWrap(true);
        detailsText.setWrapStyleWord(true);
        detailsText.setFocusable(false);
        detailsText.setBackground(UIManager.getColor("Panel.background"));
        detailsText.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(0),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        detailsText.setFont(new Font("Courier", 0, 14));
        detailsText.setPreferredSize(new Dimension(250, 250));
        detailsPanel.add(detailsText);
    }

    /**
     * updates the description panel to display the user's selected product
     * @param e Used in determining the users given action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < pList.size(); i++) {
            if (e.getSource() == pList.get(i).getButton()) {
                detailsText.setText(String.format(STR_FORMAT,
                    pList.get(i).getItemName(),
                    pList.get(i).getItemDesc(),
                    pList.get(i).getItemCost()));
            }
        }
    }


    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
