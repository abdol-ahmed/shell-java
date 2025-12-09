public class Echo implements Command {
    @Override
    public boolean execute(String[] args) {
        String arg = args.length > 1 ? args[1] : "";
        System.out.println(arg);
        return true;
    }
}
