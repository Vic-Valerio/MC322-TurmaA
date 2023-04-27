import java.util.Date;

public class ClientePF extends Cliente {

    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private final String CPF;
    private Date dataNascimento;

    //private CalcSeguro valorBase = new CalcSeguro(100.0);

    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao,
                    String genero, String classeEconomica, String CPF, Date dataNascimento) {

        super(nome, endereco);

        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
    }

    // Metodos de acesso
    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }
    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public String getCPF(){
        return CPF;
    }
    
    public Date getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    //Metodo de conversao para string
    public String toString() {
        String str = "";
        str += "informacoes de pessoa fisica:\n" 
                + "CPF: " + CPF +"\n" 
                + "Nascimento: " + dataNascimento +"\n" 
                + "Gênero: " + genero +"\n"
                + "Escolaridade: " + educacao +"\n"
                + "Classe econômica: " + classeEconomica + "\n"
                + "Data da licença: " + dataLicenca + "\n";
        return str;
    }

    // Metodo para calcular o valor a ser pago do seguro
    @Override
    public double calculaScore(double valorBase, double fatorIdade, int qtdCarros){
        return valorBase*fatorIdade*qtdCarros;
    }
}