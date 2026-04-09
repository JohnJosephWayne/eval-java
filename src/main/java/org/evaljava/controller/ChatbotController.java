package org.evaljava.controller;

import org.evaljava.dao.QuestionHistoryDao;
import org.evaljava.dto.Question;
import org.evaljava.model.QuestionHistory;
import org.evaljava.security.AppUserDetails;
import org.evaljava.security.IsAdmin;
import org.evaljava.security.IsUser;
import org.evaljava.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatbotController {

    protected final AiService aiService;
    protected final QuestionHistoryDao questionHistoryDao;

    @PostMapping("/ask")
    @IsUser
    public ResponseEntity<String> ask(
            @AuthenticationPrincipal AppUserDetails userDetails,
            @RequestBody Question question) {

        AiService.AiResult result = aiService.askGemini(question.content());

        QuestionHistory history = new QuestionHistory(null, question.content(), result.text(), userDetails.getUser(), result.recipe());
        questionHistoryDao.save(history);

        return new ResponseEntity<>(result.text(), HttpStatus.OK);
    }

    @GetMapping("/ask/history")
    @IsUser
    public List<QuestionHistory> getMyHistory(@AuthenticationPrincipal AppUserDetails userDetails) {
        return questionHistoryDao.findByUser(userDetails.getUser());
    }

    @GetMapping("/ask/history/all")
    @IsAdmin
    public List<QuestionHistory> getAllHistory() {
        return questionHistoryDao.findAll();
    }

}
