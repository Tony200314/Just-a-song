import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Musicmain {
    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        JFrame frame = new JFrame("Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton playButton = new JButton("Play");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");
        JButton quitButton = new JButton("Quit");

        JLabel imageLabel = new JLabel();
        try {
            Image img = ImageIO.read(new File("C:\\VS code\\Music\\src\\main\\java\\bild.jpeg"));

            Image resizedImg = img.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(resizedImg);
            imageLabel.setIcon(new ImageIcon(new ImageIcon(resizedImg).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = new File("src/main/java/er.wav");

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            playButton.addActionListener(e -> {
                clip.start();
                imageLabel.setText("Playing...");
            });
            stopButton.addActionListener(e -> {
                clip.stop();
                imageLabel.setText("Stopped");
            });
            resetButton.addActionListener(e -> {
                clip.setFramePosition(0);
                imageLabel.setText("Reset");
            });
            quitButton.addActionListener(e -> {
                clip.stop();
                System.exit(0);
            });
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.add(playButton);
        panel.add(stopButton);
        panel.add(resetButton);
        panel.add(quitButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(imageLabel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
