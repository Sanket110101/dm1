import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Correlation {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new FileReader("data8.txt"));
        ArrayList<ArrayList<String>> data=new ArrayList<>();
        int n=br.readLine().trim().split(",").length;
        while(true){
            String[] str=new String[n];
            try{
                str=br.readLine().trim().split(",");
            }
            catch (Exception e){
                break;
            }
            ArrayList<String>temp=new ArrayList<>();
            for(String s:str)
                 temp.add(s);
            data.add(temp);
        }
        for(int i=0;i<data.size()-1;i++){
            for(int j=i+1;j< data.size();j++){
                System.out.println("Correlation between "+data.get(i).get(0)+"and "+data.get(j).get(0)+" is:"+findCorrelation(i,j,data));

            }
        }
    }

    private static double findCorrelation(int i, int j, ArrayList<ArrayList<String>> data) {

        double yesinI=0.0,yesinJ=0.0,yesinCO=0.0;
        for(int k=0;k<data.get(i).size();k++){
            int flg1=0,flg2=0;
            if (data.get(i).get(k).equalsIgnoreCase("yes")) {
                yesinI++;
                flg1=1;
            }
            if(data.get(j).get(k).equalsIgnoreCase("yes")) {
                yesinJ++;
                flg2=1;
            }
            if(flg1==1&&flg2==1)
                yesinCO++;

        }
        int length = data.get(0).size();
       //System.out.println(""+yesinCO+" "+yesinI+" "+yesinJ);
        return ((1.0 * yesinCO *length)/(yesinI*yesinJ));

    }
}