package com.tejas.matchcolor;

/**
 * Created by Tejasvi Kumar on 08-07-2017.
 */
public class Calculate_Percentage {
    public double check_Percantage(int arr[])
    {
        double result = 0.0;
        double one_counter=0,zero_counter=0;
        for(int i = 0 ;i<arr.length;i++)
        {
            if(arr[i] == 1)
            {
                one_counter++;
            }
            else
                zero_counter++;
        }
        result = one_counter*100/(one_counter+zero_counter);
        return result;
    }
}
