package dominio.src.main.java.com.ceiba.jugador.modelo.dto;

public class DtoFiltro {

    private String defensas;
    private String mediocampistas;
    private String delanteros;

    public DtoFiltro() {
    }

    public DtoFiltro(String defensas, String mediocampistas, String delanteros) {
        this.defensas = defensas;
        this.mediocampistas = mediocampistas;
        this.delanteros = delanteros;
    }

    public String getDefensas() {
        return defensas;
    }

    public void setDefensas(String defensas) {
        this.defensas = defensas;
    }

    public String getMediocampistas() {
        return mediocampistas;
    }

    public void setMediocampistas(String mediocampistas) {
        this.mediocampistas = mediocampistas;
    }

    public String getDelanteros() {
        return delanteros;
    }

    public void setDelanteros(String delanteros) {
        this.delanteros = delanteros;
    }
}
