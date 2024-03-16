package com.crio.xcompany.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company{
    private String companyName;
    private Employee founder;
    private static Map<String,Employee> employeeBook;
    

    private Company(String companyName, Employee founder) {
        this.companyName = companyName;
        this.founder = founder;
        employeeBook = new HashMap<String,Employee>();
        employeeBook.put(founder.getName(), founder);
    }
    

    public static Company create(String companyName, Employee founder){
        return new Company(companyName,founder);
    } 


    public String getCompanyName() {
        return companyName;
    }

    // TODO: CRIO_TASK_MODULE_XCOMPANY
    // Please define all the methods required here as mentioned in the XCompany BuildOut Milestone for each functionality before implementing the logic.
    // This will ensure that the project can be compiled successfully.

    public void registerEmployee(String employeeName, Gender gender) {
        Employee e = new Employee(employeeName, gender);
        employeeBook.put(employeeName, e);
    }

    public Employee getEmployee(String employeeName) {
        return employeeBook.get(employeeName);
    }

    public void deleteEmployee(String employeeName) {
        employeeBook.remove(employeeName);
    }

    public void assignManager(String employeeName, String managerName) {
        Employee e = employeeBook.get(employeeName);
        Employee manager = employeeBook.get(managerName);
        e.assignManager(manager);
        employeeBook.put(employeeName, e);
    }

    public static List<Employee> getDirectReports(String managerName) {
        List<Employee> res = new ArrayList<>();
        
        for (Map.Entry<String,Employee> entry: employeeBook.entrySet()) {
            if (entry.getValue().getManagerName() != null && entry.getValue().getManagerName().equals(managerName)) {
                res.add(entry.getValue());
            }
        }
        Collections.sort(res, new Comparator<Employee>() {
            public int compare(Employee e1, Employee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        });
        return res;
    }

    public static List<Employee> getTeamMates(String employeeName) {
        Employee e = employeeBook.get(employeeName);
        List<Employee> res = new ArrayList<>();
        
        if(e.getManagerName() != null) {
            Employee mngr = employeeBook.get(e.getManagerName());
            res.add(mngr);
        }
        
        
        for (Map.Entry<String,Employee> entry: employeeBook.entrySet()) {
            if (entry.getValue().getManagerName() != null && entry.getValue().getManagerName().equals(e.getManagerName())) {
                res.add(entry.getValue());
            }
        }
        Collections.sort(res, new Comparator<Employee>() {
            public int compare(Employee e1, Employee e2) {
                return e1.getName().compareTo(e2.getName());
            }
        });
        return res;
    }

    public List<List<Employee>> getEmployeeHierarchy(String managerName) {
        Employee e = employeeBook.get(managerName);
        List<List<Employee>> res = new ArrayList<>();
        List<Employee> currMgrs = new ArrayList<>();
        
        currMgrs.add(e);
        res.add(currMgrs);
        List<Employee> nextMgrs = new ArrayList<>();
        
        do {
            nextMgrs = new ArrayList<>();
            // System.out.println("=======PRINTING curr mgrs========" + currMgrs.toString());
            for(int i = 0; i < currMgrs.size(); i++) {
                for (Map.Entry<String,Employee> entry: employeeBook.entrySet()) {
                    if (entry.getValue().getManagerName() != null && entry.getValue().getManagerName().equals(currMgrs.get(i).getName())) {
                        nextMgrs.add(entry.getValue());
                    }
                }
            }
            if (nextMgrs.size() > 0) {
                Collections.sort(nextMgrs, new Comparator<Employee>() {
                    public int compare(Employee e1, Employee e2) {
                        return e1.getName().compareTo(e2.getName());
                    }
                });

                res.add(nextMgrs);
                currMgrs = nextMgrs;
                // System.out.println("=======PRINTING upcoming curr mgrs========" + currMgrs.toString());
            } else {
                break;
            }
        } while(true);
        
        return res;
    }

}
