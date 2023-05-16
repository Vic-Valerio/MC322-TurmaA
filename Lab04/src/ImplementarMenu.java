import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class ImplementarMenu {
    private ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
    private Scanner teclado = new Scanner(System.in);
    
    // Metodo construtor vazio apenas para instanciar objeto e chamar as funcoes;
    // Deveria ser estatico mas vai mudar muitas implementações ja resolvidas;
    public ImplementarMenu(){
        
    }
    // Metodos de acesso;
    public ArrayList<Seguradora> getListaSeguradoras() {
        return listaSeguradoras;
    }

    // Metodos de implementacao;
    public void implementaMenu(){     
        int ope;
        boolean flagMenu = true;

        while(flagMenu){
            System.out.println("""
                    O que voce deseja realizar?\n
                    (1) Cadastrar
                    (2) Listar
                    (3) Excluir
                    (4) Gerar sinistro
                    (5) Transferir seguro
                    (6) Calcular receita da seguradora
                    (0) Sair\n""");
            ope = teclado.nextInt();
            teclado.nextLine();

            switch (ope) {
                case 1:
                    System.out.println("Opção escolhida: Cadastrar\n");
                    implementaMenuCadastrar();
                    break;
            
                case 2:
                    System.out.println("Opção escolhida: Listar\n");
                    implementaMenuListar();
                    break;
                
                case 3:
                    System.out.println("Opção escolhida: Excluir\n");
                    implementaMenuExcluir();
                    break;

                case 4:
                    System.out.println("Opção escolhida: Gerar sinistro\n");
                    geraSinistro();
                    break;

                case 5:
                    System.out.println("Opção escolhida: Transferir seguro\n");
                    transferirSeguro();
                    break;
                
                case 6: 
                    System.out.println("Opção escolhida: Calcular receita da seguradora\n");
                    calcularReceitaSeguradora();
                    break;

                case 0:
                    System.out.println("Opção escolhida: Sair\n");
                    flagMenu = false;
                    break;
            }
        }
    }

    public void implementaMenuCadastrar(){
        int ope, qtdFuncionarios, anoFabricacao;
        String nomeCliente, nomeSeguradora, endereco, tipoCliente, 
        dataLicenca, educacao, genero, classeEconomica, CPF, dataNascimento, 
        CNPJ,dataFundacao, identificadorCliente, placa, modelo, marca,
        telefone, email;
  
        Seguradora s;
        Cliente c;
        Veiculo v;
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
                System.out.println("""
                                    Opção escolhida: Cadastrar cliente\n
                                    Qual será a seguradora? Informe o nome dela""");
                nomeSeguradora = teclado.nextLine();

                s = buscarSeguradora(nomeSeguradora); 
                if(s == null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
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

                    System.out.println("Insira o CPF\n");
                    CPF = teclado.nextLine();
                    while(!Validacao.validarCPF(CPF)){
                        System.out.println("CPF invalido, insira novamente\n");
                        CPF = teclado.nextLine();
                    }
                    if (buscarCliente(s, CPF) == null){
                        System.out.println("Cliente já cadastrado\n");
                        break;
                    }
                    
                    System.out.println("Insira a data da licença no formato DD/MM/AAA\n");
                    dataLicenca = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    System.out.println("Insira o nivel de educação\n");
                    educacao = teclado.nextLine();

                    System.out.println("Insira o gênero\n");
                    genero = teclado.nextLine();

                    System.out.println("Insira a classe econômica\n");
                    classeEconomica = teclado.nextLine();

                    System.out.println("Insira a data de nascimento\n");
                    dataNascimento = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    ClientePF c1 = new ClientePF(nomeCliente, endereco,converteDataStrToLD(dataLicenca), educacao, genero,
                                                classeEconomica, CPF, converteDataStrToLD(dataNascimento));

                    s.registerCliente(c1);
                    break;
                }

                if(tipoCliente == "PJ"){
                    System.out.println("Insira o CNPJ\n");
                    CNPJ = teclado.nextLine();
                    while(!Validacao.validarCNPJ(CNPJ)){
                        System.out.println("CNPJ invalido, insira novamente\n");
                        CNPJ = teclado.nextLine();
                    }
                    if (buscarCliente(s, CNPJ) == null){
                        System.out.println("Cliente já cadastrado\n");
                        break;
                    }

                    System.out.println("Insira a data de fundação da empresa\n");
                    dataFundacao = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    System.out.println("Insira a quantidade de funcionarios da empresa\n");
                    qtdFuncionarios = teclado.nextInt();

                    ClientePJ c2 = new ClientePJ (nomeCliente, endereco, CNPJ, converteDataStrToLD(dataFundacao), qtdFuncionarios);
                    
                    s.registerCliente(c2);
                    break;
                }
                System.out.println("Tipo de cliente invalido\n");
                break;
            
            case 2: // Cadastrar veiculo
                System.out.println("""
                                    Opção escolhida: Cadastrar veículo\n
                                    Informe o nome da seguradora do cliente""");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if(s == null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF ou CNPJ do cliente\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }

                System.out.println("Insira a placa do veiculo\n");
                placa = teclado.nextLine();

                if (buscarVeiculo(c, placa) != null){
                    System.out.println("Veiculo já cadastrado\n");
                    break;
                }

                System.out.println("Insira o modelo do veiculo\n");
                modelo = teclado.nextLine();

                System.out.println("Insira a marca do veiculo\n");
                marca = teclado.nextLine();

                System.out.println("Insira o ano de fabricação\n");
                anoFabricacao = teclado.nextInt();
                teclado.nextLine();

                v = new Veiculo(placa, modelo, marca, anoFabricacao);
                c.registerVeiculo(v);
                break;

            case 3: // Cadastrar seguradora
                System.out.println("""
                                    Opção escolhida: Cadastrar seguradora\n
                                    Insira o nome da seguradora:\n""");
                nomeSeguradora = teclado.nextLine();

                if (buscarSeguradora(nomeSeguradora) !=  null){
                    System.out.println("Seguradora ja cadastrada\n");
                    break;
                }

                System.out.println("Insira o telefone da seguradora no padrao (DDD)XXXXX-XXXX:\n");
                telefone = teclado.nextLine();

                System.out.println("Insira o email da seguradora:\n");
                email = teclado.nextLine();

                System.out.println("Insira o endereco da seguradora:\n");
                endereco = teclado.nextLine();

                s = new Seguradora(nomeSeguradora, telefone, email, endereco);
                listaSeguradoras.add(s);
                break;
            
            case 4: // Voltar
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }  
    }

    public void implementaMenuExcluir(){
        int ope;
        String identificadorCliente, nomeSeguradora, placa;
        Seguradora s;
        Cliente c;
        Veiculo v;

        System.out.println("""
                            O que voce quer excluir?\n
                            (1) Cliente\n
                            (2) Veiculo\n
                            (3) Sinistro\n
                            (4) Voltar\n""");
        ope = teclado.nextInt();
        teclado.nextLine();

        switch(ope){
            case 1: // excluir cliente;
                System.out.println("""
                    Opção escolhida: Cadastrar seguradora\n
                    Insira o nome da seguradora:\n""");
                
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s == null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF ou CNPJ do cliente que deseja excluir\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }

                s.removeCliente(c);  
                break;
            
            case 2: // excluir veículo;
                System.out.println("""
                                    Opção escolhida: Excluir veículo\n
                                    Informe o nome da seguradora do cliente\n""");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if(s == null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe  o CPF ou CNPJ do cliente\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }

                System.out.println("Informe  a placa do veículo que deseja excluir\n");
                placa = teclado.nextLine();
                v = buscarVeiculo(c, placa);
                if (v == null){
                    System.out.println("Veiculo não encontrado\n");
                    break;
                }
                c.removeVeiculo(v);
                break;

            case 3: // excluir seguradora;
                System.out.println("""
                                    Opção escolhida: Excluir seguradora\n
                                    Insira o nome da seguradora que deseja excluir:\n""");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }
                listaSeguradoras.remove(s);
                break;

            case 4: // Voltar;
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }    
    }

    public void implementaMenuListar(){
        int ope;
        String nomeSeguradora, identificadorCliente;
        Seguradora s;
        Cliente c;

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

        switch(ope){
            case 1: // listar clientes por seguradora;
                System.out.println("Opção escolhida: listar cliente por seguradora\n");
                System.out.println("Informe o nome da seguradora\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }
                System.out.println("Lista de clientes da seguradora "+s.getNome()+":\n" + s.getListaClientes()+ "\n");
                break;
            
            case 2: // listar sinistros por seguradora;
                System.out.println("Opção escolhida: listar sinistros por seguradora\n");
                System.out.println("Informe o nome da seguradora\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }
                System.out.println("Lista de sinistros da seguradora "+s.getNome()+":\n" + s.getListaSinistros()+"\n");
                break;

            case 3: // listar sinistros por cliente;
                System.out.println("Opção escolhida: listar sinistros por cliente\n");
                System.out.println("Informe o nome da seguradora do cliente\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF OU CNPJ do cliente\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }
                listarSinistrosCliente(s,c);
                break;

            case 4: // listar veiculos por cliente
                System.out.println("Opção escolhida: listar veiculos por cliente\n");
                System.out.println("Informe o nome da seguradora do cliente\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF ou CNPJ do cliente\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }
                System.out.println("Lista de veiculos do clinte "+c.getNome()+":\n" + c.getListaVeiculos()+"\n");
                break;


            case 5: // listar veiculos por seguradora
                System.out.println("Opção escolhida: listar veiculos por seguradora\n");
                System.out.println("Informe o nome da seguradora do cliente\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }
                listarVeiculosSeguradora(s);
                break;

            case 6: // Voltar;
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }    
    }

    // Metodo para converter uma string para LocalDate separando a String pelo '/'
    public static LocalDate converteDataStrToLD(String data){
        // data type: dd/mm/aaaa
        String dataSplitted[] = data.split("/");
        return LocalDate.of(Integer.valueOf(dataSplitted[2]), Integer.valueOf(dataSplitted[1]),
                            Integer.valueOf(dataSplitted[0]));
    }

    // Metodo para buscar uma seguradora na lista de seguradoras;
    public Seguradora buscarSeguradora(String nomeSeg){
        for (Seguradora seg : listaSeguradoras){
            if(seg.getNome().equals(nomeSeg)){
                System.out.println("Seguradora encontrada\n");
                return seg;
            }
        }
        return null;
    }

    // Metodo para buscar um cliente na lista de clientes de uma seguradora
    public Cliente buscarCliente(Seguradora seg, String clienteID){
        for (Cliente cli: seg.getListaClientes()){
            if (cli.getIdentificador().equals(clienteID)){
                System.out.println("Cliente encontrado\n");
                return cli;
            }
        }
        return null;
    }

    // Metodo para buscar veiculo na lista de veiculos de um cliente
    public Veiculo buscarVeiculo (Cliente cliente, String placaVeiculo){
        for(Veiculo v: cliente.getListaVeiculos()){
            if(v.getPlaca().equals(placaVeiculo)){
                System.out.println("Veiculo encontrado\n");
                return v;
            }
        }
        return null;
    }

    public void listarSinistrosCliente(Seguradora seg, Cliente cli){
        int count = 0;
        boolean temSinistro = false;
        for(Sinistro sin: seg.getListaSinistros()){
            if (sin.getCliente().getIdentificador().equals(cli.getIdentificador())){
                System.out.println("Sinistro "+sin.getId()+":\n"+sin+"\n");
                if(!temSinistro){
                    temSinistro = true;
                }
            }
            // Se a lista chegou ao fim e o cliente não foi encontrado, ele não possui sinistros;
            // verificar se flag funciona corretamente;
            if(count == seg.getListaSinistros().size() -1 && !temSinistro){
                System.out.println("Nenhum sinistro gerado por esse cliente\n");
            }
            count++;
        }
    }
    public void listarVeiculosSeguradora(Seguradora seg){
        int count = 0;
        boolean temVeiculo = false;
        System.out.println("Lista de veiculos da seguradora "+seg.getNome()+":\n");

        for (Cliente cli: seg.getListaClientes()){
            if(cli.getListaVeiculos() != null){
                System.out.println(cli.getListaVeiculos()+"\n");
                temVeiculo = true;
            }
            if (count == seg.getListaClientes().size() -1 && !temVeiculo){
                System.out.println("Nenhum veiculo cadastrado para essa seguradora\n");
            }
            count++;
        }
    }

    public void geraSinistro(){
        Seguradora s;
        Cliente c;
        Veiculo v;
        String nomeSeguradora, identificadorCliente, placa, dataSinistro, enderecoSinistro;
        System.out.println("Informe o nome da seguradora\n");
        nomeSeguradora = teclado.nextLine();
        s = buscarSeguradora(nomeSeguradora);
        if (s ==  null){
            System.out.println("Seguradora não encontrada\n");
            return;
        }

        System.out.println("Informe o CPF ou CNPJ do cliente\n");
        identificadorCliente = teclado.nextLine();
        c = buscarCliente(s, identificadorCliente);
        if (c ==  null){
            System.out.println("Cliente não encontrada\n");
            return;
        }

        System.out.println("Informe  a placa do veículo que deseja excluir\n");
        placa = teclado.nextLine();
        v = buscarVeiculo(c, placa);
        if (v == null){
            System.out.println("Veiculo não encontrado\n");
            return;
        }

        System.out.println("Insira a data do sinistro no padrao DD/MM/AAAA:\n");
        dataSinistro = teclado.nextLine();

        System.out.println("Insira o endereco do sinistro:\n");
        enderecoSinistro = teclado.nextLine();

        s.gerarSinistros(dataSinistro, enderecoSinistro, s, v, c);
        return;
    }

    public void transferirSeguro(){
        Seguradora s;
        Cliente c, cNew;
        String nomeSeguradora, identificadorCliente, identificadorNewCliente;

        System.out.println("Informe o nome da seguradora\n");
        nomeSeguradora = teclado.nextLine();
        s = buscarSeguradora(nomeSeguradora);
        if (s ==  null){
            System.out.println("Seguradora não encontrada, transferência nao realizada\n");
            return;
        }

        System.out.println("Informe o CPF ou CNPJ do cliente\n");
        identificadorCliente = teclado.nextLine();
        c = buscarCliente(s, identificadorCliente);
        if (c ==  null){
            System.out.println("Cliente não encontrado, transferência nao realizada\n");
            return;
        }

        System.out.println("Informe o CPF ou CNPJ do cliente para o qual deseja transferir o seguro\n");
        identificadorNewCliente = teclado.nextLine();
        cNew = buscarCliente(s, identificadorNewCliente);
        if (cNew ==  null){
            System.out.println("Cliente não encontrado, transferência invalida\n");
            return;
        }
        // Transfere os veiculos segurados de um cliente para o outro;
        cNew.getListaVeiculos().addAll(c.getListaVeiculos());
        // Calculando novamente o score do cliente com novos carros atribuidos
        cNew.calculaScore();
        // Apaga os veiculos segurados do cliente após transferência;
        c.getListaVeiculos().removeAll(c.getListaVeiculos());

        // -------- precisa chamar o metodo calculaPrecoSeguro? --------- //
    }

    public double calcularReceitaSeguradora(){
        Seguradora s;
        String nomeSeguradora;
        double receita;

        System.out.println("Informe o nome da seguradora\n");
        nomeSeguradora = teclado.nextLine();
        s = buscarSeguradora(nomeSeguradora);
        if (s ==  null){
            System.out.println("Seguradora não encontrada, nao foi possivel calcular receita\n");
            return 0;
        }
        receita = s.calcularReceita(s);
        return receita;
    }
}