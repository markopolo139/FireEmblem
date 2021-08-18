package com.FireEmbelm.FireEmblem.web.controllers.security;

import com.FireEmbelm.FireEmblem.app.exceptions.UserNotFoundException;
import com.FireEmbelm.FireEmblem.app.interactors.PasswordRecoveryInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.web.utils.WebUtils;
import com.FireEmbelm.FireEmblem.web.validation.equipment.ValidWeaponCategory;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@CrossOrigin
@Controller
public class ForgotPasswordController {

    public final String PERSONAL_EMAIL = "Service";

    @Value("${spring.mail.username}")
    public String emailFrom;

    @Autowired
    private PasswordRecoveryInteractor mPasswordRecoveryInteractor;

    @Autowired
    private JavaMailSender mJavaMailSender;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    @GetMapping("/forgotPassword")
    public String getForgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String postForgotPassword(Model model, HttpServletRequest request) {

        String email = request.getParameter("email");
        String randomToken = RandomString.make(30);

        try{
            mPasswordRecoveryInteractor.updateToken(email,randomToken);
            String passwordRecoveryLink = WebUtils.getServerPath(request) + "/passwordRecovery?token=" + randomToken;
            sendMassage(email, passwordRecoveryLink);
            model.addAttribute("message", "Email successfully send");
        }
        catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }
        catch (MessagingException | UnsupportedEncodingException e) {
            model.addAttribute("error", "Error sending email");
        }

        return "forgotPassword";

    }

    public void sendMassage(String email, String serverPath) throws MessagingException, UnsupportedEncodingException {

        MimeMessage mimeMessage = mJavaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(emailFrom, PERSONAL_EMAIL);
        mimeMessageHelper.setTo(email);

        String subject = "Forgot Password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + serverPath + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content, true);

        mJavaMailSender.send(mimeMessage);
    }

    @GetMapping("/passwordRecovery")
    public String getPasswordRecovery(@RequestParam("token") String resetToken, Model model) {

        try {
            mPasswordRecoveryInteractor.getUserByPasswordToken(resetToken);
            model.addAttribute("token", resetToken);
            model.addAttribute("message", "You can now create new password");
        }
        catch (UserNotFoundException ex) {
            model.addAttribute("error", "Try again, there was error.");
        }

        return "newPassword";
    }

    @PostMapping("/passwordRecovery")
    public String postPasswordRecovery(HttpServletRequest request, Model model) throws UserNotFoundException {

        String token = request.getParameter("token");
        String password = request.getParameter("password");

        if(AppUtils.validatePassword(password)) {
            mPasswordRecoveryInteractor.updatePassword(token, mPasswordEncoder.encode(password));
            model.addAttribute("message", "Password updated");
        }
        else
            model.addAttribute("message", "Password is not correct " +
                    "(Password must have at least : 1 uppercase character, 1 digit, 1 special character (@,#)," +
                    " no whitespaces, and length from 8 to 30");

        return "newPassword";
    }

}
