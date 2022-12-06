import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;
class Bayes {
    static HashMap<String, HashMap<String, double[]>> map = new HashMap<>();
    static double Mp=0, NMP=0;
    static double posR =0, negR = 0;
    static String[] clm = {};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("12Naive.csv"));
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        clm = br.readLine().trim().split(",");
        int n = clm.length;

        while (true) {
            String str[] = new String[n];
            try {
                str = br.readLine().trim().split(",");
            } catch (NullPointerException e) {
                break;
            }
            ArrayList<String> temp = new ArrayList<>();
            for (String s : str)
                temp.add(s);
            data.add(temp);
        }
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get(4).equalsIgnoreCase("Mammal"))
                posR++;
            else
                negR++;
        }
        double totRecord = data.size();
        Mp = posR/totRecord;
        NMP = negR/totRecord;
        System.out.println(Mp +" " + NMP);
        for(int i=0;i<data.get(0).size()-1;i++){
            findAll(i,data);
        }
        for(Map.Entry<String, HashMap<String, double[]>> x : map.entrySet()) {
            System.out.println(x.getKey());
            for(Map.Entry<String, double[]> y : x.getValue().entrySet()){
                System.out.println(y.getKey() + " " + Arrays.toString(y.getValue()));
            }
        }
        br.close();
       // br = new BufferedReader(new FileReader("Q1.csv"));
        ArrayList<ArrayList<String>> query = new ArrayList<>();
        clm = br.readLine().trim().split(",");
        n = clm.length;

        while (true) {
            String str[] = new String[n];
            try {
                str = br.readLine().trim().split(",");
            } catch (NullPointerException e) {
                break;
            }
            ArrayList<String> temp = new ArrayList<>();
            for (String s : str)
                temp.add(s);
            query.add(temp);
        }

        double ans = 0;
        System.out.println(map.get("Give Birth"));
        for(int i = 0; i < query.size(); i++) {
            double mamprob = 1, nmamprop = 1 ;
            int j = 0;
            for(String x: query.get(j)){
                System.out.println(x+ " " +map.get(clm[j])+ "Non Mammal");
                mamprob *= map.get(clm[j]).get(x)[0];
                nmamprop*= map.get(clm[j]).get(x)[1];
                j++;
            }
            mamprob *= Mp;
            nmamprop *= NMP;

            System.out.println( i + 1+ ".Mammal Prob: " + mamprob+ " \nNonMammal Prob: " + nmamprop);
        }

    }

    private static void findAll(int i, ArrayList<ArrayList<String>> data) {
        Set<String> attribute=new HashSet<>();
        for(ArrayList<String>x:data)
           attribute.add(x.get(i));
        HashMap<String,double[]>total=new HashMap<>();
        for(String x:attribute){
            total.put(x,new double[2]);
        }
        for(ArrayList<String> x:data){
            if(x.get(4).equalsIgnoreCase(("Mammal")))
             total.get(x.get(i))[0]++;
            else
             total.get(x.get(i))[1]++;

        }
        for(Map.Entry<String,double[]> x:total.entrySet()){
            double MammalProb = x.getValue()[0]/posR;
            double NMammalPorb = x.getValue()[1]/negR;
            total.put(x.getKey(), new double[]{MammalProb, NMammalPorb});
            
        }
        map.put(clm[i], total);
    }
}