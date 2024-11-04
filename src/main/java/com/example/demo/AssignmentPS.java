package com.example.demo;
import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssignmentPS{


	public static void main(String[] args) {


		SpringApplication.run(AssignmentPS.class, args);
	}

//	nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
//	public  int findMaxSum(int[] arr){
//
//		Stack s= new Stack;
//		int maxSum= 0;
//		int currentSum= 0;
//		for(int i=0; i< arr.length; i++){
//			s.push(arr[i])
//		}
//
//		for(int i=0; i<arr.length;i++){
//			currentSum+ = s.pop();
//			if(currentSum >maxSum ){
//				maxSum= currentSum;
//			}
//		}
//		return  maxSum;
//	}


}
