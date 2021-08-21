package com.FireEmbelm.FireEmblem.app.interactors.pdf;

import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.util.UriBuilder;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

@Service
public class CharacterPDFInteractor {

    public static final String CHARACTER_TEMPLATE = "characters";

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private ITemplateEngine mITemplateEngine;

    public void generatePdf(long gameId, OutputStream outputStream) throws IOException {

        Context context = new Context();
        context.setVariable("isPdf", true);
        context.setVariable("player_characters", mCharacterRepository.findAllByGameId_GameId(gameId));
        context.setLocale(new Locale("en"));

        String url;

        try {
            url = Paths.get("/src/main/resources/").toUri().toURL().toString();
        }
        catch (Exception e) {
            throw new IllegalStateException("Invalid resource", e);
        }

        String html = mITemplateEngine.process(CHARACTER_TEMPLATE, context);

        new PdfRendererBuilder().withHtmlContent(html,url).toStream(outputStream).run();

    }

    public void setContextForHtml(long gameId, Model model) {

        model.addAttribute("isPdf", false);
        model.addAttribute("player_characters", mCharacterRepository.findAllByGameId_GameId(gameId));
    }
}
