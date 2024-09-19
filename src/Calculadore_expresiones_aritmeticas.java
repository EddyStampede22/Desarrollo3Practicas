import java.util.*;

public class Calculadore_expresiones_aritmeticas {
    public static void main(String[] args) {
        String inputString;

        Scanner keyb = new Scanner(System.in);

        while (true) {
            System.out.println("Introduce una expresion en notacion infija para calcular su resultado: ");
            System.out.print("> ");
            inputString = keyb.nextLine();
            if (inputString.equals("quit") || inputString.equals("QUIT")) {
                break;
            }
            List<String> tokens = getTokens(inputString);
            List<String> postfix = Calculadore_expresiones_aritmeticas.toPostfix(tokens);
            System.out.println("notación posfija: "+postfix);
            List<String> lista = new ArrayList<>(Collections.emptyList());
            List<String> lista2 = new ArrayList<>(Collections.emptyList());
            double operacion=0d;
            for (String n:postfix) {
                if (isOperand(n)){
                    lista.add(n);
                }
                if (isOperator(n)){
                    switch (n){
                        case "+":
                            if(lista.isEmpty()){
                                operacion=0;
                                for (String l:lista2){
                                    System.out.println(l);
                                    operacion+=Double.parseDouble(l);
                                }
                            }else {
                                for(String m:lista){
                                    operacion+=Double.parseDouble(m);
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                            }
                            break;
                        case "-":
                            if(lista.isEmpty()){
                                operacion=0;
                                for (String l:lista2){
                                    System.out.println(l);
                                    operacion-=Double.parseDouble(l);
                                }
                            }else {
                                for(String m:lista){
                                    operacion-=Double.parseDouble(m);
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                            }
                            break;
                        case "*":
                            if(lista.isEmpty()){
                                operacion=0;
                                for(String m:lista2){
                                    System.out.println(m);
                                    if(operacion==0){
                                        operacion=Double.parseDouble(m);
                                    }
                                    else {
                                        operacion*=Double.parseDouble(m);
                                    }

                                }
                            }else if (lista.size()==1){
                                for(String m:lista) {
                                    operacion=Double.parseDouble(m);
                                    for(String l:lista2){
                                        operacion*=Double.parseDouble(l);
                                    }
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                            }else {

                                for(String m:lista) {
                                    if(operacion==0){
                                        operacion=Double.parseDouble(m);
                                    }
                                    else {
                                        operacion*=Double.parseDouble(m);
                                    }
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                                operacion=0;

                            }
                            break;
                        case "/":
                            if(lista.isEmpty()){
                                operacion=0;
                                for(String m:lista2){
                                    if(operacion==0){
                                        operacion=Double.parseDouble(m);
                                    }
                                    else {
                                        operacion/=Double.parseDouble(m);
                                    }

                                }
                            }else if (lista.size()==1){
                                for(String m:lista) {
                                    operacion=Double.parseDouble(m);
                                    if(!Objects.equals(m, "0")){
                                        for(String l:lista2){
                                            System.out.println(l);
                                            System.out.println(operacion);
                                            operacion=Double.parseDouble(l)/operacion;
                                        }
                                    }
                                    else {
                                        System.out.println("no se puede divir entre cero");
                                    }
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                            }else {
                                for(String m:lista) {
                                    if(operacion==0){
                                        operacion=Double.parseDouble(m);
                                    }
                                    else {
                                        if(!Objects.equals(m, "0")){
                                            operacion=operacion/Double.parseDouble(m);
                                        }
                                        else {
                                            System.out.println("no se puede divir entre cero");
                                        }
                                    }
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                                operacion=0;

                            }
                            break;
                        case "^":
                            if(lista.isEmpty()){
                                operacion=0;
                                for(String m:lista2){
                                    System.out.println(m);
                                    if(operacion==0){
                                        operacion=Double.parseDouble(m);
                                    }
                                    else {
                                        operacion=Math.pow(operacion,Double.parseDouble(m));
                                    }
                                }
                            }else if (lista.size()==1){
                                for(String m:lista) {
                                    operacion=Double.parseDouble(m);
                                    for(String l:lista2){
                                        operacion=Math.pow(Double.parseDouble(l),operacion);
                                    }
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                            }else {
                                for(String m:lista) {
                                    if(operacion==0){
                                        operacion=Double.parseDouble(m);
                                    }
                                    else {
                                        operacion=Math.pow(operacion,Double.parseDouble(m));
                                    }
                                }
                                lista.clear();
                                lista2.add(String.valueOf(operacion));
                                operacion=0;

                            }
                            break;
                    }

                }
            }
            if(lista2.size()==1){
                System.out.println("Resultado de "+inputString+" = "+lista2.getFirst());
            }
            else {
                System.out.println("Resultado de "+inputString+" = "+operacion);
            }
        }
    }

    public static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") ||
                token.equals("*") || token.equals("/") || token.equals("^");

    }

    public static ArrayList<String> toPostfix(List<String> tokens) {

        Stack<String> stack = new Stack<>();
        ArrayList<String> output = new ArrayList<>();
        String t;

        for (String token : tokens) {
            if (token.equalsIgnoreCase("(")) {
                stack.push(token);
            } else if (token.equalsIgnoreCase(")")) {
                while (!(t = stack.pop()).equals("(")) {
                    System.out.println(token);
                    output.add(t);
                }
            } else if (isOperand(token)) {
                output.add(token);
            }
            if (isOperator(token)) {
                if ( stack.isEmpty() ) {
                    stack.push(token);
                } else {
                    int r1 = Calculadore_expresiones_aritmeticas.getPrec(token);
                    int r2 = Calculadore_expresiones_aritmeticas.getPrec( stack.peek() );

                    if( r1 > r2 ){
                        stack.push(token);
                    } else {
                        output.add(stack.pop());
                        stack.push(token);
                    }

                }
            }

        }
        String token;
        while ( !stack.isEmpty() ) {
            token = stack.pop();
            output.add(token);
        }
        return output;
    }

    public static boolean isOperand(String token) {
        boolean result = true;
        try {
            Double.parseDouble(token);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public static List<String> getTokens(String input) {

        StringTokenizer st = new StringTokenizer(input," ()+-*/^",
                true);

        ArrayList<String> tokenList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if(token.trim().length() == 0 ) {
                continue;
            }
            tokenList.add(token);
        }
        return tokenList;
    }

    public static int getPrec(String token) {
        String t = token.toLowerCase();
        int rank = 0;

        switch (t) {
            case "^":
                rank = 3;
                break;
            case "*":
            case "/":
                rank = 2;
                break;
            case "+":
            case "-":
                rank = 1;
                break;
        }

        return rank;
    }
}
