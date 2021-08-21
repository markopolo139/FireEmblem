package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.pdf.CharacterPDFInteractor;
import com.FireEmbelm.FireEmblem.app.interactors.pdf.FieldPDFInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@CrossOrigin
@Controller
public class PdfController {

    @Autowired
    private CharacterPDFInteractor mCharacterPDFInteractor;

    @Autowired
    private FieldPDFInteractor mFieldPDFInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @GetMapping("/api/v1/characters")
    public String characters(Principal principal, Model model) {
        mCharacterPDFInteractor.setContextForHtml(mAppUtils.getGameIdFromLogin(principal.getName()), model);
        return "characters";
    }

    @GetMapping("/api/v1/field")
    public String field(Principal principal, Model model) {
        mFieldPDFInteractor.setContextForHtml(mAppUtils.getGameIdFromLogin(principal.getName()), model);
        return "field";
    }

    @GetMapping("/api/v1/pdf/characters")
    public void pdfCharacters(Principal principal, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType(MediaType.APPLICATION_PDF.toString());

        mCharacterPDFInteractor.generatePdf(
                mAppUtils.getGameIdFromLogin(principal.getName()),httpServletResponse.getOutputStream()
        );

        httpServletResponse.getOutputStream().flush();
    }

    @GetMapping("/api/v1/pdf/field")
    public void pdfField(Principal principal, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType(MediaType.APPLICATION_PDF.toString());

        mCharacterPDFInteractor.generatePdf(
                mAppUtils.getGameIdFromLogin(principal.getName()),httpServletResponse.getOutputStream()
        );

        httpServletResponse.getOutputStream().flush();

    }

}
