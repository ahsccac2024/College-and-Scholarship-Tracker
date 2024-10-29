
/**
 * Write a description of class ScholarshipInfo here.
 *
 * @author (your name)
 * @version 1.0
 */
public class ScholarshipInfo
{
    private String name;
    private String requirements;
    private String cost;
    public ScholarshipInfo(String name, String requirements, String cost) {
        this.name=name;
        this.requirements=requirements;
        this.cost=cost;
    }
    
    public String getNameS() {
        return this.name;
    }
    public void setName(String name) {
        this.name=name;
    }
    public String getReqS() {
        return this.requirements;
    }
    public void setReq(String req) {
        this.requirements=req;
    }
    public String getCostS() {
        return this.cost;
    }
    public void setCost(String cost) {
        this.cost=cost;
    }
}
