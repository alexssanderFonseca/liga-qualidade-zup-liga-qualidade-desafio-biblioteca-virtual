package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.EmprestimoConcedido;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

public class ControladoraDeEmprestimo {

    private DadosUsuario dadosUsuario;
    private DadosExemplar dadosExemplar;
    private  Map<Integer, Integer> countEmprestimosPadrao;


    public ControladoraDeEmprestimo(int idUsuario, int idLivro, Set<DadosUsuario> usuarios, Set<DadosExemplar> exemplares, Map<Integer, Integer>  countEmprestimosPadrao) {
        this.dadosUsuario = DadosHelper.buscaUsuario(idUsuario, usuarios);
        this.dadosExemplar = DadosHelper.buscaExemplar(idLivro, exemplares);
        this.countEmprestimosPadrao = countEmprestimosPadrao;
    }

    public void registrarEmprestimo(Set<EmprestimoConcedido> emprestimosConcedidos, LocalDate dataDevolucaoEstimada) {

        EmprestimoConcedido emprestimoConcedido = new EmprestimoConcedido(this.dadosUsuario.idUsuario, this.dadosExemplar.idExemplar, dataDevolucaoEstimada);
        emprestimosConcedidos.add(emprestimoConcedido);
        if (this.dadosUsuario.padrao == TipoUsuario.PADRAO) {
            countEmprestimosPadrao.putIfAbsent(this.dadosUsuario.idUsuario, 0);
            countEmprestimosPadrao.put(this.dadosUsuario.idUsuario, countEmprestimosPadrao.get(this.dadosUsuario.idUsuario) + 1);
        }
    }


    public boolean livroEmprestavelEDevolvidoAntesDaDataConsiderada(LocalDate dataExpiracao, LocalDate dataDevolucao) {
        if (this.dadosUsuario.padrao == TipoUsuario.PADRAO) {
            UsuarioPadrao usuarioPadrao =  new UsuarioPadrao(dadosUsuario.idUsuario, countEmprestimosPadrao.get(dadosUsuario.idUsuario));
            return usuarioPadrao.podeRealizarEmprestimo(dataDevolucao,dataExpiracao,dadosExemplar);
        }
            UsuarioPesquisador usuarioPesquisador =  new UsuarioPesquisador(dadosUsuario.idUsuario);
            return usuarioPesquisador.podeRealizarEmprestimo(dataDevolucao,dataExpiracao);
    }

    public void empresta(Set<EmprestimoConcedido> emprestimosConcedidos, LocalDate dataDevolucaoEstimada, LocalDate dataExpiracao) {
        if (livroEmprestavelEDevolvidoAntesDaDataConsiderada(dataExpiracao, dataDevolucaoEstimada)) {
            registrarEmprestimo(emprestimosConcedidos, dataDevolucaoEstimada);
        }
    }
}
