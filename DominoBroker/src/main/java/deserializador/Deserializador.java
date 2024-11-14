/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 *
 * @author olive
 */
public class Deserializador {
    
    private static Gson gson = new Gson() ;
    
    public static <T> boolean esJsonInstanciaDe(String json, Class<T> clase) {
        try {
            gson.fromJson(json, clase);
            return true;
        } catch (JsonSyntaxException | NullPointerException e) {
            return false;
        }
    }
    
}
