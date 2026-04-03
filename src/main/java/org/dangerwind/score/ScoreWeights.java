package org.dangerwind.score;

import java.util.EnumMap;

public class ScoreWeights {
    private final EnumMap<ScorePatternType, Integer> weights = new EnumMap<>(ScorePatternType.class);


    public int get(ScorePatternType pattern) {
        return weights.getOrDefault(pattern, 0);
    }

    public void set(ScorePatternType pattern, int value) {
        weights.put(pattern, value);

    }

}
