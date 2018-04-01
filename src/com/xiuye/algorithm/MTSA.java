package com.xiuye.algorithm;

/**
 *
 *
 * Median of Two Sorted Arrays
 *
 * @author Administrator
 *
 */
public class MTSA {

	public static<T> void log(T t){
		System.out.println(t);
	}
	public static double findMedianSortedArrays(int []A,int []B){
		int m = A.length;
		int n = B.length;
		//must n > m !
		if(m > n){
			int []tmp = A;
			A = B;
			B = tmp;
		}
		int iMin = 0,iMax = m,halfLen = (m+n+1)/2;
		while(iMin <= iMax){
			int i = (iMin + iMax)/2;
			int j = halfLen - i;
			if(i < iMax && B[j-1] > A[i]){
				iMin = iMin + 1;
			}
			else if(i>iMax && A[i-1] > B[j]){
				iMax = iMax - 1;
			}
			else{
				int maxLeft = 0;
				if(i == 0){
					maxLeft = B[j-1];
				}
				else if(j==0){
					maxLeft = A[i-1];
				}
				else{
					maxLeft = Math.max(A[i-1], B[j-1]);
				}
				if((m+n)%2 == 1){
					return maxLeft;
				}
				int minRight = 0;
				if(i == m){
					minRight = B[j];
				}
				else if(j==n){
					minRight = A[i];
				}
				else{
					minRight = Math.min(B[j],A[i]);
				}
				return (maxLeft+minRight)/2.0;
			}
		}
		return 0;
	}
	public static void main(String[] args) {

		int []A = {1,3,5,7,8,10};
		int []B = {2,8,9,11,12,17,32};
		log(findMedianSortedArrays(A, B));

	}

}
