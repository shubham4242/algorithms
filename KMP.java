/**
 * Kmp Algorithm for pattern searching in O(m + n) time and O(n) complexity.
 * m = size of original String.
 * n = size of pattern.
 */
public class KMP {
    private void preProcessPattern(String aPattern, int[] preProcess) {
        int start = 1;
        int prev = 0;
        preProcess[0] = 0;
        while (start < aPattern.length()) {
            // if character is matched store into preProcess array;
            if (aPattern.charAt(start) == aPattern.charAt(prev)) {
                preProcess[start++] = ++prev;
            } else {
                if (prev > 0) {
                    // if prev is 0 then we cannot go below that.
                    prev = preProcess[prev - 1];
                } else {
                    // if 0 set the preprocess array to 0;
                    preProcess[start++] = prev;
                }
            }
        }
    }

    /**
     * Find pattern in string in polynomial time and return starting index of pattern otherwise -1.
     *
     * @param aText
     * @param aPattern
     * @return
     */
    public int findPatternInString(String aText, String aPattern) {
        int[] preprocess = new int[aPattern.length()];
        // preprocess the pattern.
        preProcessPattern(aPattern, preprocess);
        int textIndex = 0;
        int patternIndex = 0;
        while (textIndex < aText.length()) {
            // if both the characters are equals add it.
            if (aPattern.charAt(patternIndex) == aText.charAt(textIndex)) {
                textIndex++;
                patternIndex++;
            }
            // if pattern is match then return it if you want to return all the index then add it to List.
            if (patternIndex == aPattern.length())
                return textIndex - patternIndex;
            else if (aText.charAt(textIndex) != aPattern.charAt(patternIndex)) {
                if (patternIndex != 0) {
                    // if not match move it to previous suffix.
                    patternIndex = preprocess[patternIndex - 1];
                } else {
                    // if 0 then move ahead.
                    textIndex++;
                }
            }
        }
        return -1;
    }
}
