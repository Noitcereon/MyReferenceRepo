package me.noitcereon.filters;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;

/**
 * A filter that adds CORS headers to HTTP responses made with JAX-RS.
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS">Mozilla CORS Docs</a>
 * @see <a href="https://www.baeldung.com/cors-in-jax-rs">Baeldung CORS in JAX-RS</a>
 */
@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        System.out.println(LocalDateTime.now() + "Adding CORS headers to servlet response.");

        // These headers determine which origins are allowed and what they are allowed to do.
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");

        // If OPTIONS request reply with HTTP status 202 (accepted). Used for CORS handshake.
        if(requestContext.getMethod().equals("OPTIONS")){
            responseContext.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
    }
}
