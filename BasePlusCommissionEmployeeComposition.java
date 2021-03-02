/*
 * BasePlusCommissionEmployeeComposition.java
 *
 * @description: Exercises 9.3
 *               Refactor class BasePlus–CommissionEmployee (Fig.9.11 ) using composition, rather  
 *               than inheriting from class CommissionEmployee. Retest to demonstrate that the class 
 *               still provides the same functionality.
 *
 *               --Constructor--
 *               1. six-argument class constructor that receives five arguments required by class 
 *                  'CommissionEmployee' and one argument for local class field 'baseSalary'.
 *               2. instantiate and initialize local field 'employee' of type 'CommissionEmployee'
 *               3. initialize local field 'baseSalary' using the <setBaseSalary> method
 *               --Composition Methods--
 *               3. <getFirstName> 
 *                  
 *               4. <getLastName>
 *                   
 *               5. <getSocialSecurityNumber>
 *                  
 *               6. <getGrossSales>
 *
 *               7. <getCommissionRate>
 *
 *               8. <setGrossSales>
 *
 *               9. <setCommissionRate>
 *               
 *               --Class Methods--
 *               10. <getBaseSalary>
 *               11. <setBaseSalary>
 *               12. <earnings> 
 *               
 * @experimenting: Using the command line to compile and run a java program linked to
 *                 an external jar file. 
 *                      COMPILE: javac -cp ".:/CommissionEmployee.jar" BasePlusCommissionEmployeeComposition.java
 *                      RUN:     java  -cp ".:/CommissionEmployee.jar" BasePlusCommissionEmployeeComposition
 *
 *                 Referenced source:  https://www.programcreek.com/2014/01/compile-and-run-java-in-command-line-with-external-jars/
 * 
 * @author Will Oprisko, CSE-40479 
 * 
 * @version 2021-02-28
 */

public class BasePlusCommissionEmployeeComposition 
{
    // Using composition to create a local instance of the class CommissionEmployee
    // and setting the instance access permissions to 'private' in order to encapsulate 
    // the variable within the 'BasePlusCommissionEmployeeComposition' class
    private CommissionEmployee employee;

    // Class field specific to the class 'BasePlusCommissionEmployeeComposition'
    private double baseSalary; // base salary per week
    

    public BasePlusCommissionEmployeeComposition( String first, String last, String ssn, 
                                                  double sales, double rate, double salary) 
    {
        this.employee   = new CommissionEmployee(first, last, ssn, sales, rate); 	
        this.setBaseSalary(salary);
    }

    public String getFirstName() { return employee.getFirstName(); }

    public String getLastName()  { return employee.getLastName(); }

    public String getSocialSecurityNumber() { return employee.getSocialSecurityNumber(); }

    public double getGrossSales() { return employee.getGrossSales();  }

    public double getCommissionRate() { return employee.getCommissionRate(); }

    public double getBaseSalary() { return this.baseSalary; }

    public double earnings() { return this.getBaseSalary() + (this.getGrossSales() * this.getCommissionRate()); }

    public void setGrossSales(double sales) { employee.setGrossSales(sales); }

    public void setCommissionRate(double rate) { employee.setCommissionRate(rate); } 

    public void setBaseSalary(double salary) 
    {
        if (salary < 0)
            throw new IllegalArgumentException("Base salary must be >= 0.0");

        this.baseSalary = salary;    
    } 
    

    @Override
    public String toString() 
    {   
        String string = new String("");     
        string += "base-salaried commission employee: " + this.getFirstName() + " " + this.getLastName() + "\n";
        string += "social security number: " + this.getSocialSecurityNumber() + "\n";
        string += "gross sales: " + String.format("%.2f", employee.getGrossSales()) + "\n";
        string += "commission rate: " + String.format("%.2f", employee.getCommissionRate()) + "\n";
        string += "base salary: " + String.format("%.2f", this.baseSalary);
        return string;
    }
}
