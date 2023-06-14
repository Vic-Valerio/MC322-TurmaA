package Codigos;
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
    public void setListaSeguradoras(Seguradora seg){
        listaSeguradoras.add(seg);
    }

    public ArrayList<Seguradora> getListaSeguradoras() {
        return listaSeguradoras;
    }

    // Metodos de implementacao;
    public void implementaMenu(){     
        int ope;
        boolean flagMenu = true;
        MenuPrincipal mp = null;

        while(flagMenu){
            System.out.println("O que voce deseja realizar?\n" +
                    "(1) Cadastrar\n" +
                    "(2) Listar\n" +
                    "(3) Excluir\n" +
                    "(4) Gerar sinistro\n" +
                    "(5) Calcular receita da seguradora\n"+
                    "(0) Sair\n");
            ope = teclado.nextInt();
            teclado.nextLine();
            mp = MenuPrincipal.obterOperacao(ope);

            switch (mp) {
                case CADASTRAR:
                    System.out.println("Opção escolhida: Cadastrar\n");
                    implementaMenuCadastrar();
                    break;
            
                case LISTAR:
                    System.out.println("Opção escolhida: Listar\n");
                    implementaMenuListar();
                    break;
                
                case EXCLUIR:
                    System.out.println("Opção escolhida: Excluir\n");
                    implementaMenuExcluir();
                    break;

                case GERAR_SINISTRO:
                    System.out.println("Opção escolhida: Gerar sinistro\n");
                    geraSinistro();
                    break;

                
                case CALCULAR_RECEITA_SEGURADORA: 
                    System.out.println("Opção escolhida: Calcular receita da seguradora\n");
                    calcularReceitaSeguradora();
                    break;

                case SAIR:
                    System.out.println("Opção escolhida: Sair\n");
                    flagMenu = false;
                    break;
            }
        }
    }

    public void implementaMenuCadastrar(){
        int ope, anoFabricacao;
        String nomeCliente, nomeSeguradora, endereco, tipoCliente, 
        educacao, genero, CPF, dataNascimento, 
        CNPJ,dataFundacao, identificadorCliente, placa, modelo, marca,
        telefone, email;
        MenuCadastrar mc = null;
  
        Seguradora s;
        Cliente c;
        Veiculo v;
        System.out.println("O que voce quer cadastrar?\n(1) Cliente\n(2) Veiculo\n(3) Seguradora\n(4) Voltar\n");
        ope = teclado.nextInt();
        teclado.nextLine();
        mc = MenuCadastrar.obterOperacao(ope);
    
        switch(mc){
            case CADASTRAR_CLIENTE: // Cadastrar cliente
                System.out.println("Opção escolhida: Cadastrar cliente\nQual será a seguradora? Informe o nome dela");
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

                System.out.println("Insira o telefone no formato (DDD)XXXXX-XXXX\n");
                telefone = teclado.nextLine();

                System.out.println("Insira o email\n");
                email = teclado.nextLine();

                System.out.println("Insira o tipo de cliente:\n"+
                                    "PF = Pessoa fisica\n" +
                                    "PJ = Pessoa juridica\n");
                tipoCliente = teclado.nextLine();

                if(tipoCliente.equals("PF")){

                    System.out.println("Insira o CPF\n");
                    CPF = teclado.nextLine();
                    while(!Validacao.validarCPF(CPF)){
                        System.out.println("CPF invalido, insira novamente\n");
                        CPF = teclado.nextLine();
                    }
                    if (buscarCliente(s, CPF) != null){
                        System.out.println("Cliente já cadastrado\n");
                        break;
                    }

                    System.out.println("Insira o nivel de educação\n");
                    educacao = teclado.nextLine();

                    System.out.println("Insira o gênero\n");
                    genero = teclado.nextLine();

                    System.out.println("Insira a data de nascimento\n");
                    dataNascimento = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    ClientePF c1 = new ClientePF(nomeCliente, telefone, endereco, email, educacao, genero,
                                                CPF, converteDataStrToLD(dataNascimento));

                    s.cadastrarCliente(c1);
                    System.out.println("Cliente cadastrado com sucesso\n");
                    break;
                }

                if(tipoCliente.equals("PJ")){
                    System.out.println("Insira o CNPJ\n");
                    CNPJ = teclado.nextLine();
                    while(!Validacao.validarCNPJ(CNPJ)){
                        System.out.println("CNPJ invalido, insira novamente\n");
                        CNPJ = teclado.nextLine();
                    }
                    if (buscarCliente(s, CNPJ) != null){
                        System.out.println("Cliente já cadastrado\n");
                        break;
                    }

                    System.out.println("Insira a data de fundação da empresa\n");
                    dataFundacao = teclado.nextLine(); // convertida para LocalDate no construtor do cliente;

                    ClientePJ c2 = new ClientePJ (nomeCliente, endereco, telefone, email, CNPJ, converteDataStrToLD(dataFundacao));
                    
                    s.cadastrarCliente(c2);
                    System.out.println("Cliente cadastrado com sucesso\n");
                    break;
                }
                System.out.println("Tipo de cliente invalido\n");
                break;
            
            case CADASTRAR_VEICULO: // Cadastrar veiculo
                System.out.println("Opção escolhida: Cadastrar veículo\nInforme o nome da seguradora do cliente");
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
                // Buscando veiculo em um cliente PF
                if (buscarVeiculo((ClientePF) c, placa) != null){
                    System.out.println("Veiculo já cadastrado\n");
                    break;
                }
                Frota f = null;
                if (c instanceof ClientePJ){
                    String nomeFrota;
                   
                    System.out.println("Insira o nome da frota em que o veiculo sera cadastrado:\n");
                    nomeFrota = teclado.nextLine();
                    f = buscarFrota(nomeFrota, (ClientePJ) c);
                    if (f == null){
                        System.out.println("Frota não encontrado\n");
                        break;
                    }
                    // Buscando veiculo em um cliente PJ
                    if (buscarVeiculo((ClientePJ) c, f, placa) != null){
                        System.out.println("Veiculo já cadastrado\n");
                        break;
                    }
                }

                System.out.println("Insira o modelo do veiculo\n");
                modelo = teclado.nextLine();

                System.out.println("Insira a marca do veiculo\n");
                marca = teclado.nextLine();

                System.out.println("Insira o ano de fabricação\n");
                anoFabricacao = teclado.nextInt();
                teclado.nextLine();

                v = new Veiculo(placa, modelo, marca, anoFabricacao);
                
                if (c instanceof ClientePF){
                    ClientePF cPF = (ClientePF) c;
                    cPF.adicionarVeiculo(v);
                }
                else{
                    f.adicionarVeiculo(v);
                }
                
                System.out.println("Veículo cadastrado com sucesso\n");
                break;

            case CADASTRAR_SEGURADORA: // Cadastrar seguradora
                System.out.println("Opção escolhida: Cadastrar seguradora\nInsira o nome da seguradora:\n");
                nomeSeguradora = teclado.nextLine();

                if (buscarSeguradora(nomeSeguradora) !=  null){
                    System.out.println("Seguradora ja cadastrada\n");
                    break;
                }

                System.out.println("Insira o CNPJ da seguradora:\n");
                CNPJ = teclado.nextLine();

                System.out.println("Insira o telefone da seguradora no padrao (DDD)XXXXX-XXXX:\n");
                telefone = teclado.nextLine();

                System.out.println("Insira o email da seguradora:\n");
                email = teclado.nextLine();

                System.out.println("Insira o endereco da seguradora:\n");
                endereco = teclado.nextLine();

                s = new Seguradora(CNPJ, nomeSeguradora, telefone, email, endereco);
                listaSeguradoras.add(s);
                break;
            
            case VOLTAR: // Voltar
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }  
    }

    public void implementaMenuExcluir(){
        boolean temSinistro = false;
        int ope, identificadorSinistro;
        String identificadorCliente, nomeSeguradora, placa;
        Seguradora s;
        Cliente c;
        Veiculo v;
        MenuExcluir me;

        System.out.println("O que voce quer excluir?\n(1) Cliente\n(2) Veiculo\n(3) Sinistro\n(4) Voltar\n");
        ope = teclado.nextInt();
        teclado.nextLine();
        me = MenuExcluir.obterOperacao(ope);

        switch(me){
            case EXCLUIR_CLIENTE: // excluir cliente;
                System.out.println("Opção escolhida: Cadastrar seguradora\nInsira o nome da seguradora:\n");
                
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s == null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF ou CNPJ do cliente que deseja excluir, com a pontuação correta\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }
                
                s.removerCliente(c);  
                System.out.println("Cliente removido com sucesso\n");
                break;
            
            case EXCLUIR_VEICULO: // excluir veículo;
                System.out.println("Opção escolhida: Excluir veículo\nInforme o nome da seguradora do cliente\n");
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

                // Busca veiculos do cliente PF
                v = buscarVeiculo((ClientePF) c, placa);
                if (v == null){
                    System.out.println("Veiculo não encontrado\n");
                    break;
                }

                Frota f = null;
                if (c instanceof ClientePJ){
                    String nomeFrota;
                    System.out.println("Insira o nome da frota em que o veiculo sera cadastrado:\n");
                    nomeFrota = teclado.nextLine();
                    f = buscarFrota(nomeFrota, (ClientePJ) c);
                    if (f == null){
                        System.out.println("Frota não encontrado\n");
                        break;
                    }
                    // Buscando veiculo em um cliente PJ
                    if (buscarVeiculo((ClientePJ) c, f, placa) == null){
                        System.out.println("Veiculo não encontrado\n");
                        break;
                    }

                if (c instanceof ClientePF){
                    ClientePF cPF = (ClientePF) c;
                    cPF.removerVeiculo(v);
                }
                else{
                    f.removerVeiculo(v);
                }
                System.out.println("Veículo removido com sucesso\n");
                break;
            }

            case EXCLUIR_SINISTRO: // excluir sinistro;
                System.out.println("Opção escolhida: Excluir sinistro\nInsira o nome da seguradora que contém o sinistro:\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe  o ID do sinistro que deseja excluir:\n");
                identificadorSinistro = teclado.nextInt();
                teclado.nextLine();

                for(Seguro seguro:s.getListaSeguros()){
                    // Procura o sinistro na lista de sinistros do cliente;
                    for(Sinistro sinis: seguro.getListaSinistros()){
                        if(sinis.getId() == identificadorSinistro){
                            temSinistro = true;
                            seguro.getListaSinistros().remove(sinis);
                            System.out.println("Sinistro removido com sucesso\n");
                            break;
                        }
                    }
                    // Procura o sinistro na lista de sinistros do condutor;
                    for(Condutor cond: seguro.getListaCondutores()){
                        for(Sinistro sinis: cond.getListaSinistros()){
                            if(sinis.getId() == identificadorSinistro){
                                temSinistro = true;
                                cond.getListaSinistros().remove(sinis);
                                System.out.println("Sinistro removido com sucesso\n");
                                break;
                            }
                        }
                    }
                }
                if(!temSinistro){
                    System.out.println("Sinistro não encontrado e não pode ser removido\n");
                }
                break;

            case VOLTAR: // Voltar;
                System.out.println("Opção escolhida: Voltar\n");
                break;
        }    
    }

    public void implementaMenuListar(){
        int ope;
        String nomeSeguradora, identificadorCliente;
        Seguradora s;
        Cliente c;
        MenuListar ml;

        System.out.println("O que voce quer listar?\n(1) Cliente por seguradora\n(2) Sinistros por seguradora\n(3) Sinistro por cliente\n(4) Veiculo por cliente\n(5) Veiculo por seguradora\n(6) Voltar\n");

        ope = teclado.nextInt();
        teclado.nextLine();
        ml = MenuListar.obterOperacao(ope);

        switch(ml){
            case LISTAR_CLIENTE_POR_SEGURADORA: // listar clientes por seguradora;
                System.out.println("Opção escolhida: listar cliente por seguradora\n");
                System.out.println("Informe o nome da seguradora\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }
                System.out.println("Lista de clientes da seguradora "+s.getNome()+":\n"+ s.getListaClientes());
                break;
            
            case LISTAR_SINISTRO_POR_SEGURADORA: // listar sinistros por seguradora;
                System.out.println("Opção escolhida: listar sinistros por seguradora\n");
                System.out.println("Informe o nome da seguradora\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                for(Seguro seguro:s.getListaSeguros()){
                    if(seguro.getListaSinistros() != null){
                        System.out.println("Lista de sinistros do cliente "+seguro.getCliente().getIdentificador()+":\n"+seguro.getListaSinistros());
                    }
                    for(Condutor cond: seguro.getListaCondutores()){
                        if(cond.getListaSinistros() != null){
                            System.out.println("Lista de sinistros do condutor "+cond.getCpf()+":\n"+cond.getListaSinistros());
                        }
                    }
                }
                break;

            case LISTAR_SINISTRO_POR_CLIENTE: // listar sinistros por cliente;
                System.out.println("Opção escolhida: listar sinistros por cliente\n");
                System.out.println("Informe o nome da seguradora do cliente\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF OU CNPJ do cliente com a pontuação correta\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }
                listarSinistrosCliente(s,c); // Olhar esse metodo
                break;

            case LISTAR_VEICULO_POR_CLIENTE: // listar veiculos por cliente
                System.out.println("Opção escolhida: listar veiculos por cliente\n");
                System.out.println("Informe o nome da seguradora do cliente\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }

                System.out.println("Informe o CPF ou CNPJ do cliente com a pontuação correta\n");
                identificadorCliente = teclado.nextLine();
                c = buscarCliente(s, identificadorCliente);
                if (c == null){
                    System.out.println("Cliente não encontrado\n");
                    break;
                }

                if(c instanceof ClientePF){
                    ClientePF cPF = (ClientePF)c;
                    System.out.println("Lista de veiculos do clinte " +cPF.getNome()+":\n" +cPF.getListaVeiculos());
                    break;
                }
                else{
                    ClientePJ cPJ = (ClientePJ)c;
                    for(Frota frota:cPJ.getListaFrota()){
                        System.out.println("Lista de veiculos da frota " +frota.getCode()+":\n" +frota.getListaVeiculos());
                    }
                    break;
                }
                


            case LISTAR_VEICULO_POR_SEGURADORA: // listar veiculos por seguradora
                System.out.println("Opção escolhida: listar veiculos por seguradora\n");
                System.out.println("Informe o nome da seguradora\n");
                nomeSeguradora = teclado.nextLine();
                s = buscarSeguradora(nomeSeguradora);
                if (s ==  null){
                    System.out.println("Seguradora não encontrada\n");
                    break;
                }
                listarVeiculosSeguradora(s);
                break;

            case VOLTAR: // Voltar;
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
    // A busca ainda se manteve pelo nome para evitar muitas modificações no codigo;
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

    // Metodo para buscar veiculo na lista de veiculos de um cliente PF
    public Veiculo buscarVeiculo(ClientePF clientePF, String placaVeiculo){
        for(Veiculo v: clientePF.getListaVeiculos()){
            if(v.getPlaca().equals(placaVeiculo)){
                System.out.println("Veiculo encontrado\n");
                return v;
            }
        }
        return null;
    }
    // Metodo para buscar veiculo na lista de veiculos de um cliente PJ
    public Veiculo buscarVeiculo(ClientePJ clientePJ, Frota frota, String placaVeiculo){
        for (Frota f: clientePJ.getListaFrota()){
            for(Veiculo v: f.getListaVeiculos()){
                if(v.getPlaca().equals(placaVeiculo)){
                    System.out.println("Veiculo encontrado\n");
                    return v;
                }
            }
        }
        return null;
    }

    public Frota buscarFrota(String nomeFrota, ClientePJ clientePJ){
        for(Frota frota: clientePJ.getListaFrota()){
            if(frota.getCode().equals(nomeFrota)){
                System.out.println("Frota encontrada\n");
                return frota;
            }
        }
        return null;
    }

    public Condutor buscarCondutor(String cpfDoCondutor, Seguro seguroCondutor){
        for (Condutor c:seguroCondutor.getListaCondutores()){
            if(c.getCpf().equals(cpfDoCondutor)){
                System.out.println("condutor encontrado\n");
                return c;
            }
        }
        return null;

    }

    public void listarSinistrosCliente(Seguradora seguradora, Cliente cli){
        boolean temSinistro = false, temCliente = false;
        System.out.println("Sinistros do cliente "+cli.getNome()+":\n");

        for(Cliente c: seguradora.getListaClientes()){
            if(c.getIdentificador().equals(cli.getIdentificador())){
                temCliente = true;
                break;
            }
        }
        if(!temCliente){
            System.out.println("Cliente não encontrado\n");
            return;
        }

        for(Seguro seguro: seguradora.getListaSeguros()){
            System.out.println("Sinistros do cliente no seguro "+seguro.getId()+"\n");
            if(seguro.getCliente().getIdentificador().equals(cli.getIdentificador())){
                for(Sinistro sinis: seguro.getListaSinistros()){                    
                    if(seguro.getListaCondutores()!= null){
                        temSinistro = true;
                        System.out.println(sinis +"\n");
                    }
                }
                for(Condutor condutor: seguro.getListaCondutores()){
                    System.out.println("Sinistros do condutor "+condutor.getCpf()+"\n");
                    if(condutor.getListaSinistros() != null){
                        temSinistro = true;
                        System.out.println(condutor.getListaSinistros()+"\n");
                    }
                    else{
                        System.out.println("Condutor não possui sinistros");
                    }
                }
            }
            else{
                System.out.println("Nenhum sinistros do cliente no seguro "+seguro.getId()+"\n");
            }
        }
/*
        Seguro segu = null;
        for(Seguro seguro: seguradora.getListaSeguros()){
            // 1 Cliente PF possui zero ou um seguroPF;
            if (cli instanceof ClientePF){
                if(seguro.getCliente().getIdentificador().equals(cli.getIdentificador())){
                    segu = seguro;
                    break;
                }
            }
            // 1 Cliente PJ pode ter muitos seguroPJ: verificar codigo da frota;
            else if(seguro.getCliente().getIdentificador().equals(cli.getIdentificador())){
                SeguroPJ seguroPJ = (SeguroPJ)seguro;
                ClientePJ cPJ = (ClientePJ)cli;
                for(Frota frota:cPJ.getListaFrota()){
                    if(seguroPJ.getFrota().getCode().equals(frota.getCode())){
                        segu = seguro;
                        break;
                    }
                }
            }
        }
        if(segu ==  null){
            System.out.println("Seguro não existente para esse cliente\n");
            return;
        }

        for(Sinistro sinis: segu.getListaSinistros()){
            if(segu.getListaCondutores()!= null){
                System.out.println("Sinistros do cliente:\n" + sinis + "\n");
                temSinistro = true;
            }
        }
        // Printa somente os sinistros d um seguro e nao de todos os seguros...
        // Modificar esse metodo;
        for(Condutor condutor: segu.getListaCondutores()){
            if(condutor.getListaSinistros() != null){
                temSinistro = true;
                System.out.println("Sinistros do condutor "+condutor.getCpf()+"\n"+condutor.getListaSinistros()+"\n");
            }
        }
*/

        if(!temSinistro){
            System.out.println("Nenhum sinistro registrado nesse cliente\n");
        }  
        return;
    }
    public void listarVeiculosSeguradora(Seguradora seg){
        boolean temVeiculo = false;
        System.out.println("Lista de veiculos da seguradora "+seg.getNome()+":\n");
        for (Cliente cli: seg.getListaClientes()){
            if(cli instanceof ClientePF){
                ClientePF clientePF = (ClientePF)cli;
                if(clientePF.getListaVeiculos() != null){
                    System.out.println(clientePF.getListaVeiculos()+"\n");
                    temVeiculo = true;
                }
            }
            else{
                ClientePJ clientePJ = (ClientePJ)cli;
                for (Frota frota: clientePJ.getListaFrota()){
                    if(clientePJ.getListaFrota() != null){
                        System.out.println(frota.getListaVeiculos()+"\n");
                        temVeiculo = true; // Toda frota tem ao menos um veiculo
                    }  
                }
            }
        }
        if (!temVeiculo){
            System.out.println("Nenhum veiculo cadastrado para essa seguradora\n");
        }
        return;
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
            System.out.println("Seguradora não encontrada, sinistro nao pode ser gerado\n");
            return;
        }

        System.out.println("Informe o CPF ou CNPJ do cliente\n");
        identificadorCliente = teclado.nextLine();
        c = buscarCliente(s, identificadorCliente);
        if (c ==  null){
            System.out.println("Cliente não encontrado, sinistro nao pode ser gerado\n");
            return;
        }
        Seguro segu= null;
        for (Seguro seguro: s.getListaSeguros()){
            // 1 Cliente PF possui zero ou um seguroPF;
            if (c instanceof ClientePF){
                if(seguro.getCliente().getIdentificador().equals(c.getIdentificador())){
                    segu = seguro;
                    break;
                }
            }
            // 1 Cliente PJ pode ter muitos seguroPJ: verificar codigo da frota;
            else if(seguro.getCliente().getIdentificador().equals(c.getIdentificador())){
                SeguroPJ seguroPJ = (SeguroPJ)seguro;
                ClientePJ cPJ = (ClientePJ)c;
                for(Frota frota:cPJ.getListaFrota()){
                    if(seguroPJ.getFrota().getCode().equals(frota.getCode())){
                        segu = seguro;
                        break;
                    }
                }
            }
        }
        if(segu ==  null){
            System.out.println("Seguro não existente para esse cliente, sinistro nao pode ser gerado\n");
            return;
        }

        System.out.println("Informe  a placa do veículo\n");
        placa = teclado.nextLine();

        // Buscando veiculos do cliente PF
        v = buscarVeiculo((ClientePF) c, placa);
        if (v == null){
            System.out.println("Veiculo não encontrado, sinistro nao pode ser gerado\n");
            return;
        }
        Frota f = null;
        if (c instanceof ClientePJ){
            String nomeFrota;
            System.out.println("Insira o nome da frota em que o veiculo sera cadastrado:\n");
            nomeFrota = teclado.nextLine();
            f = buscarFrota(nomeFrota, (ClientePJ) c);
            if (f == null){
                System.out.println("Frota não encontrado, sinistro nao pode ser gerado\n");
                return;
            }
            // Buscando veiculo em um cliente PJ
            if (buscarVeiculo((ClientePJ) c, f, placa) == null){
                System.out.println("Veiculo não encontrado, sinistro nao pode ser gerado\n");
                return;
            }
        }

        System.out.println("Insira a data do sinistro no padrao DD/MM/AAAA:\n");
        dataSinistro = teclado.nextLine();

        System.out.println("Insira o endereco do sinistro:\n");
        enderecoSinistro = teclado.nextLine();

        String opcao = "";
        System.out.println("O sinistro foi gerado por um condutor? Responda S para sim e N para nao\n");
        opcao = teclado.nextLine();

        if(opcao.equals("S")){
            Condutor cond = null;
            String cpfCondutor = "";
            System.out.println("Insira o CPF do condutor\n");
            cpfCondutor = teclado.nextLine();
            cond = buscarCondutor(cpfCondutor, segu);
            if (cond == null){
                System.out.println("condutor não encontrado, sinistro nao pode ser gerado\n");
                return;
            }
            // Gera sinistro do condutor;
            segu.gerarSinistros(dataSinistro, enderecoSinistro, cond);
            System.out.println("Sinistro registrado na lista de sinistros do condutor "+cond.getCpf()+"\n");
            return;

        }
        else if(opcao.equals("N")){
            // Gera sinistro do cliente
            segu.gerarSinistros(dataSinistro, enderecoSinistro);
            System.out.println("Sinistro registrado na lista de sinistros do cliente "+c.getIdentificador()+"\n");
            return;
        }
        else{
            System.out.println("Opraçao inválida, sinistro nao pode ser gerado\n");
        }
        return;
    }

    public void calcularReceitaSeguradora(){
        Seguradora s;
        String nomeSeguradora;
        double receita;

        System.out.println("Informe o nome da seguradora\n");
        nomeSeguradora = teclado.nextLine();
        s = buscarSeguradora(nomeSeguradora);
        if (s ==  null){
            System.out.println("Seguradora não encontrada, nao foi possivel calcular receita\n");
            return;
        }
        receita = s.calcularReceita();
        System.out.println("Receita total da seguradora "+s.getNome()+ ": R$"+ String.format("%.1f",receita)+"\n");
        return;
    }
}