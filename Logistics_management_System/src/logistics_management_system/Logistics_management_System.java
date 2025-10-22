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
     
        
        
        
        
    }
    
    
    
    public static void distanceManagement(){
        
        
        
       
        
        
}
 public static void displayVehicleInfo(){
     
     
 }
 
 
 public static void handleDeliveryRequest(){
     
     
     
 }
 
 
 public static void findLeastCostRoute(){
     
     
     
     
     
 }
 
 
 public static void showPerformanceReports(){
     
     
     
 }
 
 public static void saveDataToFiles(){
     
     
     
     
 }
 }
