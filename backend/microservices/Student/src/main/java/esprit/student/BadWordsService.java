package esprit.student;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class BadWordsService {

    private final List<String> badWords = Arrays.asList("idiot", "stupid", "fool", "dumb");

    public boolean containsBadWord(String input) {
        if (input == null) return false;
        String lower = input.toLowerCase();
        return badWords.stream().anyMatch(lower::contains);
    }
}
