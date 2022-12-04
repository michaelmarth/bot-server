package ch.marth;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseBody {
    private final File file;
    private final Map<String, String> headers;
    private final int statusCode;

    public HttpResponseBody(String webRoot, HttpRequest request) throws IOException {
        String path = request.getPath();

        // check if the file exists
        File file = new File(webRoot + path);
        if (!file.exists()) {
            // handle file not found
            this.file = null;
            statusCode = 404;
        } else {
            this.file = file;
            statusCode = 200;
        }

        // set the response headers
        headers = new HashMap<>();
        if (statusCode == 200) {
            // set the content type header
            String contentType = Files.probeContentType(file.toPath());
            headers.put("Content-Type", contentType);
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public long getContentLength() {
        if (file == null) {
            return 0;
        }
        return file.length();
    }

    public void write(OutputStream out) throws IOException {
        if (statusCode == 200) {
            // write the file to the output stream
            Files.copy(file.toPath(), out);
        }
    }
}
