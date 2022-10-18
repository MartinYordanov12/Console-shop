package com.coherentsolutions.store.domain;


public record Product(String name, int price, int rating) {

@Override
public String toString() {
        return String.format( "%s %s %s",name,price,rating);
        }
        }

