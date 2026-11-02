package hung.edu.apptienganh.engine;

import java.util.ArrayList;
import java.util.List;

public class ReviewCenterMixSelector {
    public List<String> getSmartMix() {
        List<String> mix = new ArrayList<>();
        mix.add("3 vocabularies");
        mix.add("2 quiz wrong answers");
        mix.add("1 grammar mistake");
        mix.add("1 speaking sentence");
        mix.add("1 writing prompt");
        mix.add("1 sentence builder");
        return mix;
    }
}
