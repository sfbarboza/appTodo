package br.edu.unipam.apptodo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("sample")
public class SampleResource {

	@Inject
	@ConfigProperty(name = "message")
	private String message;
    private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();

    /**
     *
     * @param asyncResponse
     */
    @GET
	public void message(@Suspended
    final AsyncResponse asyncResponse) {
            Future<?> submit = executorService.submit(() -> {
                asyncResponse.resume(doMessage());
            });
	}

    private Response doMessage() {
        return Response.ok(message).build();
    }

}
