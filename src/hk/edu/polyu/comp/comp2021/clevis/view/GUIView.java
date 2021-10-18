package hk.edu.polyu.comp.comp2021.clevis.view;

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
    private final JFrame jMainFrame;
    private final JTextField jTextField;
    private final JScrollPane jScrollPane;

    /**
     * Constructs a GUI view.
     *
     * @param handler the command handler to be used
     */
    public GUIView(CommandHandler handler) {
        this.commandHandler = handler;
        this.jMainFrame = new JFrame("Clevis");
        this.jTextField = new JTextField();
        this.jScrollPane = new JScrollPane();

        this.jMainFrame.setResizable(false);
        this.jMainFrame.setSize(600, 650);
        this.jMainFrame.addWindowListener(new WindowControlHandler());

        this.jTextField.addActionListener(new CommandCaller());
    }

    /**
     * The quitting sequence.
     */
    private static void quit() {
        System.exit(0);
    }

    /**
     * Launches the program window.
     */
    public void launchFrame() {
        this.jMainFrame.add(this.jScrollPane, BorderLayout.CENTER);
        this.jMainFrame.add(this.jTextField, BorderLayout.SOUTH);

        this.jMainFrame.setLocationRelativeTo(null);
        this.jMainFrame.setVisible(true);
    }

    /**
     * The listener for "exit" commands on the menu bar.
     *
     * @author Ho Man Hin
     */
    public static class WindowControlHandler implements WindowListener {
        public void windowClosing(WindowEvent e) {
            quit();
        }

        public void windowOpened(WindowEvent e) {
        }

        public void windowIconified(WindowEvent e) {
        }

        public void windowDeiconified(WindowEvent e) {
        }

        public void windowClosed(WindowEvent e) {
        }

        public void windowActivated(WindowEvent e) {
        }

        public void windowDeactivated(WindowEvent e) {
        }
    }

    /**
     * A listener for user-inputted commands.
     *
     * @author Ho Man Hin
     */
    public class CommandCaller implements ActionListener {
        /**
         * (Try to) execute and give output for the user input.
         *
         * @param e a place-holder for the event
         */
        public void actionPerformed(ActionEvent e) {
            commandHandler.exec(jTextField.getText());
            ArrayList<String> tempOutString = commandHandler.getOutString();

            if (!tempOutString.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < tempOutString.size(); i++) {
                    stringBuilder.append(tempOutString.get(i));
                    if (i != tempOutString.size() - 1) {
                        stringBuilder.append("\n");
                    }
                }

                JTextArea jTextArea = new JTextArea(stringBuilder.toString());
                jTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
                jTextArea.setEditable(false);
                jTextArea.setOpaque(false);
                jTextArea.setLineWrap(true);
                jTextArea.setWrapStyleWord(true);
                jTextArea.setBorder(javax.swing.BorderFactory.createEmptyBorder());

                JScrollPane jScrollPane = new JScrollPane(jTextArea);
                jScrollPane.setPreferredSize(new Dimension(500, 200));
                jScrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());

                if (commandHandler.getWarning()) {
                    JOptionPane.showMessageDialog(jMainFrame, jScrollPane, "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(jMainFrame, jScrollPane, "Output", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            jTextField.setText("");

            if (commandHandler.getQuitting()) {
                quit();
            }
        }
    }
}