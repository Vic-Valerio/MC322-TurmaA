import java.util.Date;
import java.util.List;

public class Cliente {
    // Atributos instanciados (caracterizacao do objeto)
    private static int numVeiculos = 0; // Static mesmo?
    private String nome;
    private String endereco;
    private Date dataLicenca;
    private List <Veiculo> listaVeiculos;
    
     // Metodo de construcao
    public Cliente(String nome, String endereco, Date dataLicenca, List<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataLicenca = dataLicenca;   
        this.listaVeiculos = listaVeiculos;  
    }

    // Metodos de acesso (getters and setters)
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataLicenca() {
        return dataLicenca;
    }
    public void setDataLicenca(Date dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    // Entender como tratar as listas

    // por que tem q ser boolean e pq o retorno eh convertido em boolean?
    public boolean registerVehicle(Veiculo veiculo) {
        numVeiculos++;
        return listaVeiculos.add(veiculo);
    }

    //Metodo de conversao para string
    public String toString() {
        String str = "";
        str += "informacoes do cliente:\n" 
                + "Nome: " + nome +"\n" 
                + "Endereco: " + endereco +"\n" 
                + "Data da licença: " + dataLicenca +"\n";
                // "Veiculos: " + veiculo;
        return str;
    }

}