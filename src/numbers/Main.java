package numbers;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static long num = 0;
    public static long start = 0;
    public static long stop = 0;
    public static String strIn;

    public enum parameterNumber {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8)
        ;
        private final int paramNb;
        private parameterNumber(int paramNb) {
            this.paramNb = paramNb;
        }
        public int getParamNb() {
            return this.paramNb;
        }
    }
    public enum propertyIndx {
        isEven(0),
        isOdd(1),
        isBuzz(2),
        isDuck(3),
        isPalindromic(4),
        isGapful(5),
        isSpy(6),
        isSquare(7),
        isSunny(8),
        isJumping(9),
        isHappy(10),
        isSad(11)
        ;
        private final int paramIndx;
        private propertyIndx(int paramIndx) {
            this.paramIndx = paramIndx;
        }
        public int getParamIndx() {
            return this.paramIndx;
        }
    }

    public static void main(String[] args) {
        info();
    }
    public static void info() {
        System.out.println("Welcome to Amazing Numbers!\n" +
                "\n" +
                "Supported requests:\n" +
                "- enter a natural number to know its properties;\n" +
                "- enter two natural numbers to obtain the properties of the list:\n" +
                "   * the first parameter represents a starting number;\n" +
                "   * the second parameter shows how many consecutive numbers are to be printed;\n" +
                "- two natural numbers and properties to search for;\n" +
                "- a property preceded by minus must not be present in numbers;\n" +
                "- separate the parameters with one space;\n" +
                "- enter 0 to exit.\n");
        enterNumber();
    }
    public static void enterNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a request:");
        long secondParam = 0;
        StringBuilder mutuallyEx = null;
        List<String> arg = null;
        List<String> argNot = null;
        arg = Arrays.asList(new String[]{"even", "odd", "buzz", "duck", "palindromic", "gapful",
                "spy", "square", "sunny", "jumping", "happy", "sad"});
        argNot = Arrays.asList(new String[]{"-even", "-odd", "-buzz", "-duck", "-palindromic", "-gapful",
                "-spy", "-square", "-sunny", "-jumping", "-happy", "-sad"});
        int property = 0;
        int property1 = 0;
        int property2 = 0;
        int property3 = 0;
        int property4 = 0;

        strIn = scanner.nextLine();
        boolean[] argNotShow = checkNotShowArg(strIn.toLowerCase());

        String strInMod = strIn;
        int inputLength = strInMod.split(" ").length;
        String[] strInSplit = strInMod.toLowerCase().split(" ");

        if (strInMod.matches("\\s")) {
            info();
            enterNumber();
        }
        if (inputLength == parameterNumber.ONE.getParamNb()) {
            num = Long.parseLong(strIn);
            if (num == 0 && strInMod.split(" ").length == 1) {
                System.out.println("Goodbye!");
                System.exit(0);
            } else if (num < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                enterNumber();
            }
            boolean isEven = false;
            boolean isBuzz = false;
            boolean isDuck = false;
            boolean isPalindromic = false;
            boolean isGapful = false;
            boolean isSpy = false;
            boolean isSquare = false;
            boolean isSunny = false;
            boolean isJumping = false;
            boolean isHappy = false;
            boolean isSad = false;
            boolean[] propertyTab = new boolean[]{isEven, !isEven, isBuzz, isDuck, isPalindromic,
                    isGapful, isSpy, isSquare, isSunny, isJumping, isHappy, isSad};

            boolean printFlag = true;

            propertyTab = checkProperty(num, propertyTab, argNotShow);

            propertiesPrint(num, propertyTab);
            enterNumber();
        } else if(inputLength == parameterNumber.TWO.getParamNb()){
            start = Long.parseLong(strInSplit[0]);
            secondParam = Long.parseLong(strInSplit[1]);
            stop = start + secondParam;

            if (start == 0) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            if (start < 0 || secondParam < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println("The second parameter should be a natural number.");
                enterNumber();
            }
            //************
            checkProperty(start, secondParam, argNotShow);
            enterNumber();
        } else if (inputLength == parameterNumber.THREE.getParamNb()) {

            start = Long.parseLong(strInSplit[0]);
            secondParam = Long.parseLong(strInSplit[1]);

            if(arg.contains(strInSplit[2].toLowerCase()) || argNot.contains(strInSplit[2].toLowerCase())) {
                //input = tmp[2].toLowerCase();
                property = arg.indexOf(strInSplit[2].replaceAll("-", "").toLowerCase());
            } else {
                System.out.printf("The property [%s] is wrong.\n", strInSplit[2].toLowerCase());
                printAvailableProperties();
                enterNumber();
            }
            stop = start + secondParam;

            if (start == 0) {
                System.out.println("Goodbye!");
                System.exit(0);
            }

            if (start < 0 || secondParam < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println("The second parameter should be a natural number.");
                enterNumber();
            }
            //************

            checkProperty(start, secondParam, argNotShow,property );
            enterNumber();
        } else if (inputLength == parameterNumber.FOUR.getParamNb()) {
            String[] tmp = strIn.split(" ");

            start = Long.parseLong(strInSplit[0]);
            secondParam = Long.parseLong(strInSplit[1]);

            if (arg.contains(strInSplit[2].replaceAll("-", "")) || arg.contains(strInSplit[3].replaceAll("-", ""))) {

                if (arg.contains(strInSplit[2].replaceAll("-", "")) == false) {
                    System.out.printf("The property [%s] is wrong.\n",strInSplit[2]);
                    printAvailableProperties();
                    enterNumber();
                } else {
                    property = arg.indexOf(strInSplit[2].replaceAll("-", ""));
                }
                if (arg.contains(strInSplit[3].replaceAll("-", ""))) {
                    property1 = arg.indexOf(strInSplit[3].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[3]);
                    printAvailableProperties();
                    enterNumber();
                }
                exclusiveProperties(strIn);
            }
            if (arg.contains(strInSplit[2].replaceAll("-", "")) == false || arg.contains(strInSplit[3].replaceAll("-", "")) == false) {
                System.out.printf("The properties [%s, %s] are wrong.\n", strInSplit[2], strInSplit[3].replaceAll("-", ""));
                printAvailableProperties();
                enterNumber();
            }

            checkProperty(start, secondParam, argNotShow,property, property1);
            enterNumber();

        } else if (inputLength == parameterNumber.FIVE.getParamNb()) {
            String[] tmp = strIn.split(" ");
            start = Long.parseLong(strInSplit[0]);
            secondParam = Long.parseLong(strInSplit[1]);

            if (arg.contains(strInSplit[2].replaceAll("-", "")) || arg.contains(strInSplit[3].replaceAll("-", "")) || arg.contains(strInSplit[4].replaceAll("-", ""))) {
                if (arg.contains(strInSplit[2].replaceAll("-", "")) == false) {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[2]);
                    printAvailableProperties();
                    enterNumber();
                } else {
                    property = arg.indexOf(strInSplit[2].replaceAll("-", ""));
                }
                if (arg.contains(strInSplit[3].replaceAll("-", ""))) {
                    property1 = arg.indexOf(strInSplit[3].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[3]);
                    printAvailableProperties();
                    enterNumber();
                }
                if (arg.contains(strInSplit[4].replaceAll("-", ""))) {
                    property2 = arg.indexOf(strInSplit[4].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[4]);
                    printAvailableProperties();
                    enterNumber();
                }

                exclusiveProperties(strIn);
                checkProperty(start, secondParam, argNotShow,property, property1, property2);
                enterNumber();
            }
            if (arg.contains(strInSplit[2].replaceAll("-", "")) == false && arg.contains(strInSplit[3].replaceAll("-", "")) == false &&  arg.contains(strInSplit[4].replaceAll("-", "")) == false) {
                System.out.printf("The properties [%s, %s, %s] are wrong.\n", strInSplit[2], strInSplit[3], strInSplit[4]);
                printAvailableProperties();
                enterNumber();
            }
        } else if (inputLength == parameterNumber.SIX.getParamNb()) {
            //String[] tmp = strIn.split(" ");
            start = Long.parseLong(strInSplit[0]);
            secondParam = Long.parseLong(strInSplit[1]);

            if (arg.contains(strInSplit[2].replaceAll("-", "")) || arg.contains(strInSplit[3].replaceAll("-", "")) ||
                    arg.contains(strInSplit[4].replaceAll("-", "")) || arg.contains(strInSplit[5].replaceAll("-", ""))) {
                if (arg.contains(strInSplit[2].replaceAll("-", "")) == false) {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[2]);
                    printAvailableProperties();
                    enterNumber();
                } else {
                    property = arg.indexOf(strInSplit[2].replaceAll("-", ""));
                }
                if (arg.contains(strInSplit[3].replaceAll("-", ""))) {
                    property1 = arg.indexOf(strInSplit[3].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[3]);
                    printAvailableProperties();
                    enterNumber();
                }
                if (arg.contains(strInSplit[4].replaceAll("-", ""))) {
                    property2 = arg.indexOf(strInSplit[4].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[4]);
                    printAvailableProperties();
                    enterNumber();
                }
                if (arg.contains(strInSplit[5].replaceAll("-", ""))) {
                    property3 = arg.indexOf(strInSplit[5].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[4]);
                    printAvailableProperties();
                }
            }
            exclusiveProperties(strIn);
            if (arg.contains(strInSplit[2].replaceAll("-", "")) == false && arg.contains(strInSplit[3].replaceAll("-", "")) == false &&
                    arg.contains(strInSplit[4].replaceAll("-", "")) == false && arg.contains(strInSplit[5].replaceAll("-", ""))) {
                System.out.printf("The properties [%s, %s, %s, %s] are wrong.\n", strInSplit[2].toUpperCase(), strInSplit[3].toUpperCase(),
                        strInSplit[4].toUpperCase(), strInSplit[5].toUpperCase());
                printAvailableProperties();
                enterNumber();
            }
            checkProperty(start, secondParam, argNotShow, property, property1, property2, property3);
            enterNumber();
        } else if (inputLength == parameterNumber.SEVEN.getParamNb()) {
            //String[] strInSplit = strIn.split(" ");
            start = Long.parseLong(strInSplit[0]);
            secondParam = Long.parseLong(strInSplit[1]);

            if (arg.contains(strInSplit[2].replaceAll("-", "")) || arg.contains(strInSplit[3].replaceAll("-", "")) ||
                    arg.contains(strInSplit[4].replaceAll("-", "")) || arg.contains(strInSplit[5].replaceAll("-", "")) || arg.contains(strInSplit[6].replaceAll("-", ""))) {
                if (arg.contains(strInSplit[2].replaceAll("-", "")) == false) {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[2].toUpperCase());
                    printAvailableProperties();
                    enterNumber();
                } else {
                    property = arg.indexOf(strInSplit[2].replaceAll("-", ""));
                }
                if (arg.contains(strInSplit[3])) {
                    property1 = arg.indexOf(strInSplit[3].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[3].toUpperCase());
                    printAvailableProperties();
                    enterNumber();
                }
                if (arg.contains(strInSplit[4].replaceAll("-", ""))) {
                    property2 = arg.indexOf(strInSplit[4].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[4].toUpperCase());
                    printAvailableProperties();
                    enterNumber();
                }
                if (arg.contains(strInSplit[5].replaceAll("-", ""))) {
                    property3 = arg.indexOf(strInSplit[5].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[5].toUpperCase());
                    printAvailableProperties();
                    enterNumber();
                }
                if (arg.contains(strInSplit[6].replaceAll("-", ""))) {
                    property4 = arg.indexOf(strInSplit[6].replaceAll("-", ""));
                } else {
                    System.out.printf("The property [%s] is wrong.\n", strInSplit[6].toUpperCase());
                    printAvailableProperties();
                    enterNumber();
                }
            }
            exclusiveProperties(strIn);
            if (arg.contains(strInSplit[2].replaceAll("-", "")) == false && arg.contains(strInSplit[3].replaceAll("-", "")) == false &&
                    arg.contains(strInSplit[4].replaceAll("-", "")) == false && arg.contains(strInSplit[5].replaceAll("-", "")) && arg.contains(strInSplit[6].replaceAll("-", ""))) {
                System.out.printf("The properties [%s, %s, %s, %s, %s] are wrong.\n", strInSplit[2].toUpperCase(), strInSplit[3].toUpperCase(),
                        strInSplit[4].toUpperCase().replaceAll("-", ""), strInSplit[5].toUpperCase(), strInSplit[6].toUpperCase());
                printAvailableProperties();
                enterNumber();
            }
            checkProperty(start, secondParam, argNotShow, property, property1, property2, property3, property4);
            enterNumber();
        }
    }
    public static void  printAvailableProperties()
    {
        System.out.println("Available properties:\n" +
                "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
    }
    public static void exclusiveProperties(String input) {
        //System.out.println("input = " + input);
        if (input.contains("-odd") && input.contains("-even")) {
            System.out.println("The request contains mutually exclusive properties: [-ODD, -EVEN]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        }  else if (input.contains("-even") && input.contains("even") && input.split("even").length == 2) {
            System.out.println("The request contains mutually exclusive properties: [EVEN, -EVEN]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        }  else if (input.contains("sunny") && input.contains("square") && (!input.contains("-sunny") || !input.contains("-square"))) {
            System.out.println("The request contains mutually exclusive properties: [SUNNY, SQUARE]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("-duck") && input.split("duck").length >= 3) {
            System.out.println("The request contains mutually exclusive properties: [DUCK, -DUCK]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("spy") && input.contains("duck") && !input.contains("-duck")){
            System.out.println("The request contains mutually exclusive properties: [SPY, DUCK]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("-happy") && input.contains("-sad")) {
            System.out.println("The request contains mutually exclusive properties: [-HAPPY, -SAD]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("happy") && input.contains("sad")) {
            System.out.println("The request constains mutually exclusive properties: [HAPPY, SAD]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("gapful") && input.contains("-gapful") && input.split("gapful").length == 2) {
            System.out.println("The request constains mutually exclusive properties: [GAPFUL, -GAPFUL]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("odd") && input.contains("even")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, EVEN]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        } else if (input.contains("-odd") && input.contains("-odd")) {
            System.out.println("The request contains mutually exclusive properties: [ODD, -ODD]");
            System.out.println("There are no numbers with these properties.");
            enterNumber();
        }
    }
    public static boolean[] checkNotShowArg(String strIn ) {
        boolean[] out = new boolean[12];
        Arrays.fill(out, true);
        List<String> argNotShow = null;
        argNotShow = Arrays.asList(new String[]{"-even", "-odd", "-buzz", "-duck", "-palindromic", "-gapful",
                "-spy", "-square", "-sunny", "-jumping", "-happy", "-sad"});
        String[] tmp = strIn.split(" ");
        int indx;
        for (int i = 2; i < tmp.length; i++) {
            indx = argNotShow.indexOf(tmp[i]);
            //System.out.println("tmp[i] = "+tmp[i]+"indx = "+indx);
            if (indx > -1) {
                out[indx] = false;
            }
        }
        return out;
    }
    public static void checkProperty(long start, long secondParam, boolean[] argNotShow ,int... property ) {
        long count = 0;
        boolean isEven = false;
        boolean isBuzz = false;
        boolean isDuck = false;
        boolean isPalindromic = false;
        boolean isGapful = false;
        boolean isSpy = false;
        boolean isSquare = false;
        boolean isSunny = false;
        boolean isJumping = false;
        boolean isHappy = false;
        boolean isSad = false;
        for (long i = start; ; i++) {
            boolean[] propertyTab = new boolean[]{isEven, !isEven, isBuzz, isDuck, isPalindromic,
                    isGapful, isSpy, isSquare, isSunny, isJumping, isHappy, isSad};
            boolean printFlag = false;
            //System.out.println("sprawdzam i = " + i);
            propertyTab = checkProperty(i, propertyTab, argNotShow);
            if (chosenProperty(propertyTab, argNotShow, property) && count < secondParam) {
                count++;
            } else if (secondParam <= count) {
                break;
            }
            propertiesNumbersPrint(i, printFlag, chosenProperty(propertyTab, argNotShow, property), propertyTab,argNotShow );
        }
    }
    public static boolean chosenProperty(boolean[] tab, boolean[] argNotShow,int... property) {
        boolean out = true;
       // System.out.println("tab =" + Arrays.toString(tab));
       // System.out.println("arg =" + Arrays.toString(argNotShow));
        for (int indx: property) {
            out  = out && tab[indx];
        }
        return out;
    }
    public static boolean[] checkProperty(long i, boolean[] tabProperty, boolean[] argNotShow) {
        tabProperty[propertyIndx.isEven.paramIndx] = (argNotShow[propertyIndx.isEven.paramIndx] == true)
                ? evenCheck(i) : (evenCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isOdd.paramIndx] = (argNotShow[propertyIndx.isOdd.paramIndx] == true)
        ? oddCheck(i) : (oddCheck(i) == false ? true : false) ;
        tabProperty[propertyIndx.isBuzz.paramIndx] = argNotShow[propertyIndx.isBuzz.paramIndx] == true
                ? buzzCheck(i) : (buzzCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isDuck.paramIndx] = (argNotShow[propertyIndx.isDuck.paramIndx] == true )
                ? duckCheck(i) : (duckCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isPalindromic.paramIndx] = (argNotShow[propertyIndx.isPalindromic.paramIndx] == true)
                ? palindromeCheck(i) : (palindromeCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isGapful.paramIndx] =  (argNotShow[propertyIndx.isGapful.paramIndx] == true)
                ? gapfulCheck(i) : (gapfulCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isSpy.paramIndx] = (argNotShow[propertyIndx.isSpy.paramIndx] == true)
                ? spyCheck(i) : (spyCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isSunny.paramIndx] = (argNotShow[propertyIndx.isSunny.paramIndx] == true)
                ? sunnyCheck(i) : (sunnyCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isSquare.paramIndx] =  (argNotShow[propertyIndx.isSquare.paramIndx] == true)
                ? squareCheck(i) : (squareCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isJumping.paramIndx] = (argNotShow[propertyIndx.isJumping.paramIndx] == true)
                ? jumpingCheck(i) : (jumpingCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isHappy.paramIndx] = (argNotShow[propertyIndx.isHappy.paramIndx] == true)
                ? happyCheck(i) : (happyCheck(i) == false ? true : false);
        tabProperty[propertyIndx.isSad.paramIndx] = (argNotShow[propertyIndx.isSad.paramIndx] == true)
                ? sadCheck(i) : (sadCheck(i) == false ? true : false);
        return tabProperty;
    }
    public static long numSquareSum(long in) {
        long squareSum = 0;
        while (in != 0) {
            squareSum += (in % 10) * (in % 10);
            in /= 10;
        }
        return squareSum;
    }
    public static boolean happyCheck(long num) {
        long slow, fast;
        slow = fast = num;
        do {
            slow = numSquareSum(slow);
            fast = numSquareSum(numSquareSum(fast));
        } while (slow != fast);
        return (slow == 1);
    }
    public static boolean sadCheck(long num) {
        long slow, fast;
        slow = fast = num;
        do {
            slow = numSquareSum(slow);
            fast = numSquareSum(numSquareSum(fast));
        } while (slow != fast);
        return (slow != 1);
    }
    public static boolean jumpingCheck(long num) {
        StringBuilder str = new StringBuilder(Long.toString(num));
        int delta = 0;
        int product = 1;
        String st = null;
        boolean isJumping = true;
        for (int i = 0; i < str.length() - 1; i++) {
            delta = Integer.parseInt(Character.toString(str.charAt(i))) -
                    Integer.parseInt(Character.toString(str.charAt(i + 1)));
            //System.out.println(delta);
            if (Math.abs(delta) == 1) {
                isJumping = true;
            } else {
                isJumping = false;
                return isJumping;
            }
        }
        return isJumping;
    }
    public static boolean spyCheck(long num) {
        StringBuilder str = new StringBuilder(Long.toString(num));
        int sum = 0;
        int product = 1;
        String st = null;
        boolean isSpy;
        for (int i = 0; i < str.length(); i++) {
            sum += Integer.parseInt(Character.toString(str.charAt(i)));
            product *= Integer.parseInt(Character.toString(str.charAt(i)));
        }
        if (sum == product) {
            isSpy = true;
        } else {
            isSpy = false;
        }
        return isSpy;
    }
    public static boolean gapfulCheck(long num) {
        String str = Long.toString(num);
        boolean isGapful;
        if (str.length() < 3) {
            isGapful = false;
            return isGapful;
        }
        long nb = Integer.parseInt(str.substring(0,1) + str.substring(str.length()-1,str.length()));
        if (num % nb == 0) {
            isGapful = true;
        } else {
            isGapful = false;
        }
        return isGapful;
    }
    public static boolean evenCheck(long num) {
        boolean isEven;
        if (num % 2 == 0) {
            isEven = true;
        } else {
            isEven = false;
        }
        //System.out.println("isEven = " + isEven);
        return isEven;
    }
    public static boolean oddCheck(long num) {
        boolean isEven;
        if (num % 2 == 0) {
            isEven = true;
        } else {
            isEven = false;
        }
        //System.out.println("isEven = " + isEven);
        return !isEven;
    }
    public static boolean squareCheck(long num) {
        boolean isSquare;
        int tmp = (int)Math.sqrt(num);

        int tmp2 = tmp * tmp;
        if (tmp2 == num) {
            isSquare = true;
        } else {
            isSquare = false;
        }
        return isSquare;
    }
    public static boolean sunnyCheck(long num) {
        boolean isSunny;
        long tmp2 = num + 1;
        int tmp = (int)Math.sqrt(tmp2);

        if (tmp2 == tmp * tmp) {
            isSunny = true;
        } else {
            isSunny = false;
        }
        return isSunny;
    }
    public static boolean buzzCheck(long num) {
        boolean isBuzz;
        if (num % 7 == 0 && num % 10 == 7) {
            isBuzz = true;
        } else if (num % 7 == 0) {
            isBuzz = true;
        } else if (num % 10 == 7) {
            isBuzz = true;
        } else {
            isBuzz = false;
        }
        return isBuzz;
    }

    public static boolean duckCheck(long num) {
        boolean isDuck;
        if (String.valueOf(num).contains("0")) {
            isDuck = true;
        } else {
            isDuck = false;
        }
        return isDuck;
    }

    public static boolean palindromeCheck(long num) {
        boolean isPalindromic;
        long r;
        long sum = 0;
        long temp = num;

        while (num > 0) {
            r = num % 10;
            sum = (sum * 10) + r;
            num = num / 10;
        }

        if (temp == sum) {
            isPalindromic = true;
        } else {
            isPalindromic = false;
        }
        return isPalindromic;
    }

    public static void propertiesNumbersPrint(long num, boolean printFlag, boolean property, boolean[] propertyTab,
                                              boolean[] argNotShow) {
        if (property) {
            System.out.printf("%d is ", num);
            if (propertyTab[propertyIndx.isBuzz.paramIndx] && argNotShow[propertyIndx.isBuzz.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.printf("buzz");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isDuck.paramIndx] && argNotShow[propertyIndx.isDuck.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.printf("duck");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isPalindromic.paramIndx] && argNotShow[propertyIndx.isPalindromic.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.printf("palindromic");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isGapful.paramIndx] && argNotShow[propertyIndx.isGapful.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("gapful");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isSpy.paramIndx] && argNotShow[propertyIndx.isSpy.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("spy");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isSquare.paramIndx] && argNotShow[propertyIndx.isSquare.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("square");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isSunny.paramIndx] && argNotShow[propertyIndx.isSunny.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("sunny");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isJumping.paramIndx] && argNotShow[propertyIndx.isJumping.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("jumping");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isHappy.paramIndx] && argNotShow[propertyIndx.isHappy.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("happy");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isSad.paramIndx] && argNotShow[propertyIndx.isSad.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("sad");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isEven.paramIndx] && argNotShow[propertyIndx.isEven.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.print("even");
                printFlag = true;
            }
            if (propertyTab[propertyIndx.isOdd.paramIndx] && argNotShow[propertyIndx.isOdd.paramIndx]) {
                System.out.print(printFlag == false ? "" : ", ");
                System.out.printf("odd");
                printFlag = true;
            }
            System.out.println();
        }
    }

    public static void propertiesPrint(long num, boolean[] tabProperty) {
        System.out.printf("Properties of %d\n", num);
        System.out.printf("buzz: %b\n", tabProperty[propertyIndx.isBuzz.paramIndx]);
        System.out.printf("duck: %b\n", tabProperty[propertyIndx.isDuck.paramIndx]);
        System.out.printf("palindromic: %b\n", tabProperty[propertyIndx.isPalindromic.paramIndx]);
        System.out.printf("gapful: %b\n", tabProperty[propertyIndx.isGapful.paramIndx]);
        System.out.printf("spy: %b\n", tabProperty[propertyIndx.isSpy.paramIndx]);
        System.out.printf("square: %b\n", tabProperty[propertyIndx.isSquare.paramIndx]);
        System.out.printf("sunny: %b\n", tabProperty[propertyIndx.isSunny.paramIndx]);
        System.out.printf("jumping: %b\n", tabProperty[propertyIndx.isJumping.paramIndx]);
        System.out.printf("happy: %b\n", tabProperty[propertyIndx.isHappy.paramIndx]);
        System.out.printf("sad: %b\n", tabProperty[propertyIndx.isSad.paramIndx]);
        System.out.printf("even: %b\n", tabProperty[propertyIndx.isEven.paramIndx]);
        System.out.printf("odd: %b\n", tabProperty[propertyIndx.isOdd.paramIndx]);
    }
}