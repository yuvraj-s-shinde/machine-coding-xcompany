package com.crio.xcompany.company;

import java.util.List;

public class Employee {
    private String name;
    private Gender gender;
    private String managerName;

    public Employee(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public String getManagerName() {
        return managerName;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone before implementing the logic.
    // This will ensure that the project can be compiled successfully.


    
    public void assignManager(Employee employee) {
        this.managerName = employee.getName();
    }

    public List<Employee> getDirectReports() {
        return Company.getDirectReports(name);
    }

    public List<Employee> getTeamMates() {
        return Company.getTeamMates(name);
    }




    @Override
    public String toString() {
        return "Employee [name=" + name + ", gender=" + gender + "]";
    }   
}
