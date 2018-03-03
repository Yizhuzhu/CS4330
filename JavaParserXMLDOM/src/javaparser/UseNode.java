/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparser;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author apple
 */
public class UseNode {
    String name;
    String content;
    HashMap<String, ArrayList<UseNode>> properties;
    HashMap<String, String> attributes;
    
    UseNode()
    {
        name=new String();
        content=new String();
        properties=new HashMap();
        attributes=new HashMap();
    }
    
}
