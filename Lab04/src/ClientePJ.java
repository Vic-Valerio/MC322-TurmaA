import java.util.Date;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco,
                     String CNPJ, Date dataFundacao) {

        super(nome, endereco);
        this.CNPJ = CNPJ;
        this.dataFundacao = dataFundacao;
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

    //Metodo de conversao para string
    public String toString() {
        String str = "";
        str += "informacoes de pessoa juridica:\n" 
                    + "CNPJ: " + CNPJ +"\n" 
                    + "Fundação: " + dataFundacao +"\n";
        return str;
    }
    
    
}