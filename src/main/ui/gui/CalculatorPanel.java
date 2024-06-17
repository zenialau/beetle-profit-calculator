package src.main.ui.gui;

import src.main.model.ProfitCalculator;
import src.main.model.oberver.PurchaseObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static src.main.ui.gui.PanelsContainer.MAIN_PANEL;

// panel that shows the current profit or loss
public class CalculatorPanel extends JPanel implements ActionListener, PurchaseObserver {
    private GUISystem system;
    private JPanel container;
    private CardLayout cardLayout;

    private ProfitCalculator calc;

    private JLabel titleLabel;
    private JLabel profitLabel;
    private JButton backButton;

    public CalculatorPanel(GUISystem system, JPanel container) {
        this.system = system;
        this.container = container;
        this.cardLayout = (CardLayout) container.getLayout();
        this.setLayout(new BorderLayout());

        calc = new ProfitCalculator(system.getBuyersMap(), system.getSuppliersMap());
        calc.update();

        setupTitle();
        setupLabel();
        setupButton();
    }

    private void setupTitle() {
        titleLabel = new JLabel("Profit Calculator");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);
    }

    private void setupLabel() {
        profitLabel = new JLabel("$ " + String.valueOf(calc.getProfit()));
        profitLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        profitLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(profitLabel, BorderLayout.CENTER);
    }

    private void setupButton() {
        backButton = new JButton("< back");
        backButton.setPreferredSize(new Dimension(100, 100));
        backButton.addActionListener(this);
        this.add(backButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            cardLayout.show(container, MAIN_PANEL);
        }
    }

    @Override
    public void update() {
        calc.update();
        profitLabel.setText("$ " + String.valueOf(calc.getProfit()));
    }
}
