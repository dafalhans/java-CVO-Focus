package com.cottagerentals;

public class Runner {

    public static void main(String[] args) {
        //Intermediair inter1 = new Intermediair("Bob Bobsen","België",30.44);
        //Intermediair inter2 = new Intermediair("Hilke Herregodts","Nederland");
        //System.out.println(inter1.getCommision());
        //System.out.println(inter2.getCommision());
        //inter2.setCommision(66.66);
        //System.out.println(inter1 + inter1.getCountry() + inter1.getName() + inter1.getClass().getSimpleName());
        //System.out.println(inter2.getCommision());

        Intermediair Renata = new Intermediair("Renata Petrevska","Macedonia");
        //Cottage cot1 = new Cottage("Randy Ranch","RounchyRoad 11, 1337 BigBoysTown",99);
        //Cottage cot2 = new Cottage("Oost West Thuis Best","Dijk 100 B-8370 Blankenberghe",199);
        //Cottage cot3 = new Cottage("Haus am See","Avenue Ataturk 69, 6000 Ohrid North Macedonia",65,Renata);
        //cot2.info();
        //cot3.info();
        //cot3.hansTest(360,5);
        Catalogue c1 = new Catalogue();
        c1.addCottage("Oost West Thuis Best","Dijk 100 B-8370 Blankenberghe",199);
        c1.addCottage("Haus am See","Avenue Ataturk 69, 6000 Ohrid North Macedonia",65,Renata);
        c1.reserveCottage("Oost West Thuis Best",1,10);
        System.out.println("\n\n\n");
        c1.reserveCottage("Oost West Thuis Best",11,10);
        System.out.println("\n\n\n");
        c1.reserveCottage("Oost West Thuis Best",5,10);
        System.out.println("\n\n\n");
        c1.reserveCottage("Oost West Thuis Best",360,7);
        System.out.println("\n\n\n");
        c1.reserveCottage("Oost West Thuis Best",360,5);
        System.out.println("\n\n\n");
        c1.addCottage("Haus am See 2","Avenue Ataturk 70, 6000 Ohrid North Macedonia",65,Renata);
        c1.addCottage("Haus am See 3","Avenue Ataturk 71, 6000 Ohrid North Macedonia",65,Renata);
        c1.addCottage("Haus am See 4","Avenue Ataturk 72, 6000 Ohrid North Macedonia",65,Renata);
        System.out.println("\n\n\n");
        System.out.println("=== BEFORE DELETING RENATA's cottages ====");
        System.out.println("\n\n\n");
        c1.getCottageDetails();
        System.out.println("\n\n\n");
        c1.removeCottages("Renata Petrevska");
        System.out.println("\n\n\n");
        System.out.println("==== AFTER DELETING RENATA's cottages ====");
        System.out.println("\n\n\n");
        c1.getCottageDetails();
    }
}
