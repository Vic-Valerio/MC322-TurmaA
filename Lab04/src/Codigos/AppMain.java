package Codigos;
public class AppMain {
    public static void main(String[] args) {
        // Instanciando 1 seguradora;
        Seguradora s1 = new Seguradora("Sol Seguros", "19 99547-8236",
                                    "solseguros@gmail.com", "Rua do castor, 1028");
        

        // Instanciando 1 cliente PF e 1 cliente PJ;
        ClientePF c1 = new ClientePF("Victor Valerio", "Rua Luverci Pereira",
                                    ImplementarMenu.converteDataStrToLD("19/10/2019") , 
                                    "Superior incompleto","Masculino",
                                    "Classe media-baixa", "437.355.398-07",
                                    ImplementarMenu.converteDataStrToLD("15/03/2001"));

        ClientePJ c2 = new ClientePJ("Programacao Basica LTDA", "Rua do Python, n_o MC102",
                                "45.773.100/0001-99", ImplementarMenu.converteDataStrToLD("05/02/2010"), 15);

        // Instanciando 2 veiculos;
        Veiculo v1 =  new Veiculo("ABC0123", "Honda", "Fit", 2012);
        Veiculo v2 =  new Veiculo("ABC4567", "VW", "Gol", 1996);
        
        // Adicionando um veiculo em cada cliente;
        c1.registerVeiculo(v1);
        c2.registerVeiculo(v2);
        
        // Cadastrando os clientes na seguradora;
        s1.registerCliente(c1);
        s1.registerCliente(c2);
        
        //Calculando o valor do seguro de cada cliente antes de ter sinistros
        s1.calcularPrecoSeguroCliente(c1);
        System.out.println("valor do seguro para o cliente 1: R$" + c1.getValorSeguro()+"\n");
        s1.calcularPrecoSeguroCliente(c2);
        System.out.println("valor do seguro para o cliente 2: R$" + String.format("%.1f", c2.getValorSeguro())+"\n");

        // Gerando 1 sinistro para o cliente 1;
        s1.gerarSinistros("20/04/2023", "Rua dos acidentes", s1, v1, c1);

        // Gerando 1 sinistro para o cliente 2;
        s1.gerarSinistros("01/09/2018", "Rua das lamentações", s1, v2, c2);

        s1.calcularPrecoSeguroCliente(c1);
        System.out.println("novo valor do seguro para o cliente 1: R$" + c1.getValorSeguro()+"\n");
        s1.calcularPrecoSeguroCliente(c2);
        System.out.println("novo valor do seguro para o cliente 2: R$" + String.format("%.1f", c2.getValorSeguro())+"\n");

        // Listando os clientes PF da seguradora;
        System.out.println("Lista de clientes PF da seguradora Sol Seguros:\n");
        s1.listarClientes("PF");

        // Listando os clientes PJ da seguradora;
        System.out.println("Lista de clientes PJ da seguradora Sol Seguros:\n");
        s1.listarClientes("PJ");

        // Visualizando um sinistro (vai mostrar o primeiro que encontrar na lista apenas);
        System.out.println("Visualizar Sinistros do Victor Valerio\n");
        s1.visualizarSinistro("437.355.398-07");

        // Listando os Sinistros da seguradora;
        System.out.println("Lista de sinistros da seguradora Sol Seguros:\n");
        s1.listarSinistros();

        // Calculando a receita total da seguradora;
        System.out.println("Receita atual da seguradora Sol Seguros: R$"+ s1.calcularReceita(s1)+"\n");

        // Chamando menu interativo;
        ImplementarMenu a = new ImplementarMenu();
        a.setListaSeguradoras(s1);
        a.implementaMenu();
    }
}