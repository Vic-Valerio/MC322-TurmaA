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
                        "Ensino superior completo", ImplementarMenu.converteDataStrToLD("10/05/1997"));

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
                        
        Condutor cond2 = new Condutor("123487478-47", "Claudia", "Rua da vitória", "(19)99207-0306",
                            "claudia@gmail.com", ImplementarMenu.converteDataStrToLD("19/10/1970"));

        Condutor cond3 = new Condutor("212.985.130-49", "Jean Carlo", "Rua das amoras", null,
        "jean.carlo@gmail.com", ImplementarMenu.converteDataStrToLD("12/03/1994"));


        /*    Atribuições e relacionamentos entre os objetos    */

        // Cadastrando clientes na seguradora
        solSeguros.cadastrarCliente(c1);
        solSeguros.cadastrarCliente(c2);
        solSeguros.cadastrarCliente(c3);
        solSeguros.cadastrarCliente(c4);

        // Listando os clientes para verificar se foram cadastrados com sucesso;
        solSeguros.listarClientes("PF");
        solSeguros.listarClientes("PJ");

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
        c3PJ.getVeiculosporFrota(f1);
        c4PJ.getVeiculosporFrota(f2);

        // Passando uma frota não cadastrada ao cliente: mensagem de nao encontrada;
        c3PJ.getVeiculosporFrota(f2);

        // Atualizando a frota 1 removendo o veiculo 6, restando apenas o veiculo 5;
        c3PJ.atualizarFrota(f1, v6, 0);

        // Mostrando novamente os veiculos da frota para verificar a remoçao do veiculo;
        c3PJ.getVeiculosporFrota(f1);

        System.out.println("Recita inicial da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");
         
        /*    Gerando o seguro para os clientes PF e PJ    */

        //Cliente 1 (PF) tem dois seguros, um para cada veiculo;
        solSeguros.gerarSeguro(c1PF, v1, ImplementarMenu.converteDataStrToLD("15/05/2024"));
        solSeguros.gerarSeguro(c1PF, v2, ImplementarMenu.converteDataStrToLD("25/04/2024"));

        System.out.println("Recita com 2 seguros ativos da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");

        // Cliente 2 (PF) tem um seguro para seu unico veiculo
        solSeguros.gerarSeguro(c2PF, v3, ImplementarMenu.converteDataStrToLD("01/07/2024"));

        // Cliente 3 (PJ) tem seguro apenas para uma de suas frotas
        solSeguros.gerarSeguro(c3PJ, f1, ImplementarMenu.converteDataStrToLD("28/09/2024"));
        // Cliente 4 (PJ) nao possui seguro


        // Listando seguros de um cliente
        List<Seguro> listaSegurosC1 = solSeguros.getSegurosPorCliente(c1PF);
        System.out.println("Lista de seguros do cliente "+c1PF.getIdentificador()+"\n"+listaSegurosC1);
        
        // Guardando os seguros de cada cliente em variaveis globais
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

        System.out.println("Recita com 4 seguros da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");

        // Adicionando condutores aos clientes
        seg1C1.getListaCondutores().add(cond1);
        // Seguro 2 do cliente 1 nao tera condutores;
        seg1C2.getListaCondutores().add(cond2);
        seg1C3.getListaCondutores().add(cond3);

        // Calculando a recita final da seguradora;
        System.out.println("Recita final da seguradora "+solSeguros.getCnpj()+ "\nR$" + String.format("%.2f", solSeguros.calcularReceita())+"\n");

        // gerar sinistros
        // recalcular a receita da seguradora
        // listar sinistros de um cliente 

        // Chamando o menu iterativo

    }
}
