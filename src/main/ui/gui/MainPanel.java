package src.main.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.GUISystem.PLAIN_18_FONT;
import static src.main.ui.gui.GUISystem.TITLE_FONT;
import static src.main.ui.gui.PanelsContainer.*;

public class MainPanel extends JPanel implements ActionListener {
    protected static final float CENTER = 0.5F;

    private JPanel container;

    private JLabel titleLabel;
    private JButton customersButton;
    private JButton suppliersButton;
    private JButton calculatorButton;

    public MainPanel(JPanel container) {
        this.container = container;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addSpace(1, 60);
        addTitle();
        addSpace(1, 60);
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: add rigid space between elements
    private void addSpace(int width, int height) {
        Dimension spaceDimension = new Dimension(width, height);
        Component space = Box.createRigidArea(spaceDimension);
        this.add(space);
    }

    // MODIFIES: this
    // EFFECTS: add application title and logo to panel
    private void addTitle() {
        titleLabel = new JLabel("etern_mology");

        titleLabel.setFont(TITLE_FONT);
        titleLabel.setAlignmentX(CENTER);

        this.add(titleLabel);
    }

    // MODIFIES: this
    // EFFECTS: add the 3 main buttons to panel
    private void addButtons() {
        Dimension maxDimension = new Dimension(300, 60);

        customersButton = new JButton("Customers");
        suppliersButton = new JButton("Suppliers");
        calculatorButton = new JButton("Profit Calculator");

        customersButton.setFont(PLAIN_18_FONT);
        suppliersButton.setFont(PLAIN_18_FONT);
        calculatorButton.setFont(PLAIN_18_FONT);

        customersButton.setMaximumSize(maxDimension);
        suppliersButton.setMaximumSize(maxDimension);
        calculatorButton.setMaximumSize(maxDimension);

        customersButton.setAlignmentX(CENTER);
        suppliersButton.setAlignmentX(CENTER);
        calculatorButton.setAlignmentX(CENTER);

        customersButton.setFocusPainted(false);
        suppliersButton.setFocusPainted(false);
        calculatorButton.setFocusPainted(false);

        customersButton.addActionListener(this);
        suppliersButton.addActionListener(this);
        calculatorButton.addActionListener(this);

        this.add(customersButton);
        this.add(suppliersButton);
        this.add(calculatorButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout layout = (CardLayout) container.getLayout();
        if (e.getSource() == customersButton) {
            layout.show(container, CUSTOMERS_PANEL);
        } else if (e.getSource() == suppliersButton) {
            layout.show(container, SUPPLIERS_PANEL);
        } else if (e.getSource() == calculatorButton) {
            layout.show(container, CALCULATOR_PANEL);
        }
    }
}
