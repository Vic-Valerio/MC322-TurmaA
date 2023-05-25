package Codigos;

import java.time.LocalDate;
import java.util.List;

public abstract class Seguro {
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private List<Sinistro> listaSinistros;
    private List<Condutor> listaCondutores;
    private double valorMensal; 

    // Metodo construtor;
    public Seguro(int id, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
    }

    // Metodos de acesso
    public int getId() {
        return id;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    // Metodo para gerar sinistros (ABSTRACT)
    public abstract boolean gerarSinistros(String dataSinistro, String enderecoSinistro, Condutor condutorSinistro,
                                  Seguro seguroSinistro, Seguradora seguradoraSinistro); 

    // Metodos para habilitar ou desabilitar um condutor (pode gerar ou nao sinistros);
    public abstract boolean habilitarCondutor(Condutor condutor, Seguro seguro);

    public abstract boolean desabilitarCondutor(Condutor condutor, Seguro seguro);

    // Metodo para calcular o valor do seguro
    public abstract void calcularValor(Seguro seguro);
}
