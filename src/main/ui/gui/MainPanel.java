package src.main.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.GUISystem.PLAIN_18_FONT;
import static src.main.ui.gui.GUISystem.TITLE_FONT;
import static src.main.ui.gui.PanelsContainer.BUYERS_PANEL;
import static src.main.ui.gui.PanelsContainer.SUPPLIERS_PANEL;

public class MainPanel extends JPanel implements ActionListener {
    protected static final float CENTER = 0.5F;

    private JPanel container;

    private JLabel titleLabel;
    private JButton buyersButton;
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

        buyersButton = new JButton("Buyers");
        suppliersButton = new JButton("Suppliers");
        calculatorButton = new JButton("Profit Calculator");

        buyersButton.setFont(PLAIN_18_FONT);
        suppliersButton.setFont(PLAIN_18_FONT);
        calculatorButton.setFont(PLAIN_18_FONT);

        buyersButton.setMaximumSize(maxDimension);
        suppliersButton.setMaximumSize(maxDimension);
        calculatorButton.setMaximumSize(maxDimension);

        buyersButton.setAlignmentX(CENTER);
        suppliersButton.setAlignmentX(CENTER);
        calculatorButton.setAlignmentX(CENTER);

        buyersButton.setFocusPainted(false);
        suppliersButton.setFocusPainted(false);
        calculatorButton.setFocusPainted(false);

        buyersButton.addActionListener(this);
        suppliersButton.addActionListener(this);
        calculatorButton.addActionListener(this);

        this.add(buyersButton);
        this.add(suppliersButton);
        this.add(calculatorButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyersButton) {
            CardLayout layout = (CardLayout) container.getLayout();
            layout.show(container, BUYERS_PANEL);
        } else if (e.getSource() == suppliersButton) {
            CardLayout layout = (CardLayout) container.getLayout();
            layout.show(container, SUPPLIERS_PANEL);
        } else if (e.getSource() == calculatorButton) {
            System.out.println("calculator");
        }
    }
}
