import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Monkey {
    ArrayList<Long> items;
    int testDenominator;
    int trueDestination;
    int falseDestination;
    String worryOperator;
    String worryOperationValue;
    int inspects;

    public Monkey(ArrayList<Long> items, int testDenominator, int falseDestination, int trueDestination, String worryOperationValue, String worryOperator){
        this.items = items;
        this.testDenominator = testDenominator;
        this.trueDestination = trueDestination;
        this.falseDestination = falseDestination;
        this.worryOperator = worryOperator;
        this.worryOperationValue = worryOperationValue;
        this.inspects = 0;
    }

    public long doOperation(long val){
        long num = worryOperationValue.equals("old") ? val : Integer.parseInt(worryOperationValue);
        if(worryOperator.equals("*")){
            return val * num;
        }
        return val + num;
    }

    public boolean test(long val){
        return val % testDenominator == 0;
    }

    public String toString(){
        StringBuilder monkey = new StringBuilder();
        monkey.append("items: \n ");
        for(long item : items){
            monkey.append(item + " ");
        }
        monkey.append( "\ntest denominator: " + testDenominator +
                        "\ntrue destination: " + trueDestination +
                        "\nfalse destination: " + falseDestination +
                        "\nworryOperator: " + worryOperator +
                        "\nworry operator value : " + worryOperationValue +
                        "\nInspects: " + inspects);
        monkey.append("\n");
        return monkey.toString();
    }

}
