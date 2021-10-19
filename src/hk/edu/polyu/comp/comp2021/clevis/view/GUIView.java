package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 * The view for GUI.
 *
 * @author Ho Man Hin; Chui Wai Lam Nathan
 */
public class GUIView {
    private final CommandHandler commandHandler;
    private final JFrame mainFrame;
    private final JTextField mainTextField;
    private final PlotPanel mainPlotPanel;

    /**
     * Constructs a GUI view.
     *
     * @param handler the command handler to be used
     */
    public GUIView(CommandHandler handler) {
        this.commandHandler = handler;
        this.mainFrame = new JFrame("Clevis");
        this.mainTextField = new JTextField();
        this.mainPlotPanel = new PlotPanel();

        this.mainFrame.setSize(Config.GUI_MAIN_FRAME_DIMENSION);
        this.mainFrame.addWindowListener(new WindowControlHandler());
        this.mainFrame.setResizable(false);

        this.mainTextField.addActionListener(new CommandCaller());

        this.mainPlotPanel.setBackground(Color.white);
    }

    /**
     * The quitting sequence.
     */
    private void quit() {
        System.exit(0);
    }

    /**
     * Launches the program window.
     */
    public void launchFrame() {
        this.mainFrame.add(this.mainPlotPanel, BorderLayout.CENTER);
        this.mainFrame.add(this.mainTextField, BorderLayout.SOUTH);

        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    public static class PlotPanel extends JPanel {
        /*
         * TODO: Copied from web; demo only, please rewrite completely
         */

        Insets insets;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int x, y;

            // Get the insets.
            insets = getInsets();

            x = getWidth() - this.insets.left;
            y = getHeight() - this.insets.bottom;

            g.drawLine(x - 100, y - 100, 100, 100);
            g.drawOval(100, 100, 100, 100);

            g.drawRect(50, 50, 50, 50);
            g.drawRect(100, 50, 50, 50);
            g.drawRect(150, 50, 50, 50);
            g.drawRect(200, 50, 50, 50);
            g.drawRect(250, 50, 50, 50);

            g.drawRect(-25, -25, 50, 50);
        }
    }

    /**
     * The listener for "exit" commands on the menu bar.
     *
     * @author Ho Man Hin
     */
    public class WindowControlHandler implements WindowListener {
        @Override
        public void windowClosing(WindowEvent e) {
            commandHandler.quit();
            quit();
        }

        @Override
        public void windowOpened(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

    /**
     * A listener for user-inputted commands.
     *
     * @author Ho Man Hin
     */
    public class CommandCaller implements ActionListener {
        private final JTextArea jTextArea;
        private final JScrollPane jScrollPane;

        /**
         * Creates a command caller,
         * and initializes the components for dialog.
         */
        public CommandCaller() {
            jTextArea = new JTextArea();
            jTextArea.setFont(Config.GUI_DIALOG_FONT);
            jTextArea.setEditable(false);
            jTextArea.setOpaque(false);
            jTextArea.setLineWrap(true);
            jTextArea.setWrapStyleWord(true);
            jTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());

            jScrollPane = new JScrollPane(jTextArea);
            jScrollPane.setPreferredSize(Config.GUI_DIALOG_SCROLL_PANE_DIMENSION);
            jScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        }

        /**
         * (Try to) execute and give output for the user input.
         *
         * @param e a place-holder for the event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            commandHandler.exec(mainTextField.getText());
            ArrayList<String> tempOutString = commandHandler.getOutString();

            if (!tempOutString.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < tempOutString.size(); i++) {
                    stringBuilder.append(tempOutString.get(i));
                    if (i != tempOutString.size() - 1) {
                        stringBuilder.append("\n");
                    }
                }

                this.jTextArea.setText(stringBuilder.toString());

                if (commandHandler.getWarning()) {
                    JOptionPane.showMessageDialog(mainFrame, this.jScrollPane, "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, this.jScrollPane, "Output", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            mainTextField.setText("");

            if (commandHandler.getQuitting()) {
                quit();
            }
        }
    }
}