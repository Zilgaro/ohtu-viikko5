package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final int MAX = 4;
    private String player1Name;
    private String player2Name;
    private Map<Integer, String> scores;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        scores = new HashMap<>();
        scores.put(0, "Love");
        scores.put(1, "Fifteen");
        scores.put(2, "Thirty");
        scores.put(3, "Forty");
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(this.player1Name)) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (player1Score == player2Score) {
            score = evenScore();
        } else if (player1Score >= MAX || player2Score >= MAX) {
            score = getAdvantageOrVictory();
        } else {
            score = otherCases();
        }
        return score;
    }

    private String evenScore() {
        if (0 <= player1Score && player1Score < MAX) {
            return scores.get(player1Score) + "-All";
        } else {
            return "Deuce";
        }
    }

    private String getAdvantageOrVictory() {
        int minusResult = player1Score - player2Score;
        if (minusResult == 1) {
            return "Advantage " + player1Name;
        } else if (minusResult == -1) {
            return "Advantage " + player2Name;
        } else if (minusResult >= 2) {
            return "Win for " + player1Name;
        } else {
            return "Win for " + player2Name;
        }
    }

    private String otherCases() {
        int tempScore = 0;
        String returnValue = "";
        for (int i = 1; i < MAX - 1; i++) {
            tempScore = setTempScore(i, tempScore);
            if (i != 1) {
                returnValue += "-";
            }
            returnValue += setScore(tempScore);
        }
        return returnValue;
    }

    private int setTempScore(int index, int tempScore) {
        if (index == 1) {
            tempScore = player1Score;
        } else {
            tempScore = player2Score;
        }
        return tempScore;
    }

    private String setScore(int tempScore) {
        return scores.get(tempScore);
    }
}
