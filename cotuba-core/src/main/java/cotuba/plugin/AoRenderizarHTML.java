package cotuba.plugin;

import java.util.ServiceLoader;

public interface AoRenderizarHTML {

    String aposRenderizacao(String html);

    static String renderizou(String html) {
        for(AoRenderizarHTML plugin : ServiceLoader.load(AoRenderizarHTML.class)) {
            html = plugin.aposRenderizacao(html);
        }

        return html;
    }
}
