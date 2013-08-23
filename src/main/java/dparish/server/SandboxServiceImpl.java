package dparish.server;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dparish.client.SandboxService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SandboxServiceImpl extends RemoteServiceServlet implements
        SandboxService {

    @Override
    public String saveImageCrop(String imageData) throws IOException {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            // First strip off the beginning data url.
            imageData = imageData.replace("data:image/jpeg;base64,","");
            byte[] jpgBytes = DatatypeConverter.parseBase64Binary(imageData);

            // Now create a temp file and fill it with the jpg data.
            File tempFile = File.createTempFile("avatar",".jpg");
            fos = new FileOutputStream(tempFile);
            bos = new BufferedOutputStream(fos);
            bos.write(jpgBytes);
            return tempFile.getAbsolutePath();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ioe) {
                System.err.println("Exception closing the stream:");
                ioe.printStackTrace();
            }
        }
    }
}
