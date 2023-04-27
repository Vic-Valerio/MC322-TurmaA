import java.util.Date;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;
    private int qtdFuncionarios;

    public ClientePJ(String nome, String endereco,
                     String CNPJ, Date dataFundacao, int qtdFuncionarios) {

        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
        this.qtdFuncionarios = qtdFuncionarios;
    }

    // Metodos de acesso
    public String getCNPJ() {
        return CNPJ;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }
    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }
    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    //Metodo de conversao para string
    public String toString() {
        String str = "";
        str += "informacoes de pessoa juridica:\n" 
                    + "CNPJ: " + CNPJ +"\n" 
                    + "Fundação: " + dataFundacao +"\n";
        return str;
    }
    
    @Override
    public double calculaScore(){
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        //qtdCarros foi herdado da classe mae Cliente;
        return valorBase* (1+(qtdFuncionarios/100))*qtdCarros;
    }
}