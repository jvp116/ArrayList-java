package exercicios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

/**
 * Classe responsável por controlar uma ArrayList de String;
 * 
 * @author João Victor Paltanin
 * @since 25/02/2021
 */
public class VerificaArrayList {

	// declarando a lista String para armazenar os números
	private ArrayList<String> nomes;

	// método construtor da classe
	public VerificaArrayList() {

		// array list de nomes sendo instanciada
		nomes = new ArrayList<String>();

		// chamando o método menu principal que exibe o meno e pede uma Opção a ser
		// informada
		menuPrincipal();
	}

	// método para criar um menu de opções
	public void menuPrincipal() {

		// variável para exibir o menu
		String menu = "INFORME A OPÇÃO DESEJADA\n\n" + "1. Cadastrar nome\n" + "2. Excluir nome\n"
				+ "3. Listar nomes cadastrado\n" + "4. Listar nomes em ordem alfabética\n" + "5. Listar nomes iguais\n"
				+ "6. Listar nomes por letra inicial\n" + "7. Pesquisar nome\n" + "8. Sair do sistema\n\n";

		// looping que verifica se foi digitado uma opção de 1 a 8
		while (true) {
			try {
				// variável int para receber uma opção de 1 a 8
				int opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));
				processamentoPrincipal(opcao);
			} catch (Exception e) { // erro caso seja um número diferente de 1 a 8
				JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.numerosMenu, 0);
			}
		}
	} // fim do método

	// método para controlar o processamento do programa
	public void processamentoPrincipal(int opcao) {
		// condicional com os casos para executar o método em cada caso
		switch (opcao) {
		case 1: {
			cadastrarNome();
			break;
		}
		case 2: {
			excluirNome();
			break;
		}
		case 3: {
			listarNomes();
			break;
		}
		case 4: {
			listarNomesOrdemAlfabetica();
			break;
		}
		case 5: {
			listarNomesIguais();
			break;
		}
		case 6: {
			listarNomesLetraInicial();
			break;
		}
		case 7: {
			pesquisarNome();
			break;
		}
		case 8: {
			sair();
			break;
		}

		default:
			JOptionPane.showMessageDialog(null, Mensagem.opcaoInvalida, Rotulo.numerosMenu, 0);
			break;
		}
	} // fim do método

	// método para cadastrar nome
	public void cadastrarNome() {
		// variável boolean para fazer looping se for um nome inválido ou vazio
		boolean execute;

		// declarando variável String para receber o nome informado pelo usuário
		String nome = "";

		// condição para cadastrar no máximo 10 nomes
		if (nomes.size() < 10) {
			execute = true;
			while (execute) {
				try {
					// pedindo para o usuário informar um nome
					nome = JOptionPane.showInputDialog(Mensagem.informeNome + " ");

					// condições que verificam se é válido o que o usuário digitou
					if (Valida.isEmptyOrNull(nome)) { // condição nulo ou vazio
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.cadastroNome, 0);
						execute = true;
					} else if (Valida.isStringValida(nome)) { // condição se não for letra
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.nomeInvalido, Rotulo.cadastroNome, 0);
						execute = true;
					} else if (nome.length() == 1) {// verifica se digitou uma letra somente
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.nomePequeno, Rotulo.cadastroNome, 2);
						execute = true;
					} else {
						execute = false;
						// adicionando nome na lista de String
						nomes.add(nome);

						// mensagem de sucesso ao cadastrar nome
						JOptionPane.showMessageDialog(null, Mensagem.cadastroNomeSucesso, Rotulo.cadastroNome, 1);

						if (nomes.size() < 10) {
							// perguntando se o usuário deseja cadastrar outro nome
							int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar outro nome?",
									Rotulo.cadastroNome, JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);

							if (opcao == JOptionPane.YES_OPTION) {// se sim executa novamente o cadastro
								execute = true;
							}
						}
					}
				} catch (Exception e) {
					// informando erro caso nome inválido
					JOptionPane.showMessageDialog(null, Mensagem.nomeInvalido, Rotulo.erro, 0);
				}
			}

		} else {
			// advertência caso número de cadastros excedidos
			JOptionPane.showMessageDialog(null, Mensagem.nomeExcedido, Rotulo.advertencia, 2);
		}

	} // fim do método

	// método para excluir nome
	public void excluirNome() {
		// variável boolean para fazer looping se for um nome inválido ou vazio
		boolean execute;

		// string para receber o nome que o usuário deseja excluir
		int excluirNome = 0;
		String mensagem = "";

		// verifica se existem nomes cadastrados
		if (nomes.size() > 0) {

			for (int i = 0; i < nomes.size(); i++) {
				// concatenando na variável mensagem para exibir na tela
				mensagem += (i + 1) + " - " + nomes.get(i) + "\n";

			}

			execute = true;
			while (execute) {
				try {
					// pedindo para o usuário informar o nome que deseja excluir
					excluirNome = Integer
							.parseInt(JOptionPane.showInputDialog(mensagem + "\n" + Mensagem.informeNomeExcluir));

					// condições que verificam se é válido o que o usuário digitou
					if (Valida.isIntMenorIgualZero(excluirNome)) { // condição nulo ou vazio
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.nomeVazio, Rotulo.excluirNome, 0);
						execute = true;
					} else {
						execute = false;
						// variável auxiliar para verificar se existe o nome cadastrado
						boolean existe = false;

						// varrendo o vetor e verificando os nomes cadastrados
						for (int i = 0; i < nomes.size(); i++) {
							if (nomes.get(i) != null && i == excluirNome - 1) {
								nomes.remove(excluirNome - 1); // remove o nome que o usuário digitou
								existe = true;
							}
						} // fim do for

						// exibindo o resultado da pesquisa para o usuario
						if (existe) {
							JOptionPane.showMessageDialog(null, Mensagem.nomeExcluido, Rotulo.excluirNome, 1);
						} else {
							JOptionPane.showMessageDialog(null, Mensagem.naoTemCodigo, Rotulo.excluirNome, 2);
							execute = true;
						}
					}
				} catch (Exception e) {
					// informando erro caso nome inválido
					JOptionPane.showMessageDialog(null, Mensagem.codigoInvalidoVazio, Rotulo.excluirNome, 0);
				}
			}
		} else { // se não existir nomes cadastrados adverte o usuário
			JOptionPane.showMessageDialog(null, Mensagem.naoPossuiNomes, Rotulo.excluirNome, 2);
		}

	} // fim do método

	// método para listar nomes
	public void listarNomes() {
		// variável auxiliar para verificar se existe nome cadastrado
		boolean existe = false;

		// variavel auxiliar para exibir os nomes para o usuário
		String mensagem = "";

		// varrendo os nomes para verificar se existe nomes cadastrados
		for (int i = 0; i < nomes.size(); i++) {
			if (nomes.get(i) != null) {
				// concatenando na variável mensagem para exibir na tela
				mensagem += (i + 1) + " - " + nomes.get(i) + "\n";
				existe = true;
			}
		} // fim do for

		// exibindo resultado da pesquisa
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.listaNome, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.naoPossuiNomes, Rotulo.listaNome, 2);
		}
	} // fim do método

	// método para listar nomes em ordem alfabética
	public void listarNomesOrdemAlfabetica() {
		// variável auxiliar para verificar se existe nome cadastrado
		boolean existe = false;

		// variável auxiliar para exibir os nomes para o usuário
		String mensagem = "";

		// nova ArrayList que receberá os nomes
		ArrayList<String> nomeOrdemAlfabetica = new ArrayList<String>();

		// populando a nova ArrayList
		for (String string : nomes) {
			nomeOrdemAlfabetica.add(string);
		}

		// ordenando nomes em ordem alfabética
		Collections.sort(nomeOrdemAlfabetica);

		// for para varrer e concatenar na mensagem os nomes em ordem alfabética
		for (int i = 0; i < nomeOrdemAlfabetica.size(); i++) {
			mensagem += (i + 1) + " - " + nomeOrdemAlfabetica.get(i) + "\n";
			existe = true;
		}

		// resultado em tela
		if (existe) {
			JOptionPane.showMessageDialog(null, mensagem, Rotulo.listaNome, 1);
		} else {
			JOptionPane.showMessageDialog(null, Mensagem.naoPossuiNomes, Rotulo.listaNome, 2);
		}

	} // fim do método

	// método para listar nomes iguais
	public void listarNomesIguais() { // se sim executa listagem de vezes que o nome é igual
		// variável mensagem que receberá a mensagem a ser exibida
		String mensagem = "Lista Nomes Iguais:\n\n";

		// variável auxiliar para verificar se existe o nome cadastrado
		boolean existe = false;

		// verifica se existem nomes cadastrados
		if (nomes.size() > 0) {

			// variável para mapear uma String com um Integer, no qual o primeiro armazena o
			// nome (key) e o segundo de vezes que ele aparece
			Map<String, Integer> quantidade = new HashMap<>();

			// declarando lista que receberá os nomes iguais
			ArrayList<String> igual = new ArrayList<String>();

			// foreach para popular a lista com nomes todos em minúsculos
			for (String string : nomes) {
				igual.add(string.toLowerCase());
			}

			for (String nome : igual) {
				// utilidade frequency que conta quantidade de vezes que o nome se repeta dentro
				// da lista
				// essa quantidade é dada um put e passa para o meu MAP
				quantidade.put(nome, Collections.frequency(igual, nome));
			}

			// variável para setar a quantidade de vezes que o nome apareceu
			Set<String> nome = quantidade.keySet();

			// foreach para armazenar o nome que se repetiu e a quantidade que apareceu
			for (String aparece : nome) {
				// verifica se apareceu duas ou mais vezes para mostrar pro usuário
				if (quantidade.get(aparece) >= 2) {
					existe = true;
					// concatenando os nomes na mensagem para exibir para o usuário
					mensagem += "O nome " + aparece + " foi encontrado " + quantidade.get(aparece) + " vezes\n";
				}
			}

			// Saída dos resultados
			if (existe) {
				JOptionPane.showMessageDialog(null, mensagem, Rotulo.listaNomeIgual, 1);
			} else {
				JOptionPane.showMessageDialog(null, Mensagem.naoNomesIguais, Rotulo.listaNomeIgual, 2);
			}
		} else { // se não existir nomes cadastrados adverte o usuário
			JOptionPane.showMessageDialog(null, Mensagem.naoPossuiNomes, Rotulo.listaNomeIgual, 2);
		}
	} // fim do método

	// método para listar nomes por letra inicial
	public void listarNomesLetraInicial() {
		// variável boolean para fazer looping se for um nome inválido ou vazio
		boolean execute;

		// variável mensagem que receberá a mensagem a ser exibida
		String mensagem = "";

		// verifica se existem nomes cadastrados
		if (nomes.size() > 0) { // se sim executa listagem por letra inicial pesquisada
			execute = true;
			while (execute) {
				try {

					// variável auxiliar para verificar se existe o nome cadastrado
					boolean existe = false;

					// declarando variável para receber a letra pesquisada
					String pesquisaLetra = "";

					// recebendo do usuário qual letra a ser pesquisada
					pesquisaLetra = JOptionPane.showInputDialog(Mensagem.pesquisaLetra);

					// condições que verificam se é válido o que o usuário digitou
					if (Valida.isEmptyOrNull(pesquisaLetra)) { // condição nulo ou vazio
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.letraVazio, Rotulo.listaNomeLetraInicial, 0);
						execute = true;
					} else if (Valida.isStringValida(pesquisaLetra)) { // condição se não for letra
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.letraInvalida, Rotulo.listaNomeLetraInicial, 0);
						execute = true;
					} else {
						// varrendo o vetor e verificando os nomes cadastrados
						for (int i = 0; i < nomes.size(); i++) {

							// verificando se a letra não é nula e se existe algum nome com essa letra
							if (nomes.get(i) != null && nomes.get(i).substring(0, 1).equals(pesquisaLetra)) {// Pegando
																												// um
																												// pedaço
																												// da
																												// String
								// concatenando os nomes na mensagem para exibir para o usuário
								mensagem += (i + 1) + " - " + nomes.get(i) + "\n";
								existe = true;
							}
						} // fim do for

						execute = false;
						// exibindo o resultado da pesquisa para o usuário
						if (existe) {
							JOptionPane.showMessageDialog(null, mensagem, Rotulo.listaNomeLetraInicial, 1);
						} else {
							JOptionPane.showMessageDialog(null, Mensagem.naoTemLetra, Rotulo.listaNomeLetraInicial, 2);
							execute = true;
						}

					}

				} catch (Exception e) {
					// informando erro caso nome inválido
					JOptionPane.showMessageDialog(null, Mensagem.nomeInvalido, Rotulo.listaNomeLetraInicial, 0);
				}
			}
		} else { // se não existir nomes cadastrados adverte o usuário
			JOptionPane.showMessageDialog(null, Mensagem.naoPossuiNomes, Rotulo.listaNomeLetraInicial, 2);
		}
	} // fim do método

	// método para pesquisar nome
	public void pesquisarNome() {
		// variável boolean para fazer looping se for um nome inválido ou vazio
		boolean execute;

		// verifica se existem nomes cadastrados
		if (nomes.size() > 0) { // se sim executa a pesquisa por nome
			execute = true;
			while (execute) {
				try {
					// recebendo do usuário qual nome a ser pesquisado
					String pesquisaNome = JOptionPane.showInputDialog("Informe o Nome para pesquisa");
					// condições que verificam se é válido o que o usuário digitou
					if (Valida.isEmptyOrNull(pesquisaNome)) { // condição nulo ou vazio
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.letraVazio, Rotulo.pesquisaNome, 0);
						execute = true;
					} else if (Valida.isStringValida(pesquisaNome)) { // condição se não for letra
						// informa mensagem de erro
						JOptionPane.showMessageDialog(null, Mensagem.letraInvalida, Rotulo.pesquisaNome, 0);
						execute = true;
					} else {
						// variavel auxiliar para verificar se existe a nomes cadastrados
						boolean existe = false;

						// varrendo o vetor e verificando os nomes cadastrados
						for (int i = 0; i < nomes.size(); i++) {
							// verificando se existe o nome que o usuário informou cadastrado
							if (nomes.get(i) != null && nomes.get(i).equals(pesquisaNome)) {
								existe = true;
							}
						} // fim do for
						execute = false;

						// exibindo o resultado da pesquisa para o usuario
						if (existe) {
							JOptionPane.showMessageDialog(null, Mensagem.temNome, Rotulo.pesquisaNome, 1);
						} else {
							JOptionPane.showMessageDialog(null, Mensagem.naoTemNome, Rotulo.pesquisaNome, 2);
						}
					}
				} catch (Exception e) {
					// informando erro caso nome inválido
					JOptionPane.showMessageDialog(null, Mensagem.nomeInvalido, Rotulo.pesquisaNome, 0);
				}
			}
		} else { // se não existir nomes cadastrados adverte o usuário
			JOptionPane.showMessageDialog(null, Mensagem.naoPossuiNomes, Rotulo.pesquisaNome, 2);
		}

	} // fim do método

	// método para sair do sistema
	public void sair() {
		// variável que recebe um YES ou CANCEL
		int opcao = JOptionPane.showConfirmDialog(null, "Encerrar o sistema?", "Atenção", JOptionPane.YES_OPTION,
				JOptionPane.CANCEL_OPTION);

		// se opção for yes o sistema encerra
		if (opcao == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	} // fim do método

	/*
	 * Método principal para executar a classe
	 */
	public static void main(String[] args) {
		// instanciando o construtor da classe
		new VerificaArrayList();
	}
} // fim da classe