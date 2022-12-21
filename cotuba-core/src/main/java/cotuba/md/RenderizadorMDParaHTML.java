package cotuba.md;

import cotuba.application.RepositorioDeMDs;
import cotuba.builder.CapituloBuilder;
import cotuba.domain.Capitulo;
import cotuba.domain.LeTitulo;
import cotuba.plugin.AoRenderizarHTML;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.List;
import java.util.stream.Collectors;

public class RenderizadorMDParaHTML {

    public List<Capitulo> renderiza(RepositorioDeMDs repositorioDeMDs) {
        return repositorioDeMDs.obtemMDsDosCapitulos().stream()
                .map(conteudoMD -> {
                    CapituloBuilder capituloBuilder = new CapituloBuilder();
                    Node document = parseDoMD(conteudoMD, capituloBuilder);
                    renderizaParaHTML(capituloBuilder, document);
                    return capituloBuilder.constroi();
                }).collect(Collectors.toList());
    }

    private void renderizaParaHTML(CapituloBuilder capituloBuilder, Node document) {
        try {
            HtmlRenderer renderer = HtmlRenderer.builder().build();
            String html = renderer.render(document);

            String htmlModificado = AoRenderizarHTML.renderizou(html);
            capituloBuilder.comConteudoHTML(htmlModificado);
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao renderizar MD para HTML", ex);
        }
    }

    private Node parseDoMD(String conteudoMD, CapituloBuilder capituloBuilder) {
        Parser parser = Parser.builder().build();
        Node document;
        try {
            document = parser.parse(conteudoMD);
            document.accept(new LeTitulo(capituloBuilder));
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao fazer parse do arquivo " + conteudoMD, ex);
        }

        return document;
    }
}
