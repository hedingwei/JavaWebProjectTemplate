package com.ambimmort.app.framework.uitls;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by hedingwei on 6/1/15.
 */
public class ObjectUtils {

    public static Map fromJSONObject(JSONObject obj){
        Map map = new HashMap();
        String key = null;
        Object ptr = null;
        JSONArray arrayPtr = null;
        JSONObject objectPtr = null;
        for(Object o: obj.keySet()){
            if(o instanceof  String){
                key = (String)o;
                ptr = obj.get(key);
                if(ptr instanceof JSONObject){
                    objectPtr = (JSONObject)ptr;
                    map.put(key,fromJSONObject(objectPtr));
                }else if(ptr instanceof  JSONArray){
                    arrayPtr = (JSONArray)ptr;
                    map.put(key,fromJSONArray(arrayPtr));
                }else{
                    map.put(key,ptr);
                }
            }
        }

        return map;
    }

    public static List fromJSONArray(JSONArray array){
        List list = new ArrayList();
        Object ptr = null;
        JSONArray arrayPtr = null;
        JSONObject objectPtr = null;
        for(int i=0;i<array.size();i++){

            ptr = array.get(i);
            if(ptr instanceof JSONObject){
                objectPtr = (JSONObject)ptr;
                list.add(fromJSONObject(objectPtr));
            }else if(ptr instanceof  JSONArray){
                arrayPtr = (JSONArray)ptr;
                list.add(fromJSONArray(arrayPtr));
            }else{
                list.add(ptr);
            }
        }

        return list;
    }


    public static void main(String[] args){
        JSONObject a = new JSONObject();
        JSONObject role = new JSONObject();
        role.put("name","admin");
        a.put("username","hedingwei");
        a.put("role",role);
        a.put("time",new Date());

        System.out.println(a.toString(4));
        System.out.println(new ObjectUtils().fromJSONObject(a));
    }
}
