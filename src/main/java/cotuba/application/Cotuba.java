package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;

import java.util.List;

public class Cotuba {

    public void executa(ParametrosCotuba parametros) {
        String formato = parametros.getFormato();
        RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(parametros.getDiretorioDosMD());

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(parametros.getArquivoDeSaida());
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            GeradorPDF geradorPDF = GeradorPDF.cria();
            geradorPDF.gera(ebook);
        } else if ("epub".equals(formato)) {
            GeradorEPUB geradorEPUB = GeradorEPUB.cria();
            geradorEPUB.gera(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }
}
