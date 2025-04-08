package com.barbas.www.config;

import com.barbas.www.model.Account;
import com.barbas.www.repository.AccountRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAttributes {

    private final Config config;
    private final AccountRepository accountRepository;

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpServletRequest request) {
        model.addAttribute("logo", config.getLogo());
        model.addAttribute("copyright", config.getCopyright());

        // Verifica cookie de login
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("account".equals(cookie.getName())) {
                    try {
                        Integer userId = Integer.parseInt(cookie.getValue());
                        Optional<Account> userOpt = accountRepository.findById(userId);
                        userOpt.ifPresent(user -> model.addAttribute("loggedUser", user));
                    } catch (NumberFormatException ignored) {
                    }
                    break;
                }
            }
        }
    }
}
