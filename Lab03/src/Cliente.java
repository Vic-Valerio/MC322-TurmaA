import java.util.List;
import java.util.ArrayList;

public class Cliente {
    // Atributos instanciados
    private String nome;
    private String endereco;
    private List <Veiculo> listaVeiculos;
    
     // Metodo construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;  
        listaVeiculos = new ArrayList<>();
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

    public List<Veiculo> getListaVeiculos(){
        return listaVeiculos;
    }

    // Metodo para inserir veiculo na lista de veiculos do cliente
    public boolean registerVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo))
            return false;
        else
            return listaVeiculos.add(veiculo);
    }

    // Metodo para remover veiculo na lista de veiculos do cliente
    public boolean removeVeiculo(Veiculo veiculo){
        return listaVeiculos.remove(veiculo);
    }

    // Metodo de conversao para string 
    public String toString() {
        String str = "";
        str += "Cliente:\n" 
                + "Nome: " + nome +"\n" 
                + "Endereco: " + endereco +"\n"
                + "Veiculos: " + listaVeiculos + "\n";
        return str;
    }
}