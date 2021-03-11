package exercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;

/**
 * Classe responsável por controlar uma ArrayList de números inteiros;
 * 
 * @author João Victor Paltanin
 * @since 25/02/2021
 */
public class CalculaArray {

	// declarando a lista Integer para armazenar os números
	private ArrayList<Integer> numeros;

	// método construtor da classe
	public CalculaArray() {
		numeros = new ArrayList<Integer>();
		populaArray();
		menuPrincipal();
	}

	// método para preencher a lista com números inteiros informados pelo usuário
	public void populaArray() {
		// variável boolean para fazer looping se for um valor inválido ou vazio
		boolean execute;

		numeros = new ArrayList<Integer>();
		int num = 0;

		for (int i = 0; i < 10; i++) {
			execute = true;
			while (execute) {
				try {
					num = Integer.parseInt(JOptionPane.showInputDialog(Mensagem.informeElemento + " " + (i + 1)));
					numeros.add(num);
					if (Valida.isIntMenorIgualZero(num)) {
						JOptionPane.showMessageDialog(null, Mensagem.elementoInvalido, Rotulo.erro, 0);
						numeros.remove(i);
						execute = true;
					} else {
						execute = false;
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, Mensagem.elementoVazio, Rotulo.erro, 0);
				}
			}
		}
	}

	// método para criar um menu de opções
	public void menuPrincipal() {

		String menu = "INFORME A OPÇÃO DESEJADA\n\n" + "1. Maior elemento;\n" + "2. Menor elemento;\n"
				+ "3. Ordenar Lista Crescente;\n" + "4. Ordenar Lista Decrescente;\n" + "5. Média dos elementos;\n"
				+ "6. Soma do maior e menor elemento;\n" + "7. Soma dos elementos iguais;\n"
				+ "8. Média dos elementos iguais;\n" + "9. Sair do sistema\n";

		while (true) {
			try {
				int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
				processamentoPrincipal(opcao);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.numerosMenu, 0);
			}
		}
	}

	// método para controlar o processamento do programa
	public void processamentoPrincipal(int opcao) {
		switch (opcao) {
		case 1: {
			maiorElemento();
			break;
		}
		case 2: {
			menorElemento();
			break;
		}
		case 3: {
			listaCrescente();
			break;
		}
		case 4: {
			listaDecrescente();
			break;
		}
		case 5: {
			mediaElementos();
			break;
		}
		case 6: {
			somaMaiorMenor();
			break;
		}
		case 7: {
			somaElementosIguais();
			break;
		}
		case 8: {
			mediaElementosIguais();
			break;
		}
		case 9: {
			sair();
			break;
		}
		default:
			JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.numerosMenu, 0);
			break;
		}
	} // fim do método

	// método para exibir o maior elemento
	public void maiorElemento() {
		String mensagem = "Maior Elemento\n";

		mensagem += maiorNumero();
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
	}

	// método para calcular o maior elemento
	public int maiorNumero() {
		int maior = 0;

		for (Integer integer : numeros) {
			if (integer > maior) {
				maior = integer;
			}
		}
		return maior;
	}

	// método para exibir o menor elemento
	public void menorElemento() {
		String mensagem = "Menor Elemento\n";

		mensagem += menorNumero();
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
	}

	// método para calcular o menor elemento
	public int menorNumero() {
		int menor = Integer.MAX_VALUE;

		for (Integer integer : numeros) {
			if (integer < menor) {
				menor = integer;
			}
		}
		return menor;
	}

	// método para exibir uma lista dos números em ordem crescente
	public void listaCrescente() {
		String mensagem = "Ordem Crescente dos Elementos\n";

		// vetor integer recebendo a conversão da lista de objetos para um vetor/array
		Integer[] vetor = (Integer[]) numeros.toArray(new Integer[0]);

		// utilidade do java para ordenar em ordem crescente elementos de um array
		Arrays.sort(vetor);

		// inicializando a variável, condicionando e incrementando
		for (int i = 0; i < numeros.size(); i++) {
			mensagem += vetor[i] + "\n";
		}

		JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
	}

	// método para exibir uma lista dos números em ordem decrescente
	public void listaDecrescente() {
		String mensagem = "Ordem Decrescente dos Elementos\n";

		// vetor integer recebendo a conversão da lista de objetos para um vetor/array
		Integer[] vetor = (Integer[]) numeros.toArray(new Integer[0]);

		// utilidade do java para ordenar em ordem decrescente elementos de um array
		Arrays.sort(vetor, Collections.reverseOrder());

		// inicializando a variável, condicionando e incrementando
		for (int i = 0; i < numeros.size(); i++) {
			mensagem += vetor[i] + "\n";
		}
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
	}

	// método para calcular e exibir a média dos 10 elementos
	public void mediaElementos() {
		String mensagem = "Média dos Elementos\n";

		double media, soma = 0;
		for (int i = 0; i < numeros.size(); i++) {
			soma += numeros.get(i);
		}
		media = soma / 10;
		mensagem += media;
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
	}

	// método para calcular e exibir a soma do maior e do menor entre os elementos
	public void somaMaiorMenor() {
		String mensagem = "Soma do Maior com o Menor\n";
		int soma = 0;

		soma = maiorNumero() + menorNumero();

		mensagem += soma;
		JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
	}

	// método para calcular e exibir a soma dos elementos iguais
	public void somaElementosIguais() {
		String mensagem = "Soma dos Elementos iguais\n";
		boolean existe = false;

		for (int i = 0; i < numeros.size(); i++) {
			int quantidade = Collections.frequency(numeros, numeros.get(i));
			if (quantidade > 1) {
				existe = true;
				mensagem += "Soma dos elementos " + numeros.get(i) + " presentes na lista: "
						+ (numeros.get(i) * quantidade) + "\n";
				i += (quantidade - 1);
			}
		}
		if (existe) {
			// resultado em tela
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.resultado, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.naoElementosIguais, Rotulo.advertencia, 2);
		}

	}

	// método para calcular e exibir a média dos elementos iguais
	public void mediaElementosIguais() {
		String mensagem = "Média dos Elementos iguais\n";
		boolean existe = false;
		double soma = 0;
		int contador = 0;
		double result = 0;
		for (Integer num : numeros) {
			if (Collections.frequency(numeros, num) >= 2) {
				contador++;
				soma += num;
				result = soma / contador;
				existe = true;
			}
		}
		if (existe) {
			// resultado em tela
			JOptionPane.showMessageDialog(null, mensagem + result, Rotulo.resultado, 1);

		} else {
			JOptionPane.showMessageDialog(null, Mensagem.naoElementosIguais, Rotulo.advertencia, 2);
		}
	} // fim do método

	// método para sair do sistema
	public void sair() {
		int opcao = JOptionPane.showConfirmDialog(null, "Encerrar o sistema?", "Atenção", JOptionPane.YES_OPTION,
				JOptionPane.CANCEL_OPTION);

		if (opcao == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	} // fim do método

	public static void main(String[] args) {
		new CalculaArray();
	}
}