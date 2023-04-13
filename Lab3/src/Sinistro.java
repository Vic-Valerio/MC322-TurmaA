public class Sinistro {
    private static int counter = 0;
    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    
    // Metodo construtor
    public Sinistro(String data, String endereco, Veiculo veiculo, Cliente cliente) {
        this.id = setUniqueId();
        this.data = data;
        this.endereco = endereco;
        this.veiculo = veiculo;
        this.cliente = cliente;
        }

        // Metodo para definir um identificador unico para objeto da classe Sinistro
        private int setUniqueId() {
            id = counter;
            counter ++;
            return id;
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

        public Cliente getCliente(){
            return cliente;
        }
        public void setCliente(Cliente cliente){
            this.cliente = cliente;
        }

        public Veiculo getVeiculo(){
            return veiculo;
        }
        public void setVeiculo(Veiculo veiculo){
            this.veiculo = veiculo;
        }

        public Seguradora getSeguradora(){
            return seguradora;
        }
        public void setSeguradora(Seguradora seguradora){
            this.seguradora = seguradora;
        }
}
