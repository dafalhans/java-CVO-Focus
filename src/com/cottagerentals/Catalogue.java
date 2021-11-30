package com.cottagerentals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Catalogue {

    List<Cottage> catalogue = new ArrayList<>();

    public void addCottage(String name, String adres, int dayPrice){
        if(!addressCheck(adres)){
            catalogue.add(new Cottage(name,adres,dayPrice));
        }

    }

    public void addCottage(String name, String adres, int dayPrice, Intermediair intermediair){
        if(!addressCheck(adres)) {
            catalogue.add(new Cottage(name, adres, dayPrice, intermediair));
        }
    }

    private boolean addressCheck(String adres){
        return catalogue.stream().map(Cottage::getAddress).anyMatch(adres::equals);
    }

    public void reserveCottage(String name, int arrivalday, int stay) {
        if (catalogue.stream().map(Cottage::getName).anyMatch(name::equals)) {
            for (Cottage cottage : catalogue) {
                if (cottage.getName() == name && cottage.periodFree(arrivalday, stay)) {
                    cottage.reserveCottage(arrivalday, stay);
                }
            }
        }else{
            System.out.println("Het huisje op " + name + " waarvoor je wilt reserveren bestaat niet.");
        }
    }


    public void removeCottages(String intermediair_name){

        List<Cottage> toRemove = new ArrayList<>();
        for(Cottage cottage : catalogue){
            if(cottage.getIntermediair() != null){
                if(cottage.getIntermediair().getName() == intermediair_name){
                    toRemove.add(cottage);
                    //System.out.println(cottage);
                }
            }
        }
        catalogue.removeAll(toRemove);
    }

    public void getCottageDetails(){
        for(Cottage cottage : catalogue){
            cottage.toString();
        }
    }



}
