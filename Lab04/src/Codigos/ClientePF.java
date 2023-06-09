package Codigos;
import java.time.LocalDate;
import java.time.Period;

public class ClientePF extends Cliente {

    private LocalDate dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private final String CPF;
    private LocalDate dataNascimento;
    
    private LocalDate dataHoje = LocalDate.now();

    private Period p;
    private int idade;

    public ClientePF(String nome, String endereco, LocalDate dataLicenca, String educacao,
                    String genero, String classeEconomica, String CPF, LocalDate dataNascimento) {

        super(nome, endereco);

        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;

        
        if (dataNascimento != null && dataHoje != null) {
            p = Period.between(dataNascimento, dataHoje);
        }
        idade = p.getYears();
    }

    // Metodos de acesso
    public LocalDate getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(LocalDate dataLicenca) {
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
    
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    //Metodo de conversao para string
    @Override
    public String toString() {
        String str = "";
        str += "Nome: " + super.getNome()+"\n"
                + "Endereço: "+ super.getEndereco()+"\n"
                + "Valor do seguro: "+String.format("%.1f",super.getValorSeguro())+"\n"
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
    public double calculaScore(){
        double valorBase = CalcSeguro.VALOR_BASE.getFator();
        double fatorIdade = 1;
        int qtdCarros = listaVeiculos.size();

        if (idade >= 18 && idade < 30){
            fatorIdade = CalcSeguro.FATOR_18_30.getFator();
        }
        if (idade >= 30 && idade < 60){
            fatorIdade = CalcSeguro.FATOR_30_60.getFator();
        }
        if (idade >= 60 && idade < 90){
            fatorIdade = CalcSeguro.FATOR_60_90.getFator();
        }
        
        return valorBase*fatorIdade*qtdCarros;
    }

    @Override
    public String getIdentificador(){
        return CPF;
    }
}