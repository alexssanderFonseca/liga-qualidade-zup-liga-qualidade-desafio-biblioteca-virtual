package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosDevolucao;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.DadosEmprestimo;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solucao {

	/**
	 * Você precisa implementar o código para executar o fluxo
	 * o completo de empréstimo e devoluções a partir dos dados
	 * que chegam como argumento. 
	 *
	 * Caso você queira pode adicionar coisas nas classes que já existem,
	 * mas não pode alterar nada.
	 */

	/**
	 *
	 * @param livros dados necessários dos livros
	 * @param exemplares tipos de exemplares para cada livro
	 * @param usuarios tipos de usuarios
	 * @param emprestimos informações de pedidos de empréstimos
	 * @param devolucoes informações de devoluções, caso exista. 
	 * @param dataExpiracao aqui é a data que deve ser utilizada para verificar expiração
	 * @return
	 */

	//DadosLivro
	public static Set<EmprestimoConcedido> executa(Set<DadosLivro> livros,
												   Set<DadosExemplar> exemplares,
												   Set<DadosUsuario> usuarios,
												   Set<DadosEmprestimo> emprestimos,
												   Set<DadosDevolucao> devolucoes,
												   LocalDate dataExpiracao) {

		Set<EmprestimoConcedido> emprestimosConcedidos = new HashSet<>();
		Map<Integer, Integer> countEmprestimosPadrao = new HashMap<>();

		for (DadosEmprestimo emprestimo : emprestimos) {

			int idUsuario = emprestimo.idUsuario;
			int idLivro = emprestimo.idLivro;
			LocalDate dataDevolucaoEstimada = LocalDate.now().plusDays(emprestimo.tempo);

			ControladoraDeEmprestimo controladoraEmprestimo = new ControladoraDeEmprestimo(idUsuario, idLivro, usuarios, exemplares,countEmprestimosPadrao);
			controladoraEmprestimo.empresta(emprestimosConcedidos, dataDevolucaoEstimada,dataExpiracao);

		}

		return emprestimosConcedidos;
	}



}
