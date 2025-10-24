/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package logistics_management_system;

import java.util.Scanner;

/**
 *
 * @author acer
 */
public class Logistics_management_System {

    /**
     * @param args the command line arguments
     */
    public static final int MAX_CITIES = 30;
    public static final int MAX_DELIVERIES = 50;
    public static final double FUEL_PRICE = 310.0;
    
    public static String[] cities = new String[MAX_CITIES];
    public static int[][] distanceMatrix = new int[MAX_CITIES][MAX_CITIES];
    public static Delivery[] deliveries = new Delivery[MAX_DELIVERIES];
    
    public static final String[] VEHICLE_TYPES = {"Van", "Truck", "Lorry"};
    public static final int[] CAPACITIES = {1000, 5000, 10000};
    public static final int[] RATES_PER_KM = {30, 40, 80};
    public static final int[] AVG_SPEEDS = {60, 50, 45};
    public static final int[] FUEL_EFFICIENCIES = {12, 6, 4};
    
    public  static int cityCount = 0;
    public static int deliveryCount = 0;
    
    public static DecimalFormat df = new DecimalFormat("#,##0.00");
    
    
    
    public static void main(String[] args) {
        
        displayMainMenu();
        
    }
    
    
     public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== LOGISTICS MANAGEMENT SYSTEM ===");
            System.out.println("1. City Management");
            System.out.println("2. Distance Management");
            System.out.println("3. Vehicle Information");
            System.out.println("4. Delivery Request");
            System.out.println("5. Find Least Cost Route");
            System.out.println("6. Performance Reports");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    cityManagement();
                    break;
                case 2:
                    distanceManagement();
                    break;
                case 3:
                    displayVehicleInfo();
                    break;
                case 4:
                    handleDeliveryRequest();
                    break;
                case 5:
                    findLeastCostRoute();
                    break;
                case 6:
                    showPerformanceReports();
                    break;
                case 7:
                    saveDataToFiles();
                    System.out.println("Thank you for using Logistics Management System!");
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
     
    public static void cityManagement(){
      Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== CITY MANAGEMENT ===");
            System.out.println("1. Add New City");
            System.out.println("2. Rename City");
            System.out.println("3. Remove City");
            System.out.println("4. View All Cities");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewCity(scanner);
                    break;
                case 2:
                    renameCity(scanner);
                    break;
                case 3:
                    removeCity(scanner);
                    break;
                case 4:
                    displayAllCities();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
        
        
        
    }
    public static void addNewCity(Scanner sc){
        
         if (cityCount >= MAX_CITIES) {
            System.out.println("Maximum number of cities reached!");
            return;
        }
         
         
         System.out.print("Enter city name: ");
        String cityName = sc.nextLine();
        
        if (cityName.isEmpty()) {
            System.out.println("City name cannot be empty!");
            return;
        }
        
         for (int i = 0; i < cityCount; i++) {
            if (cities[i].equalsIgnoreCase(cityName)) {
                System.out.println("City already exists!");
                return;
            }
        }
        
        cities[cityCount] = cityName;
        cityCount++;
        System.out.println("City '" + cityName + "' added successfully!");
    }
    
    public static void renameCity(Scanner sc){
        
        if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
        
        displayAllCities();
        System.out.print("Enter city number to rename: ");
        int cityIndex = sc.nextInt() - 1;
        sc.nextLine();
        
        if (cityIndex < 0 || cityIndex >= cityCount) {
            System.out.println("Invalid city number!");
            return;
        }
        
        System.out.print("Enter new city name: ");
        String newName = sc.nextLine().trim();
        
        if (newName.isEmpty()) {
            System.out.println("City name cannot be empty!");
            return;
        }
        
         for (int i = 0; i < cityCount; i++) {
            if (i != cityIndex && cities[i].equalsIgnoreCase(newName)) {
                System.out.println("City name already exists!");
                return;
            }
        }
        
         String oldName = cities[cityIndex];
        cities[cityIndex] = newName;
        System.out.println("City '" + oldName + "' renamed to '" + newName + "' successfully!");
              
        
    }
      public static void removeCity(Scanner sc){
          
       if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
       
       displayAllCities();
        System.out.print("Enter city number to remove: ");
        int cityIndex = sc.nextInt() - 1;
        
        if (cityIndex < 0 || cityIndex >= cityCount) {
            System.out.println("Invalid city number!");
            return;
        }
        
        String removedCity = cities[cityIndex];
        
        for (int i = cityIndex; i < cityCount - 1; i++) {
            cities[i] = cities[i + 1];
        }
        cities[cityCount - 1] = null;
        cityCount--;
        
        
        updateDistanceMatrixAfterRemoval(cityIndex);
        
        System.out.println("City '" + removedCity + "' removed successfully!");
          
        }
      
       public static void updateDistanceMatrixAfterRemoval(int removedIndex) {
        
        for (int i = removedIndex; i < cityCount; i++) {
            for (int j = 0; j < MAX_CITIES; j++) {
                distanceMatrix[i][j] = distanceMatrix[i + 1][j];
            }
        }
        
        
        for (int i = 0; i < cityCount; i++) {
            for (int j = removedIndex; j < cityCount; j++) {
                distanceMatrix[i][j] = distanceMatrix[i][j + 1];
            }
        }
    }
       
        public static void displayAllCities() {
        if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
        
        System.out.println("\n=== ALL CITIES ===");
        for (int i = 0; i < cityCount; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
    }
        
        
   
    public static void distanceManagement(){
        
    Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== DISTANCE MANAGEMENT ===");
            System.out.println("1. Add/Edit Distance");
            System.out.println("2. View Distance Table");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");
                
         int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    addEditDistance(sc);
                    break;
                case 2:
                    displayDistanceTable();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }       
}
    public static void addEditDistance(Scanner sc){
        if (cityCount < 2) {
            System.out.println("Need at least 2 cities to manage distances!");
            return;
        }
        displayAllCities();
        System.out.print("Enter source city number: ");
        int source = sc.nextInt() - 1;
        System.out.print("Enter destination city number: ");
        int dest = sc.nextInt() - 1;
        
        if (source < 0 || source >= cityCount || dest < 0 || dest >= cityCount) {
            System.out.println("Invalid city numbers!");
            return;
        }
        
        if (source == dest) {
            System.out.println("Distance from city to itself is always 0!");
            return;
        }
         System.out.print("Enter distance between " + cities[source] + " and " + cities[dest] + " (km): ");
        int dist = sc.nextInt();
        
        if (dist < 0) {
            System.out.println("Distance cannot be negative!");
            return;
        }
        
        distanceMatrix[source][dest] = dist;
        distanceMatrix[dest][source] = dist; // Make symmetrical
        System.out.println("Distance updated successfully!");
    }
        
    public static void displayDistanceTable(){
        if (cityCount == 0) {
            System.out.println("No cities available!");
            return;
        }
        System.out.println("\n=== DISTANCE TABLE (km) ===");
        System.out.print("City\t\t");
        for (int i = 0; i < cityCount; i++) {
            System.out.print(String.format("%-12s", cities[i].substring(0, Math.min(10, cities[i].length()))));
        }
        System.out.println();
        
         for (int i = 0; i < cityCount; i++) {
            System.out.print(String.format("%-12s", cities[i].substring(0, Math.min(10, cities[i].length()))));
            for (int j = 0; j < cityCount; j++) {
                System.out.print(String.format("%-12d", distanceMatrix[i][j]));
            }
            System.out.println();
        }
            
    }    
        
        
    
 public static void displayVehicleInfo(){
    
      System.out.println("\n=== VEHICLE INFORMATION ===");
      System.out.println("Type\tCapacity(kg)\tRate/km(LKR)\tSpeed(km/h)\tFuel Eff(km/l)");
      System.out.println("----------------------------------------------------------------");
      
      for (int i = 0; i < VEHICLE_TYPES.length; i++) {
            System.out.printf("%-6s\t%-12d\t%-13d\t%-11d\t%-13d\n",
                    VEHICLE_TYPES[i], CAPACITIES[i], RATES_PER_KM[i],
                    AVG_SPEEDS[i], FUEL_EFFICIENCIES[i]);
        }
     
 }
 
 
 public static void handleDeliveryRequest(){
      
        if (cityCount < 2) {
            System.out.println("Need at least 2 cities to process deliveries!");
            return;
        }
        if (deliveryCount >= MAX_DELIVERIES) {
            System.out.println("Maximum delivery limit reached!");
            return;
        }
     Scanner sc = new Scanner(System.in);
        
        displayAllCities();
        System.out.print("Enter source city number: ");
        int source = sc.nextInt() - 1;
        System.out.print("Enter destination city number: ");
        int dest = sc.nextInt() - 1;
     
         if (source < 0 || source >= cityCount || dest < 0 || dest >= cityCount) {
            System.out.println("Invalid city numbers!");
            return;
        }
          if (source == dest) {
            System.out.println("Source and destination cannot be the same!");
            return;
        }
          displayVehicleInfo();
        System.out.print("Select vehicle type (1=Van, 2=Truck, 3=Lorry): ");
        int vehicleType = sc.nextInt() - 1;
        
        if (vehicleType < 0 || vehicleType >= VEHICLE_TYPES.length) {
            System.out.println("Invalid vehicle type!");
            return;
        } 
        System.out.print("Enter package weight (kg): ");
        double weight = sc.nextDouble();
        
        if (weight <= 0) {
            System.out.println("Weight must be positive!");
            return;
        }
         if (weight > CAPACITIES[vehicleType]) {
            System.out.println("Weight exceeds vehicle capacity! Maximum: " + CAPACITIES[vehicleType] + " kg");
            return;
        } 
    
              
 }
 
 
 public static void findLeastCostRoute(){
     
     
     
     
     
 }
 
 
 public static void showPerformanceReports(){
     
     
     
 }
 
 public static void saveDataToFiles(){
     
     
     
     
 }
 }


