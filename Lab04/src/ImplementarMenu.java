import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class ImplementarMenu {
    final int operacao;
    private ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
    Scanner teclado = new Scanner(System.in);

    // Metodo construtor;
    public ImplementarMenu(int operacao){
        this.operacao = operacao;
    }
    // Metodos de acesso;
    public int getOperacao() {
        return operacao;
    }
    public ArrayList<Seguradora> getListaSeguradoras() {
        return listaSeguradoras;
    }

    // Metodos de implementacao;
    public void implementaMenu(){     
        int ope;
        System.out.println("""
                O que voce deseja realizar?\n
                (1) Cadastrar\n
                (2) Listar\n
                (3) Excluir\n
                (4) Gerar sinistro\n
                (5) Transferir seguro\n
                (6) Calcular receita da seguradora\n
                (0) Sair\n""");
        ope = teclado.nextInt();
        teclado.nextLine();

        switch (ope) {
            case 1:
                implementaMenuCadastrar();
                break;
        
            case 2:
                System.out.println("""
                O que voce quer listar?\n
                (1) Cliente por seguradora\n
                (2) Sinistros por seguradora\n
                (3) Sinistro por cliente\n
                (4) Veiculo por cliente\n
                (5) Veiculo por seguradora\n
                (6) Voltar\n""");

                ope = teclado.nextInt();
                teclado.nextLine();
                implementaMenuListar();
                break;
            
            case 3:
                implementaMenuExcluir();
                break;

            case 4:

                break;

            case 5:

                break;
            
            case 6: 

                break;

            case 0:

                break;
        }
    }

    public void implementaMenuCadastrar(){
        int ope;
        System.out.println("""
                        O que voce quer cadastrar?\n
                        (1) Cliente\n
                        (2) Veiculo\n
                        (3) Seguradora\n
                        (4) Voltar\n""");
        ope = teclado.nextInt();
        teclado.nextLine();
        switch(ope){
            case 1: // Cadastrar cliente
                String nomeCliente, nomeSeguradora, endereco, tipoCliente;
                Seguradora s;
                System.out.println("""
                                    Opção escolhida: Cadastrar cliente\n
                                    Qual será a seguradora? Informe o nome dela""");
                nomeSeguradora = teclado.nextLine();

                // buscar na listaSeguradoras e inicializar s
                for (Seguradora seg : listaSeguradoras){
                    if(seg.getNome() == nomeSeguradora){
                        System.out.println("Seguradora encontrada\n");
                        s = seg;
                        // sair do for com continue?;
                    }
                    else{
                        System.out.println("Seguradora não encontrada\n");
                        s = null;
                        break;
                    }
                }

                System.out.println("Insira o nome do cliente\n");
                nomeCliente = teclado.nextLine();
                while (!Validacao.validaNome(nomeCliente)){
                    System.out.println("Insira o nome do cliente novamente apenas com caracteres alfabeticos:\n");
                    nomeCliente = teclado.nextLine();
                }

                System.out.println("Insira o endereço\n");
                endereco = teclado.nextLine();

                System.out.println("Insira o tipo de cliente:\n"+
                                    "PF = Pessoa fisica\n" +
                                    "PJ = Pessoa juridica\n");
                tipoCliente = teclado.nextLine();

                if(tipoCliente == "PF"){
                    String dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento;
                    System.out.println("Insira a data da licença no formato DD/MM/AAA\n");
                    dataLicenca = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    System.out.println("Insira o nivel de educação\n");
                    educacao = teclado.nextLine();

                    System.out.println("Insira o gênero\n");
                    genero = teclado.nextLine();

                    System.out.println("Insira a classe econômica\n");
                    classeEconomica = teclado.nextLine();

                    System.out.println("Insira o CPF\n");
                    CPF = teclado.nextLine();
                    while(!Validacao.validarCPF(CPF)){
                        System.out.println("CPF invalido, insira novamente\n");
                        CPF = teclado.nextLine();
                    }

                    System.out.println("Insira a data de nascimento\n");
                    dataNascimento = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    ClientePF c1 = new ClientePF(nomeCliente, endereco,converteDataStrToLD(dataLicenca), educacao, genero,
                                                classeEconomica, CPF, converteDataStrToLD(dataNascimento));
                    s.registerCliente(c1); // escopo local, como tornar global?
                }

                if(tipoCliente == "PJ"){
                    String CNPJ,dataFundacao;
                    int qtdFuncionarios; 
                    System.out.println("Insira o CNPJ\n");
                    CNPJ = teclado.nextLine();
                    while(!Validacao.validarCNPJ(CNPJ)){
                        System.out.println("CNPJ invalido, insira novamente\n");
                        CNPJ = teclado.nextLine();
                    }

                    System.out.println("Insira a data de fundação da empresa\n");
                    dataFundacao = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    System.out.println("Insira a quantidade de funcionarios da empresa\n");
                    qtdFuncionarios = teclado.nextInt();

                    ClientePJ c2 = new ClientePJ (nomeCliente, endereco, CNPJ, converteDataStrToLD(dataFundacao), qtdFuncionarios);
                    
                    s.registerCliente(c2); // escopo local;
                }
                break;
            
            case 2: // Cadastrar veiculo
                Cliente c; // Buscar cliente na listaClientes da seguradora X
                System.out.println("""
                                    Opção escolhida: Cadastrar veículo\n
                                    Informe o nome do cliente""");
                nomeCliente = teclado.nextLine();

                System.out.println("Informe o nome da seguradora do cliente\n");
                nomeSeguradora = teclado.nextLine();

                // buscar na listaSeguradoras e inicializar s
                for (Seguradora seg : listaSeguradoras){
                    if(seg.getNome() == nomeSeguradora){
                        System.out.println("Seguradora encontrada\n");
                        for (Cliente cli: seg.getListaClientes()){
                            if (seg.getListaClientes().contains(cli)){
                                System.out.println("Cliente encontrado\n");
                                c = cli;
                                continue;
                            }
                            else{
                                System.out.println("Cliente não encontrado\n");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("Seguradora não encontrada\n");
                        break;
                    }
                }

                System.out.println("Insira a placa do veiculo\n");
                String placa = teclado.nextLine();

                System.out.println("Insira o modelo do veiculo\n");
                String modelo = teclado.nextLine();

                System.out.println("Insira a marca do veiculo\n");
                String marca = teclado.nextLine();

                System.out.println("Insira o ano de fabricação\n");
                int anoFabricacao = teclado.nextInt();
                teclado.nextLine();

                Veiculo v = new Veiculo(placa, modelo, marca, anoFabricacao);
                c.registerVeiculo(v);
                break;

            case 3: // Cadastrar seguradora
                //String nomeSeguradora;
                System.out.println("""
                                    Opção escolhida: Cadastrar seguradora\n
                                    Insira o nome da seguradora:\n""");
                nomeSeguradora = teclado.nextLine();

                System.out.println("Insira o telefone da seguradora no padrao (DDD)XXXXX-XXXX:\n");
                String telefone = teclado.nextLine();

                System.out.println("Insira o email da seguradora:\n");
                String email = teclado.nextLine();

                System.out.println("Insira o endereco da seguradora:\n");
                endereco = teclado.nextLine();

                Seguradora s1 = new Seguradora(nomeSeguradora, telefone, email, endereco);
                if(!listaSeguradoras.contains(s1)){
                    listaSeguradoras.add(s1); // tratar o caso da seguradora ja cadastrada antes de instanciar objt?
                }
                break;
            
            case 4: // Voltar
                System.out.println("Opção escolhida: Voltar\n");
                // voltar pro metodo implementaMenu?;
                break;
        }
    }

    public void implementaMenuExcluir(){
        int ope;
        System.out.println("""
                O que voce quer excluir?\n
                (1) Cliente\n
                (2) Veiculo\n
                (3) Sinistro\n
                (4) Voltar\n""");
                ope = teclado.nextInt();
                teclado.nextLine();

                switch(ope){
                    case 1:
                        break;
                    
                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;
                }    
    }

    // Metodo para converter uma string para LocalDate separando a String
    public static LocalDate converteDataStrToLD(String data){
        String dataSplitted[] = data.split("/");
        return LocalDate.of(Integer.valueOf(dataSplitted[2]), Integer.valueOf(dataSplitted[1]),
                            Integer.valueOf(dataSplitted[0]));
    }
}