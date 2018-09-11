
package qr.domain;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;

import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.util.EnumMap;
import javax.imageio.ImageIO;

import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            EnumMap<EncodeHintType,String> hints = new EnumMap<>(EncodeHintType.class);
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
            
            imagenString = Base64.getEncoder().encodeToString(imageBytes);
            imagenBase64 += imagenString;      
         
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   
        return imagenBase64;
    }
     
     public static String qrDecode(String texto64){
            String base64Encoded = texto64.replace("data:image/png;base64,","");
 
            byte[] decoder = Base64.getDecoder().decode(base64Encoded);
            BufferedImage bufferedImage;
            Result result = null;
            
       try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(decoder));
            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap);
        } catch (IOException ex) {
            Logger.getLogger(QrGenerador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotFoundException ex) {
            Logger.getLogger(QrGenerador.class.getName()).log(Level.SEVERE, null, ex);
        }
            
         return result.getText();
     }
}
