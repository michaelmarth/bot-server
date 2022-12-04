package ch.marth;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
  private String method;
  private String path;
  private Map<String, String> headers;
  private Map<String, String> params;

  public HttpRequest(BufferedReader reader) throws IOException {
      // parse the request line
      String[] requestLine = reader.readLine().split(" ");
      method = requestLine[0];
      path = requestLine[1];

      // parse the headers
      headers = new HashMap<>();
      String line;
      while ((line = reader.readLine()) != null && !line.isEmpty()) {
          String[] parts = line.split(": ");
          headers.put(parts[0], parts[1]);
      }

      // parse the parameters
      params = new HashMap<>();
      if (path.contains("?")) {
          String[] pathParts = path.split("\\?");
          path = pathParts[0];
          String[] paramParts = pathParts[1].split("&");
          for (String param : paramParts) {
              String[] parts = param.split("=");
              params.put(parts[0], parts[1]);
          }
      }
  }

  public String getMethod() {
      return method;
  }

  public String getPath() {
      return path;
  }

  public Map<String, String> getHeaders() {
      return headers;
  }

  public Map<String, String> getParams() {
      return params;
  }
}

