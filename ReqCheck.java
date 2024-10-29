
/**
 * Write a description of class ReqCheck here.
 *
 * @author (your name)
 * @version 1.0
 */
public class ReqCheck
{
    private String req;
    private String isComplete;
    public ReqCheck(String req, String isComplete) {
        this.req=req;
        this.isComplete=isComplete;
    }
    public String getReqR() {
        return this.req;
    }
    public String getStatusR() {
        return this.isComplete;
    }
    public void setReq(String req) {
        this.req = req;
    }
    public void setStatus(String status) {
        this.isComplete=status;
    }
}
