
package qr.web.server;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import qr.domain.QrGenerador;
import qr.domain.QrTexto;
import qr.util.GsonUtil;

/**
 *
 * @author elPoeta
 */
@WebServlet(name = "QrServer", urlPatterns = {"/QrServer"})
public class QrServer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          response.setContentType("text/html;charset=UTF-8");
          String codeReq = request.getReader().readLine();
          QrTexto code = (QrTexto) GsonUtil.CONVERTIR.fromJson(codeReq, QrTexto.class);
          System.out.println("code "+code);
          BufferedImage imagen = QrGenerador.generarQR(code.getCode(), 150);
          String qrImgBase64 = QrGenerador.qrBase64(imagen,"PNG");
       
            System.out.println("decoder>> "+QrGenerador.qrDecode(qrImgBase64));
      
          response.getWriter().print(GsonUtil.CONVERTIR.toJson(qrImgBase64));
    }

}
