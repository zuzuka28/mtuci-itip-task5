package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(encrypt("Hello")));
        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));
        System.out.println(Arrays.toString(encrypt("Sunshine")));

        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));

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
        return startPosX == posX && startPosY + 1 == posY;
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
            case "Pawn" -> pawn(
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
}

