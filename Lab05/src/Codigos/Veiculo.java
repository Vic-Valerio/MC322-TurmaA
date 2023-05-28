package Codigos;
public class Veiculo {
    // Atributos instanciados
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    // Metodo construtor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    // Metodos de acesso (getters and setters)
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao(){
        return anoFabricacao;
    }
    public void setAnoFabricacao(int anoFabricacao){
        this.anoFabricacao = anoFabricacao;
    }

    // Metodo de conversao para string
    public String toString() {
        String str = "Placa: " + placa +"\n"
                + "Modelo: " + modelo +"\n" 
                + "Marca: " + marca +"\n" 
                + "Ano de fabricação: " + anoFabricacao +"\n";
        return str;
    }
}