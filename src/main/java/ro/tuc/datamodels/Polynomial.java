package ro.tuc.datamodels;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private TreeMap<Double, Double> map;

    public Polynomial(String expression){
        String regex="([+-]?\\d*)[a-zA-Z](\\^(\\d+))?|([+-]?\\d+)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(expression);
        TreeMap<Double, Double> map = new TreeMap<>();
        while (matcher.find()) {
            Double coef = null, pow = null;
            if (matcher.group(4) != null) {
                coef = Double.valueOf(matcher.group(4));
                pow = 0.0;
            }
            else {
                if (matcher.group(1) != null) {
                    if (matcher.group(1).isEmpty()) {
                        coef = 1.0;
                    } else {
                        if (matcher.group(1).equals("+")) {
                            coef = 1.0;
                        } else if (matcher.group(1).equals("-")) {
                            coef = -1.0;
                        } else
                            coef = Double.valueOf(matcher.group(1));
                    }
                }
                if(matcher.group(3)!=null) {
                    if (!matcher.group(3).isEmpty()) {
                        if (matcher.group(3).isEmpty()) {
                            pow = 1.0;
                        } else
                            pow = Double.valueOf(matcher.group(3));
                    }
                }
                else{
                    pow=1.0;
                }
            }
            if (map.containsKey(pow)) {
                map.replace(pow, map.get(pow) + coef);
            } else {
                map.put(pow, coef);
            }
        }
        this.map=map;
    }

    public TreeMap<Double, Double> getMap() {
        return map;
    }

    private String ParseMonomial(String power){
        String result=null;
        if(power.equals("0"))
            result="";
        else if(power.equals("1")){
            result="x";
        }
        else
            result="x^"+power;
        return result;
    }

    private String IntOrDouble(double coef){
        if(coef==(int)coef){
           return String.valueOf((int)coef);
        }
        else {
            return String.valueOf(coef);
        }
    }
    @Override
    public String toString() {
        String result="";
        Set<Map.Entry<Double,Double>> entries=this.map.descendingMap().entrySet();
        if(entries.isEmpty())
            result="0";
        else{
            for(Map.Entry<Double,Double> entry:entries){
                Double coef,pow;
                pow=entry.getKey();
                coef=entry.getValue();
                String aux="";
                if(entry.getKey()==this.map.lastKey())
                    aux="";
                else {
                    if (coef > 0) aux = " +";
                    else aux=" ";
                }
                if(coef!=0) {
                    if(coef==1 || coef==-1){
                        if(coef==1)
                            result=result+aux+ParseMonomial(IntOrDouble(pow));
                        else result=result+aux+"-"+ParseMonomial(IntOrDouble(pow));
                        if (pow==0){
                            result=result+(int)Math.abs(coef);
                        }
                    }
                    else {
                        if (entry.getKey().equals(map.lastKey())) {
                            result = IntOrDouble(coef) + ParseMonomial(IntOrDouble(pow));
                        } else {
                            if (coef > 0)
                                result = result + aux + IntOrDouble(coef) + ParseMonomial(IntOrDouble(pow));
                            else result = result + aux + IntOrDouble(coef) + ParseMonomial(IntOrDouble(pow));
                        }
                    }
                }
            }
        }
        return result;
    }
    }
