import java.util.ArrayList;
import java.util.Collections;

public class MonkeyBarrel {
    ArrayList<Monkey> monkeys;
    int MOD = 1;

    public MonkeyBarrel(ArrayList<Monkey> monkeys){
        this.monkeys = monkeys;
        for(Monkey monkey: monkeys){
            MOD *= monkey.testDenominator;
        }
    }

    public void process(int times){
        for(int i = 0 ; i < times ; i++){
            for(int monkey = 0 ; monkey < monkeys.size() ; monkey++){
                Monkey cur = monkeys.get(monkey);
                cur.inspects+=cur.items.size();
                while(monkeys.get(monkey).items.size()>0){
                    long item = cur.items.remove(0);
                    item = cur.doOperation(item);
                    item /= 3;
                    if(cur.test(item)){
                        monkeys.get(cur.trueDestination).items.add(item);
                    }else{
                        monkeys.get(cur.falseDestination).items.add(item);
                    }
                }
            }
        }
    }

    //all denoms are primes
    //so we can take their LCM and mod that to find for these giant numbers
    //since they are all prime they fall in the same 'bucket'
    public void processNew(int times){
        for(int i = 0 ; i < times ; i++){
            for(int monkey = 0 ; monkey < monkeys.size() ; monkey++){
                Monkey cur = monkeys.get(monkey);
                cur.inspects+=cur.items.size();
                while(cur.items.size()>0){
                    long item = cur.items.remove(0);
                    item = cur.doOperation(item) % MOD;
                    if(cur.test(item)){
                        monkeys.get(cur.trueDestination).items.add(item);
                    }else{
                        monkeys.get(cur.falseDestination).items.add(item);
                    }
                }
            }
        }
    }

    public long monkeyBusiness(){
        ArrayList<Long> counts = new ArrayList<>();
        for(Monkey monkey: monkeys){
            counts.add((long)monkey.inspects);
        }
        Collections.sort(counts);
        return (counts.get(counts.size()-1) * counts.get(counts.size()-2));
    }
}
