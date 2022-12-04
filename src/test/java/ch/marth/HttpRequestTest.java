package ch.marth;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class HttpRequestTest {
    @Test
    public void testHttpRequest() throws Exception {
        String requestString = "GET / HTTP/1.1\r\n"
            + "Host: localhost:8080\r\n"
            + "Connection: keep-alive\r\n"
            + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36\r\n"
            + "Accept: */*\r\n"
            + "Sec-Fetch-Site: none\r\n"
            + "Sec-Fetch-Mode: cors\r\n"
            + "Sec-Fetch-Dest: empty\r\n"
            + "Referer: http://localhost:8080/\r\n"
            + "Accept-Encoding: gzip, deflate, br\r\n"
            + "Accept-Language: en-US,en;q=0.9\r\n"
            + "\r\n";
        StringReader reader = new StringReader(requestString);
        BufferedReader bufferedReader = new BufferedReader(reader);

        HttpRequest request = new HttpRequest(bufferedReader);
        assertEquals("GET", request.getMethod());
        assertEquals("/", request.getPath());

        Map<String, String> headers = request.getHeaders();
        assertEquals("localhost:8080", headers.get("Host"));
        assertEquals("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36", headers.get("User-Agent"));
        assertEquals("en-US,en;q=0.9", headers.get("Accept-Language"));

        Map<String, String> params = request.getParams();
        assertEquals(0, params.size());
    }

    @Test
public void testHttpRequestWithParams() throws Exception {
    String requestString = "GET /test?foo=bar&baz=qux HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "Connection: keep-alive\r\n"
        + "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36\r\n"
        + "Accept: */*\r\n"
        + "Sec-Fetch-Site: none\r\n"
        + "Sec-Fetch-Mode: cors\r\n"
        + "Sec-Fetch-Dest: empty\r\n"
        + "Referer: http://localhost:8080/\r\n"
        + "Accept-Encoding: gzip, deflate, br\r\n"
        + "Accept-Language: en-US,en;q=0.9\r\n"
        + "\r\n";
    StringReader reader = new StringReader(requestString);
    BufferedReader bufferedReader = new BufferedReader(reader);

    HttpRequest request = new HttpRequest(bufferedReader);
    assertEquals("GET", request.getMethod());
    assertEquals("/test", request.getPath());

    Map<String, String> headers = request.getHeaders();
    assertEquals("localhost:8080", headers.get("Host"));
    assertEquals("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36", headers.get("User-Agent"));
    assertEquals("en-US,en;q=0.9", headers.get("Accept-Language"));

    Map<String, String> params = request.getParams();
    assertEquals(2, params.size());
    assertEquals("bar", params.get("foo"));
    assertEquals("qux", params.get("baz"));
}

}