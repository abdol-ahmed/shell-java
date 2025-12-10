public class PWD implements Command {
    @Override
    public boolean execute(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        return true;
    }
}
