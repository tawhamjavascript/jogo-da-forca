import java.util.ArrayList;
import java.lang.String;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Forca {
    private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
    private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
    private String dica= ""; // dica da palavra sorteada
    private String[] letras; // letras da palavra sorteada
    private int acertos; // contador de acertos
    private int penalidade;
    private final ArrayList<String> letrasJogadas = new ArrayList<>();

    public Forca(String nomearquivo) throws Exception {
        try {
          Scanner arquivo = new Scanner(new File(nomearquivo));
          String linha;
          String[] partes;
          while(arquivo.hasNextLine()) {
            linha = arquivo.nextLine();
            partes = linha.split(";");
            palavras.add(partes[0]);
            dicas.add(partes[1]);
          }
        }
        catch (FileNotFoundException e) {
          throw new Exception("Arquivo Inexistente");
        }
      }

    public void iniciar () {
        Random random = new Random();
        int temp = random.nextInt(palavras.size());
        letras = palavras.get(temp).split("");
        dica = dicas.get(temp);



    }

    public ArrayList<Integer> getPosicoes(String letra) throws Exception {
        ArrayList<Integer> temp = new ArrayList<>();
        if (letrasJogadas.contains(letra)) {
            throw new Exception("Letra já jogada");
        }
        else if (letra.length() < 2 && Character.isLetter(letra.charAt(0))) {
            for(int i = 0; i < letras.length; i++) {
                if (letras[i].equals(letra)) {
                    temp.add(i);
                    letras[i] = "*";
                    acertos += 1;

                }
            }
            letrasJogadas.add(letra);
            if (temp.size() == 0) penalidade += 1;
            return temp;
        }
        else {
            throw new Exception("Entrada inválida");
        }
    }
}
