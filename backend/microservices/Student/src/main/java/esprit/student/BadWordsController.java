package esprit.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/badwords")
public class BadWordsController {

    @Autowired
    private BadWordsService badWordsService;

    @PostMapping("/check")
    public Map<String, Boolean> checkBadWords(@RequestBody Map<String, String> body) {
        String input = body.get("text");
        boolean hasBadWord = badWordsService.containsBadWord(input);
        Map<String, Boolean> response = new HashMap<>();
        response.put("containsBadWord", hasBadWord);
        return response;
    }
}
