public class Veiculo {
    // Atributos instanciados (caracterizacao do objeto)
    private String placa;
    private String marca;
    private String modelo;

    // Metodo de construcao
    public Veiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;     
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
}