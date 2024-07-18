package TransactionManagement;

import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;


@ApplicationScoped
public class TransaccionService {
    @Inject
    EntityManager em;

    @Transactional
    public Transaccion iniciarTransaccion(TransaccionDTO transaccionDTO) {
        Cliente cliente = em.find(Cliente.class, transaccionDTO.getClienteId());
        Usuario usuario = em.find(Usuario.class, transaccionDTO.getUsuarioId());

        Transaccion transaccion = new Transaccion(
            transaccionDTO.getNumFactura(),
            cliente,
            usuario,
            transaccionDTO.getCantidad(),
            transaccionDTO.getMonto()
        );

        transaccion.setEstado(TransaccionEstado.PENDIENTE);
        em.persist(transaccion);
        return transaccion;
    }

    @Transactional
    public Transaccion ejecutarTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = em.find(Transaccion.class, transaccionDTO);

        if (transaccion != null && verificarPago(transaccion)) {
            transaccion.setEstado(TransaccionEstado.COMPLETADA);
            procesarPago(transaccion);
            enviarFacturaElectronica(transaccion);
            enviarFacturaCorreo(transaccion);
        } else {
            if (transaccion != null) {
                transaccion.setEstado(TransaccionEstado.FALLIDA);
                em.merge(transaccion);
            }
        }

        return transaccion;
    }

    private boolean verificarPago(Transaccion transaccion) {
        double totalTransaccion = transaccion.getCantidad() * transaccion.getMonto();
        // Simulación de pago realizado, en un caso real, esto debería ser validado con un servicio externo
        return totalTransaccion <= transaccion.getPagoRealizado();
    }

    private void procesarPago(Transaccion transaccion) {
        // Simulación de integración con un servicio de pago externo
        boolean pagoExitoso = simularPagoExterno(transaccion);
        if (pagoExitoso) {
            transaccion.setPagoRealizado(transaccion.getCantidad() * transaccion.getMonto());
        } else {
            transaccion.setEstado(TransaccionEstado.FALLIDA);
        }
        em.merge(transaccion);
    }

    private boolean simularPagoExterno(Transaccion transaccion) {
        // Simulación de la lógica de pago
        System.out.println("Simulando pago externo para la transacción: " + transaccion.getId());
        // Aquí se podria integrar con un servicio de pago real y devolver true si el pago fue exitoso, false en caso contrario
        return true; // Simulando un pago exitoso
    }

    private void enviarFacturaElectronica(Transaccion transaccion) {
        // Implementar la lógica para generar y enviar la factura electrónica (Jose)
        System.out.println("Enviando factura electrónica al cliente: " + transaccion.getCliente().getEmail());
    }

    private void enviarFacturaCorreo(Transaccion transaccion) {
        // Implementa la lógica para enviar la factura por correo electrónico (Jose)
        System.out.println("Enviando factura por correo electrónico al cliente: " + transaccion.getCliente().getEmail());
    }

    public List<Transaccion> obtenerHistorialTransacciones() {
        return em.createQuery("SELECT t FROM Transaccion t", Transaccion.class)
                  .getResultList();
    }

    public Transaccion obtenerTransaccion(Long id) {
        return em.find(Transaccion.class, id);
    }

    public Transaccion obtenerTransaccionPorNumFactura(String numFactura) {
        return em.createQuery("SELECT t FROM Transaccion t WHERE t.numFactura = :numFactura", Transaccion.class)
                  .setParameter("numFactura", numFactura)
                  .getSingleResult();
    }
}
