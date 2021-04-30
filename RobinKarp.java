/**
 * Robin karp algo for pattern finding. Time complexity O(n).
 */
public class RobinKarp {
    public int findPatternInString(String aText, String aPattern) {
        if (aText.length() < aPattern.length())
            return -1;
        int patternHashCode = aPattern.hashCode(); //O(1).
        int start = 0;
        int end = start + aPattern.length();
        while (end <= aText.length()) { //O(n)
            if (aText.substring(start, end).hashCode() == patternHashCode)
                return start;
            start++;
            end++;
        }
        return -1;
    }
}
