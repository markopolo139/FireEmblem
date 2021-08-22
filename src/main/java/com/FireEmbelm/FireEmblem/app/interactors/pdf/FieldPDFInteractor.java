package com.FireEmbelm.FireEmblem.app.interactors.pdf;

import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Locale;

//TODO: end field template

@Service
public class FieldPDFInteractor {

    public static final String FIELD_TEMPLATE = "field";

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private ITemplateEngine mITemplateEngine;

    public void generatePdf(long gameId, OutputStream outputStream) throws IOException {

        Context context = new Context();
        context.setVariable("isPdf", true);
        context.setVariable("field", mSpotRepository.findAllByGameId_GameId(gameId));
        context.setLocale(new Locale("en"));

        String url;

        try {
            url = Paths.get("/src/main/resources/").toUri().toURL().toString();
        }
        catch (Exception e) {
            throw new IllegalStateException("Invalid resource", e);
        }

        String html = mITemplateEngine.process(FIELD_TEMPLATE, context);

        new PdfRendererBuilder().withHtmlContent(html,url).toStream(outputStream).run();

    }

    public void setContextForHtml(long gameId, Model model) {

        model.addAttribute("isPdf", false);
        model.addAttribute("field", mSpotRepository.findAllByGameId_GameId(gameId));
    }

}
