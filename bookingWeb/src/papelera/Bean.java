package papelera;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name="Bean")
@ViewScoped
public class Bean implements java.io.Serializable {
private ArrayList inputList;
/** Creates a new instance of Bean */
public Bean() {
   inputList=new ArrayList();
    inputList.add("asdas");
}

public void addInput(){
     inputList.add("asdas");
}

public ArrayList getInputList() {
    return inputList;
}

public void setInputList(ArrayList inputList) {
    this.inputList = inputList;
}
}