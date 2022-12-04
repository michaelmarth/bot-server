package ch.marth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class HttpResponse {
    private final HttpResponseHeaders headers;
    private final HttpResponseBody body;

    public HttpResponse(String webRoot, HttpRequest request) throws IOException {
        headers = new HttpResponseHeaders();
        body = new HttpResponseBody(webRoot, request);

        // set the response status code
        int statusCode = body.getStatusCode();
        headers.setStatusCode(statusCode);

        // set the response headers
        Map<String, String> responseHeaders = body.getHeaders();
        for (Map.Entry<String, String> entry : responseHeaders.entrySet()) {
            headers.setHeader(entry.getKey(), entry.getValue());
        }

        // set the content length header
        long contentLength = body.getContentLength();
        headers.setHeader("Content-Length", String.valueOf(contentLength));
    }


    public void write(OutputStream out) throws IOException {
        // write the response headers
        headers.write(out);

        // write the response body
        body.write(out);
    }
}
