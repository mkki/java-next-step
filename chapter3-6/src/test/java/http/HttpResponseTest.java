package http;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import static org.junit.Assert.*;

public class HttpResponseTest {
    private String testDirectory = "./src/test/resources/";

    @Test
    public void responseForward() throws Exception {
        // 응답 본문에 `index.html`이 포함되어 있어야 한다.
        HttpResponse response = new HttpResponse(createOutputStream("Http_Forward.txt"));
        response.forward("/index.html");
    }

    @Test
    public void responseRedirect() throws Exception {
        // 응답 헤더에 `Location: /index.html`이 포함되어 있어야 한다.
        HttpResponse response = new HttpResponse(createOutputStream("Http_Redirect.txt"));
        response.sendRedirect("/index.html");
    }

    @Test
    public void responseCookies() throws Exception {
        // 응답 헤더에 `Set-Cookie: logined=true`가 포함되어 있어야 한다.
        HttpResponse response = new HttpResponse(createOutputStream("Http_Cookies"));
        response.addHeader("Set-Cookie", "logined=true");
        response.sendRedirect("/index.html");
    }

    private OutputStream createOutputStream(String fileName) throws FileNotFoundException{
        return new FileOutputStream(new File(testDirectory + fileName));
    }
}