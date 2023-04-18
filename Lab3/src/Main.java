import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("Insira seu nome:\n");
        //String nome;
        Scanner nomeInserido = new Scanner(System.in);
        String nome = nomeInserido.next();
        System.out.println("Nome inserido: " + nome +"\n");

        String endereco;
        System.out.println("Insira seu endereço:\n");
        Scanner enderecoInserido = new Scanner(System.in);
        endereco = enderecoInserido.next();
        System.out.println("Endereço inserido: " + enderecoInserido +"\n");

        Cliente c1 = new Cliente(nome, endereco);
    }
}
