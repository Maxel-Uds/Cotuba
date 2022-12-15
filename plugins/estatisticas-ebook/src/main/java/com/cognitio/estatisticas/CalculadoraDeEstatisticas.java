package com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.Plugin;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements Plugin {

    @Override
    public String aposRenderizacao(String html) {
        return html;
    }

    @Override
    public void aposGeracao(Ebook ebook) {
        ContagemDePalavras contagemDePalavras = new ContagemDePalavras();

        for(Capitulo capitulo : ebook.getCapitulos()) {
            String html = capitulo.getConteudoHTML();

            Document doc = Jsoup.parse(html);

            String textDoCapitulo = doc.body().text();
            String textDoCapituloSemPontuacao = textDoCapitulo.replaceAll("\\p{Punct}", " ");
            String textDoCapituloSemAcento = Normalizer.normalize(textDoCapituloSemPontuacao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

            String[] palavras = textDoCapituloSemAcento.split("\\s+");

            for(String palavra : palavras) {
                contagemDePalavras.adicionaPalavra(palavra.toUpperCase());
            }


        }

        for(Map.Entry<String, Integer> contagem : contagemDePalavras.entrySet()) {
            System.out.printf("palavra %s: %o ocorrências%n", contagem.getKey(), contagem.getValue());
        }
    }
}
