package Codigos;
//import java.io.File;
//import java.io.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoClientePF {
    String arquivoClientePF = "Dados/ClientePF.csv";
    public boolean gerarArquivo(){
        return true;
    }
    
    public String lerArquivo(){
        try{
            BufferedReader input = new BufferedReader(new FileReader(arquivoClientePF));

            BufferedWriter output = new BufferedWriter(new FileWriter("Leitura.txt"));
        }
        catch(IOException exc){
            return exc.getMessage();
        }
        
    }
}

// catch (IOException e);
// e.getMessage() -> printar exceção
// teclado.close;
// try (BufferedReader bf = ...) {

//}