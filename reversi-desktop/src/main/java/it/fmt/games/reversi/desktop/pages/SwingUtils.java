package it.fmt.games.reversi.desktop.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class SwingUtils {
    private SwingUtils() {

    }

    private static final Font font;

    private static final Dimension btnDim;

    static {
        font=new Font("Verdana", Font.PLAIN, 18);
        btnDim = new Dimension(250, 40);
    }

    public static void buildButton(JPanel panel, GridBagConstraints gbc, String text, int position, ActionListener listener) {
        JButton button = new JButton(text);
        button.setPreferredSize(btnDim);
        button.setFont(font);
        button.setFocusable(false);
        button.setPreferredSize(btnDim);
        setGbc(gbc, 0, position, 1, 1);
        panel.add(button, gbc);
        button.addActionListener(listener);
    }

    public static void setGbc(GridBagConstraints gbc, int gridx, int gridy, int weightx, int weighty) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
    }
}
