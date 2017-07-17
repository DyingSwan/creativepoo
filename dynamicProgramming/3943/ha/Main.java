


import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {

    public static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    public static int nums[] = new int[100001];
    public static void main(String argsp[])throws Exception{
    for(int i =0; i< 100001; i++){
        nums[i] = -1;
    }
    nums[1]=1;
    int testCase = Integer.parseInt(in.readLine());
    
    while(testCase--!=0){
        int init = Integer.parseInt(in.readLine());
        HSN(init,init);
        out.write((int)nums[init]+"\n");
        
    }
    
    in.close();
    out.close();
    }
    public static int HSN(int init, int index){
        if(init ==1)return 1;
        if(canDP(init)&&nums[init]==-1){
            //계산
            if(init%2==0){
                int tmp = HSN(init/2,index);
                if(tmp>init){
                    nums[index]=tmp;
                    return tmp;
                }else{ 
                    nums[index]=init;
                    return init;
                    }
            }else{
                int tmp =HSN(init*3 +1,index);
                nums[index]=tmp;
                return tmp;
            }
        }
        else if(canDP(init)&&nums[init]!=-1){
            return nums[init];
        }else{
            if(init%2==0){
                int tmp = HSN(init/2,index);
                if(tmp>init){
                    nums[index]=tmp;
                    return tmp;
                }else{ 
                    nums[index]=init;
                    return init;
                    }
            }else{
                int tmp =HSN(init*3 +1,index);
                nums[index]=tmp;
                return tmp;
            }
        }
        
    }
    public static boolean canDP(int init){
        boolean dp =false;
        if(init<=100001) dp =true;
        return dp;
    }
}

