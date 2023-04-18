public class Sinistro {
    private static int counter = 0;
    private int id;
    private String data;
    private String endereco;

    // Metodo de construcao
    public Sinistro(String data, String endereco) {
        this.id = setUniqueId();
        this.data = data;
        this.endereco = endereco;
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
    
    // Metodo para definir um identificador unico para objeto da classe Sinistro
    private int setUniqueId() { //posso usar uma biblioteca para esse fim? UUID por exemplo?
        id = counter;
        counter ++;
        return id;
    }
}