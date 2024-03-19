package ro.tuc.bussineslogic;

import ro.tuc.datamodels.Polynomial;

import java.util.*;

public class Operation {
    public static Polynomial add(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial("");
        Set<Map.Entry<Double, Double>> pol1 = polynomial1.getMap().descendingMap().entrySet();
        Set<Map.Entry<Double, Double>> pol2 = polynomial2.getMap().descendingMap().entrySet();
        for (Map.Entry<Double, Double> entry : pol1){
            if(polynomial2.getMap().containsKey(entry.getKey())) {
                result.getMap().put(entry.getKey(),entry.getValue()+polynomial2.getMap().get(entry.getKey()));
            }
            else{
                result.getMap().put(entry.getKey(),entry.getValue());
            }
        }
        for(Map.Entry<Double,Double> entry: pol2){
            if(!polynomial1.getMap().containsKey(entry.getKey())){
                result.getMap().put(entry.getKey(),entry.getValue());
            }
        }
        Operation.removeZerosAux(result);
        return result;
    }

    public static Polynomial substarct(Polynomial polynomial1, Polynomial polynomial2) {
        Polynomial result = new Polynomial("0");
        Set<Map.Entry<Double, Double>> pol1 = polynomial1.getMap().descendingMap().entrySet();
        Set<Map.Entry<Double, Double>> pol2 = polynomial2.getMap().descendingMap().entrySet();
        for (Map.Entry<Double, Double> entry : pol1){
            if(polynomial2.getMap().containsKey(entry.getKey())) {
                result.getMap().put(entry.getKey(),entry.getValue()-polynomial2.getMap().get(entry.getKey()));
            }
            else{
                result.getMap().put(entry.getKey(),entry.getValue());
            }
        }
        for(Map.Entry<Double,Double> entry: pol2){
            if(!polynomial1.getMap().containsKey(entry.getKey())){
                result.getMap().put(entry.getKey(),-entry.getValue());
            }
        }
        Operation.removeZerosAux(result);
        return result;
    }

    public static Polynomial multiplication(Polynomial polynomial1,Polynomial polynomial2){
        Polynomial result=new Polynomial("0");
        Set<Map.Entry<Double,Double>> pol1=polynomial1.getMap().descendingMap().entrySet();
        Set<Map.Entry<Double,Double>> pol2=polynomial2.getMap().descendingMap().entrySet();
        for(Map.Entry<Double,Double> entry1: pol1){
            for(Map.Entry<Double,Double> entry2: pol2){
                double pow=entry1.getKey()+entry2.getKey();
                double coef=entry1.getValue()*entry2.getValue();
                if(result.getMap().containsKey(entry1.getKey()+entry2.getKey())){
                    result.getMap().replace(pow,coef+result.getMap().get(pow));
                }
                else{
                    result.getMap().put(pow,coef);
                }
            }
        }
        Operation.removeZerosAux(result);
        return result;
    }

    public static Polynomial derivative(Polynomial polynomial){
        Polynomial result=new Polynomial("0");
        Set<Map.Entry<Double,Double>> pol=polynomial.getMap().descendingMap().entrySet();
        for(Map.Entry<Double,Double> entry:pol){
            double pow=entry.getKey();
            double coef=entry.getValue();
            if(pow==0)
                continue;
            else
                result.getMap().put(pow-1,coef*pow);
        }
        Operation.removeZerosAux(result);
        return result;
    }

    public static Polynomial integral(Polynomial polynomial){
        Polynomial result=new Polynomial("0");
        Set<Map.Entry<Double,Double>> pol=polynomial.getMap().descendingMap().entrySet();
        for(Map.Entry<Double,Double> entry:pol){
            double pow=entry.getKey();
            double coef=entry.getValue();
            result.getMap().put(pow+1,coef/(pow+1));
        }
        Operation.removeZerosAux(result);
        return result;
    }

    public static Polynomial[] division(Polynomial polynomial1,Polynomial polynomial2){
        Polynomial q=new Polynomial("0");
        Polynomial r=polynomial1;
        Polynomial d=polynomial2;
        if(r.getMap().lastKey()<d.getMap().lastKey()){
            Polynomial aux=r;
            r=d;
            d=aux;
        }
        while(!r.getMap().isEmpty() && (r.getMap().lastKey()>=d.getMap().lastKey())){
            double coef=r.getMap().lastEntry().getValue()/d.getMap().lastEntry().getValue();
            double pow=r.getMap().lastKey()-d.getMap().lastKey();
            q.getMap().put(pow,coef);
            Polynomial auxPol=new Polynomial("");
            auxPol.getMap().put(pow,coef);
            r= Operation.substarct(r, Operation.multiplication(auxPol,d));
            Operation.removeZerosAux(r);
        }
        Polynomial[] res=new Polynomial[2];
        res[0]=q;
        res[1]=r;
        return res;
    }

    public static void removeZerosAux(Polynomial polynomial){
        Set<Map.Entry<Double,Double>> entries=polynomial.getMap().entrySet();
        List<Double> zeros=new ArrayList<>();
        for(Map.Entry<Double,Double> entry:entries){
            if(entry.getValue()==0)
                zeros.add(entry.getKey());
        }
        for(Double i:zeros){
            polynomial.getMap().remove(i);
        }
    }
}