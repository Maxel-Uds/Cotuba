package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.domain.FormatoEbook;
import cotuba.md.RenderizadorMDParaHTML;
import cotuba.plugin.AoFinalizarGeracao;

import java.util.List;

public class Cotuba {

    public void executa(ParametrosCotuba parametros) {
        FormatoEbook formato = parametros.getFormato();
        RenderizadorMDParaHTML renderizador = new RenderizadorMDParaHTML();
        List<Capitulo> capitulos = renderizador.renderiza(parametros.getDiretorioDosMD());

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(parametros.getArquivoDeSaida());
        ebook.setCapitulos(capitulos);

        GeradorEbook gerador = GeradorEbook.cria(formato);
        gerador.gera(ebook);
        AoFinalizarGeracao.gerou(ebook);
    }
}
