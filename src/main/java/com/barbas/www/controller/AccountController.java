package com.barbas.www.controller;

import com.barbas.www.config.Config;
import com.barbas.www.model.Account;
import com.barbas.www.repository.AccountRepository;
import com.barbas.www.util.HashUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final Config config;
    private final AccountRepository accountRepository;

    @PostMapping("/login")
    public String doLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpServletRequest request,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes,
            Model model
    ) {

        Optional<Account> userOpt = accountRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            String hashedPassword = HashUtil.sha256(password); // Criptografa a senha digitada

            if (userOpt.get().getPassword().equals(hashedPassword)) {
                Cookie loginCookie = new Cookie("account", userOpt.get().getId().toString());
                logger.debug("Valor do cookieHoursAge: {}", config.getCookieHoursAge());
                loginCookie.setMaxAge(config.getCookieHoursAge() * 60 * 60); // 24 horas
                loginCookie.setHttpOnly(true);
                loginCookie.setPath("/");
                response.addCookie(loginCookie);

                return "redirect:/";
            }
        }

        redirectAttributes.addFlashAttribute("loginError", "E-mail ou senha inválidos!");
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("title", config.getName() + " - Perfil do usuário");
        return "account/profile";
    }

}