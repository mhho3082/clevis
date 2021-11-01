package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;
import hk.edu.polyu.comp.comp2021.clevis.controller.PlotHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The view for GUI.
 *
 * @author Ho Man Hin; Chui Wai Lam Nathan
 */
public class GUIView {
    private final CommandHandler commandHandler;
    private final PlotHandler plotHandler;

    private final JFrame mainFrame;
    private final JTextField mainTextField;
    private final PlotPanel mainPlotPanel;

    /**
     * Constructs a GUI view.
     *
     * @param commandHandler the command handler to be used
     */
    public GUIView(CommandHandler commandHandler, PlotHandler plotHandler) {
        this.commandHandler = commandHandler;
        this.plotHandler = plotHandler;
        this.mainFrame = new JFrame("Clevis");
        this.mainTextField = new JTextField();
        this.mainPlotPanel = new PlotPanel();

        this.mainFrame.setSize(Config.GUI_MAIN_FRAME_DIMENSION);
        this.mainFrame.addWindowListener(new WindowControlHandler());
        this.mainFrame.setResizable(false);

        this.mainTextField.addActionListener(new CommandCaller());

        this.mainPlotPanel.setBackground(Color.white);
        this.mainPlotPanel.addMouseMotionListener(new MouseActionHandler());
        this.mainPlotPanel.addMouseWheelListener(new MouseActionHandler());
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

    public class PlotPanel extends JPanel {
        Insets insets;

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            plotHandler.setCenter(getWidth(), getHeight(), getInsets());

            ArrayList<double[]> tempPlotList = plotHandler.getOutPlotList();

            for (double[] tempPlot :
                    tempPlotList) {
                if (tempPlot[4] == 0) {
                    g.drawLine((int) tempPlot[0],(int)  tempPlot[1],(int)  tempPlot[2],(int)  tempPlot[3]);
                } else {
                    g.drawOval((int) tempPlot[0],(int)  tempPlot[1],(int)  tempPlot[2],(int)  tempPlot[3]);
                }
            }
        }
    }

    /**
     * The listener for "exit" commands on the menu bar.
     *
     * @author Ho Man Hin
     */
    public class WindowControlHandler extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            commandHandler.quit();
            quit();
        }
    }

    /**
     * The listener for mouse events on the plot panel.
     *
     * @author Ho Man Hin
     */
    public class MouseActionHandler extends MouseAdapter {
        private Point mousePoint;

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            int movement = e.getWheelRotation();

            // TODO: Re-plot
            mainPlotPanel.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            mousePoint = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int dx = e.getX() - mousePoint.x;
            int dy = e.getY() - mousePoint.y;
            mousePoint = e.getPoint();

            plotHandler.dragUpdate(dx, dy);
            mainPlotPanel.repaint();
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

            // TODO: Re-plot
            plotHandler.commandUpdate();
            mainPlotPanel.repaint();

            if (commandHandler.getQuitting()) {
                quit();
            }
        }
    }
}