import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokerHandAnalyzerTest {
    PokerHandAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new PokerHandAnalyzer();
    }

    void testHand(Hand expected, String hand) {
        assertEquals(expected, analyzer.analyzeHand(hand.split(" ")));
    }

    @Test
    void highCard() {
        testHand(Hand.HighCard, "3D JS 8S 4H 2S");
    }

    @Test
    void pair() {
        testHand(Hand.Pair, "AH AD 8C 4S 7H");
    }

    @Test
    void twoPair() {
        testHand(Hand.TwoPair, "4C 4S 3H 3D QC");
    }

    @Test
    void threeOfAKind() {
        testHand(Hand.ThreeOfAKind, "4H 4S 4C AH 3S");
    }

    @Test
    void straight() {
        testHand(Hand.Straight, "9S 8D 7C 6D 5H");
    }

    @Test
    void flush() {
        testHand(Hand.Flush, "4S JS 8S 2S 9S");
    }

    @Test
    void fullHouse() {
        testHand(Hand.FullHouse, "TH TD TS 9C 9D");
    }

    @Test
    void fourOfAKind() {
        testHand(Hand.FourOfAKind, "JS JD JC JH 7D");
    }

    @Test
    void straightFlush() {
        testHand(Hand.StraightFlush, "8S 7S 6S 5S 4S");
    }

    @Test
    void royalFlush() {
        testHand(Hand.RoyalFlush, "AD KD QD JD TD");
    }

    @Test
    void jokerPair() {
        testHand(Hand.Pair, "AH JK 8C 4S 7H");
    }

    @Test
    void oneJokerThreeOfAKind() {
        testHand(Hand.ThreeOfAKind, "4C 4S JK 3D QC");
    }

    @Test
    void twoJokerThreeOfAKind() {
        testHand(Hand.ThreeOfAKind, "4C 7S JK JK QC");
    }

    @Test
    void oneJokerStraight() {
        testHand(Hand.Straight, "8D 7C 6H JK 4S");
    }

    @Test
    void twoJokerStraightVariation1() {
        testHand(Hand.Straight, "8D 7C JK JK 4S");
    }

    @Test
    void twoJokerStraightVariation2() {
        testHand(Hand.Straight, "8D JK JK 5S 4S");
    }

    @Test
    void twoJokerStraightVariation3() {
        testHand(Hand.Straight, "JK JK 6H 5S 4S");
    }

    @Test
    void oneJokerfullHouse() {
        testHand(Hand.FullHouse, "JK TD TS 9C 9D");
    }

    @Test
    void oneJokerFourOfAKind(){
        testHand(Hand.FourOfAKind, "JK TD TS TC 9D");
    }

    @Test
    void twoJokerFourOfAKind(){
        testHand(Hand.FourOfAKind, "JK TD TS JK 9D");
    }

    @Test
    void oneJokerStraightFlush() {
        testHand(Hand.StraightFlush, "8S 5S 6S JK 4S");
    }

    @Test
    void twoJokerStraightFlushVariation1() {
        testHand(Hand.StraightFlush, "8S JK 6S JK 4S");
    }


    @Test
    void twoJokerStraightFlushVariation2() {
        testHand(Hand.StraightFlush, "JK JK 6S 5S 4S");
    }

    @Test
    void twoJokerStraightFlushVariation3() {
        testHand(Hand.StraightFlush, "8S 7S 6S JK JK");
    }

    @Test
    void oneJokerRoyalFlush() {
        testHand(Hand.RoyalFlush, "AD KD JK JD TD");
    }

    @Test
    void twoJokerRoyalFlush() {
        testHand(Hand.RoyalFlush, "JK KD JK JD TD");
    }
}