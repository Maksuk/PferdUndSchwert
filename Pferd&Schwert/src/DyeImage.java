import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;
import javax.imageio.*;

class DyeImage
{
    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    new DyeImage();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    public DyeImage() throws Exception
    {
        JFrame f = new JFrame();
        Random r = new Random();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BufferedImage[] image = new BufferedImage[4];
        image[0] = ImageIO.read(new File("resources/Pferd2b.png"));
        image[1] = ImageIO.read(new File("resources/Stern2b.png"));
        image[2] = ImageIO.read(new File("resources/Stern1b.png"));
        image[3] = ImageIO.read(new File("resources/Rad1b.png"));
        JPanel panel = new JPanel(new GridLayout(2,2));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(new JLabel(new ImageIcon(image[0])));
        panel.add(new JLabel(new ImageIcon(dye(image[r.nextInt(4)], new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),80)))));
        panel.add(new JLabel(new ImageIcon(dye(image[r.nextInt(4)], new Color(r.nextInt(256),255,r.nextInt(256),80)))));
        panel.add(new JLabel(new ImageIcon(dye(image[r.nextInt(4)], new Color(r.nextInt(256),0,r.nextInt(256),100)))));
        panel.add(new JLabel(new ImageIcon(dye(image[r.nextInt(4)], new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256),100)))));
        panel.add(new JLabel(new ImageIcon(dye(image[r.nextInt(4)], new Color(r.nextInt(256),255,r.nextInt(256),100)))));
        f.getContentPane().add(panel);
        f.pack();
        f.setVisible(true);
        
        JFrame f2 = new JFrame();
        BufferedImage image2 = ImageIO.read(new File("resources/Pferd2b.png"));
        image2 = dye(image2, new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256), 80));
        BufferedImage image3 = ImageIO.read(new File("resources/Prerd2h.png"));
        BufferedImage combined = new BufferedImage(image2.getWidth(), image2.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = combined.getGraphics();
        g.drawImage(image2, 0, 0, null);
        g.drawImage(image3, 0, 0, null);
        
        JPanel panel2 = new JPanel(new GridLayout(1,0));
        panel2.add(new JLabel(new ImageIcon(combined)));
        f2.getContentPane().add(panel2);
        f2.pack();
        f2.setVisible(true);
        
        
    }


    private static BufferedImage dye(BufferedImage image, Color color)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
    }

}