package Codigos;
//import java.util.ArrayList;
import java.util.List;

public class AppMain {
    public static void main(String[] args) throws Exception {
        // Objetos instanciados considerando todos os dados validos
        // sem a necessidade de aplicar os metodos de validação;
        Seguradora solSeguros = new Seguradora("44.668.939/0001-02", "Sol Seguros", "19 99547-8236",
                                "solseguros@gmail.com", "Rua do castor, 1028");

        // Instanciando 2 clientes PF com CPFs validos;
        Cliente c1 = new ClientePF("Victor Valerio", "Rua Luverci Pereira", "(19)99161-8109", 
                     "v245146@dac.unicamp.br", "437.355.398-07", "Masculino",
                     "Superior incompleto", ImplementarMenu.converteDataStrToLD("15/03/2001"));

        Cliente c2 = new ClientePF("Luiz Almeida", "Rua Amazonas", "(31)99258-7496",
                        "luizalmeida@gmail.com", "431.295.130-60", "Não-binario",
                        "Ensino superior completo", ImplementarMenu.converteDataStrToLD("10/05/1991"));

        // Instanciando 2 clientes PJ com CNPJs validos;
        Cliente c3 = new ClientePJ("Programacao Basica LTDA", "Rua do Python, n_o MC102", "(19)3620-1215",
                        "programacaomc102@hotmail.com","45.773.100/0001-99",
                        ImplementarMenu.converteDataStrToLD("05/02/2010"));

        Cliente c4 = new ClientePJ("Programacao avancada LTDA", "Rua do C, n_o MC202", "(11)3084-4565",
                            "programacaomc202@gmail.com", "31.778.022/0001-00",
                            ImplementarMenu.converteDataStrToLD("20/06/2005"));

        // Instanciando duas frotas;
        Frota f1 = new Frota("Caminhoes");
        Frota f2 = new Frota("Veículos de passeio");

        // Instanciando veículos;
        Veiculo v1 = new Veiculo("ABC0123", "Honda", "Fit", 2012);
        Veiculo v2 = new Veiculo("ABC4567", "VW", "Gol", 1998);
        Veiculo v3 = new Veiculo("ABC0001", "Chevrolet", "Omega", 1994);

        Veiculo v4 = new Veiculo("FEM0369", "Toyota", "Corolla", 2018);
        Veiculo v5 = new Veiculo("FEM0147", "VW", "e-Delivery", 2021);
        Veiculo v6 = new Veiculo("FEM0258", "IVECO", "Tector", 2015);

        // Instanciando condutores
        Condutor cond1 = new Condutor("123.639.478-00", "Agnaldo", "Rua da sabedoria", "(19)99137-1007",
                            "agnaldo@gmail.com", ImplementarMenu.converteDataStrToLD("12/12/1964"));
                        
        Condutor cond2 = new Condutor("123.487.478-47", "Claudia", "Rua da vitória", "(19)99207-0306",
                            "claudia@gmail.com", ImplementarMenu.converteDataStrToLD("19/10/1970"));

        Condutor cond3 = new Condutor("212.985.130-49", "Jean Carlo", "Rua das amoras", null,
        "jean.carlo@gmail.com", ImplementarMenu.converteDataStrToLD("12/03/1994"));


        /*    Atribuições e relacionamentos entre os objetos    */

        // Cadastrando clientes na seguradora
        System.out.println("\n-------- Cadastrando clientes na seguradora -------\n");
        solSeguros.cadastrarCliente(c1);
        solSeguros.cadastrarCliente(c2);
        solSeguros.cadastrarCliente(c3);
        solSeguros.cadastrarCliente(c4);

        // Listando os clientes para verificar se foram cadastrados com sucesso;
        System.out.println("-------- Lista de clientes da seguradora -------\n");
        solSeguros.listarClientes("PF");
        solSeguros.listarClientes("PJ");
        System.out.println("------- Cadastrando veiculos e frotas aos clientes -------\n");

        // Cadastrando veiculos aos clientes PF e as frotas dos clientes PJ
        ClientePF c1PF = (ClientePF)c1;
        ClientePF c2PF = (ClientePF)c2;
        c1PF.adicionarVeiculo(v1);
        c1PF.adicionarVeiculo(v2);

        c2PF.adicionarVeiculo(v3);

        f1.adicionarVeiculo(v5);
        f1.adicionarVeiculo(v6);
        f2.adicionarVeiculo(v4);

        // Cadastrando frotas no cliente 3 e 4(PJ);
        ClientePJ c3PJ = (ClientePJ)c3;
        ClientePJ c4PJ = (ClientePJ)c4;
        c3PJ.cadastrarFrota(f1);
        c4PJ.cadastrarFrota(f2);

        //Mostrando veiculos do cliente por frota;
        System.out.println("\n-------- Lista de veiculos por frota -------");
        c3PJ.getVeiculosporFrota(f1);
        System.out.println("\n");
        c4PJ.getVeiculosporFrota(f2);

        // Passando uma frota não cadastrada ao cliente: mensagem de nao encontrada;
        System.out.println("\n--- Informando uma frota não pertencente ao cliente ---");
        c3PJ.getVeiculosporFrota(f2);

        // Atualizando a frota 1 removendo o veiculo 6, restando apenas o veiculo 5;
        System.out.println("----- Removendo um veiculo -----");
        c3PJ.atualizarFrota(f1, v6, 0);

        // Mostrando novamente os veiculos da frota para verificar a remoçao do veiculo;
        System.out.println("--- Atualização da lista de veiculos da frota ---");
        c3PJ.getVeiculosporFrota(f1);
        System.out.println("-----------------------------------------------\n");

        System.out.println("Recita inicial da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        // Espera-se receita nula pois nao ha seguros ativos;

        /*    Gerando o seguro para os clientes PF e PJ    */
        //Cliente 1 (PF) tem dois seguros, um para cada veiculo;
        solSeguros.gerarSeguro(c1PF, v1, ImplementarMenu.converteDataStrToLD("15/05/2024"));
        System.out.println("Receita com 1 seguro ativo da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        // Espera-se 166,67;

        solSeguros.gerarSeguro(c1PF, v2, ImplementarMenu.converteDataStrToLD("25/04/2024"));
        System.out.println("Receita com 2 seguros ativos da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        // Espera-se 166,67+156,25 = 322,92;

        // Cliente 2 (PF) tem um seguro para seu unico veiculo;
        solSeguros.gerarSeguro(c2PF, v3, ImplementarMenu.converteDataStrToLD("01/07/2024"));
        System.out.println("Receita com 3 seguros ativos da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        // Espera-se 133,33+322,92 = 456,25;

        // Cliente 3 (PJ) tem seguro apenas para uma de suas frotas;
        solSeguros.gerarSeguro(c3PJ, f1, ImplementarMenu.converteDataStrToLD("28/09/2024"));
        System.out.println("Receita com 4 seguros ativos da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        // Espera-se 1422,22+456,25 = 1878,47;

        // Cliente 4 (PJ) nao possui seguro

        
        // Listando seguros do cliente 1: esperam-se 2 seguros
        List<Seguro> listaSegurosC1 = solSeguros.getSegurosPorCliente(c1PF);
        System.out.println("-----------------------------------------------\n"+
                        "  Lista de seguros do cliente "+c1PF.getIdentificador()+"\n\n"+listaSegurosC1);
        System.out.println("\n-----------------------------------------------\n");
        
        // Guardando os seguros de cada cliente em variaveis
        Seguro seg1C1 = null, seg2C1 = null, seg1C2 = null, seg1C3 = null;
        for(Seguro seg:solSeguros.getListaSeguros()){
            if(seg.getId()== 0)
                seg1C1 = seg;
            else if(seg.getId()== 1)
                seg2C1 = seg;
            else if(seg.getId()== 2)
                seg1C2 = seg;
            else if(seg.getId()== 3)
                seg1C3 = seg;
        }

        // Adicionando condutores aos clientes(habilitando condutores)
        System.out.println("-------- Habilitando condutores aos seguros --------");
        seg1C1.habilitarCondutor(cond1);
        // Seguro 2 do cliente 1 nao tera condutores;
        seg1C2.habilitarCondutor(cond2);
        seg1C3.habilitarCondutor(cond3);

        // Calculando a recita da seguradora novamente;
        System.out.println("Receita da seguradora "+solSeguros.getCnpj()+ " após adicionar condutores aos seguros\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");

        // Segurados gerando sinistros aos respectivos seguros;
        System.out.println("-------- Gerando sinistros --------");
        // Cliente 1 gera sinistro ao seguro 2;
        seg2C1.gerarSinistros("22/02/2023", "Rua da formatura");
        
        // Cliente 3 gera sinistro proprio e condutor também gera sinistro ao seguro 1;
        seg1C3.gerarSinistros("29/08/2022", "Rua da Unicamp");
        seg1C3.gerarSinistros("04/11/2022", "Rua da sorte", cond3);
        
        System.out.println("Receita da seguradora "+solSeguros.getCnpj()+ " após 3 sinistros gerados\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        
        // Mostrando os sinistros do cliente
        System.out.println("----- Listando sinistros dos clientes -----\n");
        List<Sinistro> listaSinistrosC1 = solSeguros.getSinistrosPorCliente("437.355.398-07");
        List<Sinistro> listaSinistrosC3 = solSeguros.getSinistrosPorCliente("45.773.100/0001-99");
        System.out.println("Sinistros do cliente PF 1\n"+listaSinistrosC1+"\n\nSinistros do cliente PJ 3\n"+listaSinistrosC3+"\n");
        
        // Adicionando veiculo ao cliente PF 1 e gerando seguro para este veiculo
        System.out.println("--- Adicionando veiculo ao cliente 1(PF) e gerando novo seguro ---\n");
        c1PF.adicionarVeiculo(v6);
        solSeguros.gerarSeguro(c1PF, v6, ImplementarMenu.converteDataStrToLD("07/08/2024"));
        System.out.println("Receita com 5 seguros ativos da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        
        // Cacelando um seguro do cliente 1(PF)
        System.out.println("--- Cancelando seguro ID 0 do cliente 1(PF) ---\n");
        solSeguros.cancelarSeguro(0);
        System.out.println("Receita com 4 seguros ativos, após cancelamento de 1 seguro, da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
        /* Nao funciona, qtd de veiculos segurados errada, sempre 1, nao sei resolver pela complexidade de tratativas;
        mas a PED Rebeca comentou que qtdVeiculos seria o tamanho da listaVeiculos do cliente;
        nesse caso, a remoçao de um seguro nao impacta no recalculo dos seguros deste cliente;
        logo, a receita apenas retira o valor do seguro removido
        */
        
        // Chamando o menu iterativo
        ImplementarMenu a = new ImplementarMenu();
        a.setListaSeguradoras(solSeguros);
        a.implementaMenu();
        System.out.println("Você saiu do menu\n");
        
    }
}
