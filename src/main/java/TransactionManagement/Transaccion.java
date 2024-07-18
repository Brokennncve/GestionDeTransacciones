package TransactionManagement;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numFactura;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Usuario usuario;
    private int cantidad;
    private double monto;
    private double pagoRealizado;
    @Enumerated(EnumType.STRING)
    private TransaccionEstado estado;

    // Constructor, getters, setters, etc.
    
    public Transaccion() {}

    public Transaccion(String numFactura, Cliente cliente, Usuario usuario, int cantidad, double monto) {
        this.numFactura = numFactura;
        this.cliente = cliente;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(double pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public TransaccionEstado getEstado() {
        return estado;
    }

    public void setEstado(TransaccionEstado estado) {
        this.estado = estado;
    }
}
