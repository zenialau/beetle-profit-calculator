package src.main.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// buyers screen that shows existing buyers and allow the option to add buyer or purchase, or go back to main menu
public class BuyersPanel extends JPanel {
    protected static final float CENTER = 0.5F;

    private JLabel titleLabel;


    // EFFECTS: constructs the buyers panel
    public BuyersPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addTitle();

        addScrollPane();
    }

    // MODIFIES: this
    // EFFECTS: add a scroll pane containing buttons of buyers
    private void addScrollPane() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: add title label to this panel
    private void addTitle() {
        titleLabel = new JLabel("Buyers");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        titleLabel.setAlignmentX(CENTER);
        this.add(titleLabel);
    }

}
