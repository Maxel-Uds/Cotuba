package com.cognitio.estatisticas;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.plugin.AoFinalizarGeracao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

    @Override
    public void aposGeracao(Ebook ebook) {
        ContagemDePalavras contagemDePalavras = new ContagemDePalavras();

        for(Capitulo capitulo : ebook.capitulos()) {
            String html = capitulo.conteudoHTML();

            Document doc = Jsoup.parse(html);

            String textDoCapitulo = doc.body().text();
            String textDoCapituloSemPontuacao = textDoCapitulo.replaceAll("\\p{Punct}", " ");
            String textDoCapituloSemAcento = Normalizer.normalize(textDoCapituloSemPontuacao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

            String[] palavras = textDoCapituloSemAcento.split("\\s+");

            for(String palavra : palavras) {
                contagemDePalavras.adicionaPalavra(palavra.toUpperCase());
            }


        }

        for(ContagemDePalavras.Contagem contagem : contagemDePalavras) {
            System.out.printf("palavra %s: %o ocorrências%n", contagem.palavra(), contagem.ocorrencias());
        }
    }
}
