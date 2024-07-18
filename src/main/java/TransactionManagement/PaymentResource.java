package TransactionManagement;


import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/payments")
public class PaymentResource {
    @Inject
    TransaccionService transaccionService;

    @POST
    public Response iniciarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = transaccionService.iniciarTransaccion(transaccionDTO);
        return Response.status(Response.Status.CREATED).entity(transaccion).build();
    }

    @PUT
    public Response ejecutarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = transaccionService.ejecutarTransaccion(transaccionDTO);
        return Response.ok(transaccion).build();
    }
}
