package app;

import com.sun.source.tree.ModuleTree;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.*;


@ExtendWith(MockitoExtension.class)
class ServiceServletTest {

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    private ServiceServlet serviceServlet;
    private StringWriter stringWriter;
    private PrintWriter writer;


    @BeforeEach
    void init() {
        this.serviceServlet = new ServiceServlet();
        this.stringWriter = new StringWriter();
        this.writer = new PrintWriter(this.stringWriter, true);
    }


    @Test
    void putIndexOfProduct() {
        int id = serviceServlet.getIndexProduct();
        Assertions.assertEquals(id, serviceServlet.putIndexOfProduct());
    }

    @Test
    void putIndexOfOrder() {
        int id = serviceServlet.getIndexOrder();
        Assertions.assertEquals(id, serviceServlet.getIndexOrder());
    }

    @Test
    void doGetCorrectId() throws IOException, ServletException {
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        Mockito.when(httpServletRequest.getParameter("id")).thenReturn("1");
        serviceServlet.doGet(httpServletRequest, httpServletResponse);
        String result = stringWriter.toString();
        Assertions.assertTrue(result.contains(("\"id\":1")));
    }

    @Test
    void doGetNullId() throws IOException, ServletException {
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        Mockito.when(httpServletRequest.getParameter("id")).thenReturn(null);
        serviceServlet.doGet(httpServletRequest, httpServletResponse);
        String result = stringWriter.toString();
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Assertions.assertTrue(result.contains("error: bad request"));
    }

    @Test
    void doGetWrongId() throws IOException, ServletException {
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        Mockito.when(httpServletRequest.getParameter("id")).thenReturn("");
        serviceServlet.doGet(httpServletRequest, httpServletResponse);
        String result = stringWriter.toString();
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Assertions.assertTrue(result.contains("error: bad request"));
    }

    @Test
    void doPostCorrectCall() throws IOException, ServletException {
        String json = """
                {
                  "id": 2,
                  "localDateTime": "2024-12-01T14:30:00",
                  "cost": 299.99,
                  "products": [
                    {
                      "id": 101,
                      "name": "Суші Філадельфія",
                      "cost": 159.99
                    },
                    {
                      "id": 102,
                      "name": "Рол Каліфорнія",
                      "cost": 140.00
                    }
                  ]
                }
                """;
        StringReader stringReader = new StringReader(json);
        BufferedReader reader = new BufferedReader(stringReader);
        Mockito.when(httpServletRequest.getReader()).thenReturn(reader);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        serviceServlet.doPost(httpServletRequest, httpServletResponse);
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_CREATED);
    }
    @Test
    void doPostWrongCall() throws IOException, ServletException {
        String json = "";
        StringReader stringReader = new StringReader(json);
        BufferedReader reader = new BufferedReader(stringReader);
        Mockito.when(httpServletRequest.getReader()).thenReturn(reader);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        serviceServlet.doPost(httpServletRequest, httpServletResponse);
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void doPutCorrectCall() throws IOException, ServletException {
        String json = """
                {
                  "id": 1,
                  "localDateTime": "2024-12-01T14:30:00",
                  "cost": 299.99,
                  "products": [
                    {
                      "id": 101,
                      "name": "Суші Філадельфія",
                      "cost": 159.99
                    },
                    {
                      "id": 102,
                      "name": "Рол Каліфорнія",
                      "cost": 140.00
                    }
                  ]
                }
                """;
        StringReader stringReader = new StringReader(json);
        BufferedReader reader = new BufferedReader(stringReader);
        Mockito.when(httpServletRequest.getReader()).thenReturn(reader);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        serviceServlet.doPut(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
    }
    @Test
    void doPutWrongCall() throws IOException, ServletException {
        String json = """
                {
                  "id": 2,
                  "localDateTime": "2024-12-01T14:30:00",
                  "cost": 299.99,
                  "products": [
                    {
                      "id": 101,
                      "name": "Суші Філадельфія",
                      "cost": 159.99
                    },
                    {
                      "id": 102,
                      "name": "Рол Каліфорнія",
                      "cost": 140.00
                    }
                  ]
                }
                """;
        StringReader stringReader = new StringReader(json);
        BufferedReader reader = new BufferedReader(stringReader);
        Mockito.when(httpServletRequest.getReader()).thenReturn(reader);
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        serviceServlet.doPut(httpServletRequest,httpServletResponse);
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    void doDeleteCorrectCall() throws ServletException, IOException {
        Mockito.when(httpServletRequest.getParameter("id")).thenReturn("1");
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        serviceServlet.doDelete(httpServletRequest, httpServletResponse);
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
    }
    @Test
    void  doDeleteWrongCall() throws IOException, ServletException {
        Mockito.when(httpServletRequest.getParameter("id")).thenReturn("");
        Mockito.when(httpServletResponse.getWriter()).thenReturn(writer);
        serviceServlet.doDelete(httpServletRequest, httpServletResponse);
        Mockito.verify(httpServletResponse).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }


}
