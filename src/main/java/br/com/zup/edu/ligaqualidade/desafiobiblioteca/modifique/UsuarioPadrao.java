package br.com.zup.edu.ligaqualidade.desafiobiblioteca.modifique;

import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.DadosUsuario;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoExemplar;
import br.com.zup.edu.ligaqualidade.desafiobiblioteca.pronto.TipoUsuario;
import java.time.LocalDate;

public class UsuarioPadrao {

    private DadosUsuario usuario;
    private Integer quantidadeDeEmprestismosAtivos;

    public UsuarioPadrao(int  id, Integer quantidadeDeEmprestismosAtivos) {
        this.usuario = new DadosUsuario(TipoUsuario.PADRAO,id);
        this.quantidadeDeEmprestismosAtivos = quantidadeDeEmprestismosAtivos == null ? 0 : quantidadeDeEmprestismosAtivos;
    }


    public boolean podeRealizarEmprestimo(LocalDate dataDevolucao,LocalDate dataExpiracao, DadosExemplar exemplar){
        return quantidadeDeEmprestismosAtivos < 5 && dataDevolucao.isBefore(dataExpiracao) && DataUtils.validarLimiteDeDiasPorEmprestimo(dataDevolucao)  && exemplar.tipo == TipoExemplar.LIVRE;
    }






}
