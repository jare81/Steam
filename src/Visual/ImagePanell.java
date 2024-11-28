/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Dell
 */
public class ImagePanell extends JPanel  {
   private JPanel imageContainer;
    private JScrollPane scrollPane;

    public ImagePanell() {
        setLayout(new BorderLayout());
        imageContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scrollPane = new JScrollPane(imageContainer);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addImages(ArrayList<ImageIcon> images) {
        for (ImageIcon image : images) {
            // Redimensionar la imagen
            Image img = image.getImage().getScaledInstance(250, 150, Image.SCALE_SMOOTH);
            ImageIcon resizedImage = new ImageIcon(img);
            JLabel imageLabel = new JLabel(resizedImage);
            imageContainer.add(imageLabel);
        }
        imageContainer.revalidate();
        imageContainer.repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Panel Example");
        ImagePanell imagePanel = new ImagePanell();

        // Example images
        ArrayList<ImageIcon> images = new ArrayList<>();
        images.add(new ImageIcon("src/imags/hh.png"));
        images.add(new ImageIcon("src/imags/yy.png"));
        images.add(new ImageIcon("src/imags/xx.png"));

        imagePanel.addImages(images);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(imagePanel); // Asegúrate de agregar el panel al frame
        frame.setVisible(true);
    }

   

}
