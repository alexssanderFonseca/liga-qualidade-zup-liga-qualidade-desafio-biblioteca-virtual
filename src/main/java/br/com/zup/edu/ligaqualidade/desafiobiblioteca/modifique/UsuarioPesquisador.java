package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;
import java.time.LocalDate;

public class UsuarioPesquisador {

    private DadosUsuario usuario;

    public UsuarioPesquisador(int  id) {
        this.usuario = new DadosUsuario(TipoUsuario.PESQUISADOR,id);
    }


    public boolean podeRealizarEmprestimo(LocalDate dataDevolucao, LocalDate dataExpiracao){
        return dataDevolucao == null
                || dataDevolucao.isBefore(dataExpiracao)
                && DataUtils.validarLimiteDeDiasPorEmprestimo(dataDevolucao);
    }


}
