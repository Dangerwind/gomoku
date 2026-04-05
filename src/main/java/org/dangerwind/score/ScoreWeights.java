package org.dangerwind.score;

import java.util.EnumMap;

/**
 *  Коэффициенты комбинаций, 5 в ряд - это лучшая комбинация, 1 фишка и кругом закрыто - самая плохая.
 *  Коэффициенты можно меня во время игры
 */
public class ScoreWeights {
    private final EnumMap<ScorePatternType, Integer> weights = new EnumMap<>(ScorePatternType.class);


    public int get(ScorePatternType pattern) {
        return weights.getOrDefault(pattern, 0);
    }

    public void set(ScorePatternType pattern, int value) {
        weights.put(pattern, value);

    }

}
