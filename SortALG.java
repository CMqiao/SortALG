package sort;

import java.util.Arrays;

public class SortALG{
	public static void main(String[] args) {
		int[] a = new int[]{8,6,9,5,3,4,5,6,2,7,1};

		int[] b = new int[]{5,3,1};
		
//		insertSort(a);
//		selectSort(a);
//		bubbleSort(a);
//		quickSort(a,0,a.length-1);
//		mergeSort(a,b,a.length-1);		
//		shellSort(a, b);
		heapSort(a);
		System.out.println(Arrays.toString(a));
		
	}
	/**
	 * 基数排序，算法来源于网络，未进行检验
	 * @param number
	 * @param d
	 */
	static void sort(int[] number, int d) {
		int k = 0;
		int n = 1;
		int m = 1;
		int[][] temp = new int[10][number.length];
		int[] order = new int[10];
		while (m <= d) {
			for (int i = 0; i < number.length; i++) {
				int lsd = ((number[i] / n) % 10);
				temp[lsd][order[lsd]] = number[i];
				order[lsd]++;
			}
			for (int i = 0; i < 10; i++) {
				if (order[i] != 0)
					for (int j = 0; j < order[i]; j++) {
						number[k] = temp[i][j];
						k++;
					}
				order[i] = 0;
			}
			n *= 10;
			k = 0;
			m++;
		}
	}
	
	/**
	 * 堆排序
	 * @param a
	 */
	static void heapSort(int a[]){
		createHeap(a);
		a[0]=a[a.length-1]-a[0];
		a[a.length-1]=a[a.length-1]-a[0];
		a[0]=a[0]+a[a.length-1];
		for(int i=a.length-2; i>0; i--){
			heapAdjust(a, 0, i);
			a[0]=a[i]-a[0];
			a[i]=a[i]-a[0];
			a[0]=a[0]+a[i];
		}
		
	}
	/**
	 * 创建初始堆
	 * @param a
	 */
	static void createHeap(int[] a){
		int i;
		for(i=(a.length-1)/2;i>=0; i--){
			heapAdjust(a, i, a.length-1);
		}
	}
	/**
	 * 对堆进行调整
	 * @param a
	 * @param s
	 * @param m
	 */
	static void heapAdjust(int[] a, int s, int m){
		int root = a[s];
		for(int j=2*s+1;j<=m; j=2*j+1){
			if(j < m && a[j] <a[j+1])
				j++;
			if(root>a[j]) break;
			a[s]=a[j];
			s=j;
		}
		a[s]=root;
	}
	
	/**
	 * 希尔排序
	 * @param a
	 * @param d
	 */
	static void shellSort(int a[],int[] d){
		for(int i=0; i<d.length;i++){
			//对每组轮流进行插入排序
			for(int j=d[i]; j<a.length; j++){
				int x = a[j];
				int k ;
				for(k=j-d[i]; k>=0 && a[k]>x; k-=d[i])
					a[k+d[i]] = a[k];
				a[k+d[i]] = x;
			}
			
		}
	}
	
	/**
	 * 归并排序（迭代）
	 * @param a
	 * @param b
	 * @param n
	 */
	static void mergeSort(int[] a, int[] b, int n){
		int step = 1;
		boolean flag = true;
		while(step < n){
			int i =0;
			if(flag == true)
			{
				while(i+2*step<n)
				{
					merge(a, b, i, i+step-1, i+2*step-1);
					i=i+2*step;
				}
				if(i+step<=n+1)
					merge(a, b, i, i+step-1, n);
				step*=2;
				flag = false;
			}else
			{
				while(i+2*step<n)
				{
					merge(b, a, i, i+step-1, i+2*step-1);
					i=i+2*step;
				}
				if(i+step<=n+1)
					merge(b, a, i, i+step-1, n);
				step*=2;
				flag = true;
			}
		}
	}
	
	/**
	 * 归并排序（递归）
	 * @param a
	 * @param b
	 * @param l
	 * @param r
	 */
	static void mergeSort(int[] a,int[] b, int l, int r){
		int[] t=new int[b.length];
		if(l==r) {
			b[l]=a[l];
		}
		else{
			int m = (l+r)/2;
			mergeSort(a, t, l, m);
			mergeSort(a, t, m+1, r);
			merge(t, b, l, m, r);
			
		}
	}
	
	/**
	 * 归并
	 * @param a
	 * @param b
	 * @param l
	 * @param m
	 * @param r
	 */
	static void merge(int a[], int b[], int l, int m, int r){
		int k=l;
		int j = m+1;
		while(l<=m && j<=r){
			if(a[l]>a[j]){
				b[k++]=a[j++];
			}else{
				b[k++]=a[l++];
			}
		}
		while(l<=m) b[k++]=a[l++];
		while(j<=r) b[k++]=a[j++];
	}
	
	
	/**
	 * 快速排序
	 * @param a
	 */
	static void quickSort(int[] a,int l, int r){
		if(l<r){
			//划分左右区间
			int k = divide(a,l,r);
			//递归
			quickSort(a, l, k-1);
			quickSort(a, k+1, r);
		}
	}
	
	static int divide(int[] a, int l, int r){
		int pivotkey = a[l];
		while(l<r){
			while(l<r && a[r]>=pivotkey)
				r--;			
			a[l]=a[r];					
			while(l<r&&a[l]<=pivotkey)
				l++;			
			a[r]=a[l];						
		}
		a[r]=pivotkey;
		return l;	
	}
	
	/**
	 * 冒泡排序
	 * @param a
	 */
	static void bubbleSort(int a[]){
		for(int i=0; i<a.length; i++){
			//两两比较，把最小值冒到无序部分最前端
			for(int j=i+1; j<a.length; j++){
				if(a[j]<a[i]){
					int temp = a[i];
					a[i] = a[j];
					a[j] =temp;
				}
			}
		}
	}
	
	/**
	 * 选择排序
	 * @param a
	 */
	static void selectSort(int[] a){
		int min;
		for(int i=1;i<a.length;i++){
			min=a[i];
			int k = i;
			//选择最小值
			for(int j=i+1; j<a.length;j++){
				if(min>a[j]){
					min=a[j];
					k = j;
				}
			}
			//加入前面有序表的末端
			if(a[i-1]>a[k]){
				int temp=a[i-1];
				a[i-1] = a[k];
				a[k] = temp;                                
			}
		}
	}
	
	/**
	 * 插入排序
	 * @param a
	 */                                              
	static void insertSort(int[] a){
		int j;
		int x;
		for(int i =1; i<a.length; i++){
			x = a[i];
			//把大于当前数的所有值都后移
			for(j=i-1; j>=0; j--){
				if(x < a[j]){
					a[j+1] = a[j];                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
				}
				else break;
			}
			//插入当前值
			a[j+1] = x;
		}
	}
}
