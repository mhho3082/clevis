package hk.edu.polyu.comp.comp2021.clevis.view;

import hk.edu.polyu.comp.comp2021.clevis.Config;
import hk.edu.polyu.comp.comp2021.clevis.controller.CommandHandler;
import hk.edu.polyu.comp.comp2021.clevis.controller.PlotHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
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
    private final JPanel mainLayoutPanel;
    private final HorizontalRuler mainHorizontalRuler;
    private final VerticalRuler mainVerticalRuler;
    private final JButton mainHomeButton;

    /**
     * Constructs a GUI view.
     *
     * @param commandHandler the command handler to be used
     * @param plotHandler    the plot handler to be used
     */
    public GUIView(CommandHandler commandHandler, PlotHandler plotHandler) {
        this.commandHandler = commandHandler;
        this.plotHandler = plotHandler;
        this.mainFrame = new JFrame("Clevis");
        this.mainTextField = new JTextField();
        this.mainPlotPanel = new PlotPanel();
        this.mainLayoutPanel = new JPanel(new GridBagLayout());
        this.mainHorizontalRuler = new HorizontalRuler();
        this.mainVerticalRuler = new VerticalRuler();
        this.mainHomeButton = new JButton();

        WindowControlHandler windowControlHandler = new WindowControlHandler();
        MouseActionHandler mouseActionHandler = new MouseActionHandler();
        ResizeHandler resizeHandler = new ResizeHandler();
        CommandCaller commandCaller = new CommandCaller();
        HomeHandler homeHandler = new HomeHandler();

        this.mainFrame.setSize(Config.GUI_MAIN_FRAME_SIZE);
        this.mainFrame.setMinimumSize(Config.GUI_MAIN_FRAME_MIN_SIZE);
        this.mainFrame.addWindowListener(windowControlHandler);
        this.mainFrame.addComponentListener(resizeHandler);
        this.mainFrame.setResizable(true);

        this.mainTextField.addActionListener(commandCaller);

        this.mainPlotPanel.setBackground(Color.white);
        this.mainPlotPanel.addMouseListener(mouseActionHandler);
        this.mainPlotPanel.addMouseMotionListener(mouseActionHandler);
        this.mainPlotPanel.addMouseWheelListener(mouseActionHandler);
        this.mainPlotPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.mainHomeButton.setText("Home");
        this.mainHomeButton.setOpaque(false);
        this.mainHomeButton.setBackground(Color.white);
        this.mainHomeButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        this.mainHomeButton.addActionListener(homeHandler);
    }

    /**
     * The quitting sequence.
     */
    private void quit() {
        System.exit(0);
    }

    /**
     * Adds everything to layouts and/or to the main frame.
     * <p>
     * Then launches the program window.
     */
    public void launchFrame() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.mainLayoutPanel.add(this.mainHomeButton, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.mainLayoutPanel.add(this.mainHorizontalRuler, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.mainLayoutPanel.add(this.mainVerticalRuler, gridBagConstraints);

        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = Config.GUI_RATIO_HORIZONTAL;
        gridBagConstraints.weighty = Config.GUI_RATIO_VERTICAL;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.mainLayoutPanel.add(this.mainPlotPanel, gridBagConstraints);

        this.mainFrame.add(this.mainLayoutPanel, BorderLayout.CENTER);
        this.mainFrame.add(this.mainTextField, BorderLayout.SOUTH);

        mainHorizontalRuler.repaint();
        mainVerticalRuler.repaint();

        this.mainFrame.setLocationRelativeTo(null);
        this.mainFrame.setVisible(true);
    }

    /**
     * The panel for plotting the main graph.
     *
     * @author Ho Man Hin
     */
    public class PlotPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            plotHandler.sizeUpdate(getWidth(), getHeight(), getInsets());

            ArrayList<double[]> tempPlotList = plotHandler.getOutPlotList();

            for (double[] tempPlot : tempPlotList) {
                if (tempPlot[4] == 0) {
                    g.drawLine((int) tempPlot[0], (int) tempPlot[1], (int) tempPlot[2],
                            (int) tempPlot[3]);
                } else {
                    g.drawOval((int) tempPlot[0], (int) tempPlot[1], (int) tempPlot[2],
                            (int) tempPlot[3]);
                }
            }
        }
    }

    /**
     * The horizontal ruler of the plot.
     *
     * @author Ho Man Hin
     */
    public class HorizontalRuler extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            double[] size = plotHandler.getHorizontalRulerDimension();
            int height = getHeight();
            int width = getWidth();

            double difference = size[1] - size[0];

            double scale = Math.pow(10, Math.ceil(Math.log10(difference)));
            int counter = 0;
            while ((double) width / Math.ceil(
                    difference / scale) > Config.GUI_RULER_HORIZONTAL_MAJOR_MARKS_MAX_SEPARATION) {
                if (counter % 3 == 1) {
                    scale /= Config.GUI_RULER_REDUCE_2;
                } else {
                    scale /= Config.GUI_RULER_REDUCE_OTHER;
                }
                counter++;
            }

            BigDecimal remainder = BigDecimal.valueOf(size[0]).remainder(BigDecimal.valueOf(scale));

            double printValue = size[0] - remainder.doubleValue();
            while (printValue < size[1]) {
                if (printValue > size[0]) {
                    if (scale >= 1) {
                        g.drawString(String.valueOf(Math.round(printValue)),
                                (int) (width / difference * (printValue - size[0])),
                                g.getFontMetrics().getHeight());
                    } else {
                        double roundRange = Math.pow(10, String.valueOf(scale).length());
                        g.drawString(
                                String.valueOf(Math.round(printValue * roundRange) / roundRange),
                                (int) (width / difference * (printValue - size[0])),
                                g.getFontMetrics().getHeight());
                    }
                    g.drawLine((int) (width / difference * (printValue - size[0])),
                            g.getFontMetrics().getHeight()
                                    + Config.GUI_RULER_HORIZONTAL_LINE_OFFSET,
                            (int) (width / difference * (printValue - size[0])), height);
                }
                printValue += scale;
            }
        }
    }

    /**
     * The vertical ruler of the plot.
     *
     * @author Ho Man Hin
     */
    public class VerticalRuler extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            double[] size = plotHandler.getVerticalRulerDimension();
            int height = getHeight();
            int width = getWidth();

            double difference = size[1] - size[0];

            double scale = Math.pow(10, Math.ceil(Math.log10(difference)));
            int counter = 0;
            while ((double) height / Math.ceil(
                    difference / scale) > Config.GUI_RULER_VERTICAL_MAJOR_MARKS_MAX_SEPARATION) {
                if (counter % 3 == 1) {
                    scale /= Config.GUI_RULER_REDUCE_2;
                } else {
                    scale /= Config.GUI_RULER_REDUCE_OTHER;
                }
                counter++;
            }

            BigDecimal remainder = BigDecimal.valueOf(size[0]).remainder(BigDecimal.valueOf(scale));

            double printValue = size[0] - remainder.doubleValue();
            while (printValue < size[1]) {
                if (printValue > size[0]) {
                    int temp = (int) (height / difference * (printValue - size[0]));
                    if (scale >= 1) {
                        g.drawString(String.valueOf(Math.round(printValue)),
                                Config.GUI_RULER_VERTICAL_OFFSET,
                                (int) (height / difference * (printValue - size[0])
                                        + g.getFontMetrics().getHeight() / 2));
                        g.drawLine(Config.GUI_RULER_VERTICAL_OFFSET * 2 + (int) g.getFontMetrics()
                                .getStringBounds(String.valueOf(Math.round(printValue)), g)
                                .getWidth(), temp, width, temp);
                    } else {
                        double roundRange = Math.pow(10, String.valueOf(scale).length());
                        String outValue =
                                String.valueOf(Math.round(printValue * roundRange) / roundRange);
                        g.drawString(outValue, Config.GUI_RULER_VERTICAL_OFFSET,
                                (int) (height / difference * (printValue - size[0])
                                        + g.getFontMetrics().getHeight() / 2));
                        g.drawLine(Config.GUI_RULER_VERTICAL_OFFSET * 2
                                + (int) g.getFontMetrics().getStringBounds(outValue, g).getWidth(),
                                temp, width, temp);
                    }
                }
                printValue += scale;
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
     * The listener for window resizing.
     *
     * @author Ho Man Hin
     */
    public class ResizeHandler extends ComponentAdapter {
        @Override
        public void componentResized(ComponentEvent e) {
            plotHandler.sizeUpdate(mainFrame.getWidth(), mainFrame.getHeight(),
                    mainFrame.getInsets());
            mainPlotPanel.repaint();
            mainHorizontalRuler.repaint();
            mainVerticalRuler.repaint();
        }
    }

    /**
     * The listener for home button.
     * 
     * @author Ho Man Hin
     */
    public class HomeHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            plotHandler.homeUpdate();
            mainPlotPanel.repaint();
            mainHorizontalRuler.repaint();
            mainVerticalRuler.repaint();
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

            plotHandler.scrollUpdate(movement);
            mainPlotPanel.repaint();
            mainHorizontalRuler.repaint();
            mainVerticalRuler.repaint();
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
            mainHorizontalRuler.repaint();
            mainVerticalRuler.repaint();
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
         * Creates a command caller, and initializes the components for dialog.
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
            String inString = mainTextField.getText();
            if (inString.isEmpty()) {
                return;
            }
            commandHandler.exec(inString);
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
                    JOptionPane.showMessageDialog(mainFrame, this.jScrollPane, "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, this.jScrollPane, "Output",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (!commandHandler.getWarning()) {
                mainTextField.setText("");
            }

            plotHandler.commandUpdate();
            mainPlotPanel.repaint();

            if (commandHandler.getQuitting()) {
                quit();
            }
        }
    }
}