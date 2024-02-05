package com.example.Swiggato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class SwiggatoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwiggatoApplication.class, args);

//		List<Integer> l = List.of(1,2,3,4,5,6,7);

//		List<Integer> newList = l.stream().filter(x -> x%2 == 0).collect(Collectors.toList());
//		for(int i: newList)
//		System.out.println(i);
	}


}
