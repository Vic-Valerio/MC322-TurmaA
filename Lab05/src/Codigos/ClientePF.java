package Codigos;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private List <Veiculo> listaVeiculos;

    private LocalDate dataHoje = LocalDate.now();
    private Period p;
    private int idade;

    public ClientePF(String nome, String endereco, String telefone, String email,
                     String CPF, String genero, String educacao, LocalDate dataNascimento) {

        super(nome, endereco, telefone, email);

        this.educacao = educacao;
        this.genero = genero;
        this.CPF = CPF;
        this.dataNascimento = dataNascimento;
        listaVeiculos = new ArrayList<>();
        
        if (dataNascimento != null && dataHoje != null) {
            p = Period.between(dataNascimento, dataHoje);
        }
        idade = p.getYears();
    }

    // Metodos de acesso

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

    public String getCPF(){
        return CPF;
    }
    
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }

    public List<Veiculo> getListaVeiculos(){
        return listaVeiculos;
    }
    
    public int getIdade() {
        return idade;
    }

    //Metodo de conversao para string
    @Override
    public String toString() {
        String str = "";
        str += "Nome: " + super.getNome()+"\n"
                + "Endereço: "+ super.getEndereco()+"\n"
                + "Telefone: "+super.getTelefone()+"\n"
                + "Email: "+super.getEmail()+"\n"
                + "CPF: " + CPF +"\n" 
                + "Nascimento: " + dataNascimento +"\n" 
                + "Gênero: " + genero +"\n"
                + "Escolaridade: " + educacao +"\n";
        return str;
    }

    // Metodo para inserir veiculo na lista de veiculos do cliente
    public boolean registerVeiculo(Veiculo veiculo) {
        for(Veiculo v: listaVeiculos){
            if (v.getPlaca().equals(veiculo.getPlaca())){
                System.out.println("Veiculo já cadastrado\n");
                return false;
            }
        }
        System.out.println("Veiculo cadastrado com sucesso\n");
        return listaVeiculos.add(veiculo);
    }

    // Metodo para remover veiculo na lista de veiculos do cliente
    public boolean removeVeiculo(Veiculo veiculo){
        if (listaVeiculos.remove(veiculo)){
            return true;
        }
        return false;
    }
    /*
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
    */

    @Override
    public String getIdentificador(){
        return CPF;
    }
}