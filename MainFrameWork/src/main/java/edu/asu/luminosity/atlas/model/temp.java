package edu.asu.luminosity.atlas.model;

import java.lang.reflect.Array;
import java.util.Arrays;

public class temp {



	int index=-1;
	int flag=1;
	public int[] twoSum(int[] nums, int target) 
	{

		int[] tempy = Arrays.copyOf(nums, nums.length);
		Arrays.sort(tempy);
		int value2=-1;
		int value=-1;
		for(int i=0 ; i < nums.length;i++)
		{
			value=target-tempy[i];
			System.out.println(value);
			int[] newArray = Arrays.copyOfRange(tempy, 0, i);
			int[] newArray1 = Arrays.copyOfRange(tempy, i+1, nums.length);
			int[] both = concat(newArray,newArray1);
			value2=BinarySearchinArray(both, value, 0, both.length);
			if(value2 != -101)
			{

				value=i;
				for(int j=0;j<nums.length;j++)
				{
					if(flag==1)
					{
						
						if(nums[j]==value)
						{	
							flag=0;
							value2=j;
							break;
						}
					}
				}
				//break;
			}

		}
		if(flag==0)
		{
			int[] temp= {value,value2};
		
			return temp;
		}
		else
		{	int [] a= {-1,-1};
			return a;
		}

	}


	public int[] concat(int[] a, int[] b) 
	{
		int aLen = a.length;
		int bLen = b.length;
		int[] c= new int[aLen+bLen];
		System.arraycopy(a, 0, c, 0, aLen);
		System.arraycopy(b, 0, c, aLen, bLen);
		return c;
	}


	public int BinarySearchinArray(int [] arr, int value, int min, int max)
	{

		int mid =(int)((min+max)/2);
		System.out.println(arr[mid]);
		System.out.println("S-------------");
		for(int i=0 ; i < arr.length;i++)
		{
			
			System.out.println(arr[i]);
		}
		System.out.println("E-------------");
		if(arr[mid]==value)
		{
			return value;
		}


		if (min==max)
		{
			return -101;
		}

		if(arr[mid]>value)
		{
			return BinarySearchinArray(arr,value,min,mid);
		}
		else
		{
			return BinarySearchinArray(arr,value,mid,max);
		}

	}
	public static void main(String args[])
	{
		temp t= new temp();
		int [] a= {3,2,4};
		int[] tempy= t.twoSum(a,6);
		System.out.println(""+tempy[0]+","+tempy[1]);

	}
}
