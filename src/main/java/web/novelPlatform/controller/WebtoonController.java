package web.novelPlatform.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import web.novelPlatform.service.WebtoonService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WebtoonController {

    private final WebtoonService webtoonService;

}
