
package gstioncomercialenadjem;

import java.util.ArrayList;

public abstract class DialogControllerInterface {
    int id;
    ArrayList<DialogControllerInterface> list;
    public DialogControllerInterface() {
    }

    public DialogControllerInterface(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<DialogControllerInterface> getList() {
        return list;
    }

    public void setList(ArrayList<DialogControllerInterface> list) {
        this.list = list;
    }
    
   abstract boolean closeWidow(); 
   void clean(){
        for(int i=0;i<list.size();i++){
        if(id==list.get(i).getId()) {   
        list.remove(i);
        break;
        }
   }
   }
}
