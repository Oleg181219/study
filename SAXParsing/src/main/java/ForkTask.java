import java.util.concurrent.RecursiveAction;


public class ForkTask extends RecursiveAction {

    private String insert;

    public ForkTask(String insert) {
        this.insert = insert;
    }

    @Override
    protected void compute() {
        try {
            if (insert.length() != 0) {
                DBConnection.executeMultiInsert(insert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
