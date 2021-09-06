import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 * A panel maintaining a picture of a Do Not Enter sign.
 */
public class FieldVisualizer extends JPanel {

    private static final ImageIcon field = new ImageIcon("Field/src/main/resources/images/field.jpeg");
    //can replace field image for new game
    private int x = 212;
    private int y = 356;
    private final Label label = new Label(location(x, y), Label.CENTER);

    /**
     * Called by the runtime system whenever the panel needs painting.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //adds field image
        field.paintIcon(this, g, 0, 0);
        add(label);

        //updates label
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(field.getIconWidth());
                x = e.getX();
                y = e.getY();
                label.setText(location(x, y));
            }
        });
    }

    //translates pixel coordinates to Roadrunner coordinates
    public String location(int x, int y){
        float x_translated = (float) (x - (field.getIconWidth() / 2)) / field.getIconWidth() * 144;
        float y_translated = (float) (y - (field.getIconHeight() / 2)) / field.getIconHeight() * 72;
        DecimalFormat df = new DecimalFormat("##.##");
        return ( df.format(x_translated) + " , " + df.format(y_translated));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            var panel = new FieldVisualizer();
            var frame = new JFrame("Field Coordinate Visualizer");
            frame.setSize(field.getIconWidth(), field.getIconHeight());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
