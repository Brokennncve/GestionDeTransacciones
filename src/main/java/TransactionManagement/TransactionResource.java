package TransactionManagement;

import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;


@Path("/transactions")
public class TransactionResource {
    @Inject
    TransaccionService transaccionService;

    /**
     * @return
     */
    @GET
    public Response obtenerHistorialTransacciones() {
        List<Transaccion> transacciones = transaccionService.obtenerHistorialTransacciones();
        return Response.ok(transacciones).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerTransaccion(@PathParam("id") Long id) {
        Transaccion transaccion = transaccionService.obtenerTransaccion(id);
        return Response.ok(transaccion).build();
    }
}