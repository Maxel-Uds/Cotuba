package cotuba.application;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cotuba {

    private final GeradorPDF geradorPDF;
    private final GeradorEPUB geradorEPUB;
    private final RenderizadorMDParaHTML renderizador;

    public Cotuba(GeradorPDF geradorPDF, GeradorEPUB geradorEPUB, RenderizadorMDParaHTML renderizador) {
        this.geradorPDF = geradorPDF;
        this.geradorEPUB = geradorEPUB;
        this.renderizador = renderizador;
    }

    public void executa(ParametrosCotuba parametros) {
        String formato = parametros.getFormato();
        List<Capitulo> capitulos = renderizador.renderiza(parametros.getDiretorioDosMD());

        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(parametros.getArquivoDeSaida());
        ebook.setCapitulos(capitulos);

        if ("pdf".equals(formato)) {
            geradorPDF.gera(ebook);
        } else if ("epub".equals(formato)) {
            geradorEPUB.gera(ebook);
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }
    }
}
