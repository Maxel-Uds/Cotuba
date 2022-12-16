package com.cognitio.estatisticas;

import cotuba.plugin.AoFinalizarGeracao;
import cotuba.plugin.CapituloSoParaLeitura;
import cotuba.plugin.EbookSoParaLeitura;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.text.Normalizer;
import java.util.Map;

public class CalculadoraDeEstatisticas implements AoFinalizarGeracao {

    @Override
    public void aposGeracao(EbookSoParaLeitura ebook) {
        ContagemDePalavras contagemDePalavras = new ContagemDePalavras();

        for(CapituloSoParaLeitura capitulo : ebook.getCapitulos()) {
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
