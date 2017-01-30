import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/aviator/{test}")
public class Sandbox {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.TEXT_HTML)
    //@Produces("text/plain")
    public String getClichedMessage(@PathParam("test") String name) throws FileNotFoundException {
        // Return some cliched textual content
        return "<b>sorry "+name+"</b>";
        //return json.toString(2);
    }
}