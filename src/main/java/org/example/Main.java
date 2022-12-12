package org.example;

import java.util.*;
import java.util.*;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));

        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));
        System.out.println(canMove("Pawn", "D2", "D4"));
        System.out.println(canMove("Pawn", "D2", "D3"));

        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(canComplete("butlz", "beautiful"));
        System.out.println(canComplete("tulb", "beautiful"));
        System.out.println(canComplete("bbutl", "beautiful"));

        System.out.println(sumDigProd(16, 28));
        System.out.println(sumDigProd(0));
        System.out.println(sumDigProd(1, 2, 3, 4, 5, 6));

        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"toe", "ocelot", "maniac"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"many", "carriage", "emit", "apricot", "animal"})));
        System.out.println(Arrays.toString(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"})));

        System.out.println(validateCard(1234567890123456L));
        System.out.println(validateCard(1234567890123452L));

        System.out.println(numToEng(0));
        System.out.println(numToEng(1));
        System.out.println(numToEng(10));
        System.out.println(numToEng(11));
        System.out.println(numToEng(20));
        System.out.println(numToEng(21));
        System.out.println(numToEng(101));
        System.out.println(numToEng(110));
        System.out.println(numToEng(111));
        System.out.println(numToEng(120));
        System.out.println(numToEng(121));
        System.out.println(numToRu(0));
        System.out.println(numToRu(1));
        System.out.println(numToRu(10));
        System.out.println(numToRu(11));
        System.out.println(numToRu(20));
        System.out.println(numToRu(21));
        System.out.println(numToRu(101));
        System.out.println(numToRu(110));
        System.out.println(numToRu(111));
        System.out.println(numToRu(120));
        System.out.println(numToRu(121));

        System.out.println(getSha256Hash("password123"));
        System.out.println(getSha256Hash("Fluffy@home"));
        System.out.println(getSha256Hash("Hey dude!"));

        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        System.out.println(correctTitle("I DIDN'T WATCH THE-GAME-OF-THRONES."));

        System.out.println(hexLattice(1));
        System.out.println(hexLattice(7));
        System.out.println(hexLattice(19));
        System.out.println(hexLattice(37));
        System.out.println(hexLattice(61));
    }

    public static int[] encrypt(String source) {
        int[] encrypted = new int[source.length()];

        for (int i = 0; i < encrypted.length; i++) {
            if (i == 0) {
                encrypted[i] = source.charAt(i);
                continue;
            }
            encrypted[i] = source.charAt(i) - source.charAt(i - 1);
        }
        return encrypted;
    }

    public static String decrypt(int[] source) {
        char[] decrypted = new char[source.length];

        for (int i = 0; i < source.length; i++) {
            if (i == 0) {
                decrypted[i] = (char) source[i];
                continue;
            }
            decrypted[i] = (char) (decrypted[i - 1] + source[i]);
        }
        return new String(decrypted);
    }

    public static String[][] getBoard() {
        ArrayList<Character> cols = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'));
        ArrayList<Integer> rows = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));

        int boardSize = 8;
        String[][] board = new String[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            Character col = cols.get(i);
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = col + String.valueOf(rows.get(j));
            }
        }
        return board;
    }

    public static int getRowPosition(String item) {
        String[][] board = getBoard();
        for (int i = 0; i < board.length; i++) {
            if (Arrays.asList(board[i]).contains(item)) {
                return i;
            }
        }
        return 0;
    }

    public static int getColPosition(String item) {
        String[][] board = getBoard();
        String[] row = board[getRowPosition(item)];
        for (int i = 0; i < row.length; i++) {
            if (row[i].equals(item)) {
                return i;
            }
        }
        return 0;
    }

    public static boolean pawn(int startPosX, int startPosY, int posX, int posY){
        return startPosX + 1 == posX && startPosY == posY;
    }
    public static boolean king (int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) <= 1 &&  Math.abs(startPosY - posY) <= 1;
    }
    public static boolean rook(int startPosX, int startPosY, int posX, int posY){
        return startPosX == posX || startPosY == posY;
    }
    public static boolean bishop(int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) ==  Math.abs(startPosY - posY);
    }
    public static boolean queen(int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) ==  Math.abs(startPosY - posY) ||
                startPosX == posX || startPosY == posY;
    }
    public static boolean knight (int startPosX, int startPosY, int posX, int posY){
        return Math.abs(startPosX - posX) == 1 && Math.abs(startPosY - posY) == 2 ||
                Math.abs(startPosX - posX) == 2 && Math.abs(startPosY - posY) == 1;
    }

    public static boolean canMove(String figureName, String start, String end) {
        return switch (figureName) {
            case "Pawn" -> getColPosition(start) == 1 ?
                        pawn(
                                getColPosition(start), getRowPosition(start),
                                getColPosition(end), getRowPosition(end)
                        ) || pawn(
                            getColPosition(start) + 1, getRowPosition(start),
                            getColPosition(end), getRowPosition(end)
                        )
                    :
                            pawn(
                        getColPosition(start), getRowPosition(start),
                        getColPosition(end), getRowPosition(end)
                    );
            case "Rook" -> rook(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "King" -> king(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Bishop" -> bishop(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Knight" -> knight(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            case "Queen" -> queen(
                    getColPosition(start), getRowPosition(start),
                    getColPosition(end), getRowPosition(end)
            );
            default -> false;
        };
    }

    public static boolean canComplete(String source, String target) {
        if (source.equals("")) {
            return true;
        }
        Stack<String> sourceChars = new Stack<>();
        sourceChars.addAll(
                Arrays.asList(new StringBuilder(source).reverse().toString().split(""))
        );
        for (String chr : target.split("")) {
            if (!sourceChars.isEmpty() && Objects.equals(sourceChars.peek(), chr)) {
                sourceChars.pop();
            }
        }

        return sourceChars.isEmpty();
    }

    public static int sumDigProd(int ...numbers){
        if (numbers.length == 1){
            String[] digits = Integer.toString(numbers[0]).split("");
            int newNumber = 1;
            for (String i: digits){
                newNumber *= Integer.parseInt(i);
            }
            if (digits.length == 1) {
                return newNumber;
            }
            return sumDigProd(newNumber);
        }
        return sumDigProd(Arrays.stream(numbers).sum());
    }

    public static HashSet<String> getVowels(String source){
        HashSet<String> vowel = new HashSet<>(Arrays.asList("a", "e", "o", "i", "u", "y"));
        HashSet<String> vowels = new HashSet<>();

        for (String digit : source.split("")) {
            if (vowel.contains(digit)) {
                vowels.add(digit);
            }
        }
        return vowels;
    }
    public static String[] sameVowelGroup(String[] source){
        HashSet<String> fstWordVowels = getVowels(source[0]);
        ArrayList<String> result = new ArrayList<>();
        for (String word : source){
            HashSet<String> wordVowels = getVowels(word);
            if (fstWordVowels.size() == wordVowels.size() &&
                    fstWordVowels.containsAll(wordVowels)){
                result.add(word);
            }
        }
        return result.toArray(new String[0]);
    }
    public static boolean validateCard(long source) {
        String card = String.valueOf(source);
        int lastDigit = Character.getNumericValue(card.charAt(card.length()-1));
        card = card.substring(0, card.length()-1);
        card = new StringBuilder(card).reverse().toString();
        int sum = 0;
        for (int i = 0; i < card.length(); i++){
            int sumCurr = Character.getNumericValue(card.charAt(i));
            if (i % 2 == 1){
                int doubled =sumCurr * 2;
                sumCurr = doubled > 9 ? Arrays.stream(String.valueOf(doubled).split("")).mapToInt(Integer::valueOf).sum() : doubled;
            }
            sum += sumCurr;
        }
        return 10 - (sum % 10) == lastDigit;
    }

    public static String numToEng(int number){
        if (number > 999) {
            return "";
        }

        Map<Integer, String> unitsNames =  Map.of(
                0, "zero",
                1, "one",
                2, "two",
                3, "three",
                4, "four",
                5, "five",
                6, "six",
                7, "seven",
                8, "eight",
                9, "nine"
        );
        Map<Integer, String> tensNames = Map.of(
                10, "ten",
                11, "eleven",
                12, "twelve",
                13, "thirteen",
                14, "fourteen",
                15, "fifteen",
                16, "sixteen",
                17, "seventeen",
                18, "eighteen",
                19, "nineteen"
        );
        Map<Integer, String> decaNames = Map.of(
                20, "twenty",
                30, "thirty",
                40, "forty",
                50, "fifty",
                60, "sixty",
                70, "seventy",
                80, "eighty",
                90, "ninety"
        );


//        915
        int deca = number % 100;
        int hundred = number / 100;

        ArrayList<String> result = new ArrayList<>();
        if (hundred != 0){
            result.add(unitsNames.get(hundred) == null ? "" : unitsNames.get(hundred) + " " + (hundred == 1 ? "hundred" : "hundreds"));
        }
        if (deca >= 10 && deca < 20) {
            result.add(tensNames.get(deca) == null ? "": tensNames.get(deca));

        } else {
            int d = deca / 10;
            int u = deca % 10;
            if (d > 0 && u == 0){
                result.add(decaNames.get(d * 10) == null ? "" : decaNames.get(d * 10));
            } else if (d > 0 && u > 0) {
                result.add(decaNames.get(d * 10) == null ? "" : decaNames.get(d * 10));
                result.add(unitsNames.get(u) == null ? "" : unitsNames.get(u));
            } else if (d == 0){
                result.add(unitsNames.get(u) == null ? "" : unitsNames.get(u));
            }
        }
        return String.join(" ", result).strip();
    }
    public static String numToRu(int number) {
        if (number > 999) {
            return "";
        }

        Map<Integer, String> unitsNames = Map.of(
                0, "ноль",
                1, "один",
                2, "два",
                3, "три",
                4, "четыре",
                5, "пять",
                6, "шесть",
                7, "семь",
                8, "восемь",
                9, "девять"
        );
        Map<Integer, String> tensNames = Map.of(
                10, "десять",
                11, "одиннадцать",
                12, "двенадцать",
                13, "тринадцать",
                14, "четырнадцать",
                15, "пятнадцать",
                16, "шестнадцать",
                17, "семнадцать",
                18, "восемнадцать",
                19, "девятнадцать"
        );
        Map<Integer, String> decaNames = Map.of(
                20, "двадцать",
                30, "тридцать",
                40, "сорок",
                50, "пятьдесят",
                60, "шестьдесят",
                70, "семьдесят",
                80, "восемьдесят",
                90, "девяносто"
        );
        Map<Integer, String> hundredNames = Map.of(
                1, "сто",
                2, "двести",
                3, "триста",
                4, "четыреста",
                5, "пятьсот",
                6, "шестьсот",
                7, "семьсот",
                8, "восемьсот",
                9, "девятьсот"
        );


        int deca = number % 100;
        int hundred = number / 100;
        ArrayList<String> result = new ArrayList<>();
        if (hundred != 0) {
            result.add(hundredNames.get(hundred) == null ? "" : hundredNames.get(hundred));
        }
        if (deca >= 10 && deca < 20) {
            result.add(tensNames.get(deca) == null ? "": tensNames.get(deca));

        } else {
            int d = deca / 10;
            int u = deca % 10;
            if (d > 0 && u == 0){
                result.add(decaNames.get(d * 10) == null ? "" : decaNames.get(d * 10));
            } else if (d > 0 && u > 0) {
                result.add(decaNames.get(d * 10) == null ? "" : decaNames.get(d * 10));
                result.add(unitsNames.get(u) == null ? "" : unitsNames.get(u));
            } else if (d == 0){
                result.add(unitsNames.get(u) == null ? "" : unitsNames.get(u));
            }
        }
        return String.join(" ", result).strip();
    }
    public static HashCode getSha256Hash(String password) {
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();

        return sha256;
    }

    public static String correctTitle(String title) {
        String[] words = title.split(" ");
        for (int i = 0; i < words.length; i++) {
            if(words[i].matches("(?i)^(in|and|the|of)$")) {
                words[i] = words[i].toLowerCase();
                continue;
            }
            if(words[i].contains("-")) {
                String[] tmp = words[i].split("-");
                for(int j = 0; j < tmp.length; j++) {
                    tmp[j] = tmp[j].substring(0, 1).toUpperCase() + tmp[j].substring(1).toLowerCase();
                }
                words[i] = String.join("-", tmp);
                continue;
            }
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }
    public static boolean checkHex(int number){
        int i = 1;
        int hexNumber = 1;
        while (hexNumber != number){
            if (hexNumber > number){
                return false;
            }
            hexNumber = 3*i*(i-1)+1;
            i++;
        }
        return true;
    }
    public static int getHexIteration(int number){
        int i = 1;
        int hexNumber = 1;
        while (hexNumber != number){
            hexNumber = 3*i*(i-1)+1;
            i++;
        }
        return i;
    }
    public static String hexLattice(int number) {
        if (!checkHex(number)){
            return "invalid";
        }
        int edge = getHexIteration(number) - 1;
        int centerLine = edge*2 - 1;
        ArrayList<String> halfFigure = new ArrayList<>();
        for (int i = edge; i < centerLine; i++){
            String row = " ".repeat(centerLine - i) + "o ".repeat(i) + " ".repeat(centerLine - i);
            halfFigure.add(row);
        }
        ArrayList<String> reversedHalfFigure = new ArrayList<>(halfFigure);
        Collections.reverse(reversedHalfFigure);
        if (centerLine == -1) {
            return "o";
        }
        return String.join("\n", halfFigure) +
                "\n" + "o ".repeat(centerLine) + "\n" +
                String.join("\n", reversedHalfFigure);
    }
}

