package com.huishouwu.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="test")
public class Test {
	private int count;
	private String name;

}
