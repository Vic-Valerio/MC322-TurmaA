package Codigos;
import java.time.LocalDate;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;

    public ClientePJ(String nome, String endereco,
                     String CNPJ, LocalDate dataFundacao, int qtdFuncionarios) {

        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
    }

    // Metodos de acesso
    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }
    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    //Metodo de conversao para string
    @Override
    public String toString() {
        String str = "Nome: " + super.getNome()+"\n"
                    + "Endereço: "+ super.getEndereco()+"\n"
                    + "Valor do seguro: "+String.format("%.1f",super.getValorSeguro())+"\n"
                    + "CNPJ: " + CNPJ +"\n" 
                    + "Fundação: " + dataFundacao +"\n";
        return str;
    }
    
    @Override
    public double calculaScore(){
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        int qtdCarros = listaVeiculos.size();

        return valorBase* (1+(qtdFuncionarios/100.0))*qtdCarros;
    }

    @Override
    public String getIdentificador(){
        return CNPJ;
    }
}