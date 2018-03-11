package http;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class RequestLineTest {
    @Test
    public void create_method(){
        RequestLine line = new RequestLine("GET /index.html HTTP/1.1");
        assertEquals(HttpMethod.GET, line.getMethod());

        line = new RequestLine("POST /ndex.html HTTP/1.1");
        assertEquals(HttpMethod.POST, line.getMethod());
    }

    @Test
    public void create_path_and_params(){
        RequestLine line = new RequestLine("GET /user/create?userId=id&name=name HTTP/1.1");

        assertEquals(HttpMethod.GET, line.getMethod());
        assertEquals("/user/create", line.getPath());
        Map<String, String> params = line.getParams();
        assertEquals("name",params.get("name"));
        assertEquals(2, params.size());
    }
}