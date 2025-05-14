package org.pk.catcut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

  @RequestMapping("/")
  public String getFrontend() {
    return "index.html";
  }
}
