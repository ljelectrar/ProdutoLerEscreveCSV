package application;

import entities.Produto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o caminho do arquivo: ");
        String dirArq = sc.nextLine();

        File sourceFile = new File(dirArq);
        String sourceFolderStr = sourceFile.getParent();

        boolean success = new File(sourceFolderStr + "\\out").mkdir();

        String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

        List<Produto> produtoList = new ArrayList<>();

        boolean sucesso = new File(sourceFolderStr + "\\out").mkdir();

        //lEITURA DO ARQUIVO
        try (BufferedReader br = new BufferedReader(new FileReader(dirArq))) {

            String linha = br.readLine();

            while (linha != null) {
                //System.out.println(linha);

                String[] recorte = linha.split(", "); //recorta os valores

                String nome = recorte[0];
                double preco = Double.parseDouble(recorte[1]);
                int quantidade = Integer.parseInt(recorte[2]);

                produtoList.add(new Produto(nome, preco, quantidade));

                linha = br.readLine();
            }

            // ESCREVENDO UM NOVO ARQUIVO
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))){
                for(Produto item : produtoList){
                    bw.write(item.getNome() + ", " + String.format("%.2f", item.retornaValorEstoque()));
                    bw.newLine();
                }
                System.out.println(targetFileStr + " SUCESSO");

            } catch (IOException e){
                System.out.println("Erro ao escrever o arquivo: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        sc.close();

    }
}