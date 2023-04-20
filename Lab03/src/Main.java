import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) throws ParseException {
        // Classe sugerida pelo PED para tratar os paremtros da classe Date;
        SimpleDateFormat sdfDataLicenca1 = new SimpleDateFormat("18/03/2023");
        SimpleDateFormat sdfDataNascimento1 = new SimpleDateFormat("15/03/2001");
        SimpleDateFormat sdfDataLicenca2 = new SimpleDateFormat("15/04/2023");
        SimpleDateFormat sdfDataNascimento2 = new SimpleDateFormat("25/10/1994");
        SimpleDateFormat sdfDataFundacao = new SimpleDateFormat("05/02/2010");
        //Preciso criar variaveis sdf para cada dado do tipo Date?

        // Declarando clientes c1, c2 e c3;
        ClientePF c1 = new ClientePF("Victor Valerio", "Rua Luverci Pereira de Souza",sdfDataLicenca1.parse("18/03/2023"),
                                "Superior incompleto","Masculino", "Classe media-baixa",
                                "437.355.398-07", sdfDataNascimento1.parse("15/03/2001"));

        if (c1.validarCPF(c1.getCPF()))
            System.out.println("CPF " + c1.getCPF() + " do cliente " + c1.getNome() + " eh valido\n");
        else
            System.out.println("CPF " + c1.getCPF() + " do cliente " + c1.getNome() + " eh invalido\n");
        
        // Delcarando veiculos do cliente 1;
        Veiculo v1_c1 = new Veiculo("ABC0123", "Honda", "Fit", 2012);
        Veiculo v2_c1 = new Veiculo("ABC4567", "VW", "Gol", 1996);
        
        // Cadastrando 2 veiculos para o cliente 1;
        c1.registerVeiculo(v1_c1);
        if(!c1.registerVeiculo(v1_c1))
            System.out.println("Veiculo de placa " +v1_c1.getPlaca() +  " ja cadastrado\n");
        if(!c1.registerVeiculo(v2_c1))
            System.out.println("Veiculo de placa " +v2_c1.getPlaca() +  " ja cadastrado\n");

        // Checando a lista de veiculos do cliente 1;
        System.out.println("Lista de veiculos do cliente " + c1.getNome() + "\n" + c1.getListaVeiculos());

        ClientePF c2 = new ClientePF("Joao Pedro", "Rua Luverci Pereira de Souza",sdfDataLicenca2.parse("15/04/2023"),
                                "Superior completo","Masculino", "Classe media-baixa",
                                "427.883.888-37", sdfDataNascimento2.parse("25/10/1994"));

        if (c2.validarCPF(c2.getCPF()))
            System.out.println("CPF " + c2.getCPF() + " do cliente " + c2.getNome() + " eh valido\n");
        else
            System.out.println("CPF " + c2.getCPF() + " do cliente " + c2.getNome() + " eh invalido\n");
        
        // Declarando veiculo do cliente 2;
        Veiculo v1_c2 = new Veiculo("DEF0147", "Ford", "Ka", 2008);
        c2.registerVeiculo(v1_c2);

        ClientePJ c3 = new ClientePJ("Programacao Basica LTDA", "Rua do Python, n_o MC102",
                                "45.773.100/0001-99",sdfDataFundacao.parse("05/02/2010"));

        if (c3.validarCNPJ(c3.getCNPJ()))
            System.out.println("CNPJ " + c3.getCNPJ() + " da empresa " + c3.getNome() + " eh valido\n");
        else
            System.out.println("CNPJ " + c3.getCNPJ() + " da empresa " + c3.getNome() + " eh invalido\n");
        
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
        System.out.println("Atualização de clientes da seguradora Sol Seguros:\n" + seg1.getListaClientes() + "\n");
    
        // Gerando 1 sinistro para o cliente 1;
        seg1.gerarSinistros("20/04/2023", "Rua dos acidentes", seg1, v1_c1, c1);
        //System.out.println("sinistro: " + seg1.getListaSinistros());
        // Listando os Sinistros da seguradora;
        seg1.listarSinistros();
        // Listando os clientes PF da seguradora;
        seg1.listarClientes("PF");
        // Listando os clientes PJ da seguradora;
        seg1.listarClientes("PJ");
        // Visualizando sinistro para determinado cliente;
        seg1.visualizarSinistro("Victor Valerio");

    }
}
