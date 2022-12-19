package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.plugin.AoFinalizarGeracao;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.plugin.GeradorEbook;

import java.util.List;

public class Cotuba {

    public void executa(ParametrosCotuba parametros) {
        FormatoEbook formato = parametros.getFormato();
        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderiza(parametros.getDiretorioDosMD());

        Ebook ebook = new Ebook(formato, parametros.getArquivoDeSaida(), capitulos);

        GeradorEbook gerador = GeradorEbook.cria(formato);
        gerador.gera(ebook);
        AoFinalizarGeracao.gerou(ebook);
    }
}
