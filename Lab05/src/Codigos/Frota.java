package Codigos;

import java.util.ArrayList;
import java.util.List;

public class Frota {
    private final String code;
    private List <Veiculo> listaVeiculos;

    // Metodo construtor
    public Frota(String code) {
        this.code = code;
        listaVeiculos = new ArrayList<>();
    }

    // Metodos de acesso
    public String getCode() {
        return code;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    @Override
    public String toString(){
        
        System.out.println("Veiculos:\n");
        for(Veiculo v: listaVeiculos){
            System.out.println("Veiculo "+v.getPlaca()+"\n");
        }
        String str = "Frota "+ code +"\n";
        return str;
    }

    // Metodo para inserir veiculo na lista de veiculos do cliente
    public boolean adicionarVeiculo(Veiculo veiculo) {
        for(Veiculo v: listaVeiculos){
            if (v.getPlaca().equals(veiculo.getPlaca())){
                System.out.println("Veiculo de placa "+veiculo.getPlaca()+" já cadastrado\n");
                return false;
            }
        }
        System.out.println("Veiculo "+veiculo.getPlaca()+" cadastrado à frota com sucesso\n");
        return listaVeiculos.add(veiculo);
    }

    // Metodo para remover veiculo na lista de veiculos do cliente
    public boolean removerVeiculo(Veiculo veiculo){
        if (listaVeiculos.remove(veiculo)){
            System.out.println("Veiculo "+veiculo.getPlaca()+" removido com sucesso\n");
            return true;
        }
        System.out.println("Veiculo  "+veiculo.getPlaca()+"não cadastrado\n");
        return false;
    }
}