package ch.marth;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseHeaders {
    private final Map<String, String> headers;
    private int statusCode;

    public HttpResponseHeaders() {
        headers = new HashMap<>();
    }

    public void setHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void write(OutputStream out) throws IOException {
        // write the status line
        String statusLine = "HTTP/1.1 " + statusCode + " ";
        switch (statusCode) {
            case 200:
                statusLine += "OK";
                break;
            case 404:
                statusLine += "Not Found";
                break;
            default:
                // handle other status codes
        }
        statusLine += "\r\n";
        out.write(statusLine.getBytes());

        // write the headers
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            String headerLine = entry.getKey() + ": " + entry.getValue() + "\r\n";
            out.write(headerLine.getBytes());
        }

        // write the end of headers
        out.write("\r\n".getBytes());
    }
}
