
import javafx.beans.property.SimpleStringProperty;

/**
 * Write a description of class CollegeInfo here.
 *
 * @author (your name)
 * @version 1.0
 */
public class CollegeInfo
{
    public SimpleStringProperty collegeName;
    public SimpleStringProperty AdmissionRequirements;
    public SimpleStringProperty ApproxCost;

    /**
     * Constructor for objects of class CollegeInfo
     */
    public CollegeInfo(String collegeName, String AdmissionRequirements, String ApproxCost)
    {
        this.collegeName=new SimpleStringProperty(collegeName);
        this.AdmissionRequirements=new SimpleStringProperty(AdmissionRequirements);
        this.ApproxCost=new SimpleStringProperty(ApproxCost);
    }
    public String getName() {
        return this.collegeName.get();
    }
    public void setName(String name) {
        this.collegeName.set(name);
    }
    public String getReq() {
        return this.AdmissionRequirements.get();
    }
    public void setReq(String newReq) {
        this.AdmissionRequirements.set(newReq);
    }
    public String getCost() {
        return this.ApproxCost.get();
    }
    public void setCost(String cost){
        this.ApproxCost.set(cost);
    }
}
