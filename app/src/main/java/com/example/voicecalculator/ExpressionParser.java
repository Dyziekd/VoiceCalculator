package com.example.voicecalculator;

public class ExpressionParser
{
    // replaces words with numbers and symbols
    public static String parse(String expression)
    {
        // for english
        expression = parseEnglishNumbers(expression);
        expression = parseEnglishSymbols(expression);
        expression = parseEnglishSimilarWords(expression);

        // for polish
        expression = parsePolishNumbers(expression);
        expression = parsePolishSymbols(expression);
        expression = parsePolishSimilarWords(expression);
        expression = fixPolish(expression);

        // delete spaces
        expression = expression.replaceAll(" ", "");

        return expression;
    }

    private static String parseEnglishNumbers(String expression)
    {
        expression = expression.replaceAll("Ten|ten", "10");
        expression = expression.replaceAll("Eleven|eleven", "11");
        expression = expression.replaceAll("Twelve|twelve", "12");
        expression = expression.replaceAll("Thirteen|thirteen", "13");
        expression = expression.replaceAll("Fourteen|fourteen", "14");
        expression = expression.replaceAll("Fifteen|fifteen", "15");
        expression = expression.replaceAll("Sixteen|sixteen", "16");
        expression = expression.replaceAll("Seventeen|seventeen", "17");
        expression = expression.replaceAll("Eighteen|eighteen", "18");
        expression = expression.replaceAll("Nineteen|nineteen", "19");
        expression = expression.replaceAll("Twenty|twenty", "20");
        expression = expression.replaceAll("Thirty|thirty", "30");
        expression = expression.replaceAll("Forty|forty", "40");
        expression = expression.replaceAll("Fity|fifty", "50");
        expression = expression.replaceAll("Sixty|sixty", "60");
        expression = expression.replaceAll("Seventy|seventy", "70");
        expression = expression.replaceAll("Eighty|eighty", "80");
        expression = expression.replaceAll("Ninety|ninety", "90");
        expression = expression.replaceAll("One|one", "1");
        expression = expression.replaceAll("Two|two", "2");
        expression = expression.replaceAll("Three|three", "3");
        expression = expression.replaceAll("Four|four", "4");
        expression = expression.replaceAll("Five|five", "5");
        expression = expression.replaceAll("Six|six", "6");
        expression = expression.replaceAll("Seven|seven", "7");
        expression = expression.replaceAll("Eight|eight", "8");
        expression = expression.replaceAll("Nine|nine", "9");

        return expression;
    }


    private static String parseEnglishSymbols(String expression)
    {
        expression = expression.replaceAll("Add|add|Plus|plus", "+");
        expression = expression.replaceAll("Subtract|subtract|Minus|minus|Negative|negative", "-");
        expression = expression.replaceAll("Times|times|Multiply(| By| by)|multiply(| By| by)|Multiplied(| By| by)|multiplied(| By| by)", "×");
        expression = expression.replaceAll("Over|over|Divide(| By| by)|divide(| By| by)|Divided(| By| by)|divided(| By| by)", "÷");
        expression = expression.replaceAll("To The Power Of|To The Power of|To The power Of|To The power of|To the Power Of|To the Power of|To the power Of|To the power of|to The Power Of|to The Power of|to The power of|to the power of|to The power Of|to the power Of| to the Power of", "^");
        expression = expression.replaceAll("Opening Bracket(|s)|Opening bracket(|s)|opening Bracket(|s)|opening bracket(|s)|Opening Parenthesis|Opening parenthesis|opening Parenthesis|opening parenthesis", "(");
        expression = expression.replaceAll("Closing Bracket(|s)|Closing bracket(|s)|closing Bracket(|s)|closing bracket(|s)|Closing Parenthesis|Closing parenthesis|closing Parenthesis|closing parenthesis", ")");

        return expression;
    }


    private static String parseEnglishSimilarWords(String expression)
    {
        expression = expression.replaceAll("To|to|Ty|ty", "2");
        expression = expression.replaceAll("Free|free|Three|three", "3");
        expression = expression.replaceAll("For|for", "4");
        expression = expression.replaceAll("Fix|fix|Sex|sex", "6");
        expression = expression.replaceAll("IT|It|it|ID|Id|id|YT|Yt|yt", "8");
        expression = expression.replaceAll("Nein|nein", "9");

        expression = expression.replaceAll("At|at|And|and|Blues|blues", "+");
        expression = expression.replaceAll("Mulitplay(| By| by)|multiplay(| By| by)|Multiplate(| By| by)|multiplate(| By| by)|Multiplayer(| By| by)|multiplayer(| By| by)", "×");
        expression = expression.replaceAll("Open In Bracket(|s)|Open In bracket(|s)|Open in Bracket(|s)|Open in bracket(|s)|open In Bracket(|s)|open In bracket(|s)|open in Bracket(|s)|open in bracket(|s)", "(");
        expression = expression.replaceAll("Opening Black Red|Opening Black red|Opening black red|Opening black Red|opening Black Red|opening black Red|opening Black red|opening black red", "(");
        expression = expression.replaceAll("Opening Break At|Opening Break at|Opening break at|Opening breakt At|opening Breakt At|opening breat At|opening Break at|opening break at", "(");

        return expression;
    }

    private static String parsePolishNumbers(String expression)
    {
        expression = expression.replaceAll("Jeden|jeden|Pierwszej|pierwszej", "1");
        expression = expression.replaceAll("Dwa|dwa|Drugiej|drugiej", "2");
        expression = expression.replaceAll("Trzy|trzy|Trzeciej|trzeciej", "3");
        expression = expression.replaceAll("Cztery|cztery|Czwartej|czwartej", "4");
        expression = expression.replaceAll("Pięć|pięć|Piątej|piątej", "5");
        expression = expression.replaceAll("Sześć|sześć|Szóstej|szóstej", "6");
        expression = expression.replaceAll("Siedem|siedem|Siódmej|siódmej", "7");
        expression = expression.replaceAll("Osiem|osiem|Ósmej|óśmej", "8");
        expression = expression.replaceAll("Dziewięć|dziewięć|Dziewiątej|dziewiątej", "9");

        return expression;
    }


    private static String parsePolishSymbols(String expression)
    {
        expression = expression.replaceAll("Dodać|dodać", "+");
        expression = expression.replaceAll("Odjąć|odjąć", "-");
        expression = expression.replaceAll("Razy|razy|Pomnożyć Przez|Pomnożyć przez|pomnożyć Przez|pomnożyć przez", "×");
        expression = expression.replaceAll("Podzielić Przez|Podzielić przez|podzielić Przez|podzielić przez", "÷");
        expression = expression.replaceAll("Do Potęgi|Do potęgi|do Potęgi|do potęgi", "^");
        expression = expression.replaceAll("Do|do", "^");
        expression = expression.replaceAll("Początek Nawiasu|Początek nawiasu|początek Nawiasu|początek nawiasu", "(");
        expression = expression.replaceAll("Koniec Nawiasu|Koniec nawiasu|koniec Nawiasu|koniec nawiasu", ")");

        return expression;
    }


    private static String parsePolishSimilarWords(String expression)
    {
        expression = expression.replaceAll("Czy|czy", "3");
        expression = expression.replaceAll("Cześć|cześć", "6");

        return expression;
    }

    // fix for exponentiation in polish language (e.g. "2 do drugiej", "8 do dziewiątej")
    private static String fixPolish(String expression)
    {
        if(expression.contains(":00"))
            return expression.replace(":00" ,"");
        else
            return expression;
    }
}
