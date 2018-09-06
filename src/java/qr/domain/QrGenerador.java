
package qr.domain;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import javax.imageio.ImageIO;
import sun.misc.BASE64Encoder;

/**
 *
 * @author elPoeta
 */
public class QrGenerador {
    
    public static BufferedImage generarQR(String texto, int size)
    {
        BitMatrix matrix;
        Writer writer = new MultiFormatWriter();
        try {            
            EnumMap<EncodeHintType,String> hints = new EnumMap<EncodeHintType,String>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");            
            matrix = writer.encode(texto, BarcodeFormat.QR_CODE, size, size, hints);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(matrix, "PNG", output);
            byte[] textoArray = output.toByteArray();
            ByteArrayInputStream input = new ByteArrayInputStream(textoArray);
            return ImageIO.read(input);            
        } catch (com.google.zxing.WriterException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
     public static String qrBase64(BufferedImage imagen, String tipo) {
        String imagenString = null;
        String imagenBase64 = "data:image/png;base64,";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
       
        try {
            ImageIO.write(imagen, tipo, bos);
            byte[] imageBytes = bos.toByteArray();
 
            BASE64Encoder encoder = new BASE64Encoder();
            imagenString = encoder.encode(imageBytes);
            imagenBase64 += imagenString;      
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   
        return imagenBase64;
    }
}
