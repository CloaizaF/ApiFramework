package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {

    public static RequestSpecification reqSpec;

    public RequestSpecification requestSpecification() throws IOException {
        if (reqSpec == null) {
            PrintStream log = new PrintStream(new FileOutputStream("C:\\Users\\loaiz\\OneDrive\\Documentos\\Automation\\ApiFramework\\files\\logging.txt"));
            reqSpec = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
        }
        return reqSpec;
    }

    public String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream file = new FileInputStream("C:\\Users\\loaiz\\OneDrive\\Documentos\\Automation\\ApiFramework\\src\\test\\java\\resources\\global.properties");
        properties.load(file);
        return properties.getProperty(key);
    }

    public String getKeyValueFromResponse(Response response, String key) {
        JsonPath jsonResponse = new JsonPath(response.asString());
        return jsonResponse.get(key).toString();
    }
}
