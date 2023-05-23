package Codigos;
public class Sinistro {
    private static int counter = 0;
    private final int id;
    private String data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
    
    // Metodo construtor
    public Sinistro(String data, String endereco, Condutor condutor, Seguro seguro) {
        this.id = setUniqueId();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    // Metodo para definir um identificador unico para objeto da classe Sinistro
    private int setUniqueId() {
        return counter++;
    }

    // Metodos de acesso (Getters and setters)    
    public int getId() {
    return id;
    }

    public String getData() {
        return data;
    }        
    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor getCondutor() {
        return condutor;
    }
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    @Override
    public String toString() {
        String str = "";
        str += "ID: "+id +"\nData: " + data +
                "\nEndereço: "+endereco+"\n";
        return str;
    }
}
