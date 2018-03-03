/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author apple
 */
public class Loader {
    static UseNode xmlroot;
    static Stack<UseNode> stack;
    static UseNode currentNode;
    public static UseNode load(File xmlCourseFile) throws Exception
    {
        stack=new Stack<>();
        try{
      
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

               
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    UseNode node=new UseNode();
                    node.name=qName;
                    int n=attributes.getLength();
                    for(int i=0;i<n;i++)
                    {
                        node.attributes.put(attributes.getQName(i), attributes.getValue(i));
                    }
                    
                    stack.push(node);
                    
                    if(currentNode!=null){
                        if(currentNode.properties.get(localName)!=null){
                                currentNode.properties.get(localName).add(node);
                            }
                            else{
                                ArrayList<UseNode> nl= new ArrayList<>();
                                nl.add(node);
                                currentNode.properties.put(localName, nl);
                            }
                        }
                        currentNode = node;
                    }
                
                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    UseNode ppNode=stack.pop();
                    ppNode.content=ppNode.content.trim();
                    if(stack.isEmpty())
                    {
                        xmlroot=ppNode;
                        currentNode=null;
                    }
                    else
                    {
                        currentNode=stack.lastElement();
                    }
                }
                
                @Override
                public void characters(char ch[], int start, int length) throws SAXException {
                    
                    if(currentNode!= null){
                        currentNode.content+=new String(ch,start,length);
                    }
                }
            };
            
            saxParser.parse(xmlCourseFile.getAbsoluteFile(), handler);
        
        } catch (Exception e) {
            throw e;
        }
        
        return xmlroot;
    }
    
}

