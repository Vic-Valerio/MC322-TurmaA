import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Classe sugerida pelo PED para tratar os paremtros da classe Date;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Declarando clientes c1, c2 e c3;
        ClientePF c1 = new ClientePF("Victor Valerio", "Rua Luverci Pereira de Souza",sdf.parse("18/03/2023"),
                                "Superior incompleto","Masculino", "Classe media-baixa",
                                "437.355.398-07", sdf.parse("15/03/2001"));

        if (c1.validarCPF(c1.getCPF()))
            System.out.println("\n"+"CPF " + c1.getCPF() + " do cliente " + c1.getNome() + " eh valido\n");
        else
            System.out.println("\n"+"CPF " + c1.getCPF() + " do cliente " + c1.getNome() + " eh invalido\n");
        
        // Delcarando veiculos do cliente 1;
        Veiculo v1_c1 = new Veiculo("ABC0123", "Honda", "Fit", 2012);
        Veiculo v2_c1 = new Veiculo("ABC4567", "VW", "Gol", 1996);
        
        // Cadastrando 2 veiculos para o cliente 1;
        // Cadastando o veiculo v1 para testar a adição de veiculos existentes
        c1.registerVeiculo(v1_c1);
        System.out.println("Veiculo de placa " +v1_c1.getPlaca() +  " cadastrado com sucesso\n");

        if(!c1.registerVeiculo(v2_c1))
            System.out.println("Veiculo de placa " +v2_c1.getPlaca() +  " ja cadastrado\n");
        else
            System.out.println("Veiculo de placa " +v2_c1.getPlaca() +  " cadastrado com sucesso\n");

        if(!c1.registerVeiculo(v1_c1))
            System.out.println("Veiculo de placa " +v1_c1.getPlaca() +  " ja cadastrado\n");
        else
            System.out.println("Veiculo de placa " +v1_c1.getPlaca() +  " cadastrado com sucesso\n");

        // Checando os veiculos do cliente 1;
        System.out.println("Lista de veiculos do cliente " + c1.getNome() + "\n" + c1.getListaVeiculos());

        ClientePF c2 = new ClientePF("Joao Pedro", "Rua Luverci Pereira de Souza",sdf.parse("15/04/2023"),
                                "Superior completo","Masculino", "Classe media-baixa",
                                "437.355.398-09", sdf.parse("25/10/1994"));

        if (c2.validarCPF(c2.getCPF()))
            System.out.println("\nCPF " + c2.getCPF() + " do cliente " + c2.getNome() + " eh valido\n");
        else
            System.out.println("\nCPF " + c2.getCPF() + " do cliente " + c2.getNome() + " eh invalido\n");
        
        // Declarando e cadastrando veiculo do cliente 2;
        Veiculo v1_c2 = new Veiculo("DEF0147", "Ford", "Ka", 2008);
        c2.registerVeiculo(v1_c2);

        ClientePJ c3 = new ClientePJ("Programacao Basica LTDA", "Rua do Python, n_o MC102",
                                "45.773.100/0001-99",sdf.parse("05/02/2010"));
        
        // Declarando e cadastrando veiculo do cliente 3;
        Veiculo v1_c3 = new Veiculo("GHI4596", "Chevrolet", "Omega", 1994);
        c3.registerVeiculo(v1_c3);

        if (c3.validarCNPJ(c3.getCNPJ()))
            System.out.println("\nCNPJ " + c3.getCNPJ() + " da empresa " + c3.getNome() + " eh valido\n");
        else
            System.out.println("\nCNPJ " + c3.getCNPJ() + " da empresa " + c3.getNome() + " eh invalido\n");

        // Cliente 4 apenas para demonstrar validaçao do CNPJ;
        ClientePJ c4 = new ClientePJ("Programacao avançada LTDA", "Rua do Java, n_o MC322",
                                "45.773.100/0001-90",sdf.parse("05/01/2000"));

        if (c4.validarCNPJ(c4.getCNPJ()))
            System.out.println("\nCNPJ " + c4.getCNPJ() + " da empresa " + c4.getNome() + " eh valido\n");
        else
            System.out.println("\nCNPJ " + c4.getCNPJ() + " da empresa " + c4.getNome() + " eh invalido\n");
        
        // Declarando um objeto seguradora            
        Seguradora seg1 = new Seguradora("Sol Seguros", "19 99547-8236",
                                    "solseguros@gmail.com", "Rua do castor, 1028");
        // Cadastrando 2 clientes PF na seguradora;
        seg1.registerCliente(c1);
        seg1.registerCliente(c2);
        // Cadastrando 1 cliente PJ na seguradora;
        seg1.registerCliente(c3);
        // Mostrando os clientes cadastrados;
        System.out.println("Clientes da seguradora Sol Seguros:\n" + seg1.getListaClientes() +"\n");
        
        // Removendo 1 cliente PF da seguradora Sol Seguros e mostrando atualizaçao na tela;
        seg1.removeCliente(c2);
        System.out.println("Cliente excluido\nAtualização de clientes da seguradora Sol Seguros:\n" + seg1.getListaClientes() +"\n");
    
        // Gerando 1 sinistro para o cliente 1;
        seg1.gerarSinistros("20/04/2023", "Rua dos acidentes", seg1, v1_c1, c1);
        
        // Listando os Sinistros da seguradora;
        System.out.println("Listar Sinistros\n");
        seg1.listarSinistros();

        // Listando os clientes PF da seguradora;
        System.out.println("Listar Clientes\n");
        seg1.listarClientes("PF");

        // Listando os clientes PJ da seguradora;
        seg1.listarClientes("PJ");

        // Visualizando sinistro para determinado cliente;
        System.out.println("Visualizar Sinistros do Victor Valerio\n");
        seg1.visualizarSinistro("Victor Valerio");
    }
}
