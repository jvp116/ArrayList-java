package exercicios;

/**
 * Classe responsável por validar os dados inputados pelo usuário
 * 
 * @author João Victor
 * @since 25/02/2021
 */
public class Valida {

	// verificando se o valor informado é 0
	public static boolean isIntMenorIgualZero(int args) {
		return (args <= 0);
	}

	// verificando se o valor digitado é nulo ou vazio
	public static boolean isEmptyOrNull(String string) {
		return (string == null || string.trim().equals(""));
	}

	// método para verificar String válida
	public static boolean isStringValida(String args) {
		if (!args.matches("[A-Z a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*")) {
			return true;
		} else {
			return false;
		}
		// Passa para o método o matches o regex
		// se for diferente de letras dá erro
	} // fim método
}