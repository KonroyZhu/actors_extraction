package com.konroy.app.utils.getActor1.distance;

public class EditDistance {
    public static void main(String[] args){
        String str1="大幂幂";
        String str2="杨幂";
        System.out.println(new EditDistance().distance(str1,str2));
    }
    public static int distance(String str1,String str2){
        int len1=str1.length()+1;
        int len2=str2.length()+1;
        int[][] memo=new int[len1][len2];

        for(int i=0;i<len1;i++){
            memo[i][0]=i;
        }
        for(int i=0;i<len2;i++){
            memo[0][i]=i;
        }

        for(int i=1;i<len1;i++){
            for (int j=1;j<len2;j++){
                //if charts in str1 is the same as that of str2 c=0 otherwise c=1
                int c=str1.charAt(i-1)==str2.charAt(j-1)?0:1;
                //System.out.println(c);
                int min=min(memo[i-1][j-1]+c,memo[i-1][j]+1,memo[i][j-1]+1);
                memo[i][j]=min;
            }
        }
        print(memo);
        return memo[len1-1][len2-1];
    }

    /**
     * find the smallest one in a, b and c
     * @param a
     * @param b
     * @param c
     * @return
     */
    private static int min(int a,int b,int c){
        int min=Integer.MAX_VALUE;

        if(a<min)min=a;
        if(b<min)min=b;
        if(c<min)min=c;
        return min;
    }

    /**
     * function to print a square-shaped 2 dimension array
     * @param memo
     */
    private static  void print(int[][] memo){
        for(int i=0;i<memo.length;i++){
            for(int j=0;j<memo[0].length;j++){
                System.out.print(memo[i][j]+" ");
            }
            System.out.println();
        }
    }
}
