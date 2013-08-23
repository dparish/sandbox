package dparish.client;

import java.io.IOException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("sandbox")
public interface SandboxService extends RemoteService {
  String saveImageCrop(String imageData) throws IOException;
}
