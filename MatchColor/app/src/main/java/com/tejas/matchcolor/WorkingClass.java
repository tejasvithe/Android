package com.tejas.matchcolor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Tejasvi Kumar on 08-07-2017.
 */
public class WorkingClass {

    public  int[] generate_Random_No()
    {
        Random random = new Random();
        ArrayList<Integer> al = new ArrayList<Integer>();



        int n = random.nextInt(1000-100)+100;
        System.out.println(n);
        int i=0;
        while(n!=0)
        {
            int res = n%2;
            al.add(res);
            n=n/2;
            i++;
        }
        Collections.reverse(al);
        int arr[] = new int[al.size()];
        for (int j=0; j < arr.length; j++)
        {
            arr[j] = al.get(j).intValue();
        }
        return arr;
    }
}
