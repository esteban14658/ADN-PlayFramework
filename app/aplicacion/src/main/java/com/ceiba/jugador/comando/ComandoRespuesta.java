package aplicacion.src.main.java.com.ceiba.jugador.comando;

public class ComandoRespuesta<T> {

    private final T valor;

    public ComandoRespuesta(T valor){
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }
}
