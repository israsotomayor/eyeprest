package dominio;

public class Guarantor extends Person {   
 
 @Override
 public String toString(){
     return getPn_identification()+"   -   "+ getPn_first_name() +" "+getPn_last_name();
 }   
}
