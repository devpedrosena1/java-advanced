package br.com.fiap.tds.javaadv.Library.presentation.controllers;

import br.com.fiap.tds.javaadv.Library.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class PostApiController {

    private final PostService  postService;

}
