import java.util.ArrayList;
import java.lang.String;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class JogoDaForca {
    private ArrayList<String> palavras = new ArrayList<String>(); // lista de palavras lidas do arquivo
    private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
    private String dica= ""; // dica da palavra sorteada
    private String[] letras; // letras da palavra sorteada
    private int acertos; // contador de acertos
    private int penalidade;
    private final ArrayList<String> letrasJogadas = new ArrayList<>();

    public JogoDaForca(String nomearquivo) throws Exception {
        try {
          File file = new File(nomearquivo);
          Scanner arquivo = new Scanner(file);
          String linha;
          String[] partes;
          String cabecalho = arquivo.nextLine();
          while(arquivo.hasNextLine()) {
            linha = arquivo.nextLine();
            partes = linha.split(";");
            palavras.add(partes[0]);
            dicas.add(partes[1]);
          }
        }
        catch (FileNotFoundException e) {
          throw new Exception("Arquivo Não Encontrado");
        }
      }

    public void iniciar () {
        Random random = new Random();
        int temp = random.nextInt(palavras.size());

        letras = palavras.get(temp).split("");
        dica = dicas.get(temp);
    }
    
    public int getTamanho() {
      return letras.length;
    }

    public int getAcertos() {
      return acertos;
    }
    public int getPenalidade() {
      return penalidade;
    }
    

    public boolean terminou() {
      if (getAcertos() == getTamanho() || getPenalidade() == 6)
        return true;
      return false;
    }

    public String getResultado() {
      if (terminou()) {
        if (getPenalidade() == 6)
          return "Você foi enforcado";          
        else if (getAcertos() == getTamanho())
          return "Você venceu";
      }
      
      return "Jogo em Andamento";
    }

    public String getDica() {
      return dica;
    }

    public ArrayList<Integer> getPosicoes(String letra) throws Exception {
        ArrayList<Integer> temp = new ArrayList<>();
        if (letrasJogadas.contains(letra)) {
        	penalidade += 1;
            throw new Exception("Letra já jogada");
        }
        else if (letra.length() < 2 && letra.length() > 0  && Character.isLetter(letra.charAt(0))) {
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