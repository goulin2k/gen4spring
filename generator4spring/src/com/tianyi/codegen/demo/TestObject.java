/**
 * 
 */
package com.tianyi.codegen.demo;

/**
 * @author apple
 *
 */
public class TestObject {
    private String name;
    private int price;

    public TestObject(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // JavaBean properties
    // Note that public fields are not visible directly;
    // you must write a getter method for them.
    public String getName() {return name;}
    public int getPrice() {return price;}
    
    // A method
    public double sin(double x) {
        return Math.sin(x);
    }
}  
