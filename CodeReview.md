Reviewer : John Garvey
GH Repo: ser316-spring2021-C-jsgarvey

| ID#  | Location  | Problem Description  | Category  | Severity  |
|---|---|---|---|--|
| 1  | BearWorkshop line 165  | Duplicate code – there seems to be two variables for and ways to get the rawcost of the total bears in cart. One creates a Double object and computes the rawcost but the variable is never again used.  | CS | LOW |
| 2  | Bear.java line 41 | Method Banner – in the bear class the method addNoise does not have a method banner  | CG | LOW |
| 3  | BearWorkshop.java line 158 | Functional defect -the function checkout check if the customer is less than or equal to 13 years old. The specification says that a child must be 13 years of age in order to buy a  bear. The way it is set now the customer must be 14 in order to buy a bear  | FS | BR |
| 4  | Embroidery.java line 2  | Class Banner Missing – the banner for the class Embroidery.java is missing  | CG | LOW |
| 5  | Customer.java line 9,12,13  | Non-private attributes – the Customer class uses public attributes instead of getters for age, parent, and customer_id  | CD  | MJ | MJ |
| 6  | Clothing.java line 5  | Naming Convention – the naming convention for variables is the first letter is lowercase the variable in Clothing called Description is not using this convention  | CG | LOW |
| 7  | Stuffing.java line 27  | Switch Statement- the switch statement in Stuffing.java under the constructor does not use a default case statement  | CS | MJ |
