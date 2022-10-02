import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

public class IntefaceGrafica {
	
//VARIAVEIS;

	private JFrame frame;
	private JLabel labelPalavra;
	private JLabel imagemForca;
	private JPanel panelForca;
	private JLabel labelDica;
	private JButton buttonNovoJogo;
	private JButton buttonSair;
	private JFormattedTextField entradaLetra;
	private JButton buttonAdvinhar;
	private JLabel labelTentativas;
	private JPanel panelPalavra;
	private JPanel panelDica;
	private JPanel panelTentativas;
	
	private JogoDaForca jogo;
	private JPanel panelEntrada;
	
	private String[] letrasAdvinhadas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntefaceGrafica window = new IntefaceGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws ParseException 
	 */
	public IntefaceGrafica() throws ParseException {
		initialize();
	}


	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panelPalavra = new JPanel();
		panelPalavra.setBorder(new TitledBorder(null, "Palavra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPalavra.setBounds(32, 176, 310, 57);
		frame.getContentPane().add(panelPalavra);
		panelPalavra.setLayout(null);
		
		labelPalavra = new JLabel();
		labelPalavra.setBounds(6, 16, 298, 30);
		panelPalavra.add(labelPalavra);
		
		panelForca = new JPanel();
		panelForca.setBorder(new TitledBorder(null, "A Forca", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelForca.setBounds(38, 7, 298, 160);
		frame.getContentPane().add(panelForca);
		panelForca.setLayout(null);
		
		imagemForca = new JLabel("");
		imagemForca.setBounds(6, 16, 286, 133);
		panelForca.add(imagemForca);
		
		ImageIcon icon = new ImageIcon(IntefaceGrafica.class.getResource("/imagens/6.png"));
		imagemForca.setIcon(icon);
		
		
		panelDica = new JPanel();
		panelDica.setBorder(new TitledBorder(null, "Dica", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDica.setBounds(32, 233, 310, 57);
		frame.getContentPane().add(panelDica);
		panelDica.setLayout(null);
		
		labelDica = new JLabel();
		labelDica.setBounds(6, 16, 298, 30);
		panelDica.add(labelDica);
		
		buttonNovoJogo = new JButton("Novo Jogo");
		buttonNovoJogo.setBounds(403, 11, 89, 23);
		frame.getContentPane().add(buttonNovoJogo);
		
		/*---------------------------------------------------------------*/
		buttonNovoJogo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					jogo = new JogoDaForca("palavras.csv");
					jogo.iniciar();
					
					entradaLetra.setEditable(true);
					buttonAdvinhar.setEnabled(true);
					buttonSair.setEnabled(true);
					buttonNovoJogo.setEnabled(false);
					
					labelTentativas.setText("");
					
					ImageIcon icone = new ImageIcon(IntefaceGrafica.class.getResource("/imagens/6.png"));
					imagemForca.setIcon(icone);
					
					labelDica.setText(jogo.getDica());
					
					String palavra = "";
					letrasAdvinhadas = new String[jogo.getTamanho()];
					Arrays.fill(letrasAdvinhadas, "_");
					
					for(int i=0; i<letrasAdvinhadas.length;i++) {
						palavra += letrasAdvinhadas[i] + " ";
					}
					
					labelPalavra.setText(palavra);
					
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
			}
		}});
		
		/*---------------------------------------------------------------*/
		
		buttonSair = new JButton("Sair");
		buttonSair.setBounds(514, 11, 89, 23);
		buttonSair.setEnabled(false);
		
		/*--------------------------------------------------------------*/
		buttonSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					entradaLetra.setEditable(false);
					buttonAdvinhar.setEnabled(false);
					buttonNovoJogo.setEnabled(true);

					labelDica.setText("");
					labelPalavra.setText("");
					labelTentativas.setText("");
					entradaLetra.setText("");
					
					ImageIcon icone = new ImageIcon(IntefaceGrafica.class.getResource("/imagens/6.png"));
					imagemForca.setIcon(icone);
					
				}catch(Exception e1) {
					System.out.println(e1.getMessage());
			}
		}});
		
		/*--------------------------------------------------------------*/
		frame.getContentPane().add(buttonSair);
		
		panelEntrada = new JPanel();
		panelEntrada.setBorder(new TitledBorder(null, "Letra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEntrada.setBounds(454, 60, 99, 73);
		frame.getContentPane().add(panelEntrada);
		panelEntrada.setLayout(null);
		
		MaskFormatter mask = new MaskFormatter("U");
		entradaLetra = new JFormattedTextField(mask);
		entradaLetra.setFont(new Font("Times New Roman", Font.BOLD, 14));
		entradaLetra.setHorizontalAlignment(SwingConstants.CENTER);
		entradaLetra.setBounds(6, 16, 87, 51);
		panelEntrada.add(entradaLetra);
		entradaLetra.setColumns(10);
		entradaLetra.setEditable(false);
		
		buttonAdvinhar = new JButton("Advinhar");
		buttonAdvinhar.setBounds(458, 151, 89, 23);
		buttonAdvinhar.setEnabled(false);
		frame.getContentPane().add(buttonAdvinhar);
		
		/*--------------------------------------------------------------*/
		buttonAdvinhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String letra;
					String saida ="";
					letra = entradaLetra.getText();
					ArrayList<Integer> posicoes = jogo.getPosicoes(letra);
					
					ArrayList<String> letrasJogadas = jogo.getLetrasJogadas();
					for (int i=0;i<letrasJogadas.size();i++) {
						saida += letrasJogadas.get(i)+" - ";
					}
					labelTentativas.setText(saida);
					
					if (posicoes.size()>0) {
						for(int i : posicoes)
							letrasAdvinhadas[i] = letra;
						
						String palavra = "";
						
						for(int i=0; i<letrasAdvinhadas.length;i++) {
							palavra += letrasAdvinhadas[i] + " ";
						}
						
						labelPalavra.setText(palavra);
					}else {
						String caminhoImg = "/imagens/"+(6-jogo.getPenalidade())+".png";
						ImageIcon icone = new ImageIcon(IntefaceGrafica.class.getResource(caminhoImg));
						imagemForca.setIcon(icone);
					}
					if(jogo.terminou()) {
						entradaLetra.setEditable(false);
						buttonAdvinhar.setEnabled(false);
						buttonNovoJogo.setEnabled(true);
						buttonSair.setEnabled(false);
						JOptionPane.showMessageDialog(null,"Fim de Jogo\n"+jogo.getResultado());
					}
					
					entradaLetra.setText("");
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}});
		
		/*--------------------------------------------------------------*/
		
		panelTentativas = new JPanel();
		panelTentativas.setBorder(new TitledBorder(null, "Tentativas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTentativas.setBounds(397, 187, 206, 102);
		frame.getContentPane().add(panelTentativas);
		panelTentativas.setLayout(null);
		
		labelTentativas = new JLabel();
		labelTentativas.setBounds(6, 16, 194, 80);
		panelTentativas.add(labelTentativas);
	}
}
