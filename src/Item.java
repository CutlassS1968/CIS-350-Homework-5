import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Stores various information for a given product
 *
 * @author Evan Johns
 * @version 1.0
 * @since 2021-4-14
 */
public class Item {

  // product's button used in GUI
  private JButton button;

  // Item information
  private String itemName;
  private String itemDesc;
  private double itemCost;

  /**
   * Creates an item object with given information
   * @param itemName product's name
   * @param itemDesc product's description
   * @param itemCost product's cost
   */
  public Item(String itemName, String itemDesc, double itemCost) {
    this.itemName = itemName;
    this.itemDesc = itemDesc;
    this.itemCost = itemCost;
    initialize();
  }

  /**
   * initializes button with item name and formats the button
   */
  private void initialize() {
    button = new JButton(itemName);
    button.setPreferredSize(new Dimension(250, 37));
    button.setBackground(Color.decode("#d6d6d6"));
    button.setBorderPainted(false);
    button.setFocusPainted(false);
    button.setFont(new Font("Ariel", 0, 15));
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemDesc() {
    return itemDesc;
  }

  public void setItemDesc(String itemDesc) {
    this.itemDesc = itemDesc;
  }

  public double getItemCost() {
    return itemCost;
  }

  public void setItemCost(double itemCost) {
    this.itemCost = itemCost;
  }

  public JButton getButton() {
    return button;
  }

  public void setButton(JButton button) {
    this.button = button;
  }
}
