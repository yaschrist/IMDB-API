import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class stickerFactory {

    void creat(InputStream inputStream, String fileName) throws IOException {
        //image reading
        //InputStream inputStream = new FileStream(new File("input/filme.jpg"));
       // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_2.jpg").openStream();
        BufferedImage originalImage = ImageIO.read(inputStream);
       
        //create a new imagem with new size and transparency 
        int width = originalImage.getWidth(); //altura
        int height = originalImage.getHeight(); //largura
        int newWidth = width + 200;
        BufferedImage newImage = new BufferedImage(height, newWidth, BufferedImage.TRANSLUCENT); //alpha image
        
        //copy the original to a new in memory
        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        //graphic is the pen of the image
        graphics.drawImage(originalImage, 0, 0, null);

        //set the font
        var font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.yellow);
        graphics.setFont(font);

        //write a sentence in the image
        graphics.drawString("foda", 0, newWidth - 100 );
    
        //write the new image in a new file
        ImageIO.write(newImage, "png", new File(fileName));


    }
    
   // public static void main(String[] args) throws IOException {
   //     stickerFactory factory = new stickerFactory();
   //     factory.create();
    //}
}
