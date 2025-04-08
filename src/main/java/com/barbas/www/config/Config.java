package com.barbas.www.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Getter
@Setter
@Component
public class Config {
    private final String name = "Barba´s Vanilla";
    private final String shortName = "Barba´s";
    private final String logo = "/img/logo-alfa.png";
    private final String copyright = "&copy; Copyright 2025 Barba´s Vanilla";
    private String pageCSS = "";
    private String pageJS = "";

    public String getPageCSS() {
        return Objects.equals(pageCSS, "") ? "" : "<link rel=\"stylesheet\" href=\"" + pageCSS + "\">";
    }

    public String getPageJS() {
        return Objects.equals(pageJS, "") ? "" : "<script src=\"" + pageJS + "\"><\script>";
    }
}
