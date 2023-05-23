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
    private int valorMensal;    

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

    public int getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public List<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    // Metodo abstrato para calcular o valor mensal;
    public abstract double calcularValor();

    // Metodo para gerar sinistros (ABSTRACT)
    public boolean gerarSinistros(String dataSinistro, String enderecoSinistro, Condutor condutorSinistro,
                                  Seguro seguroSinistro, Seguradora seguradoraSinistro){
        boolean temCondutor = false, temSeguro = false;
        Sinistro sinistro = new Sinistro(dataSinistro, enderecoSinistro, condutorSinistro, seguroSinistro);

        // Verificar se o cliente eh valido, em caso afirmativo gera sinistro caso contrario nao;
        for (Condutor cond: listaCondutores){
            if(cond.getCpf().equals(condutorSinistro.getCpf())){
                temCondutor = true;
                // Verifica tambem se o condutor esta habilitado ou nao;
                if (!cond.isHabilitado()){
                    System.out.println("Condutor não habilitado, sinistro não pode ser gerado\n");
                    return false;
                }
                break;
            }
        }
        if(!temCondutor){
            System.out.println("Condutor não cadastrado, sinistro não pode ser gerado\n");
            return false;
        }

        // Verifica s o seguro esta contido na seguradora
        for(Seguro seguro: seguradoraSinistro.getListaSeguros()){
            if(seguro.getId() == seguroSinistro.getId()){
                temSeguro = true;
                break;
            }
        }
        if(!temSeguro){
            System.out.println("Seguro não existente, sinistro não pode ser gerado\n");
            return false;
        }
        return listaSinistros.add(sinistro);
    }

    // Metodos para habilitar ou desabilitar um condutor (pode gerar ou nao sinistros);
    public abstract void habilitarCondutor(Condutor condutor);

    public abstract void desabilitarCondutor(Condutor condutor);

    // Metodo para calcular o valor do seguro
    public abstract void calcularValor(Seguro seguro);
}
